package sanguosha2.commands.game.server.ingame;

import sanguosha2. cards.Card;
import sanguosha2. core.player.PlayerCardZone;
import sanguosha2. core.server.game.Game;
import sanguosha2. core.server.game.controllers.interfaces.CardSelectableGameController;

public class PlayerCardSelectionInGameServerCommand extends InGameServerCommand {
	
	private static final long serialVersionUID = 1L;
	
	private final Card card;
	private final PlayerCardZone zone;
	
	public PlayerCardSelectionInGameServerCommand(Card card, PlayerCardZone zone) {
		this.card = card;
		this.zone = zone;
	}

	@Override
	public void execute(Game game) {
		game.<CardSelectableGameController>getGameController().onCardSelected(this.card, this.zone);
		game.getGameController().proceed();
	}

}
