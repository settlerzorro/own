package sanguosha2.commands.game.server.ingame;

import sanguosha2. cards.Card;
import sanguosha2. core.player.PlayerCompleteServer;
import sanguosha2. core.server.game.Game;
import sanguosha2. exceptions.server.game.InvalidPlayerCommandException;
import sanguosha2. utils.DelayedType;

public class UseLightningInGameServerCommand extends InGameServerCommand {

	private static final long serialVersionUID = 9088626402130251064L;
	
	private final Card card;

	public UseLightningInGameServerCommand(Card card) {
		this.card = card;
	}

	@Override
	public void execute(Game game) {
		PlayerCompleteServer currentPlayer = game.getCurrentPlayer();
		try {
			currentPlayer.removeCardFromHand(card);
			currentPlayer.pushDelayed(card, DelayedType.LIGHTNING);
		} catch (InvalidPlayerCommandException e) {
			e.printStackTrace();
		}
		game.getGameController().proceed();
	}

}
