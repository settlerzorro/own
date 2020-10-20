package sanguosha2.core.server;

import java.util.Map;

import sanguosha2.commands.game.client.GameClientCommand;
import sanguosha2.core.heroes.Hero;

public interface ConnectionController {
	
	public void sendCommandToAllPlayers(GameClientCommand<? extends Hero> command);
	
	public void sendCommandToPlayers(Map<String, GameClientCommand<? extends Hero>> commands);
	
	public void sendCommandToPlayer(String name, GameClientCommand<? extends Hero> command);
}
