package sanguosha2.cards.equipments.weapons;

import sanguosha2.core.client.GamePanel;
import sanguosha2.core.client.game.listener.SerpentSpearAttackReactionEventListener;
import sanguosha2.core.client.game.listener.SerpentSpearInitiateAttackEventListener;
import sanguosha2.core.heroes.Hero;

public class SerpentSpear extends Weapon {

	private static final long serialVersionUID = -4325875230988779058L;

	public SerpentSpear(int num, Suit suit, int id) {
		super(3, num, suit, id);
	}

	@Override
	public String getName() {
		return "Serpent Spear";
	}
	
	@Override
	public void onEquipped(GamePanel<Hero> panel) {
		panel.registerEventListener(new SerpentSpearInitiateAttackEventListener());
		panel.registerEventListener(new SerpentSpearAttackReactionEventListener());
	}
	
	@Override
	public void onUnequipped(GamePanel<Hero> panel) {
		panel.removeEventListener(new SerpentSpearInitiateAttackEventListener());
		panel.removeEventListener(new SerpentSpearAttackReactionEventListener());
	}

}
