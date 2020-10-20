package sanguosha1.player.impl;

import sanguosha1.card.AbstractCard;
import sanguosha1.data.constant.Const_Game;
import sanguosha1.gui.main.Panel_Control;
import sanguosha1.gui.main.Panel_HandCards;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.Player_RequestIF;
import sanguosha1.service.ViewManagement;
import sanguosha1.skills.ChangeCardIF;
import sanguosha1.skills.SkillIF;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * �����Ӧʵ���� ��װ�������Ӧ�����ĳ���Ƶ���Ϊ
 * 
 * @author user
 * 
 */
public class P_Request implements Player_RequestIF {
	// ����ģ��
	AbstractPlayer player;
	//��ǰ��Ҫ��Ӧ������
	int curType;
	// ������
	public P_Request(AbstractPlayer p) {
		this.player = p;
	}

	/**
	 * ѯ���Ƿ��ɱ
	 */
	@Override
	public boolean requestSha() {
		//player.getState().setRequest(true);
		player.setRequest(true, Const_Game.SHA);
		player.refreshView();
		List<AbstractCard> listSha = hasCard(Const_Game.SHA);
		if (checkHasCardByType(Const_Game.SHA)|checkHasCardByTypeWithSkill(Const_Game.SHA)) {
			if (player.getState().isAI) {
				sleep(1000);
				if(listSha.isEmpty()){
					//player.getState().setRequest(false);
					clear();
					return false;
				}else{					
					listSha.get(0).requestUse(player, null);
					//player.getState().setRequest(false);
					clear();
					return true;
				}
			} else {
				return waitPlayerDo(listSha, Const_Game.SHA);
			}
		}
		sleep(1000);
		//player.getState().setRequest(false);
		clear();
		player.getState().setRes(0);
		synchronized (player.getProcess()) {
			while (!player.getState().isRequest) {
				player.getProcess().notify();
				break;
			}
		}
		return false;
	}

	/**
	 * ѯ���Ƿ����
	 */
	@Override
	public boolean requestShan() {
		//player.getState().setRequest(true);
		player.setRequest(true, Const_Game.SHAN);
		player.refreshView();
		// ����������е���
		List<AbstractCard> listShan = hasCard(Const_Game.SHAN);
		// ����������AI������������û����ֱ�ӷ���false
		if (checkHasCardByType(Const_Game.SHAN)
				| checkHasCardByTypeWithSkill(Const_Game.SHAN)) {
			// �����AI�����оͳ�
			if (player.getState().isAI) {
				sleep(1000);
				if (listShan.isEmpty()) {
					//player.getState().setRequest(false);
					clear();
					return false;
				} else {
					listShan.get(0).requestUse(player, null);
					//player.getState().setRequest(false);
					clear();
					return true;
				}
			} else {
				return waitPlayerDo(listShan, Const_Game.SHAN);
			}
		}
		sleep(1000);
		//player.getState().setRequest(false);
		clear();
		player.getState().setRes(0);
		synchronized (player.getProcess()) {
			while (!player.getState().isRequest) {
				player.getProcess().notify();
				break;
			}
		}
		return false;
	}

	/**
	 * ѯ���Ƿ����
	 */
	@Override
	public boolean requestTao() {
		//player.getState().setRequest(true);
		player.setRequest(true, Const_Game.TAO);
		List<AbstractCard> listTao = hasCard(Const_Game.TAO);
		if (checkHasCardByType(Const_Game.TAO)|checkHasCardByTypeWithSkill(Const_Game.TAO)) {
			if (player.getState().isAI()) {
				sleep(1000);
				if (listTao.isEmpty()) {
					clear();
					return false;
				} else {
					listTao.get(0).requestUse(player, null);
					clear();
					return true;
				}
			} else {
				return waitPlayerDo(listTao, Const_Game.TAO);
			}
		}
		//player.getState().setRequest(false);
		clear();
		player.getState().setRes(0);
		synchronized (player.getProcess()) {
			while (!player.getState().isRequest) {
				player.getProcess().notify();
				break;
			}
		}
		return false;
	}

	/**
	 * ѯ���Ƿ����и�ɻ�
	 */
	@Override
	public boolean requestWuXie() {
		//player.getState().setRequest(true);
		player.setRequest(true, Const_Game.WUXIEKEJI);
		List<AbstractCard> listWuXie = hasCard(Const_Game.WUXIEKEJI);
		if (!listWuXie.isEmpty()) {
			if (player.getState().isAI()) {
				sleep(1000);
				//player.getState().setRequest(false);
				clear();
				return listWuXie.get(0).requestUse(player, null);
			} else {
				return waitPlayerDo(listWuXie, Const_Game.WUXIEKEJI);
			}
		}
		//player.getState().setRequest(false);
		clear();
		player.getState().setRes(0);
		synchronized (player.getProcess()) {
			while (!player.getState().isRequest) {
				player.getProcess().notify();
				break;
			}
		}
		return false;
	}

	/**
	 * ����������Ƿ���ĳ���Ƶļ���
	 */
	private List<AbstractCard> hasCard(int type) {
		List<AbstractCard> list = new ArrayList<AbstractCard>();
		// �������� ���������
		for (int i = 0; i < player.getState().getCardList().size(); i++) {
			// �����������ӵ�������
			if (player.getState().getCardList().get(i).getType() == type) {
				list.add(player.getState().getCardList().get(i));
			}
		}
		return list;
	}

	/**
	 * �ȴ������Ӧ
	 */
	private boolean waitPlayerDo(final List<AbstractCard> list, final int type) {

		// �������ң��򿪷�ѡ�񣬲���ʾ����
		//player.getState().setRequest(true);
		player.setRequest(true, type);
		final Panel_Control pc = (Panel_Control) player.getPanel();
		// ����ֹͣ��ǰ�߳�
		// Thread.currentThread().interrupt();
		System.out.println("�ȴ����ѡ��" + "��ǰ�̡߳���"
				+ Thread.currentThread().getName());

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Panel_HandCards ph = pc.getHand();
				ph.unableToUseCard();
				ph.remindToUse(type);
				ph.disableClick();
				ph.enableOKAndCancel();
				ph.setTargetCheck(false);
				if (type == Const_Game.WUXIEKEJI) {
					ViewManagement.getInstance().getPrompt().show_Message(
							"�Ƿ񷢶���и�ɻ�");
				} else {
					ViewManagement.getInstance().getPrompt().show_RemindToUse(
							type, 1);
				}
			}
		});
		int res;
		while (true) {
			res = player.getState().getRes();
			if (res == type) {
				player.getState().setRes(0);
				boolean b = false;
				if (!pc.getHand().getSelectedList().isEmpty()) {
					AbstractCard c = pc.getHand().getSelectedList().get(0)
							.getCard();
					b = c.requestUse(player, null);
				}
				ViewManagement.getInstance().getPrompt().clear();
				//player.getState().setRequest(false);
				clear();
				synchronized (player.getProcess()) {
					while (!player.getState().isRequest()) {
						player.getProcess().notify();
						break;
					}
				}
				return b;
			}
			if (res == Const_Game.CANCEL) {
				// �����ʾ���Ƶ���Ϣ������
				ViewManagement.getInstance().getPrompt().clear();
				break;
			}
			if(res == Const_Game.SKILL){
				synchronized (this) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			if (res == Const_Game.REDO) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						pc.getHand().unableToUseCard();
						pc.getHand().remindToUse(type);
						if (type == Const_Game.WUXIEKEJI) {
							ViewManagement.getInstance().getPrompt()
									.show_Message("�Ƿ񷢶���и�ɻ�");
						} else {
							ViewManagement.getInstance().getPrompt()
									.show_RemindToUse(type, 1);
						}
					}
				});
				player.getState().setRes(0);
				//player.getState().setRequest(true);
				player.setRequest(true, type);
				continue;
			}
		}
		//player.getState().setRequest(false);
		clear();
		player.getState().setRes(0);
		synchronized (player.getProcess()) {
			while (!player.getState().isRequest) {
				player.getProcess().notify();
				break;
			}
		}
		return false;
	}

	private void sleep(int n) {
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * ����Ƿ�ӵ������ĳ��������������
	 */
	private boolean checkHasCardByType(int type) {
		List<AbstractCard> list = hasCard(type);
		return !list.isEmpty();
	}

	/*
	 * ����Ƿ��б��Ƽ���
	 */
	private boolean checkHasCardByTypeWithSkill(int type) {
		boolean hasChangCradSkill = false;
		for (SkillIF skill : player.getState().getSkill()) {
			if (skill instanceof ChangeCardIF) {
				ChangeCardIF cc = (ChangeCardIF) skill;
				if (cc.getResultType() == type) {
					hasChangCradSkill = true;
					break;
				}
			}
		}
		return hasChangCradSkill;
	}

	/**
	 * �����Ӧ״̬
	 */
	public  void clear(){
		player.getState().setRequest(false);
		curType = 0;
	}
	
	public int getCurType() {
		return curType;
	}

	public void setCurType(int curType) {
		this.curType = curType;
	}
	
	
}
