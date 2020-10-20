package sanguosha2.core.server.game.controllers.interfaces;

import sanguosha2.cards.Card;
import sanguosha2.core.player.PlayerCardZone;
import sanguosha2.core.server.game.controllers.GameController;

public interface CardSelectableGameController extends GameController {
	
	public void onCardSelected(Card card, PlayerCardZone zone);

}
