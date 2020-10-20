package sanguosha1.card;

import sanguosha1.data.enums.EquipmentType;
import sanguosha1.player.AbstractPlayer;

/**
 * װ���ƽӿ�
 * @author user
 *
 */
public interface EquipmentCardIF {
	
	//װ��
	void load(AbstractPlayer p);
	//ж��
	void unload(AbstractPlayer p);
	//��ȡ��������
	int getAttDistance() ;
	//��ȡ��������
	int getDefDistance();
	//��ȡ����
	EquipmentType getEquipmentType();
	//�غϳ�ʼ��
	void beginInit();
}
