package sanguosha2.commands.game.client.sync.disposal;

import sanguosha2.commands.game.client.AbstractGameUIClientCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;

public class SyncDisposalAreaRefreshGameUIClientCommand extends AbstractGameUIClientCommand {
	
	private static final long serialVersionUID = 279006683296367016L;

	@Override
	protected void execute(GamePanel<? extends Hero> panel) {
		panel.getContent().getSelf().clearDisposalArea();
	}

}
