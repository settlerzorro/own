package sanguosha2.core.client.game.listener;

import sanguosha2.core.client.GamePanel;
import sanguosha2.core.client.game.event.ClientGameEvent;
import sanguosha2.core.heroes.Hero;

public abstract class AbstractStatelessClientEventListener<T extends ClientGameEvent> extends AbstractClientEventListener<T> {

	@Override
	public void onDeactivated(GamePanel<Hero> panel) {}

}
