package sanguosha2.core.client.game.listener;

import java.util.HashSet;
import java.util.Set;

import sanguosha2.cards.equipments.Equipment.EquipmentType;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.client.game.event.AttackReactionClientGameEvent;
import sanguosha2.core.client.game.operations.equipment.SerpentSpearOperation;
import sanguosha2.core.heroes.Hero;
import sanguosha2.ui.game.interfaces.Activatable;

public class SerpentSpearAttackReactionEventListener extends AbstractClientEventListener<AttackReactionClientGameEvent> {

	@Override
	public Class<AttackReactionClientGameEvent> getEventClass() {
		return AttackReactionClientGameEvent.class;
	}

	@Override
	public void handle(AttackReactionClientGameEvent event, GamePanel<Hero> panel) {
		if (event.isStart()) {
			panel.getContent().getEquipmentRackUI().setActivatable(new HashSet<EquipmentType>(){{add(EquipmentType.WEAPON);}}, true);
			panel.getContent().getEquipmentRackUI().registerOnActivatedListener(EquipmentType.WEAPON, (e) -> {
				panel.pushOperation(new SerpentSpearOperation(false), (Activatable) e.getSource());
			});
		} else {
			this.onDeactivated(panel);
		}
	}

	@Override
	public void onDeactivated(GamePanel<Hero> panel) {
		panel.getContent().getEquipmentRackUI().setActivatable(new HashSet<EquipmentType>(){{add(EquipmentType.WEAPON);}}, false);
		panel.getContent().getEquipmentRackUI().removeOnActivatedListeners(EquipmentType.WEAPON);
	}

}
