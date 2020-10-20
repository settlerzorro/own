package sanguosha2.core;

import java.util.List;

import sanguosha2.core.player.PlayerCompleteClient;
import sanguosha2.core.player.PlayerSimple;

public interface GameState {

	public List<PlayerSimple> getOtherPlayers();

	public PlayerSimple getPlayer(String name);

	public PlayerCompleteClient getSelf();

	public int getNumberOfPlayers();
	
	public int getNumberOfPlayersAlive();

}
