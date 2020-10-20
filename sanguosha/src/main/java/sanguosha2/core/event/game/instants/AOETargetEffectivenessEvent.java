package sanguosha2.core.event.game.instants;

import sanguosha2.core.event.game.GameEvent;
import sanguosha2.core.player.PlayerCompleteServer;

public interface AOETargetEffectivenessEvent extends GameEvent {

	public PlayerCompleteServer getCurrentTarget();
}
