package com.examseries.dto;

import java.util.ArrayList;
import java.util.List;

public class ExamUploadDto {
    private String title;
    private String subject;
    private int duration;
    private List<QuestionDto> questions = new ArrayList<>();

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public List<QuestionDto> getQuestions() { return questions; }
    public void setQuestions(List<QuestionDto> questions) { this.questions = questions; }

    // Inner DTO Class
    public static class QuestionDto {
        private String questionText;
        private List<String> options = new ArrayList<>();
        private String correctOption;

        public String getQuestionText() { return questionText; }
        public void setQuestionText(String questionText) { this.questionText = questionText; }

        public List<String> getOptions() { return options; }
        public void setOptions(List<String> options) { this.options = options; }

        public String getCorrectOption() { return correctOption; }
        public void setCorrectOption(String correctOption) { this.correctOption = correctOption; }
    }
}
