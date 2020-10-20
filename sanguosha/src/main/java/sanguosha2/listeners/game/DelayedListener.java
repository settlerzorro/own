package sanguosha2.listeners.game;

import sanguosha2.cards.Card;
import sanguosha2.utils.DelayedType;

public interface DelayedListener {
	
	public void onDelayedAdded(Card card, DelayedType type);
	
	public void onDelayedRemove(DelayedType type);

}
