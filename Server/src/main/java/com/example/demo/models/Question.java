package com.example.demo.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 *  question
 *  модель для таблицы вопросов
 */
@Entity
@Table(name = "Question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "questions")
    private String questions;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_answer")
    private Answer answer;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }


    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questions='" + questions + '\'' +
                ", answer=" + answer +
                '}';
    }

    public Question() {
    }

    public Question(String questions, String type) {
        this.questions = questions;
    }


}
