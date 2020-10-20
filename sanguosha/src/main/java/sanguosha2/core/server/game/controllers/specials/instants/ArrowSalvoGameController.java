package sanguosha2.core.server.game.controllers.specials.instants;

import sanguosha2.core.event.game.instants.AOETargetEffectivenessEvent;
import sanguosha2.core.event.game.instants.ArrowSalvoTargetEffectivenessEvent;
import sanguosha2.core.player.PlayerInfo;
import sanguosha2.core.server.game.Damage;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.DamageGameController;
import sanguosha2.core.server.game.controllers.DodgeGameController;
import sanguosha2.core.server.game.controllers.interfaces.DodgeUsableGameController;

public class ArrowSalvoGameController extends AOEInstantSpecialGameController implements DodgeUsableGameController {

	private boolean effective;
	private boolean hasReacted;

	public ArrowSalvoGameController(PlayerInfo source, Game game) {
		super(source, game, false);
		this.effective = true;
		this.hasReacted = false;
	}

	@Override
	protected void takeEffect() {
		if (!this.hasReacted) {
			this.game.pushGameController(new DodgeGameController(
				this.game,
				this.currentTarget,
				this.source + " used Arrow Salvo on you, use Dodge to counter?"
			));
			this.game.getGameController().proceed();
		} else {
			this.stage = this.stage.nextStage();
			this.hasReacted = false;
			if (this.effective) {
				// if effective, deal damage
				this.effective = true;
				this.game.pushGameController(new DamageGameController(new Damage(this.source, this.currentTarget), this.game));
				this.game.getGameController().proceed();
			} else {
				// otherwise proceed to next
				this.effective = true;
				this.proceed();
			}
		}
	}
	
	@Override
	protected String getNeutralizationMessage() {
		return this.source + " used Arrow Salvo on " + this.currentTarget + ", use Neutralization?";
	}
	
	@Override
	protected AOETargetEffectivenessEvent getTargetEffectivenessEvent() {
		return new ArrowSalvoTargetEffectivenessEvent(this.currentTarget, this);
	}

	@Override
	public void onDodged() {
		// mark it not effective for the current target
		this.hasReacted = true;
		this.effective = false;
		this.proceed();
	}

	@Override
	public void onNotDodged() {
		this.hasReacted = true;
		this.proceed();
	}

}
