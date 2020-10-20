package sanguosha1.gui.main;

import sanguosha1.data.constant.Const_Game;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.skills.LockingSkillIF;
import sanguosha1.skills.SkillIF;
import sanguosha1.skills.SkillMultiIF;
import sanguosha1.util.ImgUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * ��������� �������������ܺͱ������ܵİ�ť Ĭ���ȼ����������ܣ��ټ��ر���
 * 
 * @author user
 * 
 */
public class Panel_Skill extends JPanel implements RefreshbleIF {
	private static final long serialVersionUID = 2040720083702356144L;
	AbstractPlayer p;
	Skill s1;
	Skill s2;

	public Panel_Skill(AbstractPlayer p) {
		this.p = p;
		this.setSize(170, 40);
		this.setLayout(new GridLayout(0, 3));
		this.setOpaque(false);
		this.setLocation(20, 150);
		loadSkills();
	}

	// װ�ؼ���
	private void loadSkills() {
		//��������
		List<SkillIF> skills = p.getState().getSkill();
		if (!skills.isEmpty()) {
			for (int i = 0; i < skills.size(); i++) {
				add(new Skill(p.getState().getSkill().get(i)));
			}
		}
		// ��������
		List<LockingSkillIF> lSkills = p.getState().getLockingSkill();
		if (!lSkills.isEmpty()) {
			for (int i = 0; i < lSkills.size(); i++) {
				LockingSkillIF ls = lSkills.get(i);
				if (ls instanceof SkillMultiIF) {
					SkillMultiIF sm = (SkillMultiIF) ls;
					for (int j = 0; j < sm.getNameList().size(); j++) {
						add(new Skill(sm.getNameList().get(j), 0));
					}
				} else {
					s2 = new Skill(ls.getName(), 0);
					add(s2);
				}
			}
		}
	}

	/**
	 * ˢ��
	 */
	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	/**
	 * �ڲ��� ���ܵİ�ť
	 */
	class Skill extends JPanel {
		private static final long serialVersionUID = -5187604468743452501L;

		JLabel text = new JLabel();
		String name;

		Image imgEnable = ImgUtil.getPngImgByName("bok");
		Image imgUnable = ImgUtil.getPngImgByName("bok2");
		Image imgDown = ImgUtil.getPngImgByName("bend");
		Image curImg;
		MouseListener listener = new skillListener();
		SkillIF skill ;
		// ������
		public Skill(SkillIF skill) {
			this.skill = skill;
			this.name = skill.getName();
			createUI();
			// ��ʼ������
			this.enableToUse();
		}

		// ���أ��������������
		public Skill(String name, int type) {
			this.name = name;
			createUI();
			curImg = imgUnable;
		}

		private void createUI() {
			this.setSize(100, 50);
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			curImg = imgEnable;
			Font f = new Font("����", Font.BOLD, 22);
			text.setFont(f);
			text.setForeground(Color.white);
			text.setText(name);
			this.add(text);
		}

		/**
		 * ���ð�ť
		 */
		public void unableToClick() {
			this.curImg = imgUnable;
			this.removeMouseListener(listener);
			this.setCursor(Cursor.getDefaultCursor());
			repaint();
		}

		/**
		 * ���ð�ť
		 */
		public void enableToUse() {
			this.curImg = imgEnable;
			if (this.getMouseListeners().length == 0)
				this.addMouseListener(listener);
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			repaint();
		}

		/**
		 * ����
		 */
		public void paint(Graphics g) {
			g.drawImage(curImg, 0, 0, this.getWidth(), this.getHeight(), null);
			super.paintChildren(g);
			g.dispose();
		}

		/**
		 * ��������
		 * 
		 * @author user
		 * 
		 */
		class skillListener extends MouseAdapter {

			@Override
			public void mousePressed(MouseEvent e) {
				curImg = imgDown;
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				curImg = imgEnable;
				repaint();
			}
			
			/**
			 * ���ܷ�����ԭ��
			 * ��������һ�㶼��һ��runnable
			 * �ڱ�����Ӧ�׶Σ�ֱ��newһ��thread���м���
			 * �ڳ��ƽ׶Σ���Ҫͨ���ź�������process�൱ǰ��ʲô
			 * ����Ƚ��ź�����Ϊskill��process����ס������Ϊprocess�н��ܵ�skill���źź�
			 * ��ֱ�ӵ��ü����б��еĵ�һ��������ڵ���¼�����ʱ������ǰ�����skill�Ƶ��б��һ��ȥ
			 * 
			 * ��ע��
			 * ������ֻ�����ǻ��ڵ�ǰ�ܹ��µ�һ��ѡ��δ�������ŷ���
			 * ������ˣ�ϣ������ש����
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				// ��������
				p.getState().getSkill().remove(skill);
				p.getState().getSkill().add(0, skill);
				if (p.getState().isRequest()) {
					Thread t = new Thread((Runnable) p.getState().getSkill().get(0));
					t.start();
				} else {
					p.getState().setRes(Const_Game.SKILL);
				}
			}
		}
	}
}
