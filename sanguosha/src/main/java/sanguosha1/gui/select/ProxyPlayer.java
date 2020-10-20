package sanguosha1.gui.select;

import sanguosha1.player.AbstractPlayer;

/**
 * �������
 * ֻ�б�ʶid��ͼ����ʾ
 * �洢һ��������������ã��ӳټ���
 * ��ȷ��ѡ��󣬲Ž�����������
 * @author user
 *
 */
public class ProxyPlayer {
	AbstractPlayer player ;
	String id ;
	String imgName;
	
	public ProxyPlayer(String characterID,String imgName){
		this.id = characterID;
		this.imgName = imgName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	
	
}
