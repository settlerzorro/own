package sanguosha2.commands.room;

import java.util.List;

import sanguosha2. commands.Command;
import sanguosha2. core.client.ClientFrame;
import sanguosha2. core.server.RoomInfo;
import sanguosha2.net.Connection;
import sanguosha2.net.UserInfo;
import sanguosha2. ui.client.RoomGui;

public class DisplayRoomUIClientCommand implements Command<ClientFrame> {
	private static final long serialVersionUID = -5995153324243633984L;
	private final RoomInfo room;
	private final List<UserInfo> userInfos;
	
	public DisplayRoomUIClientCommand(RoomInfo room, List<UserInfo> userInfos) {
		this.room = room;
		this.userInfos = userInfos;
	}
	
	@Override
	public void execute(ClientFrame ui, Connection connection) {
		ui.onNewPanelDisplayed(new RoomGui(room, connection));
		RoomGui room = ui.<RoomGui>getPanel().getContent();
		room.updatePlayers(userInfos);
	}

}
