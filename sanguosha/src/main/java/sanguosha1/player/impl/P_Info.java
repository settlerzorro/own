package sanguosha1.player.impl;

import sanguosha1.data.enums.Country;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * �����Ϣ��
 * ��װ��ҵĻ�����Ϣ����
 * @author user
 *
 */
public class P_Info {
	
	//��������
	protected String name ;
	//Ѫ������
	protected int maxHP;
	//�����Ա� ��-�У���-Ů
	protected boolean sex;
	//��������
	protected Country country ;
	//ͷ��
	protected Image headImg;
	//���ߵ���
	protected List<Integer> immuneCard = new ArrayList<Integer>();
	//�������ܵ�����
	protected List<String> skillName = new ArrayList<String>();
	//����������
	protected List<String> lockingSkill = new ArrayList<String>();
	


	public P_Info(){
		
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Image getHeadImg() {
		return headImg;
	}

	public void setHeadImg(Image headImg) {
		this.headImg = headImg;
	}

	public List<Integer> getImmuneCard() {
		return immuneCard;
	}


	public void setImmuneCard(List<Integer> immuneCard) {
		this.immuneCard = immuneCard;
	}


	public List<String> getSkillName() {
		return skillName;
	}


	public void setSkillName(List<String> skillName) {
		this.skillName = skillName;
	}


	public List<String> getLockingSkill() {
		return lockingSkill;
	}


	public void setLockingSkill(List<String> lockingSkill) {
		this.lockingSkill = lockingSkill;
	}


	
}
