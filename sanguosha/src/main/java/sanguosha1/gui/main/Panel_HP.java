package sanguosha1.gui.main;

import sanguosha1.data.constant.Const_UI;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.util.ImgUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Ѫ����
 * --����
 * --��ǰѪ��
 * --��Ѫ
 * --��Ѫ
 * @author user
 *
 */
public class Panel_HP extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 259553919601970080L;
	//����ģ��
	AbstractPlayer player;
	//Ѫ��ͼ��
	Image mhp = ImgUtil.getPngImgByName("hp2");
	//Ѫ��ͼ��
	Image hp = ImgUtil.getPngImgByName("hp");
	//Ѫ�۱�ǩ����
	JLabel[] hps ;
	
	/**
	 * ������
	 * @param p
	 */
	public Panel_HP(AbstractPlayer p){
		this.player = p;
		hps = new JLabel[p.getInfo().getMaxHP()];
		this.setOpaque(false);
		this.setSize(Const_UI.HPPANEL_WIDTH	,Const_UI.HPPANEL_HEIGHT);
		this.setLayout(new GridLayout(5,1));
		//����Ѫ�۱�ǩ
		for (int i = 0; i < p.getInfo().getMaxHP(); i++) {
			hps[i] = new JLabel(new ImageIcon(mhp));
			this.add(hps[i]);
		}
		for (int i = 0; i < p.getState().getCurHP(); i++) {
			hps[i].setIcon(new ImageIcon(hp));
		}
	}
	
	/**
	 * ˢ��Ѫ��
	 */
	public void refresh(){
		for (int i = 0; i < player.getInfo().getMaxHP(); i++) {
			hps[i] .setIcon(new ImageIcon(mhp));
		}
		for (int i = 0; i < player.getState().getCurHP(); i++) {
			hps[i].setIcon(new ImageIcon(hp));
		}
	}
}
