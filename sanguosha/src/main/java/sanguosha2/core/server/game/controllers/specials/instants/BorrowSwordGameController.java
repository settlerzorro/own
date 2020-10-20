package sanguosha2.core.server.game.controllers.specials.instants;

import java.util.HashSet;
import java.util.Set;

import sanguosha2.cards.Card;
import sanguosha2.cards.basics.Attack;
import sanguosha2.cards.equipments.Equipment.EquipmentType;
import sanguosha2.cards.equipments.weapons.Weapon;
import sanguosha2.core.event.game.basic.RequestAttackEvent;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.player.PlayerInfo;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.AttackGameController;
import sanguosha2.core.server.game.controllers.ReceiveCardsGameController;
import sanguosha2.core.server.game.controllers.UnequipGameController;
import sanguosha2.core.server.game.controllers.interfaces.AttackUsableGameController;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class BorrowSwordGameController
	extends SingleTargetInstantSpecialGameController
	implements AttackUsableGameController {
	
	private final PlayerCompleteServer attackTarget;
	private boolean actionTaken;

	public BorrowSwordGameController(PlayerInfo source, PlayerInfo target, PlayerInfo attackTarget, Game game) {
		super(source, target, game);
		this.attackTarget = game.findPlayer(attackTarget);
		this.actionTaken = false;
	}
	
	@Override
	protected void takeEffect() {
		if (this.actionTaken) {
			this.onUnloaded();
			this.game.getGameController().proceed();
		} else {
			try {
				this.game.emit(new RequestAttackEvent(
					this.target.getPlayerInfo(),
					"Use Attack on " + this.attackTarget + " or else " + this.source + " takes your weapon"
				));
			} catch (GameFlowInterruptedException e) {
				// should not receive GameFlowInterruptedException
			}
		}
	}

	@Override
	protected String getNeutralizationMessage() {
		return this.source + " used Borrow Sword on " + this.target + ", use Neutralization?";
	}

	@Override
	public void onAttackUsed(Card card) {
		this.actionTaken = true;
		this.game.pushGameController(new AttackGameController(this.target, this.attackTarget, (Attack) card, this.game));
	}

	@Override
	public void onAttackNotUsed() {
		this.actionTaken = true;
		Weapon weapon = this.target.getWeapon();
		this.game.pushGameController(
			new UnequipGameController(this.game, this.target, EquipmentType.WEAPON)
				.setNextController(new ReceiveCardsGameController(this.game, this.source,  new HashSet<Card>(){{add(weapon);}}))
		);
	}

}
