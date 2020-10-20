package sanguosha1.service.AI;

import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ViewManagement;

/**
 * AĮ����
 * @author user
 *
 */
public class AISpeakService {
	/**
	 * �ҳ�������
	 */
	public static void sayFuckBoss(AbstractPlayer speaker){
		String word = "[AI]"+speaker.getInfo().getName()+":���SB����";
		ViewManagement.getInstance().printChatMsg(word);
	}
}
