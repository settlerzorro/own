package sanguosha1.card.equipment;

import sanguosha1.card.AbstractCard;
import sanguosha1.data.enums.Colors;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ModuleManagement;
import sanguosha1.service.ViewManagement;

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
