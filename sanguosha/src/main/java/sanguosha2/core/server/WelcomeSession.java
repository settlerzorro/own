package sanguosha2.core.server;

import sanguosha2.net.Connection;
import sanguosha2.net.server.ServerEntity;
import sanguosha2.utils.Log;

import sanguosha2.commands.welcome.WelcomeSessionDisplayClientCommand;
import sanguosha2.commands.welcome.WelcomeSessionExitClientCommand;

/**
 * This class represents the welcome session (before user login)
 * 
 * @author Harry
 *
 */
public class WelcomeSession extends ServerEntity {
	private static final String TAG = "WelcomeSession";
	private final ServerEntity lobby;

	public WelcomeSession() {
		this.lobby = new Lobby(this);
	}

	@Override
	public boolean onReceivedConnection(Connection connection) {
		connections.add(connection);
		connection.setConnectionListener(this);
		connection.activate();
		connection.send(new WelcomeSessionDisplayClientCommand());
		return true;
	}
	
	@Override
	public void onConnectionLeft(Connection connection) {
		synchronized (connection.accessLock) {
			if (!connections.contains(connection)) {
				return;
			}
			connection.send(new WelcomeSessionExitClientCommand());
			connections.remove(connection);
		}
	}

	public void enterLobby(Connection connection) {
		synchronized(connection.accessLock) {
			if (connections.contains(connection)) {
				if(lobby.onReceivedConnection(connection))
					connections.remove(connection);
			}
			else
				Log.error(TAG, "Connection does not exist...");
		}
	}

	@Override
	public void onConnectionLost(Connection connection, String message) {
		Log.error(TAG, "Client connection lost. " + message);
		connections.remove(connection);
	}
}
