package Flashcard;

import java.util.Scanner;

public class Play implements App {

    Scanner scanner = new Scanner(System.in);

    DeckOrganizer deckOrganizer = new DeckOrganizer();
    String userChoice;

    private String caption = "Тоглох!";

    public String getCaption() {
        return caption;
    }

    public void printMenu() {
        if (deckOrganizer.getDecksSize() == 0) {
            System.out.println("Таньд одоогоор ширээ байхгүй байна. Та суралцах хэсэг руу орж ширээ үүсгэнэ үү.");
            return;
        } else {
            deckOrganizer.printAllDecks();
            System.out.println("\n0. Буцах");
        }
    }

    public void start() {

        System.out.print("\033[H\033[2J");
        for (;;) {

            System.out.println("\n-----Тоглох хэсэг-----\n");
            printMenu();

            System.out.print("\nТаны сонголт(Ширээний нэрийг оруулна уу!): ");
            userChoice = scanner.nextLine();

            // Ширээ сонгож орох
            if (deckOrganizer.findDeck(userChoice) != null) {
                System.out.print("\033[H\033[2J");

                System.out.println("\nАсуулт & Хариулт эхэллээ!");
            } else if (userChoice.equals("0")) {
                break;
            } else {
                System.out.print("\033[H\033[2J");
                System.out.println("Та ширээнээс сонголтоо хийнэ үү!");
            }
        }
    }
}
