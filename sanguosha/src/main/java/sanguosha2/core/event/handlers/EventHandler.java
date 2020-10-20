package sanguosha2.core.event.handlers;

import sanguosha2.core.event.Event;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public interface EventHandler<T extends Event> {
	
	public void handle(T event, Game game, ConnectionController connection) throws GameFlowInterruptedException;
	
	public Class<T> getEventClass();
	
	public PlayerCompleteServer getPlayerSource();
	
	public void deactivate(/* reactivate callback? */);
}
