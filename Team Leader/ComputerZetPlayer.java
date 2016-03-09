/**
 *  Represents the control panel and handles the control
 *  for the computer player in the game of SET.
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
import javax.swing.event.*;

public class ComputerZetPlayer extends JPanel
    implements ZetPlayer, ActionListener, ChangeListener
{
  private final ZetGame game;
  private final ZetGameModel gameModel;

  private final JLabel nameField;
  private final JTextField scoreField;
  private final JButton zetButton;

  private static final int AVG_DELAY = 30000;
  private int delay;
  private final JSlider speedSlider;
  private Timer clock1, clock2;

  private int state;
  private int zetCards[];

  private int score;

  public ComputerZetPlayer(ZetGame game, ZetGameModel gameModel)
  {
    this.game = game;
    this.gameModel = gameModel;

    setBackground(new Color(189, 172, 189));
    setPreferredSize(new Dimension(200, 90));

    score = 0;

    nameField = new JLabel("Computer    ");
    nameField.setFont(new Font("SansSerif", Font.PLAIN, 16));

    scoreField = new JTextField(2);
    scoreField.setEditable(false);
    scoreField.setBackground(Color.white);
    scoreField.setHorizontalAlignment(JTextField.CENTER);
    scoreField.setText(String.valueOf(score));
    scoreField.setFont(new Font("SansSerif", Font.PLAIN, 20));

    zetButton = new JButton("    Set    ");
    zetButton.setBackground(Color.white);
    zetButton.setEnabled(false);
    zetButton.setPreferredSize(new Dimension(120, 40));

    delay = AVG_DELAY;
    speedSlider = new JSlider(SwingConstants.VERTICAL, 0, 2 * AVG_DELAY, delay);
    speedSlider.addChangeListener(this);
    clock1 = new Timer(delay, this);
    clock1.setRepeats(false);
    clock2 = new Timer(4000, this);
    clock2.setRepeats(false);

    state = IDLE;

    JPanel p = new JPanel();
    p.add(nameField);
    p.add(scoreField);
    p.add(zetButton);
    setLayout(new BorderLayout());
    add(p, BorderLayout.CENTER);
    add(speedSlider, BorderLayout.EAST);
  }

  public void start()
  {
    state = LOOKING;
    clock1.setInitialDelay(delay);
    clock1.start();
    zetButton.setBackground(Color.white);
  }

  public void stop()
  {
    state = IDLE;
    clock1.stop();
    clock2.stop();
    zetButton.setBackground(Color.white);
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
   *  Called when this player has found a set.
   */
  private void declareZet()
  {
    int[] zet = gameModel.findZet();
    if (zet == null)
      return;

    game.setActivePlayer(this);

    state = PICKING;

    gameModel.pickCard(zet[0]);
    gameModel.pickCard(zet[1]);
    gameModel.pickCard(zet[2]);

    zetButton.setBackground(Color.yellow);
    clock2.start();
  }

  //*************** speed slider and clock Listeners **************

  /**
   *  Handles speed slider events
   */
  public void stateChanged(ChangeEvent e)
  {
    delay = Math.max(5000, 2 * AVG_DELAY - ((JSlider)e.getSource()).getValue());
  }

  /**
   *  Handles clock1 and clock2 events
   */
  public void actionPerformed(ActionEvent e)
  {
    if (state == LOOKING)
      declareZet();
    else if (state == PICKING)
      game.playerDone(this);
  }
}
