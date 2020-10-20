package sanguosha1.gui.main;

import sanguosha1.card.equipment.AbstractEquipmentCard;
import sanguosha1.card.equipment.ActiveSkillWeaponCardIF;
import sanguosha1.data.types.EquipmentStructure;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.util.ImgUtil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * װ��������� -- ���� -- ���� -- +1�� -- -1��
 * 
 * @author user
 * 
 */
public class Panel_Equipments extends JPanel implements RefreshbleIF {
	private static final long serialVersionUID = -5043769932933498212L;
	// private static final String[] NUMBER= {"һ","��","��","��","��"};
	AbstractPlayer p;
	Panel_Control pc;
	EquipmentStructure eqs;
	// int fontSize;
	CardEquipment att;
	CardEquipment dun;
	CardEquipment ma;
	CardEquipment _ma;
	BufferedImage img = ImgUtil.getJpgImgByName("bd_eq");

	public Panel_Equipments(AbstractPlayer p, Panel_Control pc, int fontSize) {
		this.p = p;
		this.pc = pc;
		eqs = this.p.getState().getEquipment();
		this.setLayout(new GridLayout(4, 1));
		this.setOpaque(false);
		att = new CardEquipment(eqs.getWeapons(), 1, fontSize);
		dun = new CardEquipment(eqs.getArmor(), 2, fontSize);
		ma = new CardEquipment(eqs.getAttHorse(), 3, fontSize);
		_ma = new CardEquipment(eqs.getDefHorse(), 4, fontSize);
		this.add(att);
		this.add(dun);
		this.add(_ma);
		this.add(ma);
	}

	/**
	 * ʵ��ˢ�·���
	 */
	@Override
	public void refresh() {
		update();
		repaint();
	}

	/**
	 * ��������
	 */
	private void update() {
		att.card = p.getState().getEquipment().getWeapons();
		dun.card = p.getState().getEquipment().getArmor();
		ma.card = p.getState().getEquipment().getDefHorse();
		_ma.card = p.getState().getEquipment().getAttHorse();
		enableUseWeapons();
	}

	/**
	 * ��������
	 */
	public void enableUseWeapons() {
		if (att.card != null) {
			att.enbaleUse();
		}
	}

	/**
	 * ���Ʊ���
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(ImgUtil.getJpgImgByName("bg_eq"), 0, -1, getWidth(),
				getHeight(), null);
	}

	/**
	 * ����װ������װ��������ʾ��
	 * 
	 * @author user
	 * 
	 */
	class CardEquipment extends JPanel {
		private static final long serialVersionUID = 3981675201544613463L;
		AbstractEquipmentCard card;
		Font font;
		Border border = BorderFactory.createLineBorder(Color.gray, 1);
		Border border_select = BorderFactory.createLineBorder(Color.red, 1);
		int type;
		boolean isSelected;

		/**
		 * ����
		 */
		public CardEquipment(AbstractEquipmentCard c, int type, int size) {
			this.type = type;
			// this.setBorder(BorderFactory.createLineBorder(Color.green, 1));
			/*
			 * Border inner = BorderFactory.createEtchedBorder(1); Border boder
			 * = BorderFactory.createCompoundBorder(null, inner);
			 */
			this.setBorder(border);
			this.setOpaque(false);
			font = new Font("����", Font.BOLD, size);
		}

		/**
		 * ����
		 */
		public void paint(Graphics g) {
			// g.drawImage(img, 0, 0,this.getWidth(),this.getHeight(), null);
			if (card == null)
				return;
			g.setFont(font);
			g.setColor(Color.gray);
			// ���ƻ�ɫ
			// g.drawImage(card.getColorImg(), this.getWidth()/5,
			// 10,this.getHeight()/2,this.getHeight()/2, null);
			g.drawImage(card.getColorImg(), this.getWidth() / 5, 8, font
					.getSize(), font.getSize(), null);
			// ������ֵ
			g.drawString(card.getNumberToString(), this.getWidth() / 5 * 2 - 8,
					this.getHeight() / 3 * 2 + 5);
			// ��������
			g.drawString(card.getName(), this.getWidth() / 2 - 8, this
					.getHeight() / 3 * 2 + 5);
			super.paintBorder(g);
		}

		/**
		 * ����
		 */
		public void enbaleUse() {
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			if (getMouseListeners().length == 0)
				addMouseListener(ml);
			setBorder(border);
			isSelected = false;
		}

		/*
		 * ����
		 */
		MouseListener ml = new MouseAdapter() {
			ActiveSkillWeaponCardIF asc;
			@Override
			public void mousePressed(MouseEvent e) {
				if(!(card instanceof ActiveSkillWeaponCardIF)){
					return;
				}else{
					asc = (ActiveSkillWeaponCardIF) card;
				}
				if (!isSelected) {
					if(!asc.onClick_open(pc.hand))return;
					setBorder(border_select);
					isSelected = true;
				} else {
					setBorder(border);
					isSelected = false;
					asc.onClick_close(pc.hand);
					pc.refresh();
				}
				repaint();
			}

		};
	}
}
