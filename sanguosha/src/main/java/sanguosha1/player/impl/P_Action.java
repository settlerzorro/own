package sanguosha1.player.impl;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.base.Card_Sha;
import sanguosha1.card.equipment.AbstractEquipmentCard;
import sanguosha1.data.enums.GameOver;
import sanguosha1.data.enums.Identity;
import sanguosha1.gui.Frame_Main;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.PlayerIF;
import sanguosha1.player.Player_ActionIF;
import sanguosha1.service.AI.AISpeakService;
import sanguosha1.service.ModuleManagement;
import sanguosha1.service.ViewManagement;

import java.util.List;

/**
 * �����Ϊ��ʵ���� ��װ����ҵ�һЩ��������
 * 
 * @author user
 * 
 */
public class P_Action implements Player_ActionIF {
	// ����ģ��
	protected AbstractPlayer player;

	// ������
	public P_Action(AbstractPlayer p) {
		this.player = p;
	}

	/**
	 * ��Ҽ�Ѫ -- ���ü�Ѫ�����¼�
	 */
	@Override
	public void addHP(int num) {
		player.getState().curHP += num;
		if (player.getState().curHP > player.getInfo().maxHP) {
			player.getState().curHP = player.getInfo().maxHP;
		}
		// ���ü�Ѫ����
		player.getTrigger().afterAddHP();
	}

	/**
	 * װ��װ��
	 */
	@Override
	public void loadEquipmentCard(AbstractCard c) {
		AbstractEquipmentCard ceq = (AbstractEquipmentCard) c;
		ceq.load(player);
		//����
		player.getTrigger().afterLoadEquipmentCard();
	}

	/**
	 * ��ҿ�Ѫ -- ���ÿ�Ѫ�����¼�
	 * 
	 * @param ��Ѫ����
	 * @param ����
	 */
	@Override
	public void loseHP(int num, AbstractPlayer murderer) {
		int hp = player.getState().getCurHP();
		hp -= num;
		player.getState().setCurHP(hp);
		ViewManagement.getInstance().printMsg(
				player.getInfo().getName() + "�ܵ�" + num + "���˺�");
		player.refreshView();
		// TODO ���Ѫ�����㣬��ʼ����
		hp:
		while(player.getState().getCurHP() <= 0) {
			// �����Լ�
			if (player.getRequest().requestTao()) {
				player.getAction().taoSave(player);
				if(player.getState().getCurHP()>0){
					return;
				}else{
					continue;
				}
			}
			// ����������
			List<AbstractPlayer> list = player.getFunction().getAllPlayers();
			for (int i = 0; i < list.size(); i++) {
				// ��Ϊ�п���bug����ʱ������������
				// ���о�Ϊswing�еĶ��߳����� 2013-4-5�ѽ��
				// if(list.get(i)==murderer)continue;
				if (list.get(i).getRequest().requestTao()) {
					player.getAction().taoSave(player);
					if(player.getState().getCurHP()>0)break hp;
				}
			}
			player.getAction().dead(murderer);
			return;
		}

		// ���ô����¼�
		this.player.getTrigger().afterLoseHP(murderer);
		player.refreshView();
		if (murderer != null)
			murderer.refreshView();
	}

	@Override
	public void loseHandCard(int num) {
		// TODO Auto-generated method stub

	}

	@Override
	public void magic() {
		// TODO Auto-generated method stub

	}

	/**
	 * ��Ҷ��� -- ɱ ����pΪ��ɱ�ߣ� player���ǳ�ɱ�� 2013-3-27�����������bug
	 */
	@Override
	public boolean sha(AbstractPlayer p) {
		boolean flag = false;
		if (!p.getRequest().requestShan()) {
			p.getAction().loseHP(1 + player.getState().getExtDamage(), player);
			flag = true;
		}
		// ����
		player.getState().setUsedSha(true);
		// ���ô����¼�
		player.getTrigger().afterSha();
		return flag;
	}

	@Override
	public void shan() {
		// TODO Auto-generated method stub

	}

	/**
	 * �� ��1��Ѫ
	 */
	@Override
	public void tao() {
		player.getAction().addHP(1);
		//���ô���
		player.getTrigger().afterAddHP();
	}

	/**
	 * ж��װ��
	 */
	@Override
	public void unloadEquipmentCard(AbstractCard c) {
		AbstractEquipmentCard aec = (AbstractEquipmentCard) c;
		//�����Ѿ�������ж���е���
		aec.unload(player);
	}

	/**
	 * ��ָ��������ӵ�������
	 */
	@Override
	public void addCardToHandCard(AbstractCard c) {
		ViewManagement.getInstance().printMsg(
				player.getInfo().getName() + "�����" + c.toString());
		player.getState().getCardList().add(c);
		//����
		player.getTrigger().afterGetHandCard();
	}

	/**
	 * �ƶ�����һ��
	 */
	@Override
	public void addOneCardFromList() {

		player.getState().getCardList().add(
				ModuleManagement.getInstance().getOneCard());
		//����
		player.getTrigger().afterGetHandCard();
	}

	/**
	 * ɾ��ָ�����е�����
	 */
	@Override
	public void removeCard(int index) {
		player.getState().getCardList().remove(index);
		// ��ʧ���ƴ���
		player.getTrigger().afterLoseHandCard();
	}

	/**
	 * ɾ��ָ����ĳ����
	 */
	public void removeCard(AbstractCard c) {
		player.getState().getCardList().remove(c);
		// ��ʧ���ƴ���
		player.getTrigger().afterLoseHandCard();
	}

	/**
	 * ��������
	 */
	@Override
	public void dead(AbstractPlayer murderer) {
		// ȫ���б���ɾ��
		ModuleManagement.getInstance().getPlayerList().remove(player);
		// ��������״̬
		player.getState().setDead(true);
		player.getState().setCurHP(0);
		// ɾ������
		for (AbstractCard c : player.getState().getCardList()) {
			c.gc();
		}
		player.getState().getCardList().clear();
		// ���¼�����
		player.getFunction().getLastPlayer().setNextPlayer(
				player.getNextPlayer());
		//�������������Ƿ���ֹ��Ϸ
		if(player.getState().getId() == Identity.ZHUGONG){
			if(getFanZei()>0){
				Frame_Main.me.gameOver(GameOver.FANZEI_WIN);
			}else{
				Frame_Main.me.gameOver(GameOver.NEIJIAN_WIN);
			}
			return;
		}
		if(player.getState().getId() == Identity.FANZEI || player.getState().getId() == Identity.NEIJIAN){
			int alive = getFanZeiOrNeiJian();
			System.out.println("�������ڼ黹ʣ��"+alive+"��");
			if(alive==0){
				Frame_Main.me.gameOver(GameOver.ZHUGONG_WIN);
				return;
			}
		}
		// ���������¼�
		player.getTrigger().afterDead();
		//������Ľ���
		if (murderer != null) {
			// ����������Ƿ��� ���ֻ��3����
			if (player.getState().getId() == Identity.FANZEI) {
				for (int i = 0; i < 3; i++) {
					murderer.getAction().addOneCardFromList();
				}
			}
			//������ɱ�ҳ������������
			if(player.getState().getId() == Identity.ZHONGCHEN &&
					murderer.getState().getId()==Identity.ZHUGONG){
				murderer.getState().getCardList().clear();
				murderer.getState().getEquipment().removeALL();
				AISpeakService.sayFuckBoss(player);
			}
			player.refreshView();
			murderer.refreshView();
		}
		//����ڻغ���������ֱ�������غϣ��¼ҿ�ʼ
		if(player.getStageNum()!= PlayerIF.STAGE_END){
			System.out.println("dead"+Thread.currentThread().getName());
			player.getNextPlayer().process();
		}
	}

	private int getFanZeiOrNeiJian(){
		int alive = 0;
		for (AbstractPlayer p : ModuleManagement.getInstance().getPlayerList()) {
			if(p.getState().getId()==Identity.FANZEI || p.getState().getId() == Identity.NEIJIAN){
				alive++;
			}
		}
		return alive;
	}
	
	private int getFanZei(){
		int alive = 0;
		for (AbstractPlayer p : ModuleManagement.getInstance().getPlayerList()) {
			if(p.getState().getId()==Identity.FANZEI){
				alive++;
			}
		}
		return alive;
	}
	/**
	 * ��-����
	 */
	@Override
	public void taoSave(AbstractPlayer p) {
		player.getAction().tao();
		//����
		player.getTrigger().afterAddHP();
	}

	@Override
	public void shaWithEquipment() {
		// TODO Auto-generated method stub

	}

	@Override
	public void shaWithSkill() {
		// TODO Auto-generated method stub

	}

	/**
	 * �����ж��� Ĭ��ʲô����������
	 */
	@Override
	public AbstractCard dealWithCheckCard(AbstractCard c) {
		return c;
	}

	/**
	 * ���� ���Ŀ���ɱ���򷵻�true
	 */
	@Override
	public boolean jueDou(AbstractPlayer p) {
		if (p.getRequest().requestSha()) {
			return true;
		} else {
			p.getAction().loseHP(1 + player.getState().getExtDamage(), player);
			return false;
		}
	}

	/**
	 * �Ƿ�ر�ɱ
	 */
	@Override
	public boolean avoidSha(AbstractPlayer murder, Card_Sha card) {
		return false;
	}

}
