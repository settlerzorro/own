package sanguosha2.core.event.game.basic;

import sanguosha2.core.player.PlayerInfo;

public class RequestDodgeEvent extends AbstractSingleTargetPlayerReactionEvent {

	public RequestDodgeEvent(PlayerInfo targetInfo, String message) {
		super(targetInfo, message);
	}

}
