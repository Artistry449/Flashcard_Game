package Flashcard;

public interface Card_inter {
    public String getQuestion();

    public String getAnswer();

    public boolean isEqualAnswer(String user_answer);

    public void editQuestion(String new_question);

    public void editAnswer(String new_answer);
}
