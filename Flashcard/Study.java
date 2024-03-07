package Flashcard;

import java.util.ArrayList;
import java.util.Scanner;

public class Study implements App {
    Scanner scanner = new Scanner(System.in);
    FileManager fileManager = new FileManager("Scores.txt");

    DeckOrganizer deckOrganizer = new DeckOrganizer();
    ArrayList<String> scores;
    String userMenuChoice;
    String userDeckChoice;
    int highScore;
    int userPoint = 0;

    private String caption = "Суралцах!";

    public Study() {
    }

    public String getCaption() {
        return caption;
    }

    public void printMenu() {
        System.out.println("-----Суралцах хэсэг-----");
        if (deckOrganizer.getDecksSize() == 0) {
            System.out.println("Таньд одоогоор ширээ байхгүй байна. Та суралцах хэсэг руу орж ширээ үүсгэнэ үү.");
            return;
        } else {
            deckOrganizer.printAllDecks();
            System.out.println("\n0: Буцах");
        }
    }

    public String getHighscore() {
        scores = fileManager.readFile();

        for (int i = 0; i < scores.size(); i++) {
            String[] parts = scores.get(i).split("=");
            if (parts[0].equals(userDeckChoice)) {
                highScore = Integer.parseInt(parts[1]);
                return parts[1];
            }
        }
        return "";
    }

    public void updateHighScore() {
        boolean scoreUpdated = false;
        for (int i = 0; i < scores.size(); i++) {
            String[] parts = scores.get(i).split("=");
            if (parts[0].equals(userDeckChoice)) {

                scores.set(i, parts[0] + "=" + highScore);
                scoreUpdated = true;
                break;
            }
        }

        if (!scoreUpdated) {
            scores.add(userDeckChoice + "=" + highScore);
        }
        String data = String.join("\n", scores);

        fileManager.clearFileData();
        fileManager.writeFile(data);
    }

    public void QA() {
        Deck deck = deckOrganizer.findDeck(userDeckChoice);

        System.out.print("\033[H\033[2J");

        System.out.println("\nАсуулт & Хариулт эхэллээ!\n");

        getHighscore();

        System.out.println("Таны хамгийн өндөр оноо: " + highScore);

        ArrayList<Card> cards = deck.getCards();
        for (int i = 0; i < cards.size(); i++) {
            System.out.println("\nКарт " + (i + 1) + ": " + cards.get(i).getQuestion());
            System.out.print("Хариулт: ");

            String userAnswer = scanner.nextLine();

            if (userAnswer.equals(cards.get(i).getAnswer())) {
                System.out.println("\nЗөв байна!");
                userPoint++;
            } else {
                System.out.println("Буруу!");
            }
        }

        System.out.println("\n\nДууслаа. Таны оноо: " + userPoint + "\n");

        if (userPoint > highScore) {
            highScore = userPoint;

            updateHighScore();

            System.out.println(
                    "Баяр хүргэе! Та уг ширээн дэх өөрийн дээд амжилтыг шинэчиллээ. Шинэ дээд оноо: " + highScore
                            + "\n");
        }

        System.out.println("1: Дахиж тоглох");
        System.out.println("0: Буцах");

        String userMenuChoice = scanner.nextLine();

        if (userMenuChoice.equals("1")) {
            userPoint = 0;
            QA();
        } else if (userMenuChoice.equals("0")) {
            return;
        }
    }

    public void start() {

        for (;;) {
            System.out.print("\033[H\033[2J");

            printMenu();

            System.out.print("\nТаны сонголт(Ширээний нэрийг оруулна уу!): ");
            userMenuChoice = scanner.nextLine();

            // Ширээ сонгож орох
            if (deckOrganizer.findDeck(userMenuChoice) != null) {

                userDeckChoice = userMenuChoice;
                QA();

            } else if (userMenuChoice.equals("0")) {
                break;
            } else {
                System.out.print("\033[H\033[2J");
                System.out.println("Та ширээнээс сонголтоо хийнэ үү!");
            }
        }

    }
}
