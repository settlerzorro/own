package sanguosha2.core.client.game.listener;

import java.util.HashSet;
import java.util.Set;

import sanguosha2.cards.equipments.Equipment.EquipmentType;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.client.game.event.EnableAttackClientGameEvent;
import sanguosha2.core.client.game.operations.equipment.SerpentSpearOperation;
import sanguosha2.core.heroes.Hero;
import sanguosha2.ui.game.interfaces.Activatable;

public class SerpentSpearInitiateAttackEventListener extends AbstractClientEventListener<EnableAttackClientGameEvent> {
	
	@Override
	public Class<EnableAttackClientGameEvent> getEventClass() {
		return EnableAttackClientGameEvent.class;
	}
	
	@Override
	public void handle(EnableAttackClientGameEvent event, GamePanel<Hero> panel) {
		if (event.isStart()) {
			if (panel.getContent().getSelf().getAttackUsed() < panel.getContent().getSelf().getAttackLimit()) {
				panel.getContent().getEquipmentRackUI().setActivatable(new HashSet<EquipmentType>(){{add(EquipmentType.WEAPON);}}, true);
				panel.getContent().getEquipmentRackUI().registerOnActivatedListener(EquipmentType.WEAPON, (e) -> {
					panel.pushOperation(new SerpentSpearOperation(true), (Activatable) e.getSource());
				});
			}
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
