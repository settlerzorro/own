package sanguosha2.core.event.game.basic;

import sanguosha2.core.player.PlayerInfo;

public class RequestAttackEvent extends AbstractSingleTargetPlayerReactionEvent {
	
	public RequestAttackEvent(PlayerInfo targetInfo, String message) {
		super(targetInfo, message);
	}

}
