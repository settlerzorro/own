package sanguosha1.gui.select;

import sanguosha1.data.constant.Const_UI;
import sanguosha1.gui.Frame_Main;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.Player;
import sanguosha1.service.ModuleManagement;
import sanguosha1.util.ConfigFileReadUtil;
import sanguosha1.util.ImgUtil;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * ѡ���佫����Ľ���
 * 
 * @author user
 * 
 */
public class Panel_Select extends JPanel {

	private static final long serialVersionUID = -8505197231593797314L;
	final int grid = 5;
	//��ս��
	public static List<AbstractPlayer> list = new ArrayList<AbstractPlayer>();
	// ����ͼ
	BufferedImage bgimg = ImgUtil.getJpgImgByName("bg");
	// ��ǰѡ��ı߿�
	Border border = BorderFactory.createLineBorder(Color.green, 5);
	// ��ʾ����
	JPanel showPanel = new JPanel();
	JScrollPane jsp;
	// ѡ����
	MySelectPanel selectPanel = new MySelectPanel();
	Pane_ProxyPlayer[] pps = new Pane_ProxyPlayer[5];
	Pane_ProxyPlayer curPP;
	// ѡ���α�
	int index;
	//��ť���
	ClickPanel cp1 = new ClickPanel("����",0);
	ClickPanel cp2 = new ClickPanel("��ս",1);
	
	public Panel_Select() {
		this.setSize(Const_UI.FRAME_WIDTH, Const_UI.FRAME_HEIGHT);
		this.setLocation(0, 0);
		this.setLayout(null);
		// ��Ӱ��߶�
		showPanel.setPreferredSize(new Dimension(Const_UI.PROXYWIDTH * 5,
				Const_UI.PROXYHEIGHT * 1));
		//��Ӱ����
		showPanel.setLayout(new GridLayout(0, 7, 2, 2));
		showPanel.setBackground(Color.black);
		loadProxyPlayers();
		jsp = new JScrollPane(showPanel);
		jsp.setSize(Const_UI.PROXYWIDTH * 5 + 10, Const_UI.FRAME_HEIGHT - 300);
		jsp.setLocation(Const_UI.PROXYWIDTH/2+10, 10);
		jsp
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setOpaque(false);
		jsp.getVerticalScrollBar().setUnitIncrement(20);
		jsp.getHorizontalScrollBar().setBlockIncrement(80);
		TitledBorder tb = BorderFactory.createTitledBorder("�㽫̨");
		tb.setTitlePosition(TitledBorder.TOP);
		Font font = new Font("����", Font.BOLD, 30);
		tb.setTitleFont(font);
		tb.setTitleColor(Color.white);
		jsp.setBorder(tb);
		System.out.println(jsp.getHorizontalScrollBar().getMinimum());
		jsp.getViewport().setOpaque(false);
		add(jsp);

		selectPanel.setSize(Const_UI.PROXYWIDTH * 6+20, Const_UI.PROXYHEIGHT + 50);
		selectPanel.setLocation(10, jsp.getHeight() + 20);
		selectPanel.setOpaque(false);
		TitledBorder tb2 = BorderFactory.createTitledBorder("��ս��");
		tb2.setTitleFont(font);
		tb2.setTitleColor(Color.white);
		tb2.setBorder(null);
		//selectPanel.setBorder(tb2);
		selectPanel.setLayout(null);
		for (int i = 0; i < pps.length; i++) {
			pps[i] = new Pane_ProxyPlayer(null, this);
			selectPanel.add(pps[i]);
			if (i == 4) {
				pps[i].setLocation(selectPanel.getWidth() - pps[i].getWidth()
						- 20, 35);
			} else {
				pps[i].setLocation(Const_UI.PROXYWIDTH * i + 18, 35);
			}
		}

		
		cp1.setLocation(Const_UI.PROXYWIDTH * 4 + 18, 40);
		cp2.setLocation(Const_UI.PROXYWIDTH * 4 + 18,  selectPanel.getHeight()-cp2.getHeight()-20);
		cp2.disableUse();
		selectPanel.add(cp1);
		selectPanel.add(cp2);
		paintCurPP();
		add(selectPanel);
	}

	/*
	 * ���Ƶ�ǰѡ��
	 */
	private void paintCurPP() {
		for (Pane_ProxyPlayer pp : pps) {
			pp.resetBorder();
		}
		pps[index].setBorder(border);
	}

	/*
	 * �����佫
	 */
	private void loadProxyPlayers() {
		List<ProxyPlayer> list = ConfigFileReadUtil.getProxyPlayersFromXML();
		for (int i = 0; i < list.size(); i++) {
			Pane_ProxyPlayer pp = new Pane_ProxyPlayer(list.get(i), this);
			pp.addListener();
			showPanel.add(pp);
		}
	}

	/**
	 * ����
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(bgimg, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	/**
	 * ѡ��1��
	 */
	public void selectOne(ProxyPlayer player) {
		pps[index].setPp(player);
		pps[index].repaint();
		if (index < 4) {
			index++;
			paintCurPP();
		} else {
			// ����ȷ��
			cp2.enbaleUse();
		}
	}

	public Pane_ProxyPlayer getCurPP() {
		return curPP;
	}

	public void setCurPP(Pane_ProxyPlayer curPP) {
		this.curPP = curPP;
	}

	ActionListener redo2 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {}
	};

	/**
	 * ��ť���
	 */
	class ClickPanel extends JPanel {
		private static final long serialVersionUID = 6842906804807502608L;
		JLabel text = new JLabel();
		Image imgEnable = ImgUtil.getPngImgByName("bok");
		Image imgUnable = ImgUtil.getPngImgByName("bok2");
		Image imgDown = ImgUtil.getPngImgByName("bend");
		Image curImg;
		MouseListener ml;
		String name;

		public ClickPanel(String name, int type) {
			this.setSize(140, 60);
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			Font f = new Font("����", Font.BOLD, 40);
			text.setFont(f);
			text.setForeground(Color.white);
			text.setText(name);
			this.add(text);
			if(type==0){
				ml = redo;
			}else{
				ml = finish;
			}
			enbaleUse();
		}

		public void enbaleUse() {
			curImg = imgEnable;
			if(getMouseListeners().length==0)addMouseListener(ml);
			repaint();
		}

		public void disableUse() {
			curImg = imgUnable;
			if(getMouseListeners().length>0)removeMouseListener(ml);
			repaint();
		}

		public void paint(Graphics g) {
			g.drawImage(curImg, 0, 0, this.getWidth(), this.getHeight(), null);
			super.paintChildren(g);
		}

		MouseListener redo = new MyClickListen() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (index == 0)
					return;
				if (index == 4) {
					if (pps[index].getPp() != null) {
						pps[index].setPp(null);
						pps[index].repaint();
					} else {
						index--;
						pps[index].setPp(null);
						pps[index].repaint();
						paintCurPP();
					}
					cp2.disableUse();
					return;
				}
				index--;
				pps[index].setPp(null);
				pps[index].repaint();
				paintCurPP();
			
			}
			
		};

		MouseListener finish = new MyClickListen() {

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				list=new ArrayList<AbstractPlayer>();
				for (int i = 0; i < pps.length; i++) {
					Pane_ProxyPlayer pp = pps[i];
					String id = pp.getPp().getId();
					AbstractPlayer p = new Player(id);
					list.add(0,p);
					System.out.println("����"+p.getInfo().getName());
				}
				ModuleManagement.reset();
				Frame_Main.me.loadMain();
			}
			
		};

		/*
		 * ����
		 */
		class MyClickListen extends MouseAdapter {

			@Override
			public void mouseReleased(MouseEvent e) {
				curImg = imgEnable;
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				curImg = imgDown;
				repaint();
			}
		}
	}

	class MySelectPanel extends JPanel{
		private static final long serialVersionUID = -3707416772344129876L;

		public void paintComponent(Graphics g){
			g.drawImage(ImgUtil.getJpgImgByName("bg_control"),0, 0,this.getWidth(),this.getHeight(),null);
		}
	}
}
