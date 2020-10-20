package sanguosha1.player;

import sanguosha1.card.AbstractCard;

/**
 * һЩ�����¼�
 * @author user
 *
 */
public interface Player_TriggerIF {

	//ʹ��ɱ����
	void afterSha();
	
	//ʹ��������
	void afterShan();
	
	//ʹ���Ҵ���
	void afterTao();
	
	//��Ѫ����
	void afterAddHP();
	
	//��Ѫ����
	void afterLoseHP(AbstractPlayer murderer);
	
	//������ƴ���
	void afterGetHandCard();
	
	//��ʧ���ƴ���
	void afterLoseHandCard();
	
	//���װ������
	void afterLoadEquipmentCard();
	
	//��ʧװ������
	void afterUnloadEquipmentCard();
	
	//û�����ƴ���
	void afterNullCards();
	
	//�����ж��ƺ󴥷�
	void afterCheck(AbstractCard c, boolean result);
	
	//ʹ�ý��Ҵ���
	void afterMagic();
	
	//��������
	void afterDead();
	
}
