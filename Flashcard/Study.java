package Flashcard;

import java.util.Scanner;

public class Study implements App {
    Scanner scanner = new Scanner(System.in);

    DeckOrganizer deckOrganizer = new DeckOrganizer();
    int userChoice;

    private String caption = "Суралцах!";

    public String getCaption() {
        return caption;
    }

    public void printMenu() {
        if (deckOrganizer.getDecksSize() == 0) {
            System.out.println("Таньд одоогоор ширээ байхгүй байна. Та суралцах хэсэг руу орж ширээ үүсгэнэ үү.");
            return;
        } else {
            deckOrganizer.printAllDecks();
            System.out.println("A: Буцах");
        }
    }

    public void start() {

    }
}
