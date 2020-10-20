package sanguosha2.commands.game.client.sync.status;

import sanguosha2.commands.game.client.AbstractGameUIClientCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;

public class SyncResetWineEffectiveGameUIClientCommand extends AbstractGameUIClientCommand {

	private static final long serialVersionUID = 373001483127652341L;

	private final String name;
	
	public SyncResetWineEffectiveGameUIClientCommand(String name) {
		this.name = name;
	}
	
	@Override
	protected void execute(GamePanel<? extends Hero> panel) {
		if (panel.getContent().getSelf().getName().equals(name)) {
			panel.getContent().getSelf().resetWineEffective();
		} else {
			panel.getContent().getOtherPlayerUI(name).setWineUsed(false);
		}
	}

}
