package sanguosha1.gui.main;

import sanguosha1.card.DelayKitIF;
import sanguosha1.data.constant.Const_UI;
import sanguosha1.data.types.Target;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.PlayerIF;
import sanguosha1.util.ImgUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;


/**
 * ������ҵ���ʾ���
 * 
 * @author user
 * 
 */
public class Panel_Player extends JPanel implements RefreshbleIF,PaintEffectIF {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6279313069197776880L;
	// ����������
	Panel_Main main;
	// ��Ӧ�����ģ��
	AbstractPlayer player;
	//����
	MouseListener listener ;
	// Ѫ��
	Panel_HP hp;
	// ����Ф��
	JLabel jl_img = new JLabel();
	// ��ݰ�
	Panel_Id pn_id = new Panel_Id(this);
	// ���ư�
	CardNum num;
	// װ����
	Panel_Equipments pn_eq ;
	// ����ͼ��
	BufferedImage img;
	BufferedImage bfimg;
	// ���״̬
	public static final int DEAD = -1;
	public static final int NORMAL = 0;
	public static final int DISABLE = 1;
	public static final int SELECTED = 2;
	int PanelState;
	// Ч��״̬
	public static final int DOING = 3;
	public static final int HURT = 4;
	
	int effectState;

	/**
	 * ������
	 * 
	 * @param p
	 * @param main
	 */
	public Panel_Player(AbstractPlayer p, Panel_Main main) {
		this.main = main;
		this.player = p;
		this.setSize(Const_UI.PLAYER_PANEL_WIDTH, Const_UI.PLAYER_PANEL_HEIGHT);
		this.setLayout(null);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//this.setBackground(Color.gray);
		this.setOpaque(false);
		//ע�����
		this.listener = new ClickPlayer();
		//this.addMouseListener(new Click());
		img = (BufferedImage) p.getInfo().getHeadImg();
		bfimg = img.getSubimage(0, 0, img.getWidth(), img.getHeight() / 2);
		//������
		pn_id.setLocation(getWidth() - pn_id.getWidth(), 0);
		this.add(pn_id);
		//Ѫ��
		hp = new Panel_HP(p);
		this.add(hp);
		hp.setLocation(this.getWidth() - hp.getWidth(), this.getHeight() / 3);
		//����Ф��
		this.add(jl_img);
		//װ����
		pn_eq = new Panel_Equipments(p,null,16);
		pn_eq.setSize(getWidth()-35, getHeight()/2);
		pn_eq.setLocation(18, getHeight()/2-10);
		this.add(pn_eq);
		//������
		num = new CardNum();
		this.add(num);
		//��ʼ����
		enableToUse();
	}
	/**
	 * ����
	 */
	public void paint(Graphics g) {
		//super.paint(g);
		//�������
		if(player.getState().isDead()){
			drawDead(g,Const_UI.PANEL_UNABLE);
			PanelState = DEAD;
			pn_id.showAfterDead();
		}
		//����ڻغ���
		if(player.getStageNum()!=PlayerIF.STAGE_END){
			g.drawImage(ImgUtil.getPngImgByName("bd_cur"), 0, 0, this.getWidth(),this.getHeight(),null);
		}
		//�������Ӧ
		if(player.getState().isRequest()){
			g.drawImage(ImgUtil.getPngImgByName("bd_select"), 0, 0, this.getWidth(),this.getHeight(),null);
		}
		//����ѡ��߿�
		switch (PanelState) {
		case NORMAL:
			break;
		case SELECTED:
			g.drawImage(ImgUtil.getPngImgByName("bd_select"), 0, 0, this.getWidth(),this.getHeight(),null);
			break;
		case DEAD:
			g.drawImage(ImgUtil.getPngImgByName("bd_dead"), 0, 0, this.getWidth(),this.getHeight(),null);
		case DISABLE:
			drawDead(g,.5f);
		}
		//��������ͼ��
		g.drawImage(bfimg, 10, 20, this.getWidth() - 20, this.getHeight() / 3,
				null);
		g.drawImage(ImgUtil.getPngImgByName("headborder"), 0, 0, this
				.getWidth(), this.getHeight(), null);
		//���������
		super.paintChildren(g);
		//�����ж���
		if(!player.getState().getCheckedCardList().isEmpty()){
			for (int i = 0; i < player.getState().getCheckedCardList().size(); i++) {
			DelayKitIF d = (DelayKitIF) player.getState().getCheckedCardList().get(i);
			g.setColor(Color.white);
			g.drawString(d.getShowNameInPanel(), 30+getFont().getSize()*i, getHeight()-3);
			}
		}
		
		g.dispose();
	}
	/**
	 * ����
	 */
	public void disableToUse(){
		if(PanelState==DEAD)return;
		this.PanelState = DISABLE;
		this.removeMouseListener(listener);
		this.setCursor(Cursor.getDefaultCursor());
		repaint();
	}
	/**
	 * ��̬��
	 */
	public void setNormal(){
		if(PanelState==DEAD)return;
		this.PanelState = NORMAL;
		this.removeMouseListener(listener);
		this.setCursor(Cursor.getDefaultCursor());
		repaint();
	}
	/**
	 * ����
	 */
	public void enableToUse(){
		if(PanelState==DEAD)return;
		this.PanelState = NORMAL;
		if(getMouseListeners().length==0)this.addMouseListener(listener);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		repaint();
	}
	/**
	 * ʵ��ˢ�·���
	 */
	@Override
	public void refresh() {
		this.PanelState = NORMAL;
		repaint();
		hp.refresh();
		num.repaint();
		pn_eq.refresh();
		//disableToUse();
	}
	
	/**
	 * ��������״̬
	 * @return
	 */
	public void drawDead(Graphics g,float f){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.darkGray);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2.setComposite(AlphaComposite.SrcOver.derive(f));
	}
	// ��ȡ����ģ��
	public AbstractPlayer getPlayer() {
		return player;
	}

	/*
	 * �ڲ��� ���������
	 */
	class ClickPlayer extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			main.contrl.hand.getTarget().add(player);
			//ModuleManagement.getInstance().getTarget().add(player);
			System.out.println("select" + player.getInfo().getName());
			for (Panel_Player p : main.players) {
				if(p.PanelState==DISABLE){
					continue;
				}
				p.PanelState = NORMAL;
				p.repaint();
				//p.disableToUse();
			}
			Target tg = main.contrl.hand.getTarget();
			for (int i = 0; i <tg.getList().size() ; i++) {
				Panel_Player pp = (Panel_Player) tg.getList().get(i).getPanel();
				pp.setPanelState(SELECTED);
				pp.repaint();
			}
		}
	}

	/*
	 * �ڲ��� ��ʾ���������
	 */
	class CardNum extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 592012384557111860L;
		Image backImg = ImgUtil.getPngImgByName("cardNum");
		int num = player.getState().getCardList().size();
		JLabel jl = new JLabel(String.valueOf(num));

		public CardNum() {
			this.setSize(20, 30);
			this
					.setLocation(0, Const_UI.PLAYER_PANEL_HEIGHT-this.getHeight()
							);
			this.add(jl);
			jl.setFont(new Font(Font.DIALOG, Font.BOLD, 21));
			jl.setForeground(Color.RED);
		}

		public void paint(Graphics g) {
			super.paint(g);
			// ���Ʊ���
			g.drawImage(backImg, 0, 0, this.getWidth(), this.getHeight(), null);
			// ����������
			num = player.getState().getCardList().size();
			jl.setText(String.valueOf(num));
			super.paintChildren(g);
		}
	}

	public int getPanelState() {
		return PanelState;
	}
	public void setPanelState(int panelState) {
		PanelState = panelState;
	}
	
	@Override
	public Point getPaintPoint() {
		int x = getWidth()/2+getX();
		int y = getHeight()/2+getY();
		return new Point(x, y);
	}
	
}
