package sanguosha1.card;

import java.util.List;

import sanguosha1.player.AbstractPlayer;

/**
 * ����ƽӿ�
 * @author user
 *
 */
public interface ComboCardIF {

	//��ȡ��ʵ��
	List<AbstractCard> getRealCards();
	//��ȡ�������Ƶ����ֵ
	int getCardType();
	//����ʹ��
	void use(AbstractPlayer p, AbstractPlayer toP);

}
