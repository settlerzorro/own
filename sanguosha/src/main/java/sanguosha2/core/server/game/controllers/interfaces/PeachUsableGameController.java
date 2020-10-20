package sanguosha2.core.server.game.controllers.interfaces;

import sanguosha2.cards.Card;
import sanguosha2.core.server.game.controllers.GameController;

public interface PeachUsableGameController extends GameController {
	
	public void onPeachUsed(Card peach);

}
