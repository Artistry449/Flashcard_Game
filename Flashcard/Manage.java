package Flashcard;

import java.util.Scanner;

public class Manage implements App {
    Scanner scanner = new Scanner(System.in);

    DeckOrganizer deckOrganizer = new DeckOrganizer();
    String userChoice;

    String caption = "Тохиргоо!";

    public String getCaption() {
        return caption;
    }

    public void printMenu() {
        System.out.println("\n-----Тохиргоо хэсэг-----\n");

        if (deckOrganizer.getDecksSize() == 0) {
            System.out.println("Таньд одоогоор ширээ байхгүй байна");
            return;
        } else {

            deckOrganizer.printAllDecks();

            System.out.println("\n");

            System.out.println("A: Ширээ үүсгэх");
            System.out.println("B: Буцах");
        }
    }

    public void insideDeckMenu() {
        System.out.print("\033[H\033[2J");
        System.out.println("Та энэ ширээнд юу хийхийг хүсэж байна вэ?");
        System.out.println("1. Ширээний нэрийг өөрчлөх");
        System.out.println("2. Ширээг устгах");
        System.out.println("3. Картнуудыг нь засах");
        System.out.println("0. Back");
    }

    public void insideCardMenu(String deckName) {
        System.out.print("\033[H\033[2J");

        System.out.println();
        deckOrganizer.findDeck(deckName).printAllCards();
        System.out.println();
        System.out.println("1. Шинэ карт нэмэх");
        System.out.println("2. Картны мэдээллийг өөрчлөх");
        System.out.println("3. Карт устгах");
        System.out.println("0. Буцах");
        System.out.println();
    }

    public void start() {

        for (;;) {
            System.out.print("\033[H\033[2J");
            printMenu();

            System.out.print("\nТаны сонголт(Ширээний нэрийг оруулна уу!): ");

            userChoice = scanner.nextLine();

            if (userChoice.equals("A")) {
                System.out.print("Таны үүсгэх ширээний нэр: ");

                String newDeckName = scanner.nextLine();

                deckOrganizer.createDeck(newDeckName);

                System.out.println("Ширээ амжилттай үүслээ!");
            } else if (userChoice.equals("B")) {
                break;
                // return;
            } else if (deckOrganizer.findDeck(userChoice) != null) {

                // Хэрэглэгчийн сонгосон ширээний нэрийг хадгалах
                String userDeckChoice = userChoice;

                // Ширээн дотор орсны дараа хэрэглэгчид харах ёстой меню-г хэвлэх
                insideDeckMenu();

                System.out.print("\nТаны сонголт: ");

                userChoice = scanner.nextLine();

                // 1. Ширээний нэрийг өөрчлөх
                if (userChoice.equals("1")) {
                    System.out.print("\033[H\033[2J");

                    System.out.print("Ширээний шинэ нэрийг оруулна уу: ");

                    userChoice = scanner.nextLine();

                    deckOrganizer.findDeck(userDeckChoice).editName(userChoice);

                    System.out.println("Ширээний нэр амжилттай солигдлоо!");
                }

                // 2. Ширээг устгах

                if (userChoice.equals("2")) {
                    System.out.print("\033[H\033[2J");

                    System.out.println("Та ширээг устгахдаа итгэлтэй байна уу? (y/n)");

                    userChoice = scanner.nextLine();

                    if (userChoice.equals("y")) {
                        deckOrganizer.deleteDeck(userDeckChoice);

                        System.out.println("Ширээ амжилттай устгагдлаа!");
                    } else if (userChoice.equals("n")) {
                        System.out.println("Ширээ устгагдах хүсэлт цуцлагдлаа!");
                    }
                }

                // 3. Картнуудыг нь засах

                if (userChoice.equals("3")) {
                    System.out.print("\033[H\033[2J");

                    insideCardMenu(userDeckChoice);

                    userChoice = scanner.nextLine();

                    // 1. Шинэ карт нэмэх

                    if (userChoice.equals("1")) {
                        System.out.print("Та шинээр үүсгэх картны асуултыг оруулна уу: ");
                        String question = scanner.nextLine();
                        System.out.println();
                        System.out.print("Та асуултны хариултыг нь оруулна уу уу: ");
                        String answer = scanner.nextLine();

                        deckOrganizer.findDeck(userDeckChoice).addCard(new Card(question, answer));

                        System.out.println("Уг ширээ рүү шинэ карт амжилттай нэмэгдлээ!\n");
                    }
                    // 2. Картны мэдээллийг өөрчлөх

                    // 3. Карт устгах

                    // 0. Буцах
                    else if (userDeckChoice.equals("0")) {
                        // break;
                        return;
                    }
                }
            }
        }
        deckOrganizer.pushToDB();
    }
}
