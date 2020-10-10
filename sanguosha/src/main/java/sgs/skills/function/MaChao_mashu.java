package sgs.skills.function;

import sgs.player.AbstractPlayer;
import sgs.player.impl.P_Function;
import sgs.skills.LockingSkillIF;

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
