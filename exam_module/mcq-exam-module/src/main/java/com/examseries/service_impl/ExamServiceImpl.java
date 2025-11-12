package com.examseries.service_impl;

import com.examseries.entity.Exam;
import com.examseries.entity.Question;
import com.examseries.entity.Option;
import com.examseries.repository.ExamRepository;
import com.examseries.repository.QuestionRepository;
import com.examseries.repository.OptionRepository;
import com.examseries.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Override
    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public Exam getExamById(Long id) {
        return examRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteExam(Long id) {
        examRepository.deleteById(id);
    }

    @Override
    public Question addQuestionToExam(Long examId, Question question, List<Option> options) {
        Exam exam = getExamById(examId);
        if (exam == null) throw new RuntimeException("Exam not found!");

        question.setExam(exam);
        Question savedQuestion = questionRepository.save(question);

        for (Option opt : options) {
            opt.setQuestion(savedQuestion);
            optionRepository.save(opt);
        }

        return savedQuestion;
    }

    @Override
    public List<Question> getQuestionsByExamId(Long examId) {
        Exam exam = getExamById(examId);
        if (exam == null) throw new RuntimeException("Exam not found!");
        return questionRepository.findByExam(exam);
    }
}
