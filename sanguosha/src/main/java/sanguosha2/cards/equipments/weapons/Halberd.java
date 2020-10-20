package sanguosha2.cards.equipments.weapons;

import sanguosha2.core.client.GamePanel;
import sanguosha2.core.client.game.listener.HalberdClientEventListener;
import sanguosha2.core.heroes.Hero;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.player.query_listener.HalberdPlayerAttackTargetLimitQueryListener;
import sanguosha2.core.server.game.Game;

public class Halberd extends Weapon {

	private static final long serialVersionUID = 120028584621186883L;

	public Halberd(int num, Suit suit, int id) {
		super(4, num, suit, id);
	}

	@Override
	public String getName() {
		return "Halberd";
	}
	
	@Override
	public void onEquipped(Game game, PlayerCompleteServer owner) {
		owner.registerPlayerStatusQueryListener(new HalberdPlayerAttackTargetLimitQueryListener());
	}
	
	@Override
	public void onEquipped(GamePanel<Hero> panel) {
		panel.registerEventListener(new HalberdClientEventListener());
	}
	
	@Override
	public void onUnequipped(Game game, PlayerCompleteServer owner) {
		owner.removePlayerStatusQueryListener(new HalberdPlayerAttackTargetLimitQueryListener());
	}
	
	@Override
	public void onUnequipped(GamePanel<Hero> panel) {
		panel.removeEventListener(new HalberdClientEventListener());
	}

}
