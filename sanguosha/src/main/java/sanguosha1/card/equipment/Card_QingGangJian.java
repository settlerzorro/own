package sanguosha1.card.equipment;

import sanguosha1.data.enums.Colors;
import sanguosha1.player.AbstractPlayer;
/**
 * 青钢剑
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
	 * 防具判定
	 * 无视防具
	 * 永远返回false
	 */
	@Override
	public boolean checkArmor(AbstractPlayer p, AbstractPlayer target) {
		return false;
	}
	
	
}
