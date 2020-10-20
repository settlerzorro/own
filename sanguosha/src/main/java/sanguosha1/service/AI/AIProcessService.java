package sanguosha1.service.AI;

import sanguosha1.card.AbstractCard;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.PlayerIF;
import sanguosha1.service.UsableCardCheckService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * AI������
 * 
 * @author user
 * 
 */
public class AIProcessService {
	AbstractPlayer p;
	UsableCardCheckService check = new UsableCardCheckService();
	/**
	 * ������ ����һ������ģ��
	 * @param p
	 */
	public AIProcessService(AbstractPlayer p){
		this.p = p;
	}
	
	// �غϿ�ʼ
	public void stage_begin() {
		p.setStageNum(PlayerIF.STAGE_BEGIN);
	}

	// �ж��׶�
	public void stage_check() {
		p.setStageNum(PlayerIF.STAGE_CHECK);
	}

	// ���ƽ׶�
	public void stage_addCards() {
		p.setStageNum(PlayerIF.STAGE_ADDCARDS);
		p.getAction().addOneCardFromList();
		p.getAction().addOneCardFromList();
		p.getPanel().refresh();
	}

	/**
	 *  ���ƽ׶�
	 *  ���ȡ1�����õ����ƣ�����Ƿ����ã�����������һ��
	 *  �ж��Ƶ�ʹ��ÿĿ�����ͣ������Ҫѡ��ģ�����һ����ѡ��Ҽ��ϣ����ü��Ϸǿգ������ѡһ����
	 *  TODO
	 */
	public void stage_useCards() {
		p.setStageNum(PlayerIF.STAGE_USECARDS);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//���Խ׶� Ŀ����ʱ����Ϊ�¼�
		//List<AbstractPlayer> list = new ArrayList<AbstractPlayer>();
		//����AIĿ���⺯��
		/*List<AbstractPlayer> list = AITargetCheckService.getEnableTargets(player, c)
		
		list.add(p.getNextPlayer());*/
		//��ȡ1�ſ��õ���
		List<AbstractCard> listA = check.getAvailableCard(p.getState().getCardList(), p);
		AbstractCard c = null;
		if(listA.size()>0){
			int index = new Random().nextInt(listA.size());
			c = listA.get(index);
			//listA.get(index).use(p, list);
			//p.getState().getCardList().remove(listA.get(index));
			List<AbstractPlayer> listTargets = AITargetCheckService.getEnableTargets(p, c);
			if(listTargets.isEmpty())return;
			List<AbstractPlayer> listArgs = new ArrayList<AbstractPlayer>();
			listArgs.add(listTargets.get(new Random().nextInt(listTargets.size())));
			c.use(p, listArgs);
		}
	}

	// ���ƽ׶�
	public void stage_throwCrads() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		p.setStageNum(PlayerIF.STAGE_THROWCRADS);
		System.out.println(p.getState().getId().toString()+p.getInfo().getName()+"����");
		while(p.getState().getCardList().size()>p.getState().getCurHP()){
			p.getState().getCardList().get(0).throwIt(p);
		}
		p.getPanel().refresh();
	
	}

	// �غϽ���
	public void stage_end() {
		p.setStageNum(PlayerIF.STAGE_END);
		//ViewManagement.getInstance().printMsg(p.getState().getId().toString()+p.getInfo().getName()+"����");
		System.out.println(p.getState().getId().toString()+p.getInfo().getName()+"����");
	}

}
