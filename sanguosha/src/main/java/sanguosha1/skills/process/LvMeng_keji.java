package sanguosha1.skills.process;

import sanguosha1.data.constant.Const_Game;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Process;
import sanguosha1.service.ViewManagement;
import sanguosha1.skills.LockingSkillIF;

/**
 * ���ɼ��ܡ��˼���
 * @author user
 *
 */
public class LvMeng_keji extends P_Process implements LockingSkillIF{
	
	public LvMeng_keji(AbstractPlayer p) {
		super(p);
	}

	/**
	 * ��д����
	 * ���û����ɱ����������
	 */
	@Override
	public void stage_throwCrads() {
		if(player.getState().getCurHP()>=player.getState().getCardList().size()){
			//super.stage_throwCrads();
			return;
		}
		if(player.getState().isAI())return;
		if(!player.getState().isUsedSha()){
			//System.out.println(player.getInfo().getName()+"����Ҫ����");
			ViewManagement.getInstance().ask(player, getName());
			while(true){
				if(player.getState().getRes()==Const_Game.OK){
					ViewManagement.getInstance().printBattleMsg(player.getInfo().getName()+"����"+getName());
					ViewManagement.getInstance().getPrompt().clear();
					player.getState().setRes(0);
					return;
				}
				if(player.getState().getRes()==Const_Game.CANCEL){
					player.getState().setRes(0);
					super.stage_throwCrads();
					break;
				}
			}
			
		}else{
			super.stage_throwCrads();
		}
	}

	@Override
	public String getName() {
		return "�˼�";
	}
}
