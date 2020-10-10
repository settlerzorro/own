package sgs.service.AI;

import sgs.player.AbstractPlayer;
import sgs.service.ViewManagement;

/**
 * AI台词类
 * @author user
 *
 */
public class AISpeakService {
	/**
	 * 忠臣喷主公
	 */
	public static void sayFuckBoss(AbstractPlayer speaker){
		String word = "[AI]"+speaker.getInfo().getName()+":你个SB主公";
		ViewManagement.getInstance().printChatMsg(word);
	}
}
