package com.examseries.service_impl;

import com.examseries.entity.*;
import com.examseries.repository.*;
import com.examseries.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ExamRepository examRepository;

    @Override
    public Result calculateAndSaveResult(Long userId, Long examId, List<UserAnswer> answers) {
        Exam exam = examRepository.findById(examId).orElse(null);
        if (exam == null) throw new RuntimeException("Exam not found!");

        int totalQuestions = 0;
        int correctAnswers = 0;

        for (UserAnswer ans : answers) {
            Question q = questionRepository.findById(ans.getQuestionId()).orElse(null);
            if (q != null) {
                ans.setCorrect(q.getCorrectOption().equalsIgnoreCase(ans.getSelectedOption()));
                userAnswerRepository.save(ans);
                totalQuestions++;
                if (ans.isCorrect()) correctAnswers++;
            }
        }

        int score = correctAnswers; // 1 mark per question (customize later)

        Result result = new Result(userId, examId, totalQuestions, correctAnswers, score);
        return resultRepository.save(result);
    }

    @Override
    public List<Result> getResultsByUser(Long userId) {
        return resultRepository.findByUserId(userId);
    }

    @Override
    public Result getResultByUserAndExam(Long userId, Long examId) {
        return resultRepository.findByUserIdAndExamId(userId, examId);
    }
}
