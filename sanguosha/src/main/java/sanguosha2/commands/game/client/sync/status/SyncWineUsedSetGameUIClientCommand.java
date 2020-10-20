package sanguosha2.commands.game.client.sync.status;

import sanguosha2.commands.game.client.AbstractGameUIClientCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;
import sanguosha2.exceptions.server.game.InvalidPlayerCommandException;

public class SyncWineUsedSetGameUIClientCommand extends AbstractGameUIClientCommand {

	private static final long serialVersionUID = -1579729852291363280L;

	private final int amount;
	
	public SyncWineUsedSetGameUIClientCommand(int amount) {
		this.amount = amount;
	}
	
	@Override
	protected void execute(GamePanel<? extends Hero> panel) {
		try {
			panel.getContent().getSelf().setWineUsed(amount);
		} catch (InvalidPlayerCommandException e) {
			e.printStackTrace();
		}
	}

}
