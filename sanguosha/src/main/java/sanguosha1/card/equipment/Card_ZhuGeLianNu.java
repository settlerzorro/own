package sanguosha1.card.equipment;

import sanguosha1.data.enums.Colors;
import sanguosha1.player.AbstractPlayer;

/**
 * �������
 * @author user
 *
 */
public class Card_ZhuGeLianNu extends AbstractWeaponCard{
	//��ʱ�洢ɱ�Ŀ���
	boolean useSha ;
	public Card_ZhuGeLianNu(){}
	public Card_ZhuGeLianNu(int id, int number, Colors color) {
		super(id, number, color);
	}
	
	/**
	 * ��дװ��
	 */
	@Override
	public void load(AbstractPlayer p) {
		super.load(p);
		useSha = p.getState().isUsedSha();
		p.getState().setUsedSha(false);
	}
	/**
	 * ��дж��
	 */
	@Override
	public void unload(AbstractPlayer p) {
		super.unload(p);
		p.getState().setUsedSha(useSha);
	}

	/**
	 * ����ʵ��
	 * ��дɱ����
	 */
	@Override
	public void afterSha(AbstractPlayer p, AbstractPlayer target) {
		super.afterSha(p, target);
		useSha = true;
		p.getState().setUsedSha(false);
	}
	
	@Override
	public void beginInit() {
		super.beginInit();
		useSha = false;
	}
	
	
}
