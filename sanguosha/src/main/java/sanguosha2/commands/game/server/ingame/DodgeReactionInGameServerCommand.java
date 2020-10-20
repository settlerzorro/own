package sanguosha2.commands.game.server.ingame;

import sanguosha2. cards.Card;
import sanguosha2. core.server.game.Game;
import sanguosha2. core.server.game.controllers.DodgeGameController;

public class DodgeReactionInGameServerCommand extends InGameServerCommand {

	private static final long serialVersionUID = 8547546299242633692L;

	private final Card dodge;
	
	public DodgeReactionInGameServerCommand(Card card) {
		this.dodge = card;
	}
	
	@Override
	public void execute(Game game) {
		if (dodge != null) {
			game.<DodgeGameController>getGameController().onDodgeUsed(dodge);
		} else {
			game.<DodgeGameController>getGameController().onDodgeNotUsed();
		}
		game.getGameController().proceed();
	}

}
