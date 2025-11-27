package com.examseries.service;

import java.util.List;

import com.examseries.entity.Question;


public interface QuestionService {
    List<Question> getQuestionsByExamId(Long examId);
    
}

