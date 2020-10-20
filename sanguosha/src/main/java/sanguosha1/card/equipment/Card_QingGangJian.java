package sanguosha1.card.equipment;

import sanguosha1.data.enums.Colors;
import sanguosha1.player.AbstractPlayer;

/**
 * ��ֽ�
 * @author user
 *
 */

public class Card_QingGangJian extends AbstractWeaponCard{
	
	public Card_QingGangJian(){
		super();
	}
	public Card_QingGangJian(int id, int number, Colors color){
		super(id, number, color);
	}
	
	/**
	 * �����ж�
	 * ���ӷ���
	 * ��Զ����false
	 */
	@Override
	public boolean checkArmor(AbstractPlayer p, AbstractPlayer target) {
		return false;
	}
	
	
}
