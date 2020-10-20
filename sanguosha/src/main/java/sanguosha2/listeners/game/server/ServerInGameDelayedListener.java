package sanguosha2.listeners.game.server;

import java.util.Set;

import sanguosha2.cards.Card;
import sanguosha2.commands.game.client.sync.delayed.SyncDelayedAddedGameUIClientCommand;
import sanguosha2.core.server.GameRoom;
import sanguosha2.listeners.game.DelayedListener;
import sanguosha2.utils.DelayedType;

public class ServerInGameDelayedListener extends ServerInGamePlayerListener implements DelayedListener {

	public ServerInGameDelayedListener(String name, Set<String> allNames, GameRoom room) {
		super(name, allNames, room);
	}

	@Override
	public void onDelayedAdded(Card card, DelayedType type) {
		this.room.sendCommandToAllPlayers(new SyncDelayedAddedGameUIClientCommand(this.name, card, type, true));
	}

	@Override
	public void onDelayedRemove(DelayedType type) {
		this.room.sendCommandToAllPlayers(new SyncDelayedAddedGameUIClientCommand(this.name, null, type, false));
	}

}
