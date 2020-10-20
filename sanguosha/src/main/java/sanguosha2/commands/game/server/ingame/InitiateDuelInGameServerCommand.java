package sanguosha2.commands.game.server.ingame;

import sanguosha2. cards.Card;
import sanguosha2. core.player.PlayerInfo;
import sanguosha2. core.server.game.Game;
import sanguosha2. core.server.game.controllers.GameController;
import sanguosha2. core.server.game.controllers.specials.instants.DuelGameController;

public class InitiateDuelInGameServerCommand extends AbstractInitiationInGameServerCommand {

	private static final long serialVersionUID = 1L;
	
	public InitiateDuelInGameServerCommand(PlayerInfo target, Card card) {
		super(target, card);
	}

	@Override
	protected GameController getController(Game game, PlayerInfo target) {
		return new DuelGameController(game.getCurrentPlayer().getPlayerInfo(), target, game);
	}

}
