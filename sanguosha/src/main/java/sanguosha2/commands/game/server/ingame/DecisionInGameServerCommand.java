package sanguosha2.commands.game.server.ingame;

import sanguosha2. core.server.game.Game;
import sanguosha2. core.server.game.controllers.interfaces.DecisionRequiredGameController;

public class DecisionInGameServerCommand extends InGameServerCommand {

	private static final long serialVersionUID = 1L;
	
	private final boolean confirmed;
	
	public DecisionInGameServerCommand(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	@Override
	public void execute(Game game) {
		game.<DecisionRequiredGameController>getGameController().onDecisionMade(this.confirmed);
		game.getGameController().proceed();
	}

}
