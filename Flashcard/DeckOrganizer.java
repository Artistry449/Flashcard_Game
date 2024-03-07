package Flashcard;

import java.util.ArrayList;

public class DeckOrganizer implements DeckOrganizer_inter {

    FileManager fileManager = new FileManager("Decks.txt");
    FileManager fileManager1 = new FileManager("Cards.txt");

    ArrayList<Deck> decks = new ArrayList<>();

    ArrayList<String> deckData;
    ArrayList<String> cardData;

    public DeckOrganizer() {
        // Ширээнүүдийн дата, картнуудын дата-г файл системээс татаж авчрах
        deckData = fileManager.readFile();
        cardData = fileManager1.readFile();

        // Авчирсан датагаар лист-д хуваарилалт хийх
        optimizeDecksData();
    }

    public void createDeck(String name) {
        decks.add(new Deck(name));
    }

    public boolean updateDeck(String name, String newName) {
        for (int i = 0; i < decks.size(); i++) {
            if (decks.get(i).getName().equals(name)) {
                decks.get(i).editName(newName);
            }
        }
        return false;
    }

    public boolean deleteDeck(String name) {
        for (int i = 0; i < decks.size(); i++) {
            if (decks.get(i).getName().equals(name)) {
                decks.remove(decks.get(i));
                return true;
            }
        }
        return false;
    }

    public void optimizeDecksData() {

        System.out.println("deckData:");
        System.out.println(deckData);
        for (int i = 0; i < deckData.size(); i++) {
            Deck newDeck = new Deck(deckData.get(i));
            decks.add(newDeck);
            optimizeCardsToItsDeck(newDeck);
        }

    }

    public void optimizeCardsToItsDeck(Deck deck) {
        for (String cardInfo : cardData) {
            String[] parts = cardInfo.split("/");
            if (parts.length == 2 && parts[0].trim().equals(deck.getName())) {
                String[] questionAnswer = parts[1].split("=");
                if (questionAnswer.length == 2) {
                    deck.addCard(new Card(questionAnswer[0].trim(), questionAnswer[1].trim()));
                }
            }
        }
    }

    public void pushToDB() {
        String data = "";

        // Decks-ийн дата-г хуулах
        for (Deck deck : decks) {
            data += deck.getName() + "\n";
        }
        fileManager.clearFileData();
        fileManager.writeFile(data);

        data = "";
        // Cards-ийн дата-г хуулах

        for (Deck deck : decks) {
            ArrayList<Card> cards = deck.getCards();
            for (Card card : cards) {
                String toPutDeckName = deck.getName() + "/";
                String question = card.getQuestion();
                String answer = card.getAnswer();

                data += toPutDeckName + question + "=" + answer + "\n";
            }
        }
        fileManager1.clearFileData();
        fileManager1.writeFile(data);
    }

    public void printAllDecks() {
        for (int i = 0; i < decks.size(); i++) {
            System.out.println(i + 1 + ": " + decks.get(i).getName());
        }
    }

    public int getDecksSize() {
        return decks.size();
    }

    public Deck findDeck(String deckName) {
        for (int i = 0; i < decks.size(); i++) {
            if (decks.get(i).getName().equals(deckName))
                return decks.get(i);
        }
        return null;
    }
}
