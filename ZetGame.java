/**
 *  Top level class for the SET game program.
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

public class ZetGame extends JFrame
{
  private final ZetTableDisplay tablePanel;
  private final ZetGameModel gameModel;
  private final ZetPlayer computer;
  private final ZetPlayer guest;
  private final JPanel controlPanel;
  private final JPanel buttonPanel;
  private final JButton startButton, pauseButton, resumeButton, newGameButton;
  private boolean paused;
  private ZetPlayer pausedPlayer;

  public ZetGame()
  {
    super("Set");
    setJMenuBar(new ZetMenu(this));

    gameModel = new ZetGameModel();
    tablePanel = new ZetTableDisplay(gameModel);
    tablePanel.setPreferredSize(new Dimension(640, 310));
    gameModel.addObserver(tablePanel);

    guest = new HumanZetPlayer(this, gameModel);
    computer = new ComputerZetPlayer(this, gameModel);

    controlPanel = new JPanel();
    controlPanel.setBackground(new Color(13, 66, 164));
    ((FlowLayout)controlPanel.getLayout()).setAlignment(FlowLayout.LEFT);
    Box b1 = Box.createHorizontalBox();
    b1.add(Box.createHorizontalStrut(8));
    b1.add((JPanel)computer);
    b1.add(Box.createHorizontalStrut(9));
    b1.add((JPanel)guest);
    controlPanel.add(b1);

    tablePanel.addMouseListener((MouseListener)guest);
    controlPanel.addKeyListener((KeyListener)guest);
    controlPanel.requestFocus();

    startButton = new JButton(" Start ");
    pauseButton = new JButton(" Pause ");
    pauseButton.setEnabled(false);
    resumeButton = new JButton("Resume");
    resumeButton.setEnabled(false);
    newGameButton = new JButton("New Game");
    newGameButton.setEnabled(false);
    ActionListener a = new ControlButtonListener();
    startButton.addActionListener(a);
    pauseButton.addActionListener(a);
    resumeButton.addActionListener(a);
    newGameButton.addActionListener(a);

    buttonPanel = new JPanel();
    ((FlowLayout)buttonPanel.getLayout()).setAlignment(FlowLayout.RIGHT);
    buttonPanel.setBackground(new Color(13, 66, 164));
    Box b2 = Box.createHorizontalBox();
    b2.add(startButton);
    b2.add(Box.createHorizontalStrut(15));
    b2.add(pauseButton);
    b2.add(Box.createHorizontalStrut(15));
    b2.add(resumeButton);
    b2.add(Box.createHorizontalStrut(75));
    b2.add(newGameButton);
    b2.add(Box.createHorizontalStrut(5));
    buttonPanel.add(b2);

    Container contentPane = getContentPane();
    contentPane.add(tablePanel, BorderLayout.CENTER);
    contentPane.add(controlPanel, BorderLayout.NORTH);
    contentPane.add(buttonPanel, BorderLayout.SOUTH);
  }

  protected void newGame()
  {
    guest.stop();
    computer.stop();

    gameModel.newGame();

    guest.setScore(0);
    computer.setScore(0);

    paused = false;
    startButton.setEnabled(true);
    pauseButton.setEnabled(false);
    resumeButton.setEnabled(false);
    newGameButton.setEnabled(false);
  }

  protected void startGame()
  {
    paused = false;
    startButton.setEnabled(false);
    pauseButton.setEnabled(true);
    resumeButton.setEnabled(false);
    newGameButton.setEnabled(true);
    startPlayers();
  }

  protected void stopGame()
  {
    paused = false;
    startButton.setEnabled(false);
    pauseButton.setEnabled(false);
    resumeButton.setEnabled(false);
    newGameButton.setEnabled(true);
    computer.stop();
    guest.stop();
    JOptionPane.showMessageDialog(this, "Game over\n", "Game over",
                                              JOptionPane.PLAIN_MESSAGE);
  }

  protected void pauseGame()
  {
    paused = true;
    pauseButton.setEnabled(false);
  }

  protected void resumeGame()
  {
    paused = false;
    pauseButton.setEnabled(true);
    resumeButton.setEnabled(false);
    playerDone(pausedPlayer);
  }

  protected void startPlayers()
  {
    // Make sure a Set is on the table:
    while (gameModel.findZet() == null)
      if (!gameModel.open3Cards())
      {
        stopGame();
        return;
      }

    computer.start();
    guest.start();
  }

  //***************** called by players **************************

  public void setActivePlayer(ZetPlayer p)
  {
    if (p == computer)
      guest.stop();
    else if (p == guest)
      computer.stop();
  }

  public void playerDone(ZetPlayer p)
  {
    if (paused)
    {
      pausedPlayer = p;
      resumeButton.setEnabled(true);
      return;
    }

    int score = p.getScore();

    if (gameModel.removeZet())
      score++;
    else
      score--;
    p.setScore(score);

    startPlayers();
    controlPanel.requestFocus();
  }

  private class ControlButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      JButton b = (JButton)e.getSource();
      if (b == startButton)
        startGame();
      else if (b == pauseButton)
        pauseGame();
      else if (b == resumeButton)
        resumeGame();
      else if (b == newGameButton)
        newGame();
    }
  }

  public static void main(String[] args)
  {
    JFrame w = new ZetGame();
    w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    w.setBounds(0, 0, 640, 480);
    w.setVisible(true);
  }
}
