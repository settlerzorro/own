package sgs.skills.trigger;

import sgs.card.AbstractCard;
import sgs.data.enums.Colors;
import sgs.player.AbstractPlayer;
import sgs.player.impl.P_Trigger;
import sgs.service.ModuleManagement;
import sgs.service.ViewManagement;
import sgs.skills.LockingSkillIF;

/**
 * 夏侯惇技能【刚烈】
 * 受伤后，伤害来源判定，若不为红则扣1点血，或者丢弃2张手牌
 * @author user
 *
 */
public class XiaHouDun_ganglie extends P_Trigger implements LockingSkillIF{

	public XiaHouDun_ganglie(AbstractPlayer p) {
		this.player = p;
	}
	/**
	 * 重写受伤触发
	 */
	@Override
	public void afterLoseHP(AbstractPlayer murderer) {
		AbstractCard cc = ModuleManagement.getInstance().showOneCheckCard();
		if(player.getFunction().checkRollCard(cc, Colors.HEITAO,Colors.FANGKUAI,Colors.MEIHUA)){
			murderer.getAction().loseHP(1, null);
			ViewManagement.getInstance().printChatMsg("["+player.getInfo().getName()+"]"+"鼠辈，竟敢伤我");
		}
	}
	@Override
	public String getName() {
		return "刚烈";
	}
	
	
}
