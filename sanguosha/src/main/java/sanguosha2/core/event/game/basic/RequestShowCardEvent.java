package sanguosha2.core.event.game.basic;

import sanguosha2.core.player.PlayerInfo;

public class RequestShowCardEvent extends AbstractSingleTargetPlayerReactionEvent {

	public RequestShowCardEvent(PlayerInfo target, String message) {
		super(target, message);
	}

}
