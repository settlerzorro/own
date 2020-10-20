package sanguosha1.card.equipment;

import sanguosha1.gui.main.Panel_HandCards;

public interface ActiveSkillWeaponCardIF {
	//���ʹ�ü���
	boolean onClick_open(Panel_HandCards ph);
	//����ر�
	boolean onClick_close(Panel_HandCards ph);
	//����
	void enable();
	//�ر�
	void disable();
}
