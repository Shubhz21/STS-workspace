package com.examseries.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String optionLabel; // "A", "B", "C", "D"

    private String optionText;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public Option() {}

    public Option(String optionLabel, String optionText, Question question) {
        this.optionLabel = optionLabel;
        this.optionText = optionText;
        this.question = question;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOptionLabel() { return optionLabel; }
    public void setOptionLabel(String optionLabel) { this.optionLabel = optionLabel; }

    public String getOptionText() { return optionText; }
    public void setOptionText(String optionText) { this.optionText = optionText; }

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }
}
