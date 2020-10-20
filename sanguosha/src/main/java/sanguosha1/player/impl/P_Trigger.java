package sanguosha1.player.impl;

import sanguosha1.card.AbstractCard;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.Player_TriggerIF;

/**
 * ��Ҵ�����ʵ����
 * @author user
 *
 */
public class P_Trigger implements Player_TriggerIF {
	//����ģ��
	protected AbstractPlayer player;
	
	public P_Trigger(){}
	//������
	public P_Trigger(AbstractPlayer p){
		this.player = p;
	}
	@Override
	public void afterAddHP() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterGetHandCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterLoadEquipmentCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterLoseHP(AbstractPlayer murderer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterLoseHandCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterMagic() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * ɱ�󴥷�
	 * Ĭ�Ͽ��عر�
	 */
	@Override
	public void afterSha() {
		player.getState().setUsedSha(true);
	}

	@Override
	public void afterShan() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterTao() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterUnloadEquipmentCard() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * ��������
	 */
	@Override
	public void afterDead() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterNullCards() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * �ж�����
	 * ����1���ж���
	 * ����2���ж����
	 */
	@Override
	public void afterCheck(AbstractCard c,boolean result) {
		c.gc();
	}
	
}
