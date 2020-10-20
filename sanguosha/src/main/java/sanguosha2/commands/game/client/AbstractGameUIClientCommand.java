package sanguosha2.commands.game.client;

import sanguosha2.core.client.ClientFrame;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;
import sanguosha2.net.Connection;
import sanguosha2.ui.game.interfaces.ClientGameUI;

public abstract class AbstractGameUIClientCommand implements GameClientCommand<Hero> {

	private static final long serialVersionUID = 6779995165111144810L;

	@Override
	public final void execute(ClientFrame ui, Connection connection) {
		execute((GamePanel<Hero>) ui.<ClientGameUI<Hero>>getPanel());
	}
	
	protected abstract void execute(GamePanel<? extends Hero> panel);

}
