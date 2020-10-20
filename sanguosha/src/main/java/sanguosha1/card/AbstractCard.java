package sanguosha1.card;

import sanguosha1.data.enums.Colors;
import sanguosha1.gui.main.Panel_HandCards;
import sanguosha1.gui.main.Panel_Player;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ModuleManagement;
import sanguosha1.service.ViewManagement;
import sanguosha1.util.ImgUtil;

import java.awt.*;
import java.util.List;

/**
 * �Ƶĳ�����
 * 
 * @author user
 * 
 */
public abstract class AbstractCard implements CardIF {
	// �Ƶ�id
	protected int id;
	// ������ֵ
	protected int number;
	// �ƵĻ�ɫ
	protected Colors color;

	// �Ƶ�����
	protected String name;
	// �Ƶ�����
	protected int type;
	// ʹ��Ŀ������
	protected int targetType;
	// ʹ���Ƿ���Ҫ���
	protected boolean needRange;

	// �ƵĻ�ɫͼ��
	protected Image colorImg;
	// �Ƶ�ͼ��
	protected Image cardImg;
	// �Ƶ�Ч��ͼ
	protected Image effectImage;

	// ʹ�÷�����
	// protected CardUseServiceIF useService;

	public AbstractCard() {
	}

	/**
	 * ����һ��ָ��id,��ֵ,��ɫ����
	 * 
	 * @param id
	 * @param number
	 * @param color
	 */
	public AbstractCard(int id, int number, Colors color) {
		this.id = id;
		this.number = number;
		this.color = color;
		this.colorImg = ImgUtil.getColorImg(color);
	}

	/**
	 * ���췽��
	 * 
	 * @param id
	 * @param number
	 * @param color
	 * @param name
	 * @param type
	 * @param targetType
	 * @param needRange
	 */
	public AbstractCard(int id, int number, Colors color, String name,
			int type, int targetType, boolean needRange) {
		// super();
		this.id = id;
		this.number = number;
		this.color = color;
		this.name = name;
		this.type = type;
		this.targetType = targetType;
		this.needRange = needRange;

	}

	/**
	 * ��ʾͼƬ ��ע���÷�����Ч��getCardImg(),����Ŀ�л��γ�����������ĵ��ã��������ܵ��ۺܷѽ�;
	 * �벻Ҫ���֣�֮���Ի��Եö�ˣ���ȫ�����ڷ����Ĳ��� ���øĶ�������������
	 * 
	 * @return
	 */
	public Image showImg() {
		return getCardImg();
	}

	/**
	 * Ŀ���� ���������ҪĿ�꣬���Ʊ������ʱ�� ���ô˷����������˵������ϵ�Ŀ�� �������ʵ��ʱ����ͬʱ����UI ��Ҫ����ҵ��ʱʹ�á�
	 */
	public void targetCheck(Panel_HandCards ph) {
		if (targetType == CardIF.NONE) {
			List<Panel_Player> list = ph.getMain().getPlayers();
			for (Panel_Player pp : list) {
				pp.disableToUse();
			}
		}
	}

	/**
	 * ���� �жϸ����ܷ�����������ĳ�����ʹ�� targetCheck�п��ܻ���ô˷��� AI��һЩ�ж�Ҳ����ô˷���
	 */
	public boolean targetCheck4SinglePlayer(AbstractPlayer user,
			AbstractPlayer target) {
		return true;
	}

	/**
	 * �Ƶ�use�������ṩһЩͨ�ò���
	 */
	public void use(AbstractPlayer p, List<AbstractPlayer> players) {
		// ���ս��
		ModuleManagement.getInstance().getBattle().clear();
		// ��ǰ�����������
		p.getState().getUsedCard().clear();
		// ���뵱ǰ������
		p.getState().getUsedCard().add(this);
		// ������ɾ��
		p.getAction().removeCard(this);
		// ս�������
		ModuleManagement.getInstance().getBattle().addOneCard(this);
		// �������ƶ�
		gc();
		// ʹ��������ˢ��
		// �˴�ע�͵���ԭ�������������ˢ�£����һЩ״̬��ֵ��ˢ��
		// ����ˢ�������������ʵ�ֵ�ʱ�����������
		// p.refreshView();
		drawEffect(p, players);
	}

	/**
	 * ������Ӧ���
	 */
	public boolean requestUse(final AbstractPlayer p,
			List<AbstractPlayer> players) {
		// ��ǰ�����������
		p.getState().getUsedCard().clear();
		// ���뵱ǰ������
		p.getState().getUsedCard().add(this);
		// ������ɾ��
		p.getAction().removeCard(this);
		// ս�������
		ModuleManagement.getInstance().getBattle().addOneCard(this);
		// �������ƶ�
		gc();
		// ��ӡ��Ϣ
		ViewManagement.getInstance().printBattleMsg(
				p.getInfo().getName() + "���" + this.toString());
		// ʹ��������ˢ��
		p.refreshView();
		return true;

	}

	/**
	 * �ƵĶ����������ṩһЩͨ�ò���
	 */
	public void throwIt(AbstractPlayer p) {
		// ��ǰ�����������
		p.getState().getUsedCard().clear();
		// ������ɾ��
		p.getAction().removeCard(this);
		if (!p.getState().isAI())
			p.updateCards();
		// ���뵱ǰ������
		// p.getState().getUsedCard().add(this);
		// ս�������
		ModuleManagement.getInstance().getBattle().addOneCard(this);
		// �������ƶ�
		gc();
		// ��ӡ��Ϣ
		ViewManagement.getInstance().printMsg(
				p.getInfo().getName() + "����" + this.toString());
	}

	/**
	 * �ƴ�һ����Ҵ��ݸ���һ�����
	 */
	public void passToPlayer(AbstractPlayer fromP, AbstractPlayer receiverP) {
		fromP.getAction().removeCard(this);
		receiverP.getAction().addCardToHandCard(this);
	}

	/**
	 * �����ƶѻ���
	 */
	public void gc() {
		ModuleManagement.getInstance().getGcList().add(this);
	}

	/**
	 * ���������С���ַ�����ʽ
	 * 
	 * @return
	 */
	public String getNumberToString() {
		switch (number) {
		case 1:
			return "A";
		case 11:
			return "J";
		case 12:
			return "Q";
		case 13:
			return "K";
		default:
			return String.valueOf(number);
		}
	}

	/**
	 * ͨ�÷��� �����Ƶ�����
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return this.color.toString() + this.getNumberToString()
				+ this.getName();
	}

	/**
	 * ����Ƿ���ʹ�÷�Χ��
	 */
	public boolean isInRange(AbstractPlayer user, AbstractPlayer target) {
		int p2p = user.getFunction().getDistance(target);
		int att = user.getFunction().getAttackDistance();
		int def = target.getFunction().getDefenceDistance();
		return (att - def) >= p2p;

	}

	/*
	 * ������Ч
	 */
	protected void drawEffect(AbstractPlayer p, List<AbstractPlayer> players) {

	}

	public boolean isNeedRange() {
		return needRange;
	}

	public void setNeedRange(boolean needRange) {
		this.needRange = needRange;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Colors getColor() {
		return color;
	}

	public void setColor(Colors color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getColorImg() {
		return colorImg;
	}

	public void setColorImg(Image colorImg) {
		this.colorImg = colorImg;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTargetType() {
		return targetType;
	}

	public void setTargetType(int targetType) {
		this.targetType = targetType;
	}

	public Image getCardImg() {
		return cardImg;
	}

	public void setCardImg(Image cardImg) {
		this.cardImg = cardImg;
	}

	public Image getEffectImage() {
		return effectImage;
	}

	public void setEffectImage(Image effectImage) {
		this.effectImage = effectImage;
	}

}
