package sgs.service.AI;

import sgs.player.AbstractPlayer;
import sgs.service.ViewManagement;

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
