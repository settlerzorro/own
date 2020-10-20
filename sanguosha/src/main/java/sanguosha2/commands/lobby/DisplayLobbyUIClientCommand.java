package sanguosha2.commands.lobby;

import java.util.List;

import sanguosha2.net.Connection;
import sanguosha2. ui.client.LobbyGui;

import sanguosha2. commands.Command;
import sanguosha2. core.client.ClientFrame;
import sanguosha2. core.server.RoomInfo;

/**
 * This Command draws the lobby GUI on client's screen
 * @author Harry
 *
 */
public class DisplayLobbyUIClientCommand implements Command<ClientFrame> {
	private static final long serialVersionUID = 2969055754383503593L;
	private final List<RoomInfo> rooms;
	
	/**
	 * Create lobby's GUI with rooms
	 * @param rooms : rooms currently in the lobby
	 */
	public DisplayLobbyUIClientCommand(List<RoomInfo> rooms) {
		this.rooms = rooms;
	}
	
	@Override
	public void execute(ClientFrame ui, Connection connection) {
		ui.onNewPanelDisplayed(new LobbyGui(rooms, connection));
	}
}