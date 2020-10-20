package sanguosha2.commands.room;

import java.util.List;

import sanguosha2. commands.Command;
import sanguosha2. core.client.ClientFrame;
import sanguosha2.net.Connection;
import sanguosha2.net.UserInfo;
import sanguosha2. ui.client.RoomGui;

public class UpdateRoomUIClientCommand implements Command<ClientFrame> {

	private static final long serialVersionUID = 706856426348903599L;
	private final List<UserInfo> userInfos;
	
	public UpdateRoomUIClientCommand(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}
	
	@Override
	public void execute(ClientFrame ui, Connection connection) {
		RoomGui room = ui.<RoomGui>getPanel().getContent();
		room.updatePlayers(userInfos);
	}

}
