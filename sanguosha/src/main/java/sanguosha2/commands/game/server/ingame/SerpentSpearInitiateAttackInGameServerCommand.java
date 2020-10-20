package sanguosha2.commands.game.server.ingame;

import java.util.Set;
import java.util.stream.Collectors;

import sanguosha2. cards.Card;
import sanguosha2. cards.Card.Color;
import sanguosha2. cards.basics.Attack;
import sanguosha2. core.player.PlayerCompleteServer;
import sanguosha2. core.player.PlayerInfo;
import sanguosha2. core.server.game.Game;
import sanguosha2. core.server.game.controllers.AttackGameController;
import sanguosha2. exceptions.server.game.InvalidPlayerCommandException;

public class SerpentSpearInitiateAttackInGameServerCommand extends InGameServerCommand {

	private static final long serialVersionUID = 1L;

	private final PlayerInfo source;
	private final Set<PlayerInfo> targets;
	private final Set<Card> cards;
	
	public SerpentSpearInitiateAttackInGameServerCommand(PlayerInfo source, Set<PlayerInfo> targets, Set<Card> cards) {
		this.source = source;
		this.targets = targets;
		this.cards = cards;
	}
	
	@Override
	public void execute(Game game) {
		PlayerCompleteServer player = game.findPlayer(source);
		Set<PlayerCompleteServer> targets = this.targets.stream().map(target -> game.findPlayer(target)).collect(Collectors.toSet());

		if (player.getAttackUsed() > 0 || this.cards.size() != 2 || !player.getCardsOnHand().containsAll(this.cards)) {
			return;
		}
		
		try {
			player.useAttack(targets);
			player.useCards(this.cards);
		} catch (InvalidPlayerCommandException e) {
			e.printStackTrace();
			return;
		}
		
		Color color = this.cards.stream().map(card -> card.getColor()).reduce(
			this.cards.iterator().next().getColor(),
			(c1, c2) -> c1 == c2 ? c1 : Color.COLORLESS
		);
		game.pushGameController(new AttackGameController(player, targets, new Attack(color), game));
		game.getGameController().proceed();
	}

}
