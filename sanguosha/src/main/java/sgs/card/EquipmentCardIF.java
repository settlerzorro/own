package sgs.card;

import sgs.data.enums.EquipmentType;
import sgs.player.AbstractPlayer;

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
