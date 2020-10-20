package sanguosha1.gui.main;

import sanguosha1.card.AbstractCard;
import sanguosha1.data.constant.Const_UI;
import sanguosha1.data.enums.Colors;
import sanguosha1.service.ViewManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * �Ƶ����
 * 
 * @author user
 * 
 */
public class Panel_Card extends JPanel implements RefreshbleIF {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7756121461123099368L;
	// �Ƶ�ģ��
	AbstractCard card;
	// ������
	Panel_HandCards ph;
	// �Ƿ����
	boolean enableToUse;
	// �Ƿ�ѡȡ
	boolean isSelected = false;
	// ������
	MouseListener listener;

	/**
	 * ����
	 * 
	 * @param card
	 * @param p
	 * @param click
	 */
	public Panel_Card(AbstractCard card, Panel_HandCards p, boolean click) {
		this.ph = p;
		this.card = card;
		this.setSize(Const_UI.CARD_WIDTH, Const_UI.CARD_HEIGHT);
		this.setOpaque(false);
		listener = new Mouse(this);
		if (click) {
			this.setEnableToUse(true);
			// this.addMouseListener(listener);
			this.requestFocus();
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}

	/**
	 * ���ع���
	 */
	public Panel_Card(AbstractCard c, int width, int height, boolean enable) {
		this.card = c;
		this.setSize(width, height);
		this.enableToUse = enable;
	}

	// �������漰��ɫ
	public void paintComponent(Graphics g) {
		if (!enableToUse) {
			drawUnable(g);
		}
		if (card != null) {
			g.drawImage(card.showImg(), 0, 0, null);
			Image color = card.getColorImg();
			g.drawImage(color, 5, 5, 20, 20, null);
			if (card.getColor() == Colors.FANGKUAI
					|| card.getColor() == Colors.HONGXIN) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.BLACK);
			}
			g.setFont(new Font(g.getFont().getName(), Font.BOLD, 18));
			g.drawString(card.getNumberToString(), 7, 40);
		}
	}

	/**
	 * ���Ʋ�����
	 * 
	 * @param g
	 */
	private void drawUnable(Graphics g) {
		/*
		 * BufferedImage image = new BufferedImage(getWidth(), getHeight(),
		 * BufferedImage.TYPE_INT_ARGB);
		 */
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.darkGray);
		g2.fillRect(0, 0, card.showImg().getWidth(null), card.showImg()
				.getHeight(null));
		//g2.fillRect(0, 0, Const_UI.CARD_WIDTH, Const_UI.CARD_HEIGHT);
		g2.setComposite(AlphaComposite.SrcOver.derive(Const_UI.CARD_UNABLE));
	}

	/**
	 * �����Ƿ��ʹ��
	 * 
	 * @param b
	 */
	public void setEnableToUse(boolean b) {
		if (this.enableToUse == b)
			return;
		this.enableToUse = b;
		//�ػ���ǰ��������ã��ӿ������ʾ
		repaint();
		//��̨����������߳�
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				if (enableToUse) {
					if (getMouseListeners().length == 0)
						addMouseListener(listener);
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				} else {
					if (getMouseListeners().length > 0)
						removeMouseListener(listener);
					setCursor(Cursor.getDefaultCursor());
				}
			}
		}).start();
		
	}

	/**
	 * �Ʊ����ѡ�� --��̧�� --�����Ƶ�ʹ��Ŀ�����ͣ�ȷ��Ŀ��ѡ�� --�����aoe �� ��Ŀ����� ������ť --�����ҪĿ��
	 * ��������п�ѡ������ ��ѡ�к��ٿ�����ť
	 */
	public void select() {
		// ��ӵ�ѡ�������б�
		ph.getSelectedList().add(this);
		// �������Ƚ��ȳ��޳�
		if (ph.selectedList.size() > ph.getSelectLimit()) {
			ph.selectedList.get(0).unselect();
		}
		// ���ѡ��Ŀ������б�
		ph.getTarget().getList().clear();
		this.setLocation(this.getX(), this.getY() - 50);
		this.setSelected(true);
		ph.useP.enableToUse();
		// �������Ҫѡ��Ŀ��
		if (!ph.targetCheck) {
			return;
		}
		// ����ph��target�����ã����ÿ���ѡ������
		/*
		 * if (!ph.getTarget().isNeedCheck()) { // ����ѡ��
		 * SwingUtilities.invokeLater(new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * List<Panel_Player> list = ph.main.players; for (Panel_Player pp :
		 * list) { if (!pp.getPlayer().getState().isDead()) { pp.enableToUse();
		 * } }
		 * 
		 * } }); } else { }
		 */

		cardUseTargetCheck(ph.getSelectedList().get(0).getCard());
		card.targetCheck(ph);
		System.out.println("���Ŀ����");
	}

	/*
	 * ����ѡ�е��ƣ����ж���Щ��ҷ��ϱ�ѡ��
	 */
	private void cardUseTargetCheck(AbstractCard card) {
		// ���� ���
		List<Panel_Player> list = ph.main.players;
		for (int i = 0;i<list.size();i++) {
			Panel_Player pp = list.get(i);
			// �������
			if (pp.getPlayer().getState().isDead()) {
				pp.disableToUse();
				continue;
			}
			// �������
			if (pp.getPlayer().getState().getImmuneCards().contains(
					card.getType())) {
				pp.disableToUse();
				continue;
			}
			/*// �����Ҫ���
			if (card.isNeedRange()
					&& !card.isInRange(ph.player, pp.getPlayer())) {
				pp.disableToUse();
				continue;
			}*/
			pp.enableToUse();
		}
	}

	/*
	 * ȡ��ѡ��
	 */
	public void unselect() {
		// �Ʒ���
		this.setLocation(this.getX(), this.getY() + 50);
		this.setSelected(false);
		repaint();
		// ��յ�ǰѡ��
		ph.getSelectedList().remove(this);
		// ���ѡ���Ŀ��
		// ModuleManagement.getInstance().setTarget(null);
		// �����ʾ��Ϣ
		ViewManagement.getInstance().getPrompt().clear();
		if (!ph.getSelectedList().isEmpty())
			return;
		
		ph.getTarget().setLimit(1);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				List<Panel_Player> list = ph.main.players;
				if (ph.targetCheck) {
					// ��ֹ����������
					for (Panel_Player pp : list) {
						pp.setNormal();
					}
				} else {
					// ȡ����ѡ����ҵ�״̬
					for (Panel_Player pp : list) {
						if (pp.getPanelState() == Panel_Player.SELECTED)
							pp.enableToUse();
					}
				}
			}
		});
	
		// ���ѡ���б�Ϊ�����ֹ���
		if (ph.selectedList == null || ph.selectedList.size() == 0) {
			ph.useP.unableToClick();
			// ph.cancelP.unableToClick();
		}
	
		
	}

	// ������ �ڲ���
	class Mouse extends MouseAdapter {
		Panel_Card pc;

		public Mouse(Panel_Card p) {
			this.pc = p;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// System.out.println("click");
			if (pc.isSelected()) {
				pc.unselect();
			} else {
				pc.select();
			}
		}
	}

	/**
	 * ˢ��
	 */
	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public AbstractCard getCard() {
		return card;
	}

	public void setCard(AbstractCard card) {
		this.card = card;
	}

}
