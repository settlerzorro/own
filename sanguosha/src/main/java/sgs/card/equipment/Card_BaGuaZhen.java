package sgs.card.equipment;

import sgs.card.AbstractCard;
import sgs.data.enums.Colors;
import sgs.player.AbstractPlayer;
import sgs.service.ModuleManagement;
import sgs.service.ViewManagement;

/**
 * 八卦阵
 * @author user
 *
 */
public class Card_BaGuaZhen extends AbstractEquipmentCard implements ArmorIF{
	public Card_BaGuaZhen(){
	}
	public Card_BaGuaZhen(int id, int number, Colors color){
		super(id, number, color);
	}
	/**
	 * 免疫检测
	 * 如果生效，则可以回避该次攻击
	 */
	@Override
	public boolean check(AbstractCard card,AbstractPlayer player) {
		AbstractCard cc = ModuleManagement.getInstance().showOneCheckCard();
		if(player.getFunction().checkRollCard(cc, Colors.HONGXIN)){
			ViewManagement.getInstance().printBattleMsg(player.getInfo().getName()+"八卦阵生效");
			return true;
		}
		return false;
	}
	
}
