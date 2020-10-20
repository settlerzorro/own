package sanguosha2.commands.game.client;

import java.util.List;

import sanguosha2.core.client.ClientFrame;
import sanguosha2.core.client.GamePanelOriginal;
import sanguosha2.core.heroes.Hero;
import sanguosha2.core.player.PlayerInfo;
import sanguosha2.net.Connection;

public class EnterOriginalGameRoomGameClientCommand implements GameClientCommand<Hero> {
	
	private static final long serialVersionUID = 1094417892948875381L;
	
	private final List<PlayerInfo> players;
	private final PlayerInfo self;
	
	public EnterOriginalGameRoomGameClientCommand(List<PlayerInfo> players, PlayerInfo self) {
		this.players = players;
		this.self = self;
	}
	@Override
	public void execute(ClientFrame ui, Connection connection) {
		ui.onNewPanelDisplayed(new GamePanelOriginal(self, players, connection));
	}

}
