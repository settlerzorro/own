package sgs.card.equipment;

import sgs.card.AbstractCard;
import sgs.player.AbstractPlayer;

/**
 * 防具接口
 * @author user
 *
 */
public interface ArmorIF {
	//是否免疫该牌
	boolean check(AbstractCard card, AbstractPlayer player);
}
