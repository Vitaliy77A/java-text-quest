package com.javarush;

import com.javarush.model.Question;
import com.javarush.model.QuestionRepository;

public class TestMain {
    public static void main(String[] args) {
        System.out.println("--- Початок теста ---");

        try {
            QuestionRepository repo = new QuestionRepository();

            Question question = repo.getQuestionById(1);

            if (question != null) {
                System.out.println("Успіх! Питання завантажено:");
                System.out.println("Текст: " + question.getText());
                System.out.println("Кількість відповідей: " + (question.getAnswers() != null ? question.getAnswers().size() : 0));
            } else {
                System.out.println("Помилка: Питання з id=1 повернуло null. Перевір JSON.");
            }

        } catch (Exception e) {
            System.out.println("Все впало з помилкою:");
            e.printStackTrace();

        }

        System.out.println("--- Кінець теста ---");
    }
}
