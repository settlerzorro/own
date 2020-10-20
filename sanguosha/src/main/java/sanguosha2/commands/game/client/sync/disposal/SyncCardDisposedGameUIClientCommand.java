package sanguosha2.commands.game.client.sync.disposal;

import sanguosha2.cards.Card;
import sanguosha2.commands.game.client.AbstractGameUIClientCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;

public class SyncCardDisposedGameUIClientCommand extends AbstractGameUIClientCommand {
	
	private static final long serialVersionUID = 8495650738265955748L;

	private final String name;
	private final Card card;
	
	public SyncCardDisposedGameUIClientCommand(String name, Card card) {
		this.name = name;
		this.card = card;
	}

	@Override
	protected void execute(GamePanel<? extends Hero> panel) {
		if (panel.getContent().getSelf().getName().equals(name)) {
			panel.getContent().getSelf().getDisposalListener().onCardDisposed(card);
		} else {
			panel.getContent().getPlayer(name).getDisposalListener().onCardDisposed(card);
		}
	}

}
