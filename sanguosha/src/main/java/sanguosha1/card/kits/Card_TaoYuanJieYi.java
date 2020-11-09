package sanguosha1.card.kits;

import java.util.List;

import javax.swing.SwingUtilities;

import sanguosha1.card.AbstractCard;
import sanguosha1.gui.main.PaintService;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ViewManagement;

/**
 * 桃园结义
 * @author user
 *
 */
public class Card_TaoYuanJieYi extends AbstractCard{
	public Card_TaoYuanJieYi(){}


	@Override
	public void use(final AbstractPlayer p, List<AbstractPlayer> players) {
		super.use(p, players);
		
		ViewManagement.getInstance().printBattleMsg(p.getInfo().getName()+"使用"+this.name);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				List<AbstractPlayer> list = p.getFunction().getAllPlayers();
				PaintService.drawLine(p,list);
			}
		});
		p.getTrigger().afterMagic();
		p.getAction().addHP(1);
		p.refreshView();
		AbstractPlayer tmp = p.getNextPlayer();
		while(tmp!=p){
			tmp.getAction().addHP(1);
			//System.out.println(tmp.getInfo().getName()+"+1HP");
			tmp.refreshView();
			tmp = tmp.getNextPlayer();
		}
	}
}
