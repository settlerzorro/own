package sgs.skills.trigger;

import sgs.player.AbstractPlayer;
import sgs.player.impl.P_Trigger;
import sgs.skills.LockingSkillIF;

/**
 * ½ѷ����Ӫ��
 * @author user
 *
 */
public class LuXun_lianying extends P_Trigger implements LockingSkillIF{
	public LuXun_lianying(AbstractPlayer p){
		this.player = p;
	}
	
	/**
	 * ��д��ʧ���ƴ���
	 */
	@Override
	public void afterLoseHandCard() {
		//�����������һ��
		if(player.getState().getCardList().isEmpty()){
			player.getAction().addOneCardFromList();
			System.out.println("�Ʋ������ܵģ�û���������ܵģ�");
		}
	}
	
	@Override
	public String getName() {
		return "��Ӫ";
	}

}
