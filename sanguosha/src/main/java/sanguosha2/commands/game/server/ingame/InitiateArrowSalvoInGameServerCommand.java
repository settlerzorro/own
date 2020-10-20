package sanguosha2.commands.game.server.ingame;

import sanguosha2. cards.Card;
import sanguosha2. core.player.PlayerInfo;
import sanguosha2. core.server.game.Game;
import sanguosha2. core.server.game.controllers.GameController;
import sanguosha2. core.server.game.controllers.specials.instants.ArrowSalvoGameController;

public class InitiateArrowSalvoInGameServerCommand extends AbstractInitiationInGameServerCommand {

	private static final long serialVersionUID = 1L;

	public InitiateArrowSalvoInGameServerCommand(Card card) {
		super(null, card);
	}

	@Override
	protected GameController getController(Game game, PlayerInfo target) {
		return new ArrowSalvoGameController(game.getCurrentPlayer().getPlayerInfo(), game);
	}

}
