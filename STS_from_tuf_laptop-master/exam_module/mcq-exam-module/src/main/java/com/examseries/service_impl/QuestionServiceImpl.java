package com.examseries.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examseries.entity.Question;
import com.examseries.repository.QuestionRepository;
import com.examseries.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getQuestionsByExamId(Long examId) {
        return questionRepository.findByExam_Id(examId);
    }
}

