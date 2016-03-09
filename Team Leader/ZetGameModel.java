/**
 *  Represents a model for the game of SET.  Handles "picked" set cards.
 *  Updates the display when necessary.
 *
 *  Copyright (C) 2004 by Maria Litvin, Gary Litvin, and
 *  Skylight Publishing.  All rights reserved.
 *  Teachers may make a limited number of copies of this file
 *  for noncommercial, face-to-face teaching purposes.
 *
 *  SET® is a registered trademark of SET Enterprises, Inc.
 *
 */

public class ZetGameModel extends java.util.Observable
{
  private ZetTable table;
  private int[] pickedCards;
  private int numPickedCards;

  /**
   *  Creates a configuration for a new game.
   */
  public ZetGameModel()
  {
    newGame();
  }

  /**
   *  Creates a new Set table and clears "picked" cards.
   */
  public void newGame()
  {
    table = new ZetTable();
    pickedCards = new int[] {-1, -1, -1};
    numPickedCards = 0;
    setChanged();
    notifyObservers();
  }

  /**
   *  Returns the card table for this game.
   *  @return the card table for this game.
   */
  public ZetTable getTable()
  {
    return table;
  }

  /**
   *  Returns an array of indices of the picked cards.
   *  @return an array of indices of the picked cards.
   */
  public int[] getPickedCards()
  {
    return pickedCards;
  }

  /**
   *  "Picks" the specified open card.  The card is not picked if
   *  3 or more cards are already picked, if i < 0, or if
   *  table.getOpenCard(i) == null.
   *  @param i index of the card to be picked.
   *  @return true if successful at picking the card; false otherwise.
   */
  public boolean pickCard(int i)
  {
    if (numPickedCards >= 3 ||  i < 0 || table.getOpenCard(i) == null)
      return false;

    for (int k = 0; k < numPickedCards; k++)
    {
      if (i == pickedCards[k]) // if already picked
        return false;
    }

    pickedCards[numPickedCards] = i;
    numPickedCards++;
    setChanged();
    notifyObservers();
    return true;
  }

  /**
   *  Finds a "set" on the table.
   *  @return an array of the indices of the three "set" cards,
   *  or null if no sets are found.
   */
  public int[] findZet()
  {
    return table.findZet();
  }

  /**
   *  Removes picked cards from the table if they form a set.  If the set
   *  is removed and there are not enough open cards left, opens
   *  three more cards.
   *  @return true if the picked cards form a set and are successfully
   *  removed; false otherwise.
   */
  public boolean removeZet()
  {
    boolean zetFound = false;

    if (numPickedCards >= 3)
    {
      ZetCard card1 = table.getOpenCard(pickedCards[0]);
      ZetCard card2 = table.getOpenCard(pickedCards[1]);
      ZetCard card3 = table.getOpenCard(pickedCards[2]);
      zetFound = ZetAnalyzer.isZet(card1, card2, card3);

      if (zetFound)
      {
        table.remove3Cards(pickedCards);
        table.compactOpenCards();
      }
    }

    pickedCards[0] = -1;
    pickedCards[1] = -1;
    pickedCards[2] = -1;
    numPickedCards = 0;

    if (!table.enoughOpen())
      table.open3Cards();

    setChanged();
    notifyObservers();
    return zetFound;
  }

  /**
   *  Opens three cards from the deck if available.
   *  @return true if the cards are available in the deck; false otherwise.
   */
  public boolean open3Cards()
  {
    if (table.open3Cards())
    {
      setChanged();
      notifyObservers();
      return true;
    }
    else
      return false;
  }
}
