package sanguosha2.core.event.game.basic;

import sanguosha2.core.event.game.PlayerReactionGameEvent;
import sanguosha2.core.player.PlayerInfo;

public abstract class AbstractSingleTargetPlayerReactionEvent
	extends AbstractSingleTargetGameEvent
	implements PlayerReactionGameEvent {
	
	private final String message;
	
	public AbstractSingleTargetPlayerReactionEvent(PlayerInfo target, String message) {
		super(target);
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

}
