package sanguosha2.core.event.game;

import sanguosha2.core.event.game.basic.AbstractSingleTargetGameEvent;
import sanguosha2.core.player.PlayerInfo;

public class DodgeArbitrationEvent extends AbstractSingleTargetGameEvent {

	public DodgeArbitrationEvent(PlayerInfo target) {
		super(target);
	}

}
