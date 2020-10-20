package sanguosha2.commands.game.client.sync.health;

import sanguosha2.commands.game.client.AbstractGameUIClientCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;

public class SyncHealthLimitGameUIClientCommand extends AbstractGameUIClientCommand {
	
	private static final long serialVersionUID = -6642017853595462196L;

	private final String name;
	private final int limit;
	
	public SyncHealthLimitGameUIClientCommand(String name, int limit) {
		this.name = name;
		this.limit = limit;
	}

	@Override
	protected void execute(GamePanel<? extends Hero> panel) {
		if (panel.getContent().getSelf().getName().equals(name)) {
			panel.getContent().getSelf().changeHealthLimitTo(limit);
		} else {
			panel.getContent().getPlayer(name).changeHealthLimitTo(limit);
		}
	}

}
