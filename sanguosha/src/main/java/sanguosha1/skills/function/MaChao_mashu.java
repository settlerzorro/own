package sanguosha1.skills.function;

import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Function;
import sanguosha1.skills.LockingSkillIF;

/**
 * ����������
 * @author user
 *
 */
public class MaChao_mashu extends P_Function implements LockingSkillIF{

	public MaChao_mashu(AbstractPlayer player) {
		super(player);
	}

	@Override
	public int getDistance(AbstractPlayer p) {
		return super.getDistance(p)-1;
	}

	@Override
	public String getName() {
		return "����";
	}

}
