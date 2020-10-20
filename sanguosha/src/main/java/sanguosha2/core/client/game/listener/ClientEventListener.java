package sanguosha2.core.client.game.listener;

import sanguosha2.core.client.GamePanel;
import sanguosha2.core.client.game.event.ClientGameEvent;
import sanguosha2.core.heroes.Hero;

public interface ClientEventListener<E extends ClientGameEvent, H extends Hero> {
	
	public Class<E> getEventClass();

	public void handle(E event, GamePanel<H> panel);
	
	public void onDeactivated(GamePanel<H> panel);

}
