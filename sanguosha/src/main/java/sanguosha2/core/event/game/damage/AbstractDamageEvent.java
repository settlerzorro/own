package sanguosha2.core.event.game.damage;

import sanguosha2.core.event.game.AbstractGameEvent;
import sanguosha2.core.server.game.Damage;

public abstract class AbstractDamageEvent extends AbstractGameEvent {

	private final Damage damage;
	
	public AbstractDamageEvent(Damage damage) {
		this.damage = damage;
	}
	
	public Damage getDamage() {
		return this.damage;
	}
}
