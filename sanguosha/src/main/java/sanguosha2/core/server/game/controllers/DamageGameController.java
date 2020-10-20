package sanguosha2.core.server.game.controllers;

import sanguosha2.core.event.game.damage.TargetEquipmentCheckDamageEvent;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.Damage;
import sanguosha2.core.server.game.Damage.Element;
import sanguosha2.core.server.game.Game;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;
import sanguosha2.utils.EnumWithNextStage;

public class DamageGameController extends AbstractGameController {
	
	public static enum DamageStage implements EnumWithNextStage<DamageStage> {
		TARGET_HERO_SKILLS,
		TARGET_EQUIPMENT_SKILLS,
		TARGET_CHECK_CHAINED,
		TARGET_DAMAGE,
		SOURCE_HERO_SKILLS_AFTER_DAMAGE,
		SKILLS_AFTER_DAMAGE,
		END;
	}
	
	private final Damage originalDamage;
	private final Damage damage;
	private DamageStage stage;
	
	public DamageGameController(Damage damage, Game game) {
		super(game);
		this.damage = damage;
		this.originalDamage = damage.clone();
		this.stage = DamageStage.TARGET_HERO_SKILLS;
	}

	@Override
	public void proceed() {
		while (true) {
			switch (stage) {
				case TARGET_HERO_SKILLS:
					// nothing here yet
					stage = stage.nextStage();
					continue;
				case TARGET_EQUIPMENT_SKILLS:
					try {
						this.game.emit(new TargetEquipmentCheckDamageEvent(damage));
					} catch (GameFlowInterruptedException e) {
						// Do nothing
					}
					stage = stage.nextStage();
					continue;
				case TARGET_CHECK_CHAINED:
					// nothing here yet
					stage = stage.nextStage();
					continue;
				case TARGET_DAMAGE:
					damage.apply();
					stage = stage.nextStage();
					continue;
				case SOURCE_HERO_SKILLS_AFTER_DAMAGE:
					// nothing here yet
					stage = stage.nextStage();
					continue;
				case SKILLS_AFTER_DAMAGE:
					// nothing here yet
					stage = stage.nextStage();
					continue;
				case END:
					PlayerCompleteServer target = this.originalDamage.getTarget();
					// pass down damage too all chained players if damage type is not NORMAL
					if (this.originalDamage.getElement() != Element.NORMAL && target.isChained()) {
						target.setChained(false);
						for (PlayerCompleteServer next = this.game.getNextPlayerAlive(target); next != target; next = this.game.getNextPlayerAlive(next)) {
							if (next.isChained()) {
								// chained players always take damage based on the initial damage value
								int damageAmount = this.originalDamage.isChainedDamage() ? this.originalDamage.getAmount() : this.damage.getAmount();
								Damage newDamage = new Damage(damageAmount, this.originalDamage.getElement(), this.originalDamage.getSource(), next);
								newDamage.setIsChainedDamage(true);
								this.game.pushGameController(new DamageGameController(newDamage, this.game));
								this.game.getGameController().proceed();
								return;
							}
						}
					}
					this.onUnloaded();
					this.game.getGameController().proceed();
					return;
				default:
					return;
			}
		}
	}

}
