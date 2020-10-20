package sanguosha2.core.event.game.damage;

import sanguosha2.core.server.game.Damage;

public class AttackDamageModifierEvent extends AbstractDamageEvent {
	
	public AttackDamageModifierEvent(Damage damage) {
		super(damage);
	}

}
