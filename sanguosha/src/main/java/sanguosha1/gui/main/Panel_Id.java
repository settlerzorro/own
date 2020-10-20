package sanguosha1.gui.main;

import sanguosha1.data.constant.Const_UI;
import sanguosha1.data.enums.Identity;
import sanguosha1.util.ImgUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * ��ʾ(ѡ��)�����ݵ����
 * @author user
 *
 */
public class Panel_Id extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -474307825678140295L;
	//������������
	Panel_Player panel ;
	//��ʾ���
	JLabel jl_show = new JLabel(new ImageIcon(ImgUtil.getPngImgByName("id_null")));
	//�ҳ������ڼ�
	JLabel jl_Zhong =new JLabel(new ImageIcon(ImgUtil.getPngImgByName("id_zhong")));
	JLabel jl_Fan = new JLabel(new ImageIcon(ImgUtil.getPngImgByName("id_fan")));
	JLabel jl_Nei = new JLabel(new ImageIcon(ImgUtil.getPngImgByName("id_nei")));
	//����
	boolean isOpen;
	
	public Panel_Id(Panel_Player pp){
		this.panel = pp;
		this.setOpaque(false);
		this.setLayout(new GridLayout(4, 1));
		this.setSize(40, Const_UI.PLAYER_PANEL_HEIGHT);
		add(jl_show);
		jl_show.addMouseListener(new clickOpen());
		//this.open();
	}
	//��
	public void open(){
		add(jl_Zhong);
		add(jl_Fan);
		add(jl_Nei);
		validate();
		repaint();
		addListener();
		isOpen = true;
	}
	//����
	public void close(){
		remove(jl_Zhong);
		remove(jl_Fan);
		remove(jl_Nei);
		repaint();
		validate();
		isOpen = false;
	}  
	//ע�����
	public void addListener(){
		jl_Zhong.addMouseListener(new clickSelect(jl_Zhong));
		jl_Fan.addMouseListener(new clickSelect(jl_Fan));
		jl_Nei.addMouseListener(new clickSelect(jl_Nei));
	}
	
	/*
	 * ���������ݼ���
	 */
	class clickSelect extends MouseAdapter{
		JLabel jl ;
		public clickSelect(JLabel jl){
			this.jl = jl;
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			//super.mouseClicked(e);
			close();
			jl_show.setIcon(jl.getIcon());
		}
	}
	
	/**
	 * ��������ʾ
	 */
	public void showAfterDead(){
		Identity id = panel.getPlayer().getState().getId();
		switch(id){
		case FANZEI:
			jl_show.setIcon(jl_Fan.getIcon());
			break;
		case NEIJIAN:
			jl_show.setIcon(jl_Nei.getIcon());
			break;
		case ZHONGCHEN:
			jl_show.setIcon(jl_Zhong.getIcon());
			break;
		}
	}
	
	/*
	 * ����򿪼���
	 */
	class clickOpen extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			jl_show.setIcon( new ImageIcon(ImgUtil.getPngImgByName("id_null")));
			if(!isOpen){
				open();
			}else{
				close();
			}
		}
	}
}
