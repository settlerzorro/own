package sanguosha2.commands.lobby;

import sanguosha2. core.server.Lobby;
import sanguosha2. core.server.RoomConfig;
import sanguosha2. core.server.game.GameConfig;
import sanguosha2.net.Connection;

/**
 * This Command attempts to create a new room 
 * in the {@linkplain Lobby} for the player
 * 
 * @author Harry
 *
 */
public class CreateRoomLobbyServerCommand implements LobbyServerCommand {
	private static final long serialVersionUID = -6076120031259035335L;
	private final GameConfig game;
	private final RoomConfig room;
	
	/**
	 * Create a room with {@linkplain Lobby#addRoom(GameConfig, RoomConfig)}
	 * 
	 * @param gameConfig : game configuration, nullable
	 * @param roomConfig : room configuration, nullable
	 * 
	 * @see GameConfig#GameConfig()
	 * @see RoomConfig#RoomConfig()
	 */
	public CreateRoomLobbyServerCommand(GameConfig gameConfig, RoomConfig roomConfig) {
		this.game = gameConfig;
		this.room = roomConfig;
	}
	
	@Override
	public void execute(Lobby lobby, Connection connection) {
		lobby.addRoom(game, room, connection);
	}

}
