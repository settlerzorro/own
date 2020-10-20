package sanguosha2.listeners.game;

import sanguosha2.cards.Card;

public interface CardOnHandListener 
{
	/**
	 * invoked when card is received
	 * @param card
	 */
	public void onCardAdded(Card card);
	/**
	 * invoked when card is discarded (disposed or used)
	 * @param card
	 */
	public void onCardRemoved(Card card);
}
