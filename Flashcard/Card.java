package Flashcard;

public class Card {
    private String question;
    private String answer;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isEqualAnswer(String user_answer) {
        if (user_answer == answer)
            return true;
        else
            return false;
    }

    public void editQuestion(String new_question) {
        this.question = new_question;
    }

    public void editAnswer(String new_answer) {
        this.answer = new_answer;
    }
}
