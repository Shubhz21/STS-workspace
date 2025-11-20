package com.examseries.service;

import com.examseries.entity.Exam;
import com.examseries.entity.Question;
import com.examseries.entity.Option;
import java.util.List;

public interface ExamService {
    Exam createExam(Exam exam);
    List<Exam> getAllExams();
    Exam getExamById(Long id);
    void deleteExam(Long id);

    Question addQuestionToExam(Long examId, Question question, List<Option> options);
    List<Question> getQuestionsByExamId(Long examId);
}
