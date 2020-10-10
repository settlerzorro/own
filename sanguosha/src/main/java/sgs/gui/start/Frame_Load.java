package sgs.gui.start;

import sgs.Main;
import sgs.util.ImgUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * 载入窗体
 * @author user
 *
 */
public class Frame_Load extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5475941235030060504L;
	BufferedImage img ;
	public Frame_Load(){
		img = ImgUtil.getJpgImgByName("loadimg");
		setUI();
		Runnable run = new Runnable() {

			@Override
			public void run() {
				showIt();
			}
		};
		new Thread(run).start();
	}
	/**
	 * 设置UI
	 */
	public void setUI(){
		setBounds(new Rectangle(450, 300));
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		setVisible(true);
	}
	/**
	 * 绘制背景
	 */
	public void paint(Graphics g){
		//super.paint(g);
		if(img!=null){
			g.drawImage(img, 0, 0, null);
		}
	}
	/**
	 * 未加载完成则一直显示
	 */
	public void showIt(){
		while(!Main.isFinished){

		}
		setVisible(false);
		dispose();
	}
	
}
