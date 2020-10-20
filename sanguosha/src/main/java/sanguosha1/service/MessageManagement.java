package sanguosha1.service;

import sanguosha1.data.enums.ErrorMessageType;

/**
 * ��Ϣ������
 * @author user
 *
 */
public class MessageManagement {
	public static void printErroMsg(ErrorMessageType emt ){
		String msg = null;
		switch (emt){
		case cannotUseNow:
			msg = "δ������ʱ��";
			break;
		case cannotUseCause_FullHP:
			msg = "��Ѫ������ʹ��";
			break;
		case hasUsed:
			msg = "�Ѿ�ʹ�ù����޷��ٴ�ʹ��";
			break;
		case hasUsed_Sha:
			msg = "�Ѿ�����ɱ";
			break;
		}
		ViewManagement.getInstance().printChatMsg(msg);
	}
}
