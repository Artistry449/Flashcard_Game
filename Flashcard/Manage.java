package Flashcard;

import java.util.Scanner;

public class Manage implements App {
    Scanner scanner = new Scanner(System.in);

    DeckOrganizer deckOrganizer = new DeckOrganizer();
    String userChoice;

    String caption = "Ширээний цуглуулгыг өөрчлөх!";

    public String getCaption() {
        return caption;
    }

    public void changeDeckName(Deck deck) {
        System.out.print("\033[H\033[2J");

        System.out.print("Ширээний шинэ нэрийг оруулна уу: ");

        userChoice = scanner.nextLine();

        deck.editName(userChoice);

        System.out.println("Ширээний нэр амжилттай солигдлоо!");

        deckOrganizer.pushDecksToDB();
    }

    public void deleteDeck(String userDeckChoice) {
        System.out.println("Та ширээг устгахдаа итгэлтэй байна уу? (y/n)");

        userChoice = scanner.nextLine();

        if (userChoice.equals("y")) {
            deckOrganizer.deleteDeck(userDeckChoice);

            System.out.println("Ширээ амжилттай устгагдлаа!");
        } else if (userChoice.equals("n")) {
            System.out.println("Ширээ устгагдах хүсэлт цуцлагдлаа!");
        }
        deckOrganizer.pushDecksToDB();
        deckOrganizer.pushCardsToDB();
    }

    public void changeCardDetail(Card card) {
        for (;;) {
            System.out.print("\033[H\033[2J");

            System.out.println("Картны асуулт: " + card.getQuestion());
            System.out.println("Картны хариулт: " + card.getAnswer() + "\n");

            System.out.println("1. Асуултыг нь өөрчлөх");
            System.out.println("2. Хариултыг нь өөрчлөх");
            System.out.println("0. Буцах\n");

            System.out.print("Таны сонголт: ");
            userChoice = scanner.nextLine();

            if (userChoice.equals("1")) {

                System.out.print("\nШинэ асуулт: ");
                card.editQuestion(scanner.nextLine());

                // deckOrganizer.optimizeCardsToItsDeck(deck);

            } else if (userChoice.equals("2")) {

                System.out.print("\nШинэ хариулт: ");
                card.editAnswer(scanner.nextLine());

                // deckOrganizer.optimizeCardsToItsDeck(deck);

            } else if (userChoice.equals("0")) {
                break;
            } else {
                System.out.println("Та меню-ээс сонгоно уу!");
            }
        }
    }

    public void addCard(Deck deck) {
        System.out.print("Та шинээр үүсгэх картны асуултыг оруулна уу: ");
        String question = scanner.nextLine();
        System.out.println();
        System.out.print("Та асуултны хариултыг нь оруулна уу уу: ");
        String answer = scanner.nextLine();

        deck.addCard(new Card(question, answer));

        System.out.println("Уг ширээ рүү шинэ карт амжилттай нэмэгдлээ!\n");

        deckOrganizer.pushCardsToDB();
    }

    public void deleteCard(Deck deck) {
        deck.printAllCards();
        System.out.println("\n0: Буцах\n");

        System.out.print("Таны сонголт(Картны нэрийг оруулна уу): ");
        userChoice = scanner.nextLine();

        if (deck.findCard(userChoice) != null) {
            deck.deleteCard(userChoice);

            deckOrganizer.pushCardsToDB();
            System.out.println("Карт амжилттай устгагдлаа!");
        }
    }

    public void printMenu() {
        System.out.println("\n-----Ширээний цуглуулгыг өөрчлөх хэсэг-----\n");

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
        System.out.println("\n0. Back");
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

                deckOrganizer.pushDecksToDB();
            } else if (userChoice.equals("B")) {
                break;
                // return;
            } else if (deckOrganizer.findDeck(userChoice) != null) {
                Deck deck = deckOrganizer.findDeck(userChoice);

                // Хэрэглэгчийн сонгосон ширээний нэрийг хадгалах
                String userDeckChoice = userChoice;

                // Ширээн дотор орсны дараа хэрэглэгчид харах ёстой меню-г хэвлэх
                insideDeckMenu();

                System.out.print("\nТаны сонголт: ");

                userChoice = scanner.nextLine();

                // 1. Ширээний нэрийг өөрчлөх
                if (userChoice.equals("1")) {
                    changeDeckName(deck);
                }

                // 2. Ширээг устгах

                if (userChoice.equals("2")) {
                    System.out.print("\033[H\033[2J");

                    deleteDeck(userDeckChoice);
                }

                // 3. Картнуудыг нь засах

                if (userChoice.equals("3")) {
                    System.out.print("\033[H\033[2J");

                    insideCardMenu(userDeckChoice);

                    System.out.print("Таны сонголт: ");
                    userChoice = scanner.nextLine();

                    // 1. Шинэ карт нэмэх

                    if (userChoice.equals("1")) {
                        addCard(deck);
                    }
                    // 2. Картны мэдээллийг өөрчлөх

                    else if (userChoice.equals("2")) {
                        System.out.print("\033[H\033[2J");
                        for (;;) {
                            System.out.print("\033[H\033[2J");

                            deck.printAllCards();
                            System.out.println("\n0: Буцах");

                            System.out.print("\nТаны сонголт(Картны нэрийг эсвэл меню-ээс сонгоно уу): ");
                            userChoice = scanner.nextLine();

                            Card card = deck.findCard(userChoice);
                            if (card != null) {
                                // System.out.print("\033[H\033[2J");

                                changeCardDetail(card);
                                deckOrganizer.pushCardsToDB();
                            } else if (userChoice.equals("0"))
                                break;
                            else {
                                System.out.print("\033[H\033[2J");
                                System.out.println("Та менюнээс сонголтоо хийнэ үү!");
                            }
                        }

                    }
                    // 3. Карт устгах
                    else if (userChoice.equals("3")) {
                        System.out.print("\033[H\033[2J");

                        deleteCard(deck);
                    }
                    // 0. Буцах
                    else if (userDeckChoice.equals("0")) {
                        // break;
                        return;
                    }
                }
            }
        }
        // deckOrganizer.pushToDB();
    }
}
