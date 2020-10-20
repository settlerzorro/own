package sanguosha2.core.server.game.controllers.interfaces;

import sanguosha2.cards.Card;
import sanguosha2.core.server.game.controllers.GameController;

public interface AttackUsableGameController extends GameController {

	public void onAttackUsed(Card card);
	
	public void onAttackNotUsed();
}
