package com.examseries.controller;

import com.examseries.entity.*;
import com.examseries.service.ExamService;
import com.examseries.service.ResultService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private ResultService resultService;
 
    // Show List of Available Exams
    @GetMapping("/list")
    public String listExams(Model model) {
        model.addAttribute("exams", examService.getAllExams());
        return "exam_list";
    }
    
    

    // Take Exam
    @GetMapping("/take/{id}")
    public String takeExam(@PathVariable Long id, Model model, HttpSession session) {
        Exam exam = examService.getExamById(id);
        if (exam == null) {
            model.addAttribute("error", "Exam not found!");
            return "exam_list";
        }

        model.addAttribute("exam", exam);
        model.addAttribute("questions", examService.getQuestionsByExamId(id));
        return "take_exam";
    }

    // Submit Exam
    @PostMapping("/submit")
    public String submitExam(@RequestParam Long examId,
                             @RequestParam List<Long> questionId,
                             @RequestParam List<String> selectedOption,
                             HttpSession session,
                             HttpServletRequest request,
                             HttpServletResponse response) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        // --- SAVE USER ANSWERS ---
        List<UserAnswer> answers = new ArrayList<>();

        for (int i = 0; i < questionId.size(); i++) {

            UserAnswer ans = new UserAnswer();
            ans.setUserId(user.getId());
            ans.setExamId(examId);
            ans.setQuestionId(questionId.get(i));

            // This will already contain "no answer" for unanswered questions
            ans.setSelectedOption(selectedOption.get(i));

            answers.add(ans);
        }

        // --- SAVE RESULT ---
        resultService.calculateAndSaveResult(user.getId(), examId, answers);

        // --- LOGOUT AFTER SAVE ---
        session.invalidate();   // Destroy session

        // Clear JSESSIONID cookie to ensure logout
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        // Redirect to login page after logout
        return "redirect:/login?submitted=true";
    }


    // View Previous Results
    @GetMapping("/results")
    public String viewResults(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        model.addAttribute("results", resultService.getResultsByUser(user.getId()));
        return "results"; // ✅ new template
    }

}
