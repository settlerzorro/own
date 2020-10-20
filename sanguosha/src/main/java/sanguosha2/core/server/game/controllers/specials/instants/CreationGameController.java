package sanguosha2.core.server.game.controllers.specials.instants;

import sanguosha2.core.player.PlayerInfo;
import sanguosha2.core.server.game.Game;

public class CreationGameController extends SingleTargetInstantSpecialGameController {

	public CreationGameController(PlayerInfo source, Game game) {
		super(source, source, game);
	}

	@Override
	protected void takeEffect() {
		this.game.drawCards(this.source, 2);
		this.stage = this.stage.nextStage();
		this.proceed();
	}
	
	@Override
	protected String getNeutralizationMessage() {
		return this.source + " used Creation, use Neutralization?";
	}

}
