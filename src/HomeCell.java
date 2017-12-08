/**
 * @author Shep Sims, Andrew Baca, Trip Calihan, Peter Blair
 * File: HomeCell.java
 * HomeCell, a subclass, extends AbstractCell
 */
public class HomeCell extends AbstractCell
{
	/**
	 * Recalls the constructor from AbstractCell. 
	 * HomeCells will always be built with array lengths of 13.
	 */
	public HomeCell() { 
			super(13);
	}
	
	/**
	 * Determines if the conditions are met for adding a card from cell c
	 * @param c - cell to check if a card can be added from
	 * @return false if the following conditions are not met, which determine whether a card can be added,
	 * otherwise it returns true, indicating that it can be added
	 */
	public boolean canAddFrom(Cell c) {
		
		if (!super.canAddFrom(c)) {
			return false;
		}
		
		Card topCard = c.seeTop();
		if (isEmpty()) {
			if (topCard.getRank() == 1) {
				return true;
			}
			return false;
		}
		Card bottomCard = seeTop();
		if (topCard.sameSuit(bottomCard) && topCard.oneLarger(bottomCard)) {
			return true;
		}
		return false;
	}
}