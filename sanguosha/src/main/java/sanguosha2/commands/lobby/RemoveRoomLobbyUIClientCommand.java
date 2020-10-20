package sanguosha2.commands.lobby;

import sanguosha2. core.server.RoomInfo;
import sanguosha2.net.Connection;
import sanguosha2. ui.client.LobbyGui;

public class RemoveRoomLobbyUIClientCommand extends LobbyUIClientCommand {

	private static final long serialVersionUID = -1553637366676111161L;

	private final RoomInfo room;
	
	public RemoveRoomLobbyUIClientCommand(RoomInfo room) {
		this.room = room;
	}
	@Override
	public void execute(LobbyGui lobby, Connection connection) {
		lobby.removeRoom(room);
	}

}
