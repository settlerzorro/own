package sanguosha2.core.server.game.controllers.interfaces;

import sanguosha2.core.server.game.controllers.GameController;

public interface DecisionRequiredGameController extends GameController {
	
	public void onDecisionMade(boolean confirmed);
	
}
