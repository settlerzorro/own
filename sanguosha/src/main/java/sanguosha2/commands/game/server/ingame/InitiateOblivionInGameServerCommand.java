package sanguosha2.commands.game.server.ingame;

import sanguosha2. cards.Card;
import sanguosha2. core.player.PlayerCompleteServer;
import sanguosha2. core.player.PlayerInfo;
import sanguosha2. core.server.game.Game;
import sanguosha2. exceptions.server.game.InvalidPlayerCommandException;
import sanguosha2. utils.DelayedType;

public class InitiateOblivionInGameServerCommand extends InGameServerCommand {

	private static final long serialVersionUID = 1L;
	
	private final PlayerInfo target;
	private final Card card;

	public InitiateOblivionInGameServerCommand(PlayerInfo target, Card card) {
		this.target = target;
		this.card = card;
	}

	@Override
	public void execute(Game game) {
		PlayerCompleteServer currentPlayer = game.getCurrentPlayer();
		try {
			currentPlayer.removeCardFromHand(card);
			game.findPlayer(this.target).pushDelayed(card, DelayedType.OBLIVION);
		} catch (InvalidPlayerCommandException e) {
			e.printStackTrace();
		}
		game.getGameController().proceed();
	}
}
