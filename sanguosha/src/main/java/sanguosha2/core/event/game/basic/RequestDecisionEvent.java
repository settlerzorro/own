package sanguosha2.core.event.game.basic;

import sanguosha2.core.player.PlayerInfo;

public class RequestDecisionEvent extends AbstractSingleTargetPlayerReactionEvent {

	public RequestDecisionEvent(PlayerInfo target, String message) {
		super(target, message);
	}

}
