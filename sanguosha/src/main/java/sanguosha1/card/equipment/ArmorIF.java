package sanguosha1.card.equipment;

import sanguosha1.card.AbstractCard;
import sanguosha1.player.AbstractPlayer;

/**
 * 防具接口
 * @author user
 *
 */
public interface ArmorIF {
	//是否免疫该牌
	boolean check(AbstractCard card, AbstractPlayer player);
}
