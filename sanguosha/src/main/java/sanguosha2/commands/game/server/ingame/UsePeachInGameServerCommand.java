package sanguosha2.commands.game.server.ingame;

import sanguosha2. cards.Card;
import sanguosha2. cards.basics.Peach;
import sanguosha2. core.player.PlayerCompleteServer;
import sanguosha2. core.server.game.Game;
import sanguosha2. core.server.game.controllers.HealGameController;
import sanguosha2. exceptions.server.game.InvalidPlayerCommandException;

public class UsePeachInGameServerCommand extends InGameServerCommand {

	private static final long serialVersionUID = 9088626402130251064L;
	
	private final Card card;

	public UsePeachInGameServerCommand(Card card) {
		this.card = card;
	}

	@Override
	public void execute(Game game) {
		PlayerCompleteServer currentPlayer = game.getCurrentPlayer();
		try {
			if (card != null) { 
				if (!(card instanceof Peach)) {
					throw new InvalidPlayerCommandException("card " + card + " is not peach");
				}
				if (!currentPlayer.getCardsOnHand().contains(card)) {
					throw new InvalidPlayerCommandException("card " + card + " is not on current player's hand");
				}
			}
			if (!currentPlayer.isDamaged()) {
				throw new InvalidPlayerCommandException("player is at full health");
			}
			currentPlayer.useCard(card);
		} catch (InvalidPlayerCommandException e) {
			e.printStackTrace();
		}
		game.pushGameController(new HealGameController(currentPlayer.getPlayerInfo(), currentPlayer.getPlayerInfo(), game));
		game.getGameController().proceed();
	}

}
