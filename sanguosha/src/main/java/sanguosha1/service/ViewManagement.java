package sanguosha1.service;

import sanguosha1.gui.main.*;
import sanguosha1.player.AbstractPlayer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ��ͼ������ ������Ϸ�����ˢ�µȲ���
 * 
 * @author user
 * 
 */
public class ViewManagement {

	// ��������
	private static ViewManagement instance;

	private ViewManagement() {
	}

	public static ViewManagement getInstance() {
		if (instance == null) {
			instance = new ViewManagement();
		}
		return instance;
	}

	/**
	 * ����
	 */
	public static void reset() {
		instance = new ViewManagement();
	}

	// ��ˢ���������
	List<RefreshbleIF> refreshList = new ArrayList<RefreshbleIF>();
	// ��Ϣ���
	JTextArea msg;
	// �������
	JTextArea msgChat;
	// ��ʾ��Ϣ
	Panel_Prompt prompt;
	// ս����Ϣ���
	Panel_Message message;

	/**
	 * ֪ͨ�������ˢ��
	 */
	public void refreshAll() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < refreshList.size(); i++) {
					refreshList.get(i).refresh();
					// System.out.println(i+"ˢ��");
				}
				printChatMsg("refresh!");
			}
		});
	}

	/**
	 * ��ӡ��Ϣ
	 */
	public void printMsg(String str) {
		msg.append(str + "\n");
		msg.setCaretPosition(msg.getText().length());
		// printBattleMsg(str);
	}

	/**
	 * ������Ϣ׷��
	 * 
	 * @return
	 */
	public void printChatMsg(String msg) {
		// msgChat.append("��AI��Ȩ��������ȥ�����˸��˵ã��᲻���氡"+"\n");
		msgChat.append(msg + "\n");
		msgChat.setCaretPosition(msgChat.getText().length());
	}

	/**
	 * ս����Ϣ��ʾ ս����ϢĬ��ͬʱ����Ϣ������
	 * 
	 * @return
	 */
	public void printBattleMsg(String str) {
		message.addMessage(str);
		message.repaint();
		printMsg(str);
	}

	/**
	 * ѯ������Ƿ񷢶�����
	 * 
	 * @return
	 */
	public void ask(final AbstractPlayer player, final String skillName) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Panel_Control pc = (Panel_Control) player.getPanel();
				Panel_HandCards ph = pc.getHand();
				ViewManagement.getInstance().getPrompt().show_Message(
						"�Ƿ񷢶�" +skillName);
				ph.unableToUseCard();
				ph.enableOKAndCancel();

			}
		});
	}

	public List<RefreshbleIF> getRefreshList() {
		return refreshList;
	}

	public JTextArea getMsg() {
		return msg;
	}

	public void setMsg(JTextArea msg) {
		this.msg = msg;
	}

	public void setMsgChat(JTextArea msgChat) {
		this.msgChat = msgChat;
	}

	public Panel_Prompt getPrompt() {
		return prompt;
	}

	public void setPrompt(Panel_Prompt prompt) {
		this.prompt = prompt;
	}

	public void setMessage(Panel_Message message) {
		this.message = message;
	}

}
