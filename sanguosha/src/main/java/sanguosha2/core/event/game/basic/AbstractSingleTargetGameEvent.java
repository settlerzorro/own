package sanguosha2.core.event.game.basic;

import sanguosha2.core.event.game.AbstractGameEvent;
import sanguosha2.core.player.PlayerInfo;

public class AbstractSingleTargetGameEvent extends AbstractGameEvent {

	private PlayerInfo target;
	
	public AbstractSingleTargetGameEvent(PlayerInfo target) {
		this.target = target;
	}
	
	public PlayerInfo getTarget() {
		return this.target;
	}
}
