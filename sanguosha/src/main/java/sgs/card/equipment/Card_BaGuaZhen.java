package sgs.card.equipment;

import sgs.card.AbstractCard;
import sgs.data.enums.Colors;
import sgs.player.AbstractPlayer;
import sgs.service.ModuleManagement;
import sgs.service.ViewManagement;

/**
 * ������
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
	 * ���߼��
	 * �����Ч������Իرܸôι���
	 */
	@Override
	public boolean check(AbstractCard card,AbstractPlayer player) {
		AbstractCard cc = ModuleManagement.getInstance().showOneCheckCard();
		if(player.getFunction().checkRollCard(cc, Colors.HONGXIN)){
			ViewManagement.getInstance().printBattleMsg(player.getInfo().getName()+"��������Ч");
			return true;
		}
		return false;
	}
	
}
