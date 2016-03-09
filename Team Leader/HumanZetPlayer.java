/**
 *  Represents the control panel and handles the control
 *  for the human player in the game of SET.
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

public class HumanZetPlayer extends JPanel
    implements ZetPlayer, MouseListener, ActionListener, KeyListener
{
  private final ZetGame game;
  private final ZetGameModel gameModel;

  private final JLabel nameField;
  private final JTextField scoreField;
  private final JButton zetButton;

  private int state;
  private int cardsPicked;

  private Timer timer;
  private int score;

  public HumanZetPlayer(ZetGame game, ZetGameModel gameModel)
  {
    this.game = game;
    this.gameModel = gameModel;

    setBackground(new Color(252, 190, 182));
    setPreferredSize(new Dimension(200, 90));

    score = 0;

    nameField = new JLabel("Guest  ");
    nameField.setFont(new Font("SansSerif", Font.PLAIN, 16));

    scoreField = new JTextField(2);
    scoreField.setEditable(false);
    scoreField.setBackground(Color.white);
    scoreField.setHorizontalAlignment(JTextField.CENTER);
    scoreField.setText(String.valueOf(score));
    scoreField.setFont(new Font("SansSerif", Font.PLAIN, 20));

    zetButton = new JButton("Set");
    zetButton.setBackground(Color.lightGray);
    zetButton.setPreferredSize(new Dimension(120, 40));
    zetButton.addActionListener(this);

    add(nameField);
    add(scoreField);
    add(zetButton);

    state = IDLE;
  }

  public void start()
  {
    state = LOOKING;
    zetButton.setBackground(Color.lightGray);
  }

  public void stop()
  {
    state = IDLE;
    zetButton.setBackground(Color.lightGray);
  }

  public int getScore()
  {
    return score;
  }

  public void setScore(int score)
  {
    this.score = score;
    scoreField.setText(String.valueOf(score));
  }

  /**
   *  Called when this player has declared "set".
   */
  private void declareZet()
  {
    state = PICKING;
    cardsPicked = 0;
    game.setActivePlayer(this);
    zetButton.setBackground(Color.yellow);

    // Must point to a set within 3 seconds:

    timer = new Timer(3000, new TimeoutListener(this));
    timer.start();
  }

  //*************** Set button, mouse, keyboard and timer listeners **************

  /**
   *  Handles speed "Set" button events.
   */
  public void actionPerformed(ActionEvent e)
  {
    if (state == LOOKING)
      declareZet();
  }

  /**
   *  Handles keyboard events.  Declares "set" when the spacebar is pressed.
   */
  public void keyPressed(KeyEvent e)
  {
    int code = e.getKeyCode();
    if (state == LOOKING && code == KeyEvent.VK_SPACE)
      declareZet();
  }

  public void keyReleased (KeyEvent e) { }
  public void keyTyped (KeyEvent e) { }

  /**
   *  Handles mouse events for picking 3 "set" cards.
   */
  public void mousePressed(MouseEvent e)
  {
    if (state != PICKING || cardsPicked >= 3)
      return;
    ZetTableDisplay d = (ZetTableDisplay)e.getSource();
    int i = d.getCardLocation(e.getX(), e.getY());
    if (gameModel.pickCard(i))
      cardsPicked++;
  }

  public void mouseReleased(MouseEvent e) { }
  public void mouseClicked(MouseEvent e) { }
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }

  /**
   *  Handles the time out clock.
   */
  private class TimeoutListener implements ActionListener
  {
    private ZetPlayer player;

    public TimeoutListener(ZetPlayer player)
    {
      this.player = player;
    }

    public void actionPerformed(ActionEvent e)
    {
      timer.stop();
      if (state == PICKING)
      {
        state = IDLE;
        game.playerDone(player);
      }
    }
  }
}
