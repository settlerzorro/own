package sanguosha2.commands.game.server.ingame;

import sanguosha2. cards.Card;
import sanguosha2. core.player.PlayerInfo;
import sanguosha2. core.server.game.Game;
import sanguosha2. core.server.game.controllers.GameController;
import sanguosha2. core.server.game.controllers.specials.instants.HarvestGameController;

public class InitiateHarvestInGameServerCommand extends AbstractInitiationInGameServerCommand {

	private static final long serialVersionUID = 1L;

	public InitiateHarvestInGameServerCommand(Card card) {
		super(null, card);
	}

	@Override
	protected GameController getController(Game game, PlayerInfo target) {
		return new HarvestGameController(game.getCurrentPlayer().getPlayerInfo(), game);
	}

}
