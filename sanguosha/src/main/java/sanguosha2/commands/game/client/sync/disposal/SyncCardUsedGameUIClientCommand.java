package sanguosha2.commands.game.client.sync.disposal;

import sanguosha2.cards.Card;
import sanguosha2.commands.game.client.AbstractGameUIClientCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;

public class SyncCardUsedGameUIClientCommand extends AbstractGameUIClientCommand {
	
	private static final long serialVersionUID = -3052922194486998505L;

	private final String name;
	private final Card card;
	
	public SyncCardUsedGameUIClientCommand(String name, Card card) {
		this.name = name;
		this.card = card;
	}

	@Override
	protected void execute(GamePanel<? extends Hero> panel) {
		if (panel.getContent().getSelf().getName().equals(name)) {
			panel.getContent().getSelf().getDisposalListener().onCardUsed(card);
		} else {
			panel.getContent().getPlayer(name).getDisposalListener().onCardUsed(card);
		}
	}

}
