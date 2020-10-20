package sanguosha1.data.enums;
/**
 * ��Ϸ�����ļ��ֽ��
 * @author user
 *
 */
public enum GameOver {
	FANZEI_WIN,
	ZHUGONG_WIN,
	NEIJIAN_WIN;
	
	public String getWinner(){
		switch(this){
		case FANZEI_WIN:
			return "����ʤ";
		case ZHUGONG_WIN:
			return "����ʤ";
		case NEIJIAN_WIN:
			return "�ڼ�ʤ";
		}
		return "";
	}
	
	public String getWords(){
		switch(this){
		case FANZEI_WIN:
			return "�������������쵱��";
		case ZHUGONG_WIN:
			return "�⽭ɽ��ʼ�����ҵ�";
		case NEIJIAN_WIN:
			return "���´��ƣ�Ϊ������";
		}
		return "";
	}
}
