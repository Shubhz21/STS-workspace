package com.examseries.controller;

import com.examseries.dto.ExamUploadDto;
import com.examseries.entity.Exam;
import com.examseries.entity.Option;
import com.examseries.entity.Question;
import com.examseries.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ExamService examService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("exams", examService.getAllExams());
        return "admin_dashboard";
    }

    @GetMapping("/upload-exam")
    public String uploadExamForm(Model model) {
        model.addAttribute("examUploadDto", new ExamUploadDto());
        return "upload_exam";
    }

    @PostMapping("/upload-exam")
    public String uploadExam(@ModelAttribute ExamUploadDto dto, Model model) {
        // ✅ create Exam using existing fields
        Exam exam = new Exam();
        exam.setTitle(dto.getTitle());
        exam.setSubject(dto.getSubject());     // description → subject
        exam.setDuration(dto.getDuration()); // duration
        exam.setTotalMarks(100); // set default marks or calculate later

        Exam savedExam = examService.createExam(exam);

        for (ExamUploadDto.QuestionDto qDto : dto.getQuestions()) {
            Question question = new Question();
            question.setExam(savedExam);
            question.setQuestionText(qDto.getQuestionText());
            question.setCorrectOption(qDto.getCorrectOption());

            List<Option> options = new ArrayList<>();
            for (String opt : qDto.getOptions()) {
                Option o = new Option();
                o.setOptionText(opt);
                o.setQuestion(question);
                options.add(o);
            }

            examService.addQuestionToExam(savedExam.getId(), question, options);
        }

        model.addAttribute("success", "Exam uploaded successfully!");
        model.addAttribute("exams", examService.getAllExams());
        return "admin_dashboard";
    }

    @GetMapping("/delete/{id}")
    public String deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return "redirect:/admin/dashboard";
    }
}
