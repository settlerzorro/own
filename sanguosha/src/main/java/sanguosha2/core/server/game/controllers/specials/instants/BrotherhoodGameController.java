package sanguosha2.core.server.game.controllers.specials.instants;

import sanguosha2.core.event.game.instants.AOETargetEffectivenessEvent;
import sanguosha2.core.event.game.instants.BrotherhoodTargetEffectivenessEvent;
import sanguosha2.core.player.PlayerInfo;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.HealGameController;

public class BrotherhoodGameController extends AOEInstantSpecialGameController {

	public BrotherhoodGameController(PlayerInfo source, Game game) {
		super(source, game, true);
	}

	@Override
	protected void takeEffect() {
		this.stage = this.stage.nextStage();
		if (this.currentTarget.isDamaged()) {
			this.game.pushGameController(new HealGameController(this.source.getPlayerInfo(), this.currentTarget.getPlayerInfo(), this.game));
		}
		this.game.getGameController().proceed();
	}
	
	@Override
	protected String getNeutralizationMessage() {
		return this.source + " used Brotherhood on " + this.currentTarget + ", use Neutralization?";
	}
	
	@Override
	protected boolean canBeNeutralized() {
		// only consider players that is not at full health
		return this.currentTarget.isDamaged();
	}

	@Override
	protected AOETargetEffectivenessEvent getTargetEffectivenessEvent() {
		return new BrotherhoodTargetEffectivenessEvent(this.currentTarget, this);
	}

}
