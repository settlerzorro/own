package sanguosha2.commands.game.server.ingame;

import java.util.Set;

import sanguosha2. cards.Card;
import sanguosha2. cards.Card.Color;
import sanguosha2. cards.basics.Attack;
import sanguosha2. core.player.PlayerCompleteServer;
import sanguosha2. core.player.PlayerInfo;
import sanguosha2. core.server.game.Game;
import sanguosha2. core.server.game.controllers.interfaces.AttackUsableGameController;
import sanguosha2. exceptions.server.game.InvalidPlayerCommandException;

public class SerpentSpearAttackReactionInGameServerCommand extends InGameServerCommand {

	private static final long serialVersionUID = 1L;

	private final PlayerInfo source;
	private final Set<Card> cards;
	
	public SerpentSpearAttackReactionInGameServerCommand(PlayerInfo source, Set<Card> cards) {
		this.source = source;
		this.cards = cards;
	}
	
	@Override
	public void execute(Game game) {
		PlayerCompleteServer player = game.findPlayer(source);
		if (this.cards.size() != 2 || !player.getCardsOnHand().containsAll(this.cards)) {
			return;
		}
		
		try {
			player.useCards(this.cards);
		} catch (InvalidPlayerCommandException e) {
			e.printStackTrace();
			return;
		}
		
		Color color = this.cards.stream().map(card -> card.getColor()).reduce(
			this.cards.iterator().next().getColor(),
			(c1, c2) -> c1 == c2 ? c1 : Color.COLORLESS
		);
		game.<AttackUsableGameController>getGameController().onAttackUsed(new Attack(color));
		game.getGameController().proceed();
	}

}
