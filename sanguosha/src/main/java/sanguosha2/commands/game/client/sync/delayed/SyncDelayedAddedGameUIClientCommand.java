package sanguosha2.commands.game.client.sync.delayed;

import sanguosha2.cards.Card;
import sanguosha2.commands.game.client.AbstractGameUIClientCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;
import sanguosha2.utils.DelayedType;

public class SyncDelayedAddedGameUIClientCommand extends AbstractGameUIClientCommand {
	
	private static final long serialVersionUID = 1L;
	
	private final String name;
	private final Card card;
	private final DelayedType type;
	private final boolean add;
	
	public SyncDelayedAddedGameUIClientCommand(String name, Card card, DelayedType type, boolean add) {
		this.name = name;
		this.card = card;
		this.type = type;
		this.add = add;
	}

	@Override
	protected void execute(GamePanel<? extends Hero> panel) {
		if (panel.getContent().getSelf().getName().equals(this.name)) {
			if (this.add) {
				panel.getContent().getSelf().pushDelayed(this.card, this.type);
			} else {
				panel.getContent().getSelf().removeDelayed(this.type);
			}
		} else {
			if (this.add) {
				panel.getContent().getPlayer(this.name).pushDelayed(this.card, this.type);
			} else {
				panel.getContent().getPlayer(this.name).removeDelayed(this.type);
			}
		}
	}

}
