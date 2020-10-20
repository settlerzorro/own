package sanguosha2.commands.welcome;

import sanguosha2. net.Connection;
import sanguosha2. ui.client.WelcomeSessionGui;

import sanguosha2. commands.Command;
import sanguosha2. core.client.ClientFrame;

public class WelcomeSessionDisplayClientCommand implements Command<ClientFrame> {
	private static final long serialVersionUID = 4057080358974917775L;

	@Override
	public void execute(ClientFrame ui, Connection connection) {
		ui.onNewPanelDisplayed(new WelcomeSessionGui(connection));
	}

}
