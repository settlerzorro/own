package sanguosha2.core.server.game.controllers.specials.instants;

import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.player.PlayerInfo;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.specials.AbstractSpecialGameController;

public abstract class AbstractInstantSpecialGameController extends AbstractSpecialGameController {

	protected SpecialStage stage;
	protected PlayerCompleteServer source;
	
	public AbstractInstantSpecialGameController(PlayerInfo source, Game game) {
		super(game);
		this.stage = SpecialStage.TARGET_LOCKED;
		this.source = game.findPlayer(source);
	}
	
	@Override
	public void onNeutralized() {
		this.neutralized = !this.neutralized;
		this.neutralizedCount = 0;
	}
	
	@Override
	public void onNeutralizationCanceled() {
		this.neutralizedCount++;
	}
	
}
