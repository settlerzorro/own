package sanguosha1.gui.main;

import sanguosha1.data.constant.Const_UI;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.util.ImgUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * ���ͷ���������
 * --ͷ��ͼƬ
 * --����
 * --Ѫ��
 * --���
 * @author user
 *
 */
public class Panel_HeadImg extends JPanel implements RefreshbleIF{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1541397598002315656L;
	AbstractPlayer player;
	//�߿�ͼ��
	Image border;
	//ͷ��ͼ��
	Image img ;
	//Ѫ����
	Panel_HP jp_HP ;
	//������
	JLabel jl_id ;
	//�������
	Panel_Skill skill;
	//��ҿ������
	Panel_Control pc;
	/**
	 * ������
	 * @param p
	 */
	public Panel_HeadImg(AbstractPlayer p,Panel_Control pc){
		this.pc = pc;
		player = p;
		border = ImgUtil.getPngImgByName("headborder");
		img = p.getInfo().getHeadImg();
		jp_HP = new Panel_HP(p);
		this.setLayout(null);
		this.add(jp_HP);
		jp_HP.setLocation(Const_UI.FRAME_WIDTH/5-jp_HP.getWidth(), 25);
		jl_id = new JLabel(new ImageIcon(ImgUtil.getPngImgByName("id_zhu")));
		jl_id.setSize(35, 50);
		this.add(jl_id);
		jl_id.setLocation(jp_HP.getX()-jl_id.getWidth(), 8);
		skill = new Panel_Skill(p);
		this.add(skill);
		//���Լ���
		addMouseListener(listen);
	}
	
	/**
	 * ��д���Ʒ���
	 */
	public void paint(Graphics g){
		if(img!=null){
			g.drawImage(img, 15, 20,this.getWidth()-25,this.getHeight()-30,null);
		}
		if(border!=null){
			g.drawImage(border, 0, 0,this.getWidth(),this.getHeight(), null);
		}
		//����ڻغ���
		/*if(player.getStageNum()!=PlayerIF.STAGE_END){
			g.drawImage(ImgUtil.getPngImgByName("bd_cur"), 10, 10, this.getWidth(),this.getHeight(),null);
		}
		//�������Ӧ
		if(player.getState().isRequest()){
			g.drawImage(ImgUtil.getPngImgByName("bd_select"), 10, 10, this.getWidth(),this.getHeight(),null);
		}*/
		super.paintChildren(g);
	}
	
	/**
	 * ʵ��ˢ�·���
	 */
	@Override
	public void refresh() {
		//Ѫ����ˢ��
		jp_HP.refresh();
		//���ܰ�ˢ��
		skill.refresh();
		repaint();
	}
	
	/**
	 * ����
	 */
	MouseListener listen = new MouseAdapter() {

		@Override
		public void mousePressed(MouseEvent e) {
			pc.hand.target.add(player);
		}
		
	};
}
