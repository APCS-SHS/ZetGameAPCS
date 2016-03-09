/**
 *  Provides static methods for finding sets.
 */

/*

  Tips:

  - Given a, b, c from {1, 2, 3}, they are all equal or all different if and only if
    (a + b + c) % 3 == 0.  This property can be used for testing three cards for a set
    easily.

    (For more mathematically inclined, the SET deck corresponds to the 4-dimensional vector
    space over the algebraic field {0, 1, 2} with modulo 3 arithmetic.  Three cards form a "set"
    if and only if the sum of the corresponding vectors is 0.)

  - isZet and findZet are static methods.

  - in isZet, don't forget to check that the cards are not all the same.

  - Note that an array passed to findZet can have nulls in some of the elements.

*/

public class ZetAnalyzer
{

}
