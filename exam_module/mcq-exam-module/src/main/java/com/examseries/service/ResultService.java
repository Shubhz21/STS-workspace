package com.examseries.service;

import com.examseries.entity.Result;
import com.examseries.entity.UserAnswer;
import java.util.List;

public interface ResultService {
    Result calculateAndSaveResult(Long userId, Long examId, List<UserAnswer> answers);
    List<Result> getResultsByUser(Long userId);
    Result getResultByUserAndExam(Long userId, Long examId);
}
