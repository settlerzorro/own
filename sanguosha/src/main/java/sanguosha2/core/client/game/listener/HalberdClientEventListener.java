package sanguosha2.core.client.game.listener;

import sanguosha2.core.client.GamePanel;
import sanguosha2.core.client.game.event.InitiateAttackClientGameEvent;
import sanguosha2.core.heroes.Hero;

public class HalberdClientEventListener extends AbstractStatelessClientEventListener<InitiateAttackClientGameEvent> {

	@Override
	public Class<InitiateAttackClientGameEvent> getEventClass() {
		return InitiateAttackClientGameEvent.class;
	}

	@Override
	public void handle(InitiateAttackClientGameEvent event, GamePanel<Hero> panel) {
		if (panel.getContent().getSelf().getHandCount() == 1) {
			event.operation.addMaxTargets(2);
		}
	}

}
