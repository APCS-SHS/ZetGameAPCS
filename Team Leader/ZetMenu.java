/**
 *  A menu bar for the SET game program.
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
import java.awt.event.*;
import javax.swing.*;

public class ZetMenu extends JMenuBar
    implements ActionListener
{
  private ZetGame game;
  private JCheckBoxMenuItem sound;
  private JMenuItem newgame, exit, howtoplay, about;

  public ZetMenu(ZetGame game)
  {
    this.game = game;

    // "File" menu:

    JMenu fileMenu = new JMenu("Game");
    fileMenu.setMnemonic('G');

    newgame = new JMenuItem("New game");
    newgame.setMnemonic('N');
    newgame.addActionListener(this);

    exit = new JMenuItem("Exit");
    exit.setMnemonic('x');
    exit.addActionListener(this);

    fileMenu.add(newgame);
    fileMenu.add(exit);

    add(fileMenu);

    // "Help" menu:

    JMenu helpMenu = new JMenu("Help");
    helpMenu.setMnemonic('H');

    howtoplay = new JMenuItem("How to play...");
    howtoplay.setMnemonic('p');
    howtoplay.addActionListener(this);

    about = new JMenuItem("About...");
    about.setMnemonic('A');
    about.addActionListener(this);

    helpMenu.add(howtoplay);
    helpMenu.add(about);

    add(helpMenu);
  }

  public void actionPerformed(ActionEvent e)
  {
    JMenuItem src = (JMenuItem)e.getSource();

    if (src == newgame)
      game.newGame();
    else if (src == exit)
      System.exit(0);
    else if (src == howtoplay)
    {
      game.pauseGame();
      showHelp();
    }
    else if (src == about)
    {
      game.pauseGame();
      showAbout();
    }
  }

  private void showHelp()
  {
    JOptionPane.showMessageDialog(null,
        "Each card has four attributes: color (red, green, or blue),\n" +
        "shape (oval, squiggle, or diamond), fill (empty, solid, or striped)\n" +
        "and the number of symbols (1, 2, or 3).  The deck contains 81 cards.\n\n" +

        "A \"set\" is a set of three cards, such that for each attribute\n" +
        "its values for all three cards are either all the same or\n" +
        "all different.  For example, three single solid ovals,\n" +
        "red, green, and blue, form a set.  The objective of the\n" +
        "game is to collect as many sets as you can.\n\n" +

        "Press the Spacebar or click on the \"Set\" button when you see\n" +
        "a set, then click on each of the three cards to collect the set.\n" +
        "Once you declare a set, you only have a few seconds to collect it.\n" +
        "You gain a point for each correct set and lose a point for\n" +
        "an incomplete or incorrect set.\n\n" +

        "The picked cards are replaced with three cards from the deck.\n" +
        "If there is no set in the open cards, additional 3 cards\n" +
        "are opened.\n\n" +

        "www.setgame.com has more information and the detailed rules.\n\n" +

        "SET\u00AE  is a registered trademark of SET Enterprises, Inc.\n\n",

        "How to Play",       // Dialog title
        JOptionPane.PLAIN_MESSAGE);
  }

  private void showAbout()
  {
    JOptionPane.showMessageDialog(null,
        "This prototype has been developed by Maria and\n" +
        "Gary Litvin to illustrate how an OOP project\n" +
        "can be implemented by a team of programmers.\n\n" +

        "You are allowed to copy and use this game and its code\n" +
        "for teaching and educational purposes only.\n\n",

        "About",       // Dialog title
        JOptionPane.PLAIN_MESSAGE);
  }

  public boolean isSoundEnabled()
  {
    return sound.isSelected();
  }
}

