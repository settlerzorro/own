package sanguosha2.cards.specials;

import sanguosha2.cards.Card;

/**
 * The "special" type of cards, including Steal, Lightning, etc.
 * 
 * @author Harry
 *
 */
public abstract class Special extends Card {

	private static final long serialVersionUID = 1458323578635106662L;

	private boolean instant;

	public Special(int num, Suit suit, boolean isInstant, int id) {
		super(num, suit, CardType.SPECIAL, id);
		instant = isInstant;
	}
	
	public Special(boolean isInstant) {
		super(Color.COLORLESS, CardType.SPECIAL);
		this.instant = isInstant;
	}

	public boolean isInstant() {
		return instant;
	}
}
