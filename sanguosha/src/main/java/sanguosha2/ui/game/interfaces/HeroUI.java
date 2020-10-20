package sanguosha2.ui.game.interfaces;

import sanguosha2.core.heroes.Hero;
import sanguosha2.core.player.PlayerCompleteClient;
import sanguosha2.listeners.game.PlayerStatusListener;

public interface HeroUI<T extends Hero> extends PlayerUI, PlayerStatusListener {

	public T getHero();

	public void setPlayer(PlayerCompleteClient player);

}
