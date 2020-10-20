package sanguosha1.card;

import sanguosha1.player.AbstractPlayer;

import java.util.List;


/**
 * �ƵĽӿ�
 * 
 * @author user
 * 
 */
public interface CardIF {
	
	/**
	 * ��ɫͼƬ���ļ�������
	 */
	public static final String HEITAO_FN = "color_heitao";
	public static final String HONGXIN_FN = "color_hongxin";
	public static final String MEIHUA_FN = "color_meihua";
	public static final String FANGKUAI_FN = "color_fangkuai";
	
	/**
	 * ʹ��Ŀ������
	 * 
	 */
	public static final int AOE = 0;
	public static final int SELECT = 1;
	public static final int NONE = 2;
	

	/**
	 *  ʹ��
	 * 
	 */
	void use(AbstractPlayer p, List<AbstractPlayer> players);
	/**
	 * ������Ӧʹ��
	 */
	boolean requestUse(AbstractPlayer p, List<AbstractPlayer> players);
	/**
	 * ����
	 */
	void throwIt(AbstractPlayer p);
	/**
	 * �������
	 */
	void passToPlayer(AbstractPlayer fromP, AbstractPlayer receiverP);
	/**
	 * �Ƿ���Ҫ���
	 */
	boolean isNeedRange();
}
