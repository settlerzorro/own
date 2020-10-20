package sanguosha2.commands.game.client.sync.cardonhand;

import sanguosha2.cards.Card;
import sanguosha2.commands.game.client.AbstractGameUIClientCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;
import sanguosha2.core.player.PlayerCompleteClient;
import sanguosha2.exceptions.server.game.InvalidPlayerCommandException;

public class SyncPlayerCardGameUIClientCommand extends AbstractGameUIClientCommand {
	private static final long serialVersionUID = 5370641268667157302L;

	private final Card card;
	private final boolean add;
	
	public SyncPlayerCardGameUIClientCommand(Card card, boolean add) {
		this.card = card;
		this.add = add;
	}

	@Override
	public void execute(GamePanel<? extends Hero> panel) {
		PlayerCompleteClient player = panel.getContent().getSelf();
		if (add) {
			player.addCard(card);
		} else {
			try {
				player.removeCardFromHand(card);
			} catch (InvalidPlayerCommandException e) {
				e.printStackTrace();
			}
		}
	}

}
