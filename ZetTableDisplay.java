/**
 *  Implements graphics for the card table in the game of SET.
 *
 *  Copyright (C) 2004 by Maria Litvin, Gary Litvin, and
 *  Skylight Publishing.  All rights reserved.
 *  Teachers may make a limited number of copies of this file
 *  for noncommercial, face-to-face teaching purposes.
 *
 *  SET® is a registered trademark of SET Enterprises, Inc.
 *
 */

import java.awt.*;
import javax.swing.*;

public class ZetTableDisplay extends JPanel
   implements java.util.Observer
{
  private ZetGameModel gameModel;

  private final int X_OFF = 10;
  private final int Y_OFF = 10;
  private final int X_PITCH = 105;
  private final int Y_PITCH = 70;
  private final int X_SIZE = 98;
  private final int Y_SIZE = 63;

  private Icon deckIcon = new ImageIcon("deck.jpg");

  private final int maxOpenCards = 21;

  public ZetTableDisplay(ZetGameModel model)
  {
    this.gameModel = model;
    setBackground(new Color(149, 183, 247));
  }

  public void update(java.util.Observable model, Object obj)
  {
    this.gameModel = (ZetGameModel)model;
    repaint();
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    int i, x, y;

    int[] pickedCards = gameModel.getPickedCards();
    ZetTable table = gameModel.getTable();

    for (int k = 0; k < pickedCards.length; k++)
    {
      i = pickedCards[k];
      if (i >= 0 && i < maxOpenCards)
      {
        x = X_OFF + (i / 3) * X_PITCH;
        y = Y_OFF + (i % 3) * Y_PITCH;
        drawPicked(g, x, y);
      }
    }

    for (i = 0; i < maxOpenCards; i++)
    {
      ZetCard card = table.getOpenCard(i);
      if (card != null)
      {
        x = X_OFF + (i / 3) * X_PITCH;
        y = Y_OFF + (i % 3) * Y_PITCH;
        drawCard(g, x, y, card);
      }
    }

    if (table.cardsInDeck() > 0)
    {
      x = X_OFF + X_PITCH / 2;
      y = Y_OFF + 3 * Y_PITCH;
      drawDeck(g, x, y, table.cardsInDeck());
    }
  }

  /**
   *  Returns the index of the open card that contains x, y.
   *  called from humanZetPlayer's mouse handler.
   *  @param x x-coordinate of the point.
   *  @param y y-coordinate of the point.
   *  @return the index of the open card that contains x, y, or -1 if (x, y) is not in a card.
   */
  public int getCardLocation(int x, int y)
  {
    x -= X_OFF;
    y -= Y_OFF;
    if (x % X_PITCH > X_SIZE || y % Y_PITCH > Y_SIZE)
      return -1;
    int i = x / X_PITCH * 3 + y / Y_PITCH;
    if (i >= 0 && i < maxOpenCards)
      return i;
    return -1;
  }

  //******************* Private drawing methods ***********************

  private void drawPicked(Graphics g, int x, int y)
  {
    g.setColor(Color.yellow);
    g.fillRoundRect(x + 4, y + 4, X_SIZE, Y_SIZE, 12, 12);
  }

  private void drawCard(Graphics g, int x, int y, ZetCard card)
  {
    g.setColor(Color.white);
    g.fillRoundRect(x, y, X_SIZE, Y_SIZE, 12, 12);
    g.setColor(Color.black);
    g.drawRoundRect(x, y, X_SIZE, Y_SIZE, 12, 12);

    switch (card.getNumber())
    {
      case 1:
      drawSymbol(g, x + X_SIZE / 2, y + Y_SIZE / 2, card);
      break;

      case 2:
      drawSymbol(g, x + X_SIZE / 2 - X_SIZE / 7, y + Y_SIZE / 2, card);
      drawSymbol(g, x + X_SIZE / 2 + X_SIZE / 7, y + Y_SIZE / 2, card);
      break;

      case 3:
      drawSymbol(g, x + X_SIZE / 2 - X_SIZE / 4 - 4, y + Y_SIZE / 2, card);
      drawSymbol(g, x + X_SIZE / 2, y + Y_SIZE / 2, card);
      drawSymbol(g, x + X_SIZE / 2 + X_SIZE / 4 + 4, y + Y_SIZE / 2, card);
      break;
    }
  }

  private void drawDeck(Graphics g, int x, int y, int nCards)
  {
    deckIcon.paintIcon(this, g, x, y);
    g.setColor(Color.black);
    g.drawString(String.valueOf(nCards), x + X_SIZE - 10, y + Y_SIZE);
  }

  private void drawSymbol(Graphics g, int x, int y, ZetCard card)
  {
    g.translate(x, y);
    switch(card.getShape())
    {
      case 1: drawOval(g, card); break;
      case 2: drawSquiggle(g, card); break;
      case 3: drawDiamond(g, card); break;
    }
    g.translate(-x, -y);
  }

  //********** red, green, and blue colors *****************

  private static Color darkColor[] =
    {new Color(255, 0, 0), new Color(33, 143, 20), new Color(51, 39, 97)};
  private static Color lightColor[] =
    {new Color(255, 170, 170), new Color(125, 236, 113), new Color(137, 121, 200)};

  private static final int OUTLINED = 1;
  private static final int SOLID = 2;
  private static final int STRIPED = 3;

  //********** Oval *****************

  private static final int oWidth = 20, oHeight = 42;
  private static final int[] xoLinePoints1 = {-4,-7,-8,-8,-8,-8,-8,-8,-7,-4};
  private static final int[] xoLinePoints2 = { 4, 7, 8, 8, 8, 8, 8, 8, 7, 4};
  private static final int yoLinePoint = -18;

  private void drawOval(Graphics g, ZetCard card)
  {
    int c = card.getColor() - 1;

    switch (card.getFill())
    {
      case OUTLINED:

      g.translate(1, 0);
      g.setColor(lightColor[c]);
      g.drawRoundRect(-oWidth / 2, -oHeight / 2, oWidth, oHeight, oWidth + 2, oWidth + 2);
      g.translate(-1, 0);
      g.setColor(darkColor[c]);
      g.drawRoundRect(-oWidth / 2, -oHeight / 2, oWidth, oHeight, oWidth + 2, oWidth + 2);
      break;

      case SOLID:

      g.setColor(darkColor[c]);
      g.fillRoundRect(-oWidth / 2 + 1, -oHeight / 2, oWidth, oHeight, oWidth + 2, oWidth + 2);
      g.fillRoundRect(-oWidth / 2, -oHeight / 2, oWidth, oHeight, oWidth + 2, oWidth + 2);
      break;

      case STRIPED:

      g.translate(1, 0);
      g.setColor(lightColor[c]);
      g.drawRoundRect(-oWidth / 2, -oHeight / 2, oWidth, oHeight, oWidth + 2, oWidth + 2);
      g.translate(-1, 0);
      g.setColor(darkColor[c]);
      g.drawRoundRect(-oWidth / 2, -oHeight / 2, oWidth, oHeight, oWidth + 2, oWidth + 2);
      drawStripes(g, xoLinePoints1, xoLinePoints2, yoLinePoint);
      break;
    }
  }

  //********** Squiggle *****************

  private static final int[] xsPoints =
      { 7, 7, 8, 8, 9, 9, 8, 8, 3, 2, 1,-6,-9,-9,-8,-8,-7,-7,-6,-6,-7,
       -7,-7,-8,-8,-9,-9,-8,-8,-3,-2,-1, 6, 9, 9, 8, 8, 7, 7, 6, 6, 7};
  private static final int[] ysPoints =
      {  0, -1, -2, -3, -4,-11,-12,-15,-20,-20,-21,-21,-18,-13,-12,-11,-10, -9, -8, -2, -1,
         0,  1,  2,  3,  4, 11, 12, 15, 20, 20, 21, 21, 18, 13, 12, 11, 10,  9,  8,  2,  1};
  private static final int[] xsLinePoints1 = {-6,-7,-5,-4,-4,-5,-7,-7,-6,-3};
  private static final int[] xsLinePoints2 = { 3, 6, 7, 7, 5, 4, 4, 5, 7, 6};
  private static final int ysLinePoint = -18;

  private void drawSquiggle(Graphics g, ZetCard card)
  {
    int c = card.getColor() - 1;

    switch (card.getFill())
    {
      case OUTLINED:

      g.translate(1, 0);
      g.setColor(lightColor[c]);
      g.drawPolygon(xsPoints, ysPoints, 42);
      g.translate(-1, 0);
      g.setColor(darkColor[c]);
      g.drawPolygon(xsPoints, ysPoints, 42);
      break;

      case SOLID:

      g.translate(1, 0);
      g.setColor(lightColor[c]);
      g.drawPolygon(xsPoints, ysPoints, 42);
      g.translate(-1, 0);
      g.setColor(darkColor[c]);
      g.drawPolygon(xsPoints, ysPoints, 42);
      g.fillPolygon(xsPoints, ysPoints, 42);
      break;

      case STRIPED:

      g.translate(1, 0);
      g.setColor(lightColor[c]);
      g.drawPolygon(xsPoints, ysPoints, 42);
      g.translate(-1, 0);
      g.setColor(darkColor[c]);
      g.drawPolygon(xsPoints, ysPoints, 42);
      drawStripes(g, xsLinePoints1, xsLinePoints2, ysLinePoint);
      break;
    }
  }

  //********** Diamond *****************

  private static final int[] xdPoints = {-10, 0, 10, 0};
  private static final int[] ydPoints = {0, -21, 0, 21};
  private static final int[] xdLinePoints1 = {-3,-4,-5,-6,-6,-5,-4,-3};
  private static final int[] xdLinePoints2 = { 3, 4, 5, 6, 6, 5, 4, 3};
  private static final int ydLinePoint = -14;

  private void drawDiamond(Graphics g, ZetCard card)
  {
    int c = card.getColor() - 1;

    switch (card.getFill())
    {
      case OUTLINED:

      g.translate(1, 0);
      g.setColor(lightColor[c]);
      g.drawPolygon(xdPoints, ydPoints, 4);
      g.translate(-1, 0);
      g.setColor(darkColor[c]);
      g.drawPolygon(xdPoints, ydPoints, 4);
      break;

      case SOLID:

      g.translate(1, 0);
      g.setColor(lightColor[c]);
      g.drawPolygon(xdPoints, ydPoints, 4);
      g.translate(-1, 0);
      g.setColor(darkColor[c]);
      g.drawPolygon(xdPoints, ydPoints, 4);
      g.fillPolygon(xdPoints, ydPoints, 4);
      break;

      case STRIPED:

      g.translate(1, 0);
      g.setColor(lightColor[c]);
      g.drawPolygon(xdPoints, ydPoints, 4);
      g.translate(-1, 0);
      g.setColor(darkColor[c]);
      g.drawPolygon(xdPoints, ydPoints, 4);
      drawStripes(g, xdLinePoints1, xdLinePoints2, ydLinePoint);
      break;
    }
  }

  //********** Stripes *****************

  private void drawStripes(Graphics g, int[] xs1, int[] xs2, int y)
  {
    for (int k = 0; k < xs1.length; k++)
    {
      g.drawLine(xs1[k], y, xs2[k], y);
      y += 4;
    }
  }
}
