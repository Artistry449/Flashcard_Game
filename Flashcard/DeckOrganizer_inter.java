package Flashcard;

public interface DeckOrganizer_inter {
    public void createDeck(String name);

    public boolean updateDeck(String name, String newName);

    public boolean deleteDeck(String name);

    public void optimizeCardsToItsDeck(Deck deck);

    public void printAllDecks();
}
