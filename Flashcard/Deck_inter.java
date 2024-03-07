package Flashcard;

public interface Deck_inter {
    public String getName();

    // public String getPurpose();

    // public String getDescription();

    // public void editName(String new_name);

    // public void editPurpose(String new_purpose);

    // public void editDescription(String new_description);

    public int getDeckSize();

    public void addCard(Card card);

    public void deleteCard(String cardName);
}