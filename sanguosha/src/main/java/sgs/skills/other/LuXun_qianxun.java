package sgs.skills.other;

import sgs.player.AbstractPlayer;
import sgs.skills.LockingSkillIF;

/**
 * ½ѷ��ǫѷ��
 * ����˳��ǣ����ֲ�˼��
 * �����ֻ����Ϊһ����ʶ��
 * �����ʵ�����������ļ���<immuneCard>������д�Ƶľ�����ֵ
 * @author user
 *
 */
public class LuXun_qianxun implements LockingSkillIF{
	AbstractPlayer player;
	public LuXun_qianxun(AbstractPlayer p){
		this.player = p;
	}
	@Override
	public String getName() {
		return "ǫѷ";
	}

}
