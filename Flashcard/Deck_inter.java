package Flashcard;

import java.util.ArrayList;

public interface Deck_inter {
    public String getName();

    public void editName(String new_name);

    public int getDeckSize();

    public void addCard(Card card);

    public void deleteCard(String cardName);

    public void printAllCards();

    public ArrayList<Card> getCards();

    public Card findCard(String cardQuestion);
}