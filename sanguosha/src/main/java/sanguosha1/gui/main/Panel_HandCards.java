package sanguosha1.gui.main;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.DelayKitIF;
import sanguosha1.data.constant.Const_Game;
import sanguosha1.data.constant.Const_UI;
import sanguosha1.data.enums.Colors;
import sanguosha1.data.types.Target;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.PlayerIF;
import sanguosha1.service.UsableCardCheckService;
import sanguosha1.util.ImgUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ��������
 * 
 * @author user
 * 
 */
public class Panel_HandCards extends JPanel implements RefreshbleIF {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7517229300815663345L;
	// ����������
	Panel_Main main;
	// ����ģ��
	AbstractPlayer player;
	// �����ļ���
	List<Panel_Card> cards = new ArrayList<Panel_Card>();
	// �Ƶ�ģ�͵ļ���
	List<AbstractCard> cardsModule = new ArrayList<AbstractCard>();
	// ��ǰ�ɳ��Ƶļ���
	List<AbstractCard> useList = new ArrayList<AbstractCard>();
	// ��ǰѡ����
	List<Panel_Card> selectedList = new ArrayList<Panel_Card>();
	// ��ѡ�������
	int selectLimit = 1;
	// ѡ����Ƶ�Ŀ��
	Target target = new Target(1);
	// �Ƿ���Ŀ����
	boolean targetCheck = true;
	// ���ư�ť
	UsePanel useP = new UsePanel("ȷ��", 1);
	UsePanel cancelP = new UsePanel("ȡ��", 2);
	UsePanel skipP = new UsePanel("����", 3);
	// ���Ƽ����
	UsableCardCheckService check = new UsableCardCheckService();

	// ������
	public Panel_HandCards(AbstractPlayer p, Panel_Main main) {
		this.setLayout(null);
		this.main = main;
		this.player = p;
		updateCards();

		this.add(useP);
		this.add(cancelP);
		this.add(skipP);
		useP.setLocation(Const_UI.CARD_WIDTH * 5 + Const_UI.BUTTON_OFFSET, 20);
		cancelP.setLocation(Const_UI.CARD_WIDTH * 5 + Const_UI.BUTTON_OFFSET,
				useP.getHeight() + useP.getY());
		skipP.setLocation(Const_UI.CARD_WIDTH * 5 + Const_UI.BUTTON_OFFSET,
				useP.getHeight() + cancelP.getHeight()
						+ Const_UI.BUTTON_OFFSET_SKIP);
	}

	/**
	 * ����ģ�� ˢ���Ƶ����
	 */
	public synchronized void updateCards() {
		cardsModule = this.player.getState().getCardList();
		// ��ԭ�ȵı�ǩ���
		for (int i = 0; i < cards.size(); i++) {
			this.remove(cards.get(i));
		}
		cards.clear();
		// ���´�����ǩ
		for (int i = 0; i < cardsModule.size(); i++) {
			cards.add(new Panel_Card(cardsModule.get(i), this, false));
		}
		for (int i = cardsModule.size() - 1; i >= 0; i--) {
			this.add(cards.get(i));
		}
		this.repaint();
		// ������
		carding();
		// �������ﵱǰ״̬��ѡ�����ƵĿ���״̬�Ͱ�ť״̬
		selectShowCardAndClick();
	}

	/**
	 * ����
	 * 
	 * @param g
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// �����ж���
		if (!player.getState().getCheckedCardList().isEmpty()) {
			for (int i = 0; i < player.getState().getCheckedCardList().size(); i++) {
				DelayKitIF d = (DelayKitIF) player.getState()
						.getCheckedCardList().get(i);
				g.setColor(Color.white);
				g.drawString(d.getShowNameInPanel(), useP.getX() + 20 * i, 10);
			}
		}
	}

	/**
	 * ������ �������5�žͲ����ʾ
	 */
	public void carding() {
		int interval = Const_UI.CARD_WIDTH;
		if (cards.size() > 5) {
			interval = (Const_UI.CARD_WIDTH * 5 - Const_UI.CARD_WIDTH)
					/ (cards.size() - 1);
		}
		for (int i = 0; i < cards.size(); i++) {
			cards.get(i).setLocation(i * interval, Const_UI.CARD_UP);

		}
	}

	/**
	 * ���ƺ󣬽���������
	 */
	public void unableToUseCard() {
		for (final Panel_Card pc : cards) {
			pc.setEnableToUse(false);
		}
		carding();
	}

	/**
	 * ��ʾ�������õ���
	 */
	public void showAvailableCards() {
		// ��ȡ�������Ƽ���
		List<AbstractCard> listA = check.getAvailableCard(cardsModule, player);
		// ��ʾ���п��õ���
		for (Panel_Card pc : cards) {
			if (listA.contains(pc.getCard())) {
				pc.setEnableToUse(true);
			} else {
				pc.setEnableToUse(false);
			}
		}
		if (player.getStageNum() == PlayerIF.STAGE_THROWCRADS) {
			if (player.getState().getCardList().size() > player.getState()
					.getCurHP()) {
				selectLimit = player.getState().getCardList().size()
						- player.getState().getCurHP();
			}
		}
	}

	/**
	 * ѡ�����ƿ���״̬
	 */
	public void selectShowCardAndClick() {
		// �������Լ��غϣ�ȫ���ã�������ʾ���õ���
		/*
		 * if (player.isSkip()) { unableToUseCard(); disableClick(); } else {
		 * showAvailableCards(); skipP.enableToUse(); }
		 */
		showAvailableCards();
		disableClick();
		if (player.getStageNum() == PlayerIF.STAGE_USECARDS)
			skipP.enableToUse();
	}

	/**
	 * ���ƺ� ��ֹ���а�ť
	 */
	public void disableClick() {
		useP.unableToClick();
		cancelP.unableToClick();
		skipP.unableToClick();
		repaint();
	}

	/**
	 * ����ȷ����ȡ��
	 */
	public void enableOKAndCancel() {
		useP.enableToUse();
		cancelP.enableToUse();
	}

	/**
	 * ֻ����ȡ��
	 */
	public void enableCancel() {
		cancelP.enableToUse();
	}

	/**
	 * ����ʱ�ڣ�ֻ��ȡ��������
	 */
	public void enableToClick() {
		// useP.enableToUse();
		cancelP.enableToUse();
		if (player.getStageNum() == PlayerIF.STAGE_USECARDS)
			skipP.enableToUse();
		repaint();
	}

	/**
	 * ��ʾ���� ���κ�����
	 */
	public void remindToUseALL() {
		for (int i = 0; i < cards.size(); i++) {
			cards.get(i).setEnableToUse(true);
		}
		repaint();
	}

	/**
	 * ��ʾ���� ����Ӧ������ҳ�����ʱ��������ڷ��������� ����̧���ҿ��Ű�ť ������ƥ������
	 */
	public void remindToUse(Integer... type) {
		List<Integer> types = Arrays.asList(type);
		for (int i = 0; i < cards.size(); i++) {
			int t = cards.get(i).getCard().getType();
			if (types.contains(t)) {
				cards.get(i).setEnableToUse(true);
				continue;
			}
		}
		cancelP.enableToUse();
		repaint();
	}

	/**
	 * ��ʾ���� ����Ӧ������ҳ�����ʱ��������ڷ��������� ����̧���ҿ��Ű�ť ������ƥ�仨ɫ
	 */
	public void remindToUse(Colors... color) {
		List<Colors> listColor = Arrays.asList(color);
		for (int i = 0; i < cards.size(); i++) {
			Colors c = cards.get(i).getCard().getColor();
			if (listColor.contains(c)) {
				cards.get(i).setEnableToUse(true);
				continue;
			}
		}
		repaint();
	}

	/**
	 * ����ѡ��
	 */
	public void waitSelect(int limit) {
		// ���ÿɳ���������
		this.selectLimit = limit;
		// ��ʾ��ѡ�����
		selectShowCardAndClick();
	}

	/**
	 * ------------------------------------------------------------------------
	 * �����л��������ڲ��ఴť�Լ���ť���ڲ������� ֮������������Ϊ�ڲ������ʽ�ڱ��������ϱȽϷ���
	 * ----------------------------------------------------------------------
	 */

	/**
	 * �ڲ��� ���Ƶİ�ť
	 */
	class UsePanel extends JPanel {
		private static final long serialVersionUID = -5187604468743452501L;
		final int USE = 1;
		final int CANCEL = 2;
		final int SKIP = 3;
		UsePanel me = this;
		JLabel text = new JLabel();
		String name;

		Image imgEnable = ImgUtil.getPngImgByName("bok");
		Image imgUnable = ImgUtil.getPngImgByName("bok2");
		Image imgDown = ImgUtil.getPngImgByName("bend");
		Image curImg;
		MouseListener listener;

		// ������
		public UsePanel(String name, int type) {
			this.setSize(100, 45);
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			// ��ʼ������
			this.unableToClick();
			Font f = new Font("����", Font.BOLD, 30);
			text.setFont(f);
			text.setForeground(Color.white);
			text.setText(name);
			this.add(text);
			switch (type) {
			case USE:
				listener = new Click();
				break;
			case CANCEL:
				listener = new cancelClick();
				break;
			case SKIP:
				listener = new skipClick();
				break;
			}
			this.addMouseListener(listener);
		}

		/**
		 * ���ð�ť
		 */
		public void unableToClick() {
			this.curImg = imgUnable;
			this.removeMouseListener(listener);
			this.setCursor(Cursor.getDefaultCursor());
			repaint();
		}

		/**
		 * ���ð�ť
		 */
		public void enableToUse() {
			this.curImg = imgEnable;
			if (this.getMouseListeners().length == 0)
				this.addMouseListener(listener);
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			repaint();
		}

		/**
		 * ����
		 */
		public void paint(Graphics g) {
			g.drawImage(curImg, 0, 0, this.getWidth(), this.getHeight(), null);
			super.paintChildren(g);
			g.dispose();
		}

		/**
		 * �ڲ�����ڲ������
		 * 
		 * @author user
		 * 
		 */
		class Click extends MouseAdapter {

			@Override
			public void mousePressed(MouseEvent e) {
				curImg = imgDown;
				me.repaint();

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				curImg = imgEnable;
				me.repaint();
				clicked();
			}

			/*
			 * ������¼�
			 */
			private void clicked() {
				player.getState().setRes(Const_Game.OK);
				if(player.getState().getRes()==Const_Game.SKILL){
					player.getState().setRes(Const_Game.OK);
					return;
				}
				// ����Ǳ�����Ӧ��
				if (player.getState().isRequest()) {
					//getPlayer().getState().setRequest(false);
					//player.getRequest().clear();
					if (!getSelectedList().isEmpty()) {
						getPlayer().getState().setRes(
								getSelectedList().get(0).getCard().getType());
						return;
					}
				}
				unableToClick();
			}
		}

		/**
		 * ȡ����ť�ļ����� click������
		 * 
		 * @author user
		 * 
		 */
		class cancelClick extends Click {
			/*
			 * ��д����¼�
			 */
			@Override
			public void mouseReleased(MouseEvent e) {
				curImg = imgEnable;
				me.repaint();
				player.getState().setRes(Const_Game.CANCEL);
			}
		}

		/**
		 * ���ư�ť click������
		 * 
		 * @author user
		 * 
		 */
		class skipClick extends Click {
			/*
			 * ��д����¼�
			 */
			@Override
			public void mouseReleased(MouseEvent e) {

				curImg = imgEnable;
				if (selectedList != null) {
					for (Panel_Card p : cards) {
						if (p.isSelected) {
							p.unselect();
						}
					}
					selectedList.clear();
				}
				player.setSkip(true);
				disableClick();
			}
		}
	}

	/**
	 * ʵ��ˢ�·���
	 */
	@Override
	public void refresh() {
		// ����������ͼ
		updateCards();
		// �������״̬
		selectedList.clear();
		setSelectLimit(1);
		// ���ѡ��
		target.setNeedCheck(true);
		target.setLimit(1);
		target.getList().clear();
		// ����Ŀ����
		targetCheck = true;
		if (player.getStageNum() == PlayerIF.STAGE_THROWCRADS) {
			targetCheck = false;
		}
		repaint();
	}

	public List<AbstractCard> getUseList() {
		return useList;
	}

	public List<Panel_Card> getSelectedList() {
		return selectedList;
	}

	public AbstractPlayer getPlayer() {
		return player;
	}

	public void setPlayer(AbstractPlayer player) {
		this.player = player;
	}

	public int getSelectLimit() {
		return selectLimit;
	}

	public void setSelectLimit(int selectLimit) {
		this.selectLimit = selectLimit;
	}

	public List<Panel_Card> getCards() {
		return cards;
	}

	public void setCards(List<Panel_Card> cards) {
		this.cards = cards;
	}

	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}

	public UsePanel getUseP() {
		return useP;
	}

	public UsePanel getCancelP() {
		return cancelP;
	}

	public UsePanel getSkipP() {
		return skipP;
	}

	public Panel_Main getMain() {
		return main;
	}

	public boolean isTargetCheck() {
		return targetCheck;
	}

	public void setTargetCheck(boolean targetCheck) {
		this.targetCheck = targetCheck;
	}
}
