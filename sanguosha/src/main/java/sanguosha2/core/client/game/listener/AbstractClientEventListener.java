package sanguosha2.core.client.game.listener;

import sanguosha2. core.client.game.event.ClientGameEvent;
import sanguosha2. core.heroes.Hero;

public abstract class AbstractClientEventListener<T extends ClientGameEvent> implements ClientEventListener<T, Hero> {
	
	@Override
	public int hashCode() {
		return this.getClass().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getClass().equals(obj.getClass());
	}
}
