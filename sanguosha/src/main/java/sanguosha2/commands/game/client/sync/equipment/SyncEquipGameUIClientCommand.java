package sanguosha2.commands.game.client.sync.equipment;

import sanguosha2.cards.equipments.Equipment;
import sanguosha2.commands.game.client.AbstractGameUIClientCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;
import sanguosha2.exceptions.server.game.InvalidPlayerCommandException;

public class SyncEquipGameUIClientCommand extends AbstractGameUIClientCommand {

	private static final long serialVersionUID = -9130336753281283554L;

	private final String name;
	private final Equipment equipment;
	
	public SyncEquipGameUIClientCommand(String name, Equipment equipment) {
		this.name = name;
		this.equipment = equipment;
	}
	
	@Override
	protected void execute(GamePanel<? extends Hero> panel) {
		try {
			if (panel.getContent().getSelf().getName().equals(name)) {
				panel.getContent().getSelf().equip(equipment);
				// TODO: Fix GamePanel generic type
				equipment.onEquipped((GamePanel<Hero>) panel);
			} else {
				panel.getContent().getPlayer(name).equip(equipment);
			}
		} catch (InvalidPlayerCommandException e) {
			e.printStackTrace();
		}
	}

}
