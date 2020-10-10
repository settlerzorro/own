package sgs.gui.main;

import sgs.data.constant.Const_UI;
import sgs.player.AbstractPlayer;
import sgs.util.ImgUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 血条板
 * --上限
 * --当前血量
 * --加血
 * --扣血
 * @author user
 *
 */
public class Panel_HP extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 259553919601970080L;
	//人物模型
	AbstractPlayer player;
	//血槽图像
	Image mhp = ImgUtil.getPngImgByName("hp2");
	//血快图像
	Image hp = ImgUtil.getPngImgByName("hp");
	//血槽标签数组
	JLabel[] hps ;
	
	/**
	 * 构造器
	 * @param p
	 */
	public Panel_HP(AbstractPlayer p){
		this.player = p;
		hps = new JLabel[p.getInfo().getMaxHP()];
		this.setOpaque(false);
		this.setSize(Const_UI.HPPANEL_WIDTH	,Const_UI.HPPANEL_HEIGHT);
		this.setLayout(new GridLayout(5,1));
		//加载血槽标签
		for (int i = 0; i < p.getInfo().getMaxHP(); i++) {
			hps[i] = new JLabel(new ImageIcon(mhp));
			this.add(hps[i]);
		}
		for (int i = 0; i < p.getState().getCurHP(); i++) {
			hps[i].setIcon(new ImageIcon(hp));
		}
	}
	
	/**
	 * 刷新血条
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
