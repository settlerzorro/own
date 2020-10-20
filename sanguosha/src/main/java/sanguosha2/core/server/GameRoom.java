package sanguosha2.core.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sanguosha2.commands.game.client.GameClientCommand;
import sanguosha2.core.heroes.Hero;
import sanguosha2.core.player.PlayerInfo;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.GameConfig;
import sanguosha2.core.server.game.GameImpl;
import sanguosha2.net.Connection;
import sanguosha2.net.server.ServerEntity;

public class GameRoom extends ServerEntity implements ConnectionController {
	
	private final Room room;
	private final Game game;
	private Map<String, Connection> connectionMap;
	
	public GameRoom(Room room, Set<Connection> connections, GameConfig config) {
		this.connections.addAll(connections);
		this.room = room;
		this.connectionMap = new HashMap<>();
		
		// TODO: Fix this when we have actual player info
		// begin ugly part because connection doesn't have unique user information yet
		int i = 0;
		List<PlayerInfo> players = new ArrayList<>();
		for (Connection connection : this.connections) {
			this.connectionMap.put("Player " + i, connection);
			players.add(new PlayerInfo("Player " + i, i));
			i++;
		}
		// end ugly part
		this.game = new GameImpl(this, config, players);

	}
	
	public Game getGame() {
		return game;
	}
	
	@Override
	public synchronized void sendCommandToAllPlayers(GameClientCommand<? extends Hero> command) {
		this.connectionMap.forEach((name, connection) -> connection.send(command));
	}
	
	@Override
	public synchronized void sendCommandToPlayers(Map<String, GameClientCommand<? extends Hero>> commands) {
		commands.forEach((name, command) -> this.connectionMap.get(name).send(command));
	}
	
	@Override
	public synchronized void sendCommandToPlayer(String name, GameClientCommand<? extends Hero> command) {
		this.connectionMap.get(name).send(command);
	}
	
	@Override
	public void onConnectionLost(Connection connection, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onReceivedConnection(Connection connection) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onConnectionLeft(Connection connection) {
		// TODO Auto-generated method stub
		
	}

}
