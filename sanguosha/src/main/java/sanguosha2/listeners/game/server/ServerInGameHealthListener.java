package sanguosha2.listeners.game.server;

import java.util.Set;

import sanguosha2.commands.game.client.sync.SyncCommandsUtil;
import sanguosha2.commands.game.client.sync.health.SyncDeathGameUIClientCommand;
import sanguosha2.commands.game.client.sync.health.SyncHealthCurrentChangedGameUIClientCommand;
import sanguosha2.commands.game.client.sync.health.SyncHealthCurrentGameUIClientCommand;
import sanguosha2.commands.game.client.sync.health.SyncHealthLimitGameUIClientCommand;
import sanguosha2.core.server.GameRoom;
import sanguosha2.listeners.game.HealthListener;

public class ServerInGameHealthListener extends ServerInGamePlayerListener implements HealthListener {

	public ServerInGameHealthListener(String name, Set<String> allNames, GameRoom room) {
		super(name, allNames, room);
	}
	
	@Override
	public void onSetHealthLimit(int limit) {
		room.sendCommandToPlayers(
			SyncCommandsUtil.generateMapForSameCommand(
				name, 
				otherNames, 
				new SyncHealthLimitGameUIClientCommand(name, limit)
			)
		);
	}

	@Override
	public void onSetHealthCurrent(int current) {
		room.sendCommandToPlayers(
			SyncCommandsUtil.generateMapForSameCommand(
				name, 
				otherNames, 
				new SyncHealthCurrentGameUIClientCommand(name, current)
			)
		);
	}

	@Override
	public void onHealthChangedBy(int amount) {
		room.sendCommandToPlayers(
			SyncCommandsUtil.generateMapForSameCommand(
				name, 
				otherNames, 
				new SyncHealthCurrentChangedGameUIClientCommand(name, amount)
			)
		);		
	}

	@Override
	public void onDeath() {
		room.sendCommandToPlayers(
			SyncCommandsUtil.generateMapForSameCommand(
				name, 
				otherNames, 
				new SyncDeathGameUIClientCommand(name)
			)
		);	
	}

}
