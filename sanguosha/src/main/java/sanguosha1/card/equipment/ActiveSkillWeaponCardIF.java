package sanguosha1.card.equipment;

import sanguosha1.gui.main.Panel_HandCards;

public interface ActiveSkillWeaponCardIF {
	//点击使用技能
	boolean onClick_open(Panel_HandCards ph);
	//点击关闭
	boolean onClick_close(Panel_HandCards ph);
	//开启
	void enable();
	//关闭
	void disable();
}
