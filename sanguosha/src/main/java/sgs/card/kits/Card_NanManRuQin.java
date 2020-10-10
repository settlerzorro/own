package sgs.card.kits;

import sgs.gui.main.PaintService;
import sgs.gui.main.Panel_Player;
import sgs.player.AbstractPlayer;
import sgs.service.ViewManagement;

import javax.swing.*;
import java.util.List;

/**
 * ��������
 * 
 * @author user
 * 
 */
public class Card_NanManRuQin extends AbstractKitCard {
	public Card_NanManRuQin() {

	}

	/**
	 * ��дuse����
	 */
	@Override
	public void use(final AbstractPlayer p, List<AbstractPlayer> players) {
		super.use(p, players);
		// ����
		p.getTrigger().afterMagic();

		// �����������ΪĿ��
		players = p.getFunction().getAllPlayers();

		AbstractPlayer tmp = null;
		// ����Ŀ�꣬ѯ�ʳ�ɱ
		for (int i = 0; i < players.size(); i++) {
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tmp = players.get(i);
			//���Ʊ߿�
			if(tmp.getState().isAI()){
				Panel_Player pp = (Panel_Player) tmp.getPanel();
				pp.setPanelState(Panel_Player.SELECTED);
				pp.repaint();
			}
			//ѯ����и�ɻ�
			askWuXieKeJi(p, players);
			if(isWuXie){
				isWuXie = false;
				tmp.refreshView();
				continue;
			}
			// �������
			if (!tmp.getRequest().requestSha()) {
				// ��1��Ѫ
				tmp.getAction().loseHP(1, p);
				tmp.refreshView();
			}
			// ViewManagement.getInstance().refreshAll();
		}
	}

	/**
	 * ��дЧ������
	 */
	@Override
	protected void drawEffect(final AbstractPlayer p,
			List<AbstractPlayer> players) {
		final List<AbstractPlayer> list = p.getFunction().getAllPlayers();
		ViewManagement.getInstance().printBattleMsg(
				p.getInfo().getName() + "ʹ��" + this.name);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				p.refreshView();
				PaintService.drawEffectImage(getEffectImage(), p);
				PaintService.drawLine(p, list);
			}
		});
	}

}
