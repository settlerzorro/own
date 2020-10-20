package sanguosha1.skills.other;

import sanguosha1.player.AbstractPlayer;
import sanguosha1.skills.LockingSkillIF;

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
