package sanguosha1.player;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.base.Card_Sha;

/**
 * ��Ҷ�����Ϊ�ӿ�
 * @author user
 *
 */
public interface Player_ActionIF {


	//ʹ��ɱ
	boolean sha(AbstractPlayer p);
	
	//������ɱ
	void shaWithSkill();
	
	//��װ��ɱ
	void shaWithEquipment();
	
	//ʹ����
	void shan();
	
	//ʹ����
	void tao();
	
	//�Ƿ�ر�ɱ
	boolean avoidSha(AbstractPlayer murder, Card_Sha card);

	//����
	boolean jueDou(AbstractPlayer p);

	//����
	void taoSave(AbstractPlayer p);

	//��Ѫ
	void addHP(int num);

	//��Ѫ
	void loseHP(int num, AbstractPlayer murderer);
	
	//���ƶ�����ӵ�������
	void addCardToHandCard(AbstractCard c);
	
	//���ƶ���1����
	void addOneCardFromList();
	
	//��ʧ����
	void loseHandCard(int num);
	
	//ɾ������
	void removeCard(int index);
	
	//ɾ��ָ������
	void removeCard(AbstractCard c);
	
	//װ��װ��
	void loadEquipmentCard(AbstractCard c);
	
	//��ʧװ��
	void unloadEquipmentCard(AbstractCard c);
	
	//��������Ч��
	void magic();
	
	//�����ж���
	AbstractCard dealWithCheckCard(AbstractCard c);
	
	//����
	void dead(AbstractPlayer murderer);

}
