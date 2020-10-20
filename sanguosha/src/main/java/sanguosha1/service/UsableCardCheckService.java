package sanguosha1.service;

import sanguosha1.card.AbstractCard;
import sanguosha1.data.constant.Const_Game;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.PlayerIF;

import java.util.ArrayList;
import java.util.List;


/**
 * �������� 
 * �������״̬������ ����һ����õ����Ƽ��� 
 * AI����Ҷ����Է��ص��������Ƽ���Ϊ�ο����в���
 * 
 * @author user
 * 
 */
public class UsableCardCheckService {
	public UsableCardCheckService() {

	}

	/**
	 * ���ؿ����Ƽ���
	 */
	public List<AbstractCard> getAvailableCard(List<AbstractCard> list,
			AbstractPlayer p) {
		List<AbstractCard> list2 = new ArrayList<AbstractCard>(list);
		// ������ҵĽ׶��ж�
		switch (p.getStageNum()) {
		case PlayerIF.STAGE_BEGIN:
			break;
		case PlayerIF.STAGE_CHECK:
			break;
		case PlayerIF.STAGE_ADDCARDS:
			break;
		case PlayerIF.STAGE_USECARDS:
			// �޳���
			removeAllCardsByType(list2, Const_Game.SHAN);
			// �����Ѫ���޳���
			if (p.getFunction().isFullHP()) {
				removeAllCardsByType(list2, Const_Game.TAO);
			}
			//���ݿ����޳�ɱ
			if(p.getState().isUsedSha()){
				removeAllCardsByType(list2, Const_Game.SHA);
			}
			break;
		case PlayerIF.STAGE_THROWCRADS:
			if(p.getState().getCardList().size()<=p.getState().getCurHP()){
				list2.clear();
			}
			break;
		case PlayerIF.STAGE_END:
			list2.clear();
			break;
		}
		return list2;
	}

	// �޳��б��е�ĳ������
	private void removeAllCardsByType(List<AbstractCard> list, int type) {
		List<AbstractCard> listType = new ArrayList<AbstractCard>();
		// ������������޳���������
		for (AbstractCard c : list) {
			if (c.getType() == type) {
				listType.add(c);
			}
		}
		// �޳�ָ�����͵�����
		list.removeAll(listType);
	}

}
