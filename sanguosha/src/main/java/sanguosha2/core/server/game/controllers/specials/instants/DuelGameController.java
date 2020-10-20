package sanguosha2.core.server.game.controllers.specials.instants;

import sanguosha2.cards.Card;
import sanguosha2.core.event.game.basic.RequestAttackEvent;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.player.PlayerInfo;
import sanguosha2.core.server.game.Damage;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.DamageGameController;
import sanguosha2.core.server.game.controllers.interfaces.AttackUsableGameController;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class DuelGameController extends SingleTargetInstantSpecialGameController implements AttackUsableGameController {
	
	private PlayerCompleteServer currentAttackUser;
	
	public DuelGameController(PlayerInfo source, PlayerInfo target, Game game) {
		super(source, target, game);
		this.currentAttackUser = this.target;
	}

	@Override
	protected void takeEffect() {
		try {
			// Ask current attack user to use Attack
			this.game.emit(new RequestAttackEvent(
				this.currentAttackUser.getPlayerInfo(),
				"You are in a Duel against " +
				(this.currentAttackUser == this.target ? this.source : this.target) +
				", it's your turn to use Attack."
			));
		} catch (GameFlowInterruptedException e) {
			e.resume();
		}
	}
	

	@Override
	protected String getNeutralizationMessage() {
		return this.source + " used Duel on " + this.target + ", use Neutralization?";
	}

	@Override
	public void onAttackUsed(Card card) {
		// change attack user and continue
		this.currentAttackUser = this.currentAttackUser == this.target ? this.source : this.target;
	}

	@Override
	public void onAttackNotUsed() {
		this.stage = this.stage.nextStage();
		// current attack user takes 1 damage from the other player
		PlayerCompleteServer damageTarget = this.currentAttackUser;
		PlayerCompleteServer damageSource = damageTarget == this.target ? this.source : this.target;
		this.game.pushGameController(new DamageGameController(new Damage(damageSource, damageTarget), this.game));
	}

}
