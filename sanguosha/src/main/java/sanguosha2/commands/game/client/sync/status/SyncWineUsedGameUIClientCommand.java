package sanguosha2.commands.game.client.sync.status;

import sanguosha2.commands.game.client.AbstractGameUIClientCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;
import sanguosha2.exceptions.server.game.InvalidPlayerCommandException;

public class SyncWineUsedGameUIClientCommand extends AbstractGameUIClientCommand {

	private static final long serialVersionUID = 604587862780017946L;

	private final String name;
	
	public SyncWineUsedGameUIClientCommand(String name) {
		this.name = name;
	}
	
	@Override
	protected void execute(GamePanel<? extends Hero> panel) {
		if (panel.getContent().getSelf().getName().equals(name)) {
			try {
				panel.getContent().getSelf().useWine();
			} catch (InvalidPlayerCommandException e) {
				e.printStackTrace();
			}
		} else {
			panel.getContent().getOtherPlayerUI(name).setWineUsed(true);
		}
	}

}
