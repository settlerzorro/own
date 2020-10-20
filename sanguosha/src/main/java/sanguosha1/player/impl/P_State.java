package sanguosha1.player.impl;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.DelayKitIF;
import sanguosha1.data.enums.Identity;
import sanguosha1.data.types.EquipmentStructure;
import sanguosha1.skills.LockingSkillIF;
import sanguosha1.skills.SkillIF;

import java.util.ArrayList;
import java.util.List;


/**
 * ���״̬��
 * ��װ��Ϸʱ��ҵĸ�������
 * @author user
 *
 */
public class P_State {
	//��ҵ����
	protected Identity id ;
	//��ǰѪ��
	protected int curHP;
	//�Ƿ�����
	protected boolean isDead;
	//���Ƽ���
	protected List<AbstractCard> cardList ;
	//������
	protected List<Integer> immuneCards;
	//�Ƿ�ΪAI
	protected boolean isAI;
	//װ������
	protected EquipmentStructure equipment;
	//���е��ж��Ƽ���
	protected List<AbstractCard> checkedCardList;
	//�غ��е�ǰ�������
	protected List<AbstractCard> usedCard;
	//��������
	protected int attDistance ;
	//��������
	protected int defDistance ;
	//��������
	protected List<SkillIF> skill;
	//��������
	protected List<LockingSkillIF> lockingSkill;
	//�����˺�ֵ
	protected int extDamage;
	//�Ƿ����ɱ
	protected boolean usedSha ;
	//�Ƿ�����Ӧ����״̬
	protected boolean isRequest;
	//��Ӧ���˵Ľ��
	protected int res;
	//������ѡ��ʱ��ѡ�����
	protected AbstractCard selectCard;
	/**
	 * ����
	 * @param info
	 */
	public P_State(P_Info info){
		cardList = new ArrayList<AbstractCard>();
		equipment = new EquipmentStructure();
		checkedCardList = new ArrayList<AbstractCard>();
		usedCard = new ArrayList<AbstractCard>();
		skill = new ArrayList<SkillIF>();
		lockingSkill = new ArrayList<LockingSkillIF>();
		attDistance = 1;
		defDistance = 0;
		extDamage = 0;
		curHP = info.getMaxHP();
		immuneCards = new ArrayList<Integer>(info.getImmuneCard());
	}

	
	/**
	 * �غϿ�ʼǰ��һЩ״̬���
	 */
	public void reset(){
		
	}
	/**
	 * �Ƿ���ĳ���ӳٽ���
	 * @param type
	 * @return
	 */
	public boolean hasDelayKit(int type){
		for (AbstractCard c : checkedCardList) {
			DelayKitIF d = (DelayKitIF) c;
			if(d.getKitCardType() == type){
				return true;
			}
		}
		return false;
	}
	public void attChange(int n){
		attDistance += n;
	}
	
	public void defChange(int n){
		defDistance+=n;
	}
	public List<AbstractCard> getCardList() {
		return cardList;
	}

	public void setCardList(List<AbstractCard> cardList) {
		this.cardList = cardList;
	}

	

	public EquipmentStructure getEquipment() {
		return equipment;
	}

	public void setEquipment(EquipmentStructure equipment) {
		this.equipment = equipment;
	}

	public List<AbstractCard> getCheckedCardList() {
		return checkedCardList;
	}

	public void setCheckedCardList(List<AbstractCard> checkedCardList) {
		this.checkedCardList = checkedCardList;
	}

	public int getAttDistance() {
		return attDistance;
	}

	public void setAttDistance(int attDistance) {
		this.attDistance = attDistance;
	}

	public int getDefDistance() {
		return defDistance;
	}

	public void setDefDistance(int defDistance) {
		this.defDistance = defDistance;
	}

	public boolean isUsedSha() {
		return usedSha;
	}

	public void setUsedSha(boolean usedSha) {
		this.usedSha = usedSha;
	}

	public Identity getId() {
		return id;
	}

	public void setId(Identity id) {
		this.id = id;
	}

	public int getCurHP() {
		return curHP;
	}

	public void setCurHP(int curHP) {
		this.curHP = curHP;
	}

	public List<AbstractCard> getUsedCard() {
		return usedCard;
	}

	public void setUsedCrad(List<AbstractCard> usedCard) {
		this.usedCard = usedCard;
	}

	public boolean isAI() {
		return isAI;
	}

	public void setAI(boolean isAI) {
		this.isAI = isAI;
	}



	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public void setUsedCard(List<AbstractCard> usedCard) {
		this.usedCard = usedCard;
	}

	public int getRes() {
		return res;
	}

	

	public List<SkillIF> getSkill() {
		return skill;
	}

	public void setSkill(List<SkillIF> skill) {
		this.skill = skill;
	}

	public List<LockingSkillIF> getLockingSkill() {
		return lockingSkill;
	}

	public void setLockingSkill(List<LockingSkillIF> lockingSkill) {
		this.lockingSkill = lockingSkill;
	}

	public void setRes(int res) {
		this.res = res;
	}

	public boolean isRequest() {
		return isRequest;
	}

	public void setRequest(boolean isRequest) {
		this.isRequest = isRequest;
	}
	
	public int getExtDamage() {
		return extDamage;
	}

	public void setExtDamage(int extDamage) {
		this.extDamage = extDamage;
	}

	public AbstractCard getSelectCard() {
		return selectCard;
	}

	public void setSelectCard(AbstractCard selectCard) {
		this.selectCard = selectCard;
	}

	public List<Integer> getImmuneCards() {
		return immuneCards;
	}

	public void setImmuneCards(List<Integer> immuneCards) {
		this.immuneCards = immuneCards;
	}
	
}
