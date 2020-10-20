package sanguosha1.card.equipment;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.EquipmentCardIF;
import sanguosha1.data.enums.Colors;
import sanguosha1.data.enums.EquipmentType;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ModuleManagement;
import sanguosha1.service.ViewManagement;

import java.util.List;

/**
 * װ���Ƶĳ�����
 * @author user
 *
 */
public abstract class AbstractEquipmentCard extends AbstractCard implements EquipmentCardIF{
	//��������ӳ�
	protected int attDistance = 0;
	//��������ӳ�
	protected int defDistance = 0;
	//װ������
	protected EquipmentType equipmentType;
	
	public AbstractEquipmentCard(){
		//super();
	}
	/**
	 * ����
	 */
	public AbstractEquipmentCard(int id, int number, Colors color){
		super(id, number, color);
		//���ݳ�ʼ��
		init();
	}
	
	/**
	 * ��дuse
	 */
	@Override
	public void use(AbstractPlayer p, List<AbstractPlayer> players) {
		//���ս��
		ModuleManagement.getInstance().getBattle().clear();
		//��ǰ�����������
		p.getState().getUsedCard().clear();
		//������ɾ��
		p.getState().getCardList().remove(this);
		//װ��
		p.getAction().loadEquipmentCard(this);
		//ʹ��������ˢ��
		p.refreshView();
	}
	/**
	 *  ��ʼ������ 
	 */
	protected  void init(){
		
	}

	/**
	 * װ�ص������
	 */
	public void load(AbstractPlayer p) {
		ViewManagement.getInstance().printBattleMsg(p.getInfo().getName()+"װ��"+getName());
		//���ݲ�ͬ���Ͷ�Ӧ��ͬλ��
		//�������װ������ж����װ��
		AbstractEquipmentCard old;
		switch(equipmentType){
		case WUQI:
			old = p.getState().getEquipment().getWeapons();
			if(old!=null){
				old.unload(p);
			}
			p.getState().getEquipment().setWeapons(this);
			break;
		case FANGJU:
			old = p.getState().getEquipment().getArmor();
			if(old!=null){
				old.unload(p);
			}
			p.getState().getEquipment().setArmor(this);
			break;
		case _MA:
			old = p.getState().getEquipment().getAttHorse();
			if(old!=null){
				old.unload(p);
			}
			p.getState().getEquipment().setAttHorse(this);
			break;
		case MA:
			old = p.getState().getEquipment().getDefHorse();
			if(old!=null){
				old.unload(p);
			}
			p.getState().getEquipment().setDefHorse(this);
			break;
		}
		p.getState().attChange(attDistance);
		p.getState().defChange(defDistance);
	}

	/**
	 * �����ж��
	 */
	public void unload(AbstractPlayer p) {
		switch(equipmentType){
		case WUQI:
			p.getState().getEquipment().setWeapons(null);
			break;
		case FANGJU:
			p.getState().getEquipment().setArmor(null);
			break;
		case _MA:
			p.getState().getEquipment().setAttHorse(null);
			break;
		case MA:
			p.getState().getEquipment().setDefHorse(null);
			break;
		}
		this.throwIt(p);
		//����ж�ش���
		p.getTrigger().afterUnloadEquipmentCard();
		p.refreshView();
	}
	
	/**
	 * Ŀ����
	 *//*
	@Override
	public void targetCheck(Panel_HandCards ph) {
		List<Panel_Player> list = ph.getMain().getPlayers();
		for (Panel_Player pp : list) {
			
				pp.disableToUse();
			
		}
	}*/
	public int getAttDistance() {
		return attDistance;
	}
	public int getDefDistance() {
		return defDistance;
	}
	public EquipmentType getEquipmentType() {
		return equipmentType;
	}
	
	public void setAttDistance(int attDistance) {
		this.attDistance = attDistance;
	}
	public void setDefDistance(int defDistance) {
		this.defDistance = defDistance;
	}
	public void setEquipmentType(EquipmentType equipmentType) {
		this.equipmentType = equipmentType;
	}
	public void beginInit() {
		
	}
	
}
