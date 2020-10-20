package sanguosha1.card.kits;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.CardIF;
import sanguosha1.data.constant.Const_Game;
import sanguosha1.data.enums.Colors;
import sanguosha1.gui.main.PaintService;
import sanguosha1.gui.main.Panel_Control;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ModuleManagement;
import sanguosha1.service.ViewManagement;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ��������� �ṩһЩ�����Ƶ�ͨ�÷���
 * 
 * @author user
 * 
 */
public abstract class AbstractKitCard extends AbstractCard {
	// �Ƿ���и
	protected boolean isWuXie;
	// ��ҿ���������UI�����ӵ�
	protected Panel_Control pc;

	/*
	 * Ŀ�꼯�� ��Ҫ�Ƿ�ֹ���ֶ��̲߳���ʱ����Ϊ����������б�ǿ��final���������һЩ���⣬������ʱ����һ���������洢 Ҳ�������Ҷ�����
	 */
	protected List<AbstractPlayer> targetPlayers;

	public AbstractKitCard(int id, int number, Colors color) {
		super(id, number, color);
	}

	public AbstractKitCard() {
	}

	/**
	 * ����ʹ��ǰ�ĳ�ʼ��
	 */
	protected void initKit() {
		isWuXie = false;
	}

	/**
	 * ��дuse
	 */
	@Override
	public void use(AbstractPlayer p, List<AbstractPlayer> players) {
		super.use(p, players);
		if (!p.getState().isAI())
			pc = (Panel_Control) p.getPanel();
		targetPlayers = new ArrayList<AbstractPlayer>(players);
		initKit();
	}

	/**
	 * ѯ����и�ɻ�
	 * 
	 * ��и�ɻ���ʵ�ַ����� �������ж���һ��booleanֵ��ʾ�Ƿ���и �����������ѯ�ʳ����Ƿ�����и����������и��booleanֵȡ��
	 * �������ս����������ʵ��ʱ�����booleanֵ�ж��Ƿ񷢶�Ч��
	 * 
	 * @param p
	 * @param players
	 */
	public void askWuXieKeJi(AbstractPlayer p, List<AbstractPlayer> players) {
		if (hasWuxiekejiInBattle()) {
			p.refreshView();
			System.out.println("��������и");
			// ѯ����и
			List<AbstractPlayer> askPlayers = ModuleManagement.getInstance()
					.getPlayerList();
			for (int i = 0; i < askPlayers.size(); i++) {
				// ������˳���и
				if (askPlayers.get(i).getRequest().requestWuXie()) {
					isWuXie = true;
					break;
				}
			}
		}
	}

	/*
	 * �����Ƿ�����и����
	 */
	protected boolean hasWuxiekejiInBattle() {
		for (AbstractPlayer p : ModuleManagement.getInstance().getPlayerList()) {
			if (p.hasCard(Const_Game.WUXIEKEJI))
				return true;
		}
		return false;
	}

	/**
	 * ������Ļ��� �󲿷ֿ���ʹ�����ģ�� ��Щ�������Ҫ��д�������硢��и��
	 */
	@Override
	protected void drawEffect(final AbstractPlayer p,
			List<AbstractPlayer> players) {
		//��Ŀ��Ľ���
		if (targetType == CardIF.SELECT) {
			final AbstractPlayer tmp = players.get(0);
			ViewManagement.getInstance().printBattleMsg(
					p.getInfo().getName() + "��" + tmp.getInfo().getName()
							+ "ʹ����" + this.toString());
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					if (getEffectImage() != null)
						PaintService.drawEffectImage(getEffectImage(), p);
					PaintService.drawLine(p, tmp);
				}
			});
			//��Ŀ��Ľ���
		}else{
			ViewManagement.getInstance().printBattleMsg(p.getInfo().getName()+"ʹ����"+getName());
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					if(getEffectImage()!=null)PaintService.drawEffectImage(getEffectImage(), p);
					PaintService.clearAfter(1000);
				}
			});
		
		}
	}
}
