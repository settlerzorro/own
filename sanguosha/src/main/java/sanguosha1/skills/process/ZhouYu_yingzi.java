package sanguosha1.skills.process;

import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Process;
import sanguosha1.service.ViewManagement;
import sanguosha1.skills.LockingSkillIF;

/**
 * ÖÜè¤¼¼ÄÜ¡¾Ó¢×Ë¡¿
 * ÃşÅÆ½×¶Î¿ÉÒÔÃş3ÕÅÅÆ
 * @author user
 *
 */
public class ZhouYu_yingzi extends P_Process implements LockingSkillIF{

	public ZhouYu_yingzi(AbstractPlayer p) {
		super(p);
	}

	/**
	 * ÖØĞ´ÃşÅÆ½×¶Î
	 */
	@Override
	public void stage_addCards() {
		super.stage_addCards();
		ViewManagement.getInstance().printBattleMsg("ÖÜè¤¡¾Ó¢×Ë¡¿´¥·¢");
		player.getAction().addOneCardFromList();
	}

	@Override
	public String getName() {
		return "Ó¢×Ë";
	}
	
}
