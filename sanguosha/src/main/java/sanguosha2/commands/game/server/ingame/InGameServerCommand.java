package sanguosha2.commands.game.server.ingame;

import sanguosha2. commands.game.server.GameServerCommand;
import sanguosha2. core.server.GameRoom;
import sanguosha2. core.server.game.Game;
import sanguosha2.net.Connection;

public abstract class InGameServerCommand implements GameServerCommand {

	private static final long serialVersionUID = 4744490907869746041L;

	@Override
	public final void execute(GameRoom room, Connection connection) {
		this.execute(room.getGame());
	}
	
	public abstract void execute(Game game);
}
