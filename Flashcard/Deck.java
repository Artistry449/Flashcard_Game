package Flashcard;

import java.util.ArrayList;

public class Deck implements Deck_inter {

    private String name;

    private ArrayList<Card> cards = new ArrayList<>();

    public Deck() {

    }

    public Deck(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void editName(String new_name) {
        this.name = new_name;
    }

    public int getDeckSize() {
        return cards.size();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void deleteCard(String cardName) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getQuestion().equals(cardName))
                cards.remove(i);
        }
    }

    public void printAllCards() {
        System.out.println("Энэ ширээний картнууд:");
        for (int i = 0; i < cards.size(); i++) {
            System.out.println("~ " + cards.get(i).getQuestion());
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card findCard(String cardQuestion) {
        for (Card card : cards) {
            if (card.getQuestion().equals(cardQuestion))
                return card;
        }
        return null;
    }
}
