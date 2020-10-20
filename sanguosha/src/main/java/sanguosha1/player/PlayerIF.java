package sanguosha1.player;


/**
 * �����Ľӿ�
 * @author user
 *
 */
public interface PlayerIF {
	/**
	 * 6�׶�״̬����
	 * ���Ա�ʾ��Ҵ����ĸ��׶�
	 */
	public static final int STAGE_BEGIN = 1; 
	public static final int STAGE_CHECK = 2; 
	public static final int STAGE_ADDCARDS = 3; 
	public static final int STAGE_USECARDS = 4; 
	public static final int STAGE_THROWCRADS = 5; 
	public static final int STAGE_END = 6; 
	
	//���뼼��
	void loadSkills(String name) ;
	//ִ�лغ�
	void process();
	//ˢ�¹��������
	void refreshView();
	
}
