package sanguosha2.core.server.game.controllers;

import java.util.*;

import sanguosha2.cards.Card;
import sanguosha2.cards.basics.Attack;
import sanguosha2.core.event.game.basic.AttackPostAcquisitionWeaponAbilitiesCheckEvent;
import sanguosha2.core.event.game.basic.AttackPreAcquisitionWeaponAbilitiesCheckEvent;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.Game;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;
import sanguosha2.utils.EnumWithNextStage;

public class AttackGameController extends AbstractGameController {

	public static enum AttackStage implements EnumWithNextStage<AttackStage> {
		PRE_TARGET_ACQUISITION_WEAPON_ABILITIES,
		TARGET_ACQUISITION,
		POST_TARGET_ACQUISITION_WEAPON_ABILITIES,
		ATTACK_RESOLUTION,
		END,
	}
	
	private AttackStage stage;
	private PlayerCompleteServer source;
	private List<PlayerCompleteServer> targets;
	private int currentTargetIndex;
	private int damageAmount;
	private Attack attack;
	
	public AttackGameController(PlayerCompleteServer source, Collection<PlayerCompleteServer> targets, Attack card, Game game) {
		super(game);
		this.stage = AttackStage.PRE_TARGET_ACQUISITION_WEAPON_ABILITIES;
		this.source = source;
		this.targets = new ArrayList<>();
		for (PlayerCompleteServer next = game.getNextPlayerAlive(source); next != source; next = game.getNextPlayerAlive(next)) {
			if (targets.contains(next)) {
				this.targets.add(next);
			}
		}
		this.currentTargetIndex = 0;
		this.damageAmount = 1;
		if (source.isWineEffective()) {
			this.damageAmount++;
			this.source.resetWineEffective();
		}
		if (card != null) {
			this.attack = card;
		} else {
			this.attack = new Attack();
		}
	}
	
	public AttackGameController(PlayerCompleteServer source, PlayerCompleteServer target, Attack card, Game game) {
		this(source,new HashSet<PlayerCompleteServer>(){{add(target);}}, card, game);
	}
	
	@Override
	public void proceed() {
		switch (stage) {
			case PRE_TARGET_ACQUISITION_WEAPON_ABILITIES:
				try {
					this.game.emit(new AttackPreAcquisitionWeaponAbilitiesCheckEvent(this.source, this));
					this.stage = this.stage.nextStage();
					this.proceed();
				} catch (GameFlowInterruptedException e) {
					e.resume();
				}
				break;
			case TARGET_ACQUISITION:
				this.stage = this.stage.nextStage();
				this.proceed();
				break;
			case POST_TARGET_ACQUISITION_WEAPON_ABILITIES:
				try {
					PlayerCompleteServer currentTarget = this.targets.get(this.currentTargetIndex);
					this.game.emit(new AttackPostAcquisitionWeaponAbilitiesCheckEvent(this.source, currentTarget));
				} catch (GameFlowInterruptedException e) {
					// should not receive GameFlowInterruptedException
				}
				this.currentTargetIndex++;
				if (this.currentTargetIndex == this.targets.size()) {
					this.currentTargetIndex = 0;
					this.stage = this.stage.nextStage();
				}
				this.proceed();
				break;
			case ATTACK_RESOLUTION:
				PlayerCompleteServer currentTarget = this.targets.get(this.currentTargetIndex);
				this.game.pushGameController(new AttackResolutionGameController(this.source, currentTarget, this.attack, this.damageAmount, this.game));
				this.currentTargetIndex++;
				if (this.currentTargetIndex == this.targets.size()) {
					this.currentTargetIndex = 0;
					this.stage = this.stage.nextStage();
				}
				this.game.getGameController().proceed();
				break;
			case END:
				this.onUnloaded();
				this.game.getGameController().proceed();
				break;
		}
	}
	
	public void setStage(AttackStage stage) {
		this.stage = stage;
	}

	public Attack getAttackCard() {
		return this.attack;
	}
	
	public void setAttackCard(Attack card) {
		this.attack = card;
	}
	
}
