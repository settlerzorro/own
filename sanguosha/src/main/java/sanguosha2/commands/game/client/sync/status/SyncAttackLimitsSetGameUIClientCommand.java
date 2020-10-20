package sanguosha2.commands.game.client.sync.status;

import sanguosha2.commands.game.client.AbstractGameUIClientCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;
import sanguosha2.exceptions.server.game.InvalidPlayerCommandException;

public class SyncAttackLimitsSetGameUIClientCommand extends AbstractGameUIClientCommand {
	
	private static final long serialVersionUID = 111665161579659324L;

	private final int limit;
	
	public SyncAttackLimitsSetGameUIClientCommand(int limit) {
		this.limit = limit;
	}

	@Override
	protected void execute(GamePanel<? extends Hero> panel) {
		try {
			panel.getContent().getSelf().setAttackLimit(limit);
		} catch (InvalidPlayerCommandException e) {
			e.printStackTrace();
		}
	}

}
