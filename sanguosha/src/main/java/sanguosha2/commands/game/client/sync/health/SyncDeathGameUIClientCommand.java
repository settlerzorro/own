package sanguosha2.commands.game.client.sync.health;

import sanguosha2.commands.game.client.AbstractGameUIClientCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;

public class SyncDeathGameUIClientCommand extends AbstractGameUIClientCommand {

	private static final long serialVersionUID = -5617576331867784827L;

	private final String name;
	
	public SyncDeathGameUIClientCommand(String name) {
		this.name = name;
	}
	
	@Override
	protected void execute(GamePanel<? extends Hero> panel) {
		if (panel.getContent().getSelf().getName().equals(name)) {
			panel.getContent().getSelf().kill();
		} else {
			panel.getContent().getPlayer(name).kill();
		}
	}

}