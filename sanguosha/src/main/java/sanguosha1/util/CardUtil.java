package sanguosha1.util;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.CardFactory;

import java.util.*;


/**
 * �ƵĹ�����
 * 
 * --�����ƶ�
 * --ϴ��
 * @author user
 *
 */
public class CardUtil {
	
	/**
	 * �����ƶ�
	 */
	@SuppressWarnings("unchecked")
	public static List<AbstractCard> createCards(){
		List<AbstractCard> list =new ArrayList<AbstractCard>();

		//��ȡ�ƶ��б�
		Properties p = ConfigFileReadUtil.getCardList();
		Enumeration<String> en = (Enumeration<String>) p.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			String Property = p.getProperty(key);
			list.add(CardFactory.newCard(key, Property));
		}

		return washCards(list);
	}
	
	/**
	 * �������á�
	 * ���������
	 */
	@SuppressWarnings("unchecked")
	public static List<AbstractCard> createTestCards(){

		List<AbstractCard> list =new ArrayList<AbstractCard>();

		//��ȡ�ƶ��б�
		Properties p = ConfigFileReadUtil.getTestCardList();
		Enumeration<String> en = (Enumeration<String>) p.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			String Property = p.getProperty(key);
			list.add(CardFactory.newCard(key, Property));
		}
		return list;
	
	}
	/**
	 * ϴ��
	 */
	public static List<AbstractCard> washCards(List<AbstractCard> list){
		for (int i = 0; i < list.size(); i++) {
			AbstractCard c = list.get(i);
			list.remove(c);
			int r = new Random().nextInt(list.size());
			list.add(r, c);
		}
		return list;
	}
}
