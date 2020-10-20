package sanguosha2.commands.welcome;

import sanguosha2. core.server.WelcomeSession;
import sanguosha2.net.Connection;

public class EnterLobbyServerCommand implements WelcomeSessionServerCommand {
	private static final long serialVersionUID = 3018744020722565195L;

	@Override
	public void execute(WelcomeSession session, Connection connection) {
		session.enterLobby(connection);
	}
}
