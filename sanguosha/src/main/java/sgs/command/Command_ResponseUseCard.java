package sgs.command;

import sgs.gui.main.Panel_HandCards;
import sgs.service.ViewManagement;

/**
 * ��Ӧ����
 * @author user
 *
 */
public class Command_ResponseUseCard implements Runnable {
	Panel_HandCards ph ;
	public Command_ResponseUseCard(Panel_HandCards ph){
		this.ph = ph;
	}
	@Override
	public void run() {
		ph.getSelectedList().get(0).getCard().requestUse(ph.getPlayer(), null);
		/*ph.getPlayer().getState().setRequest(false);
		ph.getPlayer().getState().setRes(ph.getSelectedList().get(0).getCard().getType());*/
		ph.getSelectedList().clear();
		ViewManagement.getInstance().getPrompt().clear();
	}
	
}
