package sanguosha2.commands.lobby;

import sanguosha2. core.server.RoomInfo;
import sanguosha2.net.Connection;
import sanguosha2. ui.client.LobbyGui;

public class UpdateRoomLobbyUIClientCommand extends LobbyUIClientCommand {

	private static final long serialVersionUID = 3640415584254168416L;

	private final RoomInfo room;
	
	public UpdateRoomLobbyUIClientCommand(RoomInfo room) {
		this.room = room;
	}
	
	@Override
	public void execute(LobbyGui lobby, Connection connection) {
		lobby.updateRoom(room);
	}

}
