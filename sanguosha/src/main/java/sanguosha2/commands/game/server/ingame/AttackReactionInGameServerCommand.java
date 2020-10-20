package sanguosha2.commands.game.server.ingame;

import sanguosha2. cards.Card;
import sanguosha2. cards.basics.Attack;
import sanguosha2. core.player.PlayerCompleteServer;
import sanguosha2. core.player.PlayerInfo;
import sanguosha2. core.server.game.Game;
import sanguosha2. core.server.game.controllers.interfaces.AttackUsableGameController;
import sanguosha2. exceptions.server.game.InvalidPlayerCommandException;

public class AttackReactionInGameServerCommand extends InGameServerCommand {

	private static final long serialVersionUID = 1L;

	private final PlayerInfo source;
	private final Card attack;
	
	public AttackReactionInGameServerCommand(PlayerInfo source, Card card) {
		this.source = source;
		this.attack = card;
	}
	
	@Override
	public void execute(Game game) {
		PlayerCompleteServer user = game.findPlayer(this.source);
		if (this.attack != null) {
			try {
				if (!(this.attack instanceof Attack) || !user.getCardsOnHand().contains(this.attack)) {
					throw new InvalidPlayerCommandException("Card is not attack or target does not have this card");
				}
				user.useCard(this.attack);
			} catch (InvalidPlayerCommandException e) {
				e.printStackTrace();
				return;
			}
			game.<AttackUsableGameController>getGameController().onAttackUsed(this.attack);
		} else {
			game.<AttackUsableGameController>getGameController().onAttackNotUsed();
		}
		game.getGameController().proceed();
	}

}
