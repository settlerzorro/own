package sanguosha1.gui;

import sanguosha1.card.AbstractCard;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ModuleManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * debug����
 * 
 * @author user
 * 
 */
public class Frame_Debug extends JFrame {
	private static final long serialVersionUID = -2564964062995402777L;
	DefaultListModel mod = new DefaultListModel();
	JList listCards = new JList(mod);
	JScrollPane jsp = new JScrollPane(listCards);

	DefaultListModel mod2 = new DefaultListModel();
	JList listCards2 = new JList(mod2);
	JScrollPane jsp2 = new JScrollPane(listCards2);

	JTextArea txt = new JTextArea();
	JScrollPane jsp3 = new JScrollPane(txt);
	JButton jbUpdate = new JButton("update");

	public Frame_Debug() {
		setTitle("debug");
		setLayout(new GridLayout(2, 2));
		setBounds(50, 100, 400, 400);
		setVisible(true);
		add(jsp);
		add(jsp2);
		add(jsp3);
		add(jbUpdate);
		jsp.setBorder(BorderFactory.createTitledBorder("��ǰ�ƶѣ�"));
		jsp2.setBorder(BorderFactory.createTitledBorder("�����ƶѣ�"));
		jsp3.setBorder(BorderFactory.createTitledBorder("�������ƣ�"));
		jbUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
	}

	public void update() {
		//�����ƶ�
		List<AbstractCard> list = ModuleManagement.getInstance().getCardList();
		if (list != null) {
			mod.removeAllElements();
			for (int i = 0; i < list.size(); i++) {
				mod.addElement(list.get(i).getId()+"--"+list.get(i).toString());
			}
		}
		listCards.updateUI();
		//�������ƶ�
		list = ModuleManagement.getInstance().getGcList();
		if (list != null) {
			mod2.removeAllElements();
			for (int i = 0; i < list.size(); i++) {
				mod2.addElement(list.get(i));
			}
		}
		listCards2.updateUI();
		
		//���³�������������
		txt.setText(null);
		List<AbstractPlayer> listP = ModuleManagement.getInstance().getPlayerList();
		for (int i = 1; i < listP.size(); i++) {
			txt.append(listP.get(i).getInfo().getName()+"���ƣ�"+"\n");
			for (AbstractCard c : listP.get(i).getState().getCardList()) {
				txt.append("----"+c.toString()+"\n");
			}
		}
	}
}
