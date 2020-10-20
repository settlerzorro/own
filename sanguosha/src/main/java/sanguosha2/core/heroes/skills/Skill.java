package sanguosha2.core.heroes.skills;

import sanguosha2.cards.Card;
import sanguosha2.core.GameState;

public interface Skill {

	public String getName();

	public String getDescription();
	
	default public boolean canBeTargeted(Card card, GameState state) {
		return canBeTargeted(state);
	}
	
	public boolean canBeTargeted(GameState state);
}
