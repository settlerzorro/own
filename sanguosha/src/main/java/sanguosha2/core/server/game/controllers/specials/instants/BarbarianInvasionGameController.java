package sanguosha2.core.server.game.controllers.specials.instants;

import sanguosha2.cards.Card;
import sanguosha2.core.event.game.basic.RequestAttackEvent;
import sanguosha2.core.event.game.instants.AOETargetEffectivenessEvent;
import sanguosha2.core.event.game.instants.BarbarianInvasionTargetEffectivenessEvent;
import sanguosha2.core.player.PlayerInfo;
import sanguosha2.core.server.game.Damage;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.DamageGameController;
import sanguosha2.core.server.game.controllers.interfaces.AttackUsableGameController;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class BarbarianInvasionGameController extends AOEInstantSpecialGameController implements AttackUsableGameController {
	
	private boolean effective;
	private boolean hasReacted;

	public BarbarianInvasionGameController(PlayerInfo source, Game game) {
		super(source, game, false);
		this.effective = true;
		this.hasReacted = false;
	}

	@Override
	protected void takeEffect() {
		if (!this.hasReacted) {
			try {
				this.game.emit(new RequestAttackEvent(
					this.currentTarget.getPlayerInfo(),
					this.source + " used Barbarian Invasion on you, use Attack to counter?"
				));
			} catch (GameFlowInterruptedException e) {
				e.resume();
			}
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
		return this.source + " used Barbarian Invasion on " + this.currentTarget + ", use Neutralization?";
	}

	@Override
	protected AOETargetEffectivenessEvent getTargetEffectivenessEvent() {
		return new BarbarianInvasionTargetEffectivenessEvent(this.currentTarget, this);
	}

	@Override
	public void onAttackUsed(Card card) {
		// mark it not effective for the current target
		this.hasReacted = true;
		this.effective = false;
	}

	@Override
	public void onAttackNotUsed() {
		this.hasReacted = true;
	}

}
