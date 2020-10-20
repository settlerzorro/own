package sanguosha2.commands.game.client.sync.health;

import sanguosha2.commands.game.client.AbstractGameUIClientCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;

public class SyncHealthCurrentChangedGameUIClientCommand extends AbstractGameUIClientCommand {
	
	private static final long serialVersionUID = -9022015799166943772L;

	private final String name;
	private final int amount;
	
	public SyncHealthCurrentChangedGameUIClientCommand(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}

	@Override
	protected void execute(GamePanel<? extends Hero> panel) {
		if (panel.getContent().getSelf().getName().equals(name)) {
			panel.getContent().getSelf().changeHealthCurrentBy(amount);
		} else {
			panel.getContent().getPlayer(name).changeHealthCurrentBy(amount);
		}
	}

}
