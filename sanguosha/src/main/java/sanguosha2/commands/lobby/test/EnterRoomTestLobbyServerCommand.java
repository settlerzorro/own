package sanguosha2.commands.lobby.test;

import sanguosha2.net.Connection;
import sanguosha2. commands.lobby.LobbyServerCommand;
import sanguosha2. core.server.Lobby;

public class EnterRoomTestLobbyServerCommand implements LobbyServerCommand {

	private static final long serialVersionUID = -1261682255723435476L;

	@Override
	public void execute(Lobby lobby, Connection connection) {
		if (lobby.hasRoom()) {
			lobby.proceedToRoom(0, connection); // super hack
		} else {
			lobby.addRoom(null, null, connection);
		}
	}

}
