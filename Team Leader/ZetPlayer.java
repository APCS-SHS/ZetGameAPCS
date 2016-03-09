/**
 *  Represents a SET game player.
 *
 *  Copyright (C) 2004 by Maria Litvin, Gary Litvin, and
 *  Skylight Publishing.  All rights reserved.
 *  Teachers may make a limited number of copies of this file
 *  for noncommercial, face-to-face teaching purposes.
 *
 *  SET® is a registered trademark of SET Enterprises, Inc.
 *
 */

public interface ZetPlayer
{
  static final int IDLE = 0;
  static final int LOOKING = 1;
  static final int PICKING = 2;

  /**
   *  Sets this player into the LOOKING state.
   */
  void start();

  /**
   *  Sets this player into the IDLE state
   */
  void stop();

  /**
   *  Returns this player's score.
   *  @return this player's score.
   */
  int getScore();

  /**
   *  Sets this player's score.
   *  @param score the new score value.
   */
  void setScore(int score);
}
