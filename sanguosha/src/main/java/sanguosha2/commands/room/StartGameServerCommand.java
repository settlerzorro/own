package sanguosha2.commands.room;

import sanguosha2. core.server.Room;
import sanguosha2.net.Connection;

public class StartGameServerCommand implements RoomServerCommand {
	private static final long serialVersionUID = -1646855974120788771L;

	@Override
	public void execute(Room room, Connection connection) {
		room.startGame();
	}

}
