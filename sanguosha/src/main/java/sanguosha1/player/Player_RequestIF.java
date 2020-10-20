package sanguosha1.player;
/**
 * �����Ӧ�����ӿ���
 * @author user
 *
 */
public interface Player_RequestIF {
	//ѯ���Ƿ��ɱ
	boolean requestSha();
	//ѯ���Ƿ����
	boolean requestShan();
	//ѯ���Ƿ����
	boolean requestTao();
	//ѯ���Ƿ����и
	boolean requestWuXie();
	//��ȡ��ǰ��Ӧ����
	int getCurType();
	//���õ�ǰ��Ӧ����
	void setCurType(int curType);
	//���״̬
	void clear();
	
	
	/*//��Ӧ�����Ƿ���ס�Է�������
	void  setSkilling(boolean b);
	boolean isSkilling();*/
}
