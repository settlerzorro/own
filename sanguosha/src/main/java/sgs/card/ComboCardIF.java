package sgs.card;

import sgs.player.AbstractPlayer;

import java.util.List;

/**
 * 组合牌接口
 * @author user
 *
 */
public interface ComboCardIF {

	//获取真实牌
	List<AbstractCard> getRealCards();
	//获取该虚拟牌的替代值
	int getCardType();
	//主动使用
	void use(AbstractPlayer p, AbstractPlayer toP);

}
