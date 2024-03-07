package Flashcard;

public interface DeckOrganizer_inter {
    public void createDeck(String name);

    public boolean updateDeck(String name, String newName);

    public boolean deleteDeck(String name);

    public void optimizeDecksData();

    public void optimizeCardsToItsDeck(Deck deck);

    public void pushDecksToDB();

    public void pushCardsToDB();

    public void pushToDB();

    public void printAllDecks();

    public int getDecksSize();

    public Deck findDeck(String deckName);
}
