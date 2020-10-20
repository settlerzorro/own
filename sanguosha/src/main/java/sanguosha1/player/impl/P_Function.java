package sanguosha1.player.impl;

import sanguosha1.card.AbstractCard;
import sanguosha1.data.enums.Colors;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.Player_FunctionIF;

import java.util.ArrayList;
import java.util.List;

/**
 * ��װ����ҵ�һЩͨ�ò�������
 * @author user
 *
 */
public class P_Function implements Player_FunctionIF{
	AbstractPlayer player;
	public P_Function(AbstractPlayer player){
		this.player = player;
	}
	/**
	 * ��ȡ�ϼ�
	 * @return
	 */
	public AbstractPlayer getLastPlayer() {
		AbstractPlayer p = player;
		while (p.getNextPlayer() != player) {
			p = p.getNextPlayer();
		}
		return p;
	}

	/**
	 * �Ƿ���Ѫ
	 * 
	 * @return
	 */
	public boolean isFullHP() {
		return (player.getState().getCurHP() == player.getInfo().getMaxHP());
	}

	/**
	 * ��Ѫ
	 * 
	 * @return
	 */
	public void isNullHP() {

	}
	
	/**
	 * �����������֮��ľ���
	 * ���߸�����ʱ����㣬ȡ��Сֵ
	 */
	public int getDistance(AbstractPlayer p){
		AbstractPlayer pNext = player.getNextPlayer();
		int i = 1;
		int j = 1;
		while(pNext!=p){
			i++;
			pNext = pNext.getNextPlayer();
		}
		pNext = p.getNextPlayer();
		while(pNext!=player){
			j++;
			pNext = pNext.getNextPlayer();
		}
		return i<=j?i:j;
	}
	/**
	 * ��ȡ����ȫ�����
	 */
	@Override
	public List<AbstractPlayer> getAllPlayers() {
		List<AbstractPlayer> list = new ArrayList<AbstractPlayer>();
		AbstractPlayer p = player.getNextPlayer();
		while(p!=player){
			list.add(p);
			p = p.getNextPlayer();
		}
		return list;
	}
	
	/**
	 * ��ȡ���������������
	 */
	public List<String> getAllPlayersHandCard(){
		List<String> listRes = new ArrayList<String>();
		List<AbstractPlayer> list = getAllPlayers();
		for (int i = 0; i < list.size(); i++) {
			StringBuilder sb = new StringBuilder(list.get(i).getInfo().getName()+":");
			for (int j = 0; j < list.get(i).getState().getCardList().size(); j++) {
				String s =  list.get(i).getState().getCardList().get(j).toString();
				sb.append(s+",");
			}
			listRes.add(new String(sb));
		}
		return listRes;
	}
	
	/**
	 *��ȡ�������� 
	 */
	public int getAttackDistance(){
		return player.getState().getAttDistance();
	}
	
	/**
	 *��ȡ���ؾ��� 
	 */
	public int getDefenceDistance(){
		return player.getState().getDefDistance();
	}
	
	/**
	 * ����ʹ�þ���
	 * Ĭ���빥��������ͬ
	 */
	public int getKitUseDistance(){
		return getAttackDistance();
	}
	
	/**
	 * ��һ�����ж���ɫ
	 */
	@Override
	public boolean checkRollCard(AbstractCard card,Colors... color) {
		boolean result = false ; 
		for (int i = 0; i < color.length; i++) {
			if(card.getColor() == color[i]){
				result = true;
				break;
			}
		}
		//�ж��ƴ���
		player.getTrigger().afterCheck(card, result);
		return result;
	}
	
	/**
	 * ��һ�����ж���ֵ
	 */
	@Override
	public boolean checkRollCard(AbstractCard card, int max, int min) {
		int n = card.getNumber();
		return n>=min&&n<=max;
	}
	
	/**
	 * ����Ƿ���ʹ�÷�Χ��
	 */
	@Override
	public boolean isInRange(AbstractPlayer target){
		int p2p = getDistance(target);
		int att = getAttackDistance();
		int def = getDefenceDistance();
		return (att-def)>=p2p;
		
	}
	/**
	 * �Ƿ�ͬһ����
	 */
	@Override
	public boolean isSameCountry(AbstractPlayer target) {
		return player.getInfo().getCountry()==target.getInfo().getCountry();
	}
	
	/**
	 * �Ƿ�ͬ��
	 */
	@Override
	public boolean isSameSex(AbstractPlayer target) {
		return player.getInfo().sex == target.getInfo().sex;
	}
	
}
