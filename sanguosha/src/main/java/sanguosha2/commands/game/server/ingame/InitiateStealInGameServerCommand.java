package sanguosha2.commands.game.server.ingame;

import sanguosha2. cards.Card;
import sanguosha2. core.player.PlayerInfo;
import sanguosha2. core.server.game.Game;
import sanguosha2. core.server.game.controllers.GameController;
import sanguosha2. core.server.game.controllers.specials.instants.StealGameController;

public class InitiateStealInGameServerCommand extends AbstractInitiationInGameServerCommand {

	private static final long serialVersionUID = 1L;
	
	public InitiateStealInGameServerCommand(PlayerInfo target, Card card) {
		super(target, card);
	}

	@Override
	protected GameController getController(Game game, PlayerInfo target) {
		return new StealGameController(game.getCurrentPlayer().getPlayerInfo(), target, game);
	}

}
