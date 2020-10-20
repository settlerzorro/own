package sanguosha1.card;

import sanguosha1.player.AbstractPlayer;

import java.util.List;

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
