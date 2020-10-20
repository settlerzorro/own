package sanguosha2.core.event.game.basic;

import sanguosha2.core.player.PlayerInfo;

public class RequestNeutralizationEvent extends AbstractSingleTargetPlayerReactionEvent {

	public RequestNeutralizationEvent(PlayerInfo target, String message) {
		super(target, message);
	}

}
