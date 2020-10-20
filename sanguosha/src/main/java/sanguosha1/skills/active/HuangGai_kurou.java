package sanguosha1.skills.active;

import sanguosha1.gui.Frame_Main;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.skills.SkillIF;

/**
 * �Ƹǡ����⡿
 * ��1��Ѫ��2������
 * @author user
 *
 */
public class HuangGai_kurou implements Runnable,SkillIF{
	AbstractPlayer player;
	public HuangGai_kurou(AbstractPlayer p){
		this.player = p;
	}
	/**
	 * �����ʹ�õ�ʱ��
	 * ��1��Ѫ����û���͵�����
	 */
	@Override
	public void run() {
		//��1��Ѫ
		player.getAction().loseHP(1, null);
		if(!player.getState().isDead()){			
			//���2����
			player.getAction().addOneCardFromList();
			player.getAction().addOneCardFromList();
			player.refreshView();
		}
		//����
		synchronized (player.getProcess()) {
			player.getState().setRes(0);
			player.getProcess().notify();
			//�������������
			if(player.getState().isDead()&&!Frame_Main.isGameOver){
				player.setSkip(true);
			}
		}
	}

	@Override
	public String getName() {
		return "����";
	}

	@Override
	public boolean isEnableUse() {
		return false;
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
