package sanguosha2.commands.game.server.ingame;

import sanguosha2. core.server.game.Game;
import sanguosha2. core.server.game.controllers.TurnGameController;

public class EndStageInGameServerCommand extends InGameServerCommand {

	private static final long serialVersionUID = 9055072795730543708L;

	@Override
	public void execute(Game game) {
		game.<TurnGameController>getGameController().nextStage();
	}

}
