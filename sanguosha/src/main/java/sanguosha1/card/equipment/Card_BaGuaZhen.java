package sanguosha1.card.equipment;

import sanguosha1.card.AbstractCard;
import sanguosha1.data.enums.Colors;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ModuleManagement;
import sanguosha1.service.ViewManagement;
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
