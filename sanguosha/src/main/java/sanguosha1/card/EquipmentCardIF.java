package sanguosha1.card;

import sanguosha1.data.enums.EquipmentType;
import sanguosha1.player.AbstractPlayer;

/**
 * 装备牌接口
 * @author user
 *
 */
public interface EquipmentCardIF {
	
	//装载
	void load(AbstractPlayer p);
	//卸载
	void unload(AbstractPlayer p);
	//获取攻击距离
	int getAttDistance() ;
	//获取防御距离
	int getDefDistance();
	//获取类型
	EquipmentType getEquipmentType();
	//回合初始化
	void beginInit();
}
