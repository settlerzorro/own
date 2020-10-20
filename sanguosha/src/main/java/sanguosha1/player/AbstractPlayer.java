package sanguosha1.player;

import sanguosha1.card.AbstractCard;
import sanguosha1.gui.main.Panel_Control;
import sanguosha1.gui.main.Panel_HandCards;
import sanguosha1.gui.main.RefreshbleIF;
import sanguosha1.player.impl.*;
import sanguosha1.service.AI.AIProcessService;
import sanguosha1.service.ViewManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ��ҵĳ�����
 * 
 * ����ˣ������Ϣ--���״̬ --�����Ϊ�� --�����Ӧ�� --��Ҵ�����
 * 
 * ������ һЩ��ҵ�ͨ�÷���
 * 
 * @author Wangfuyuan
 * 
 */

public abstract class AbstractPlayer implements PlayerIF {

	// �����Ϣ
	protected P_Info info;

	// ���״̬
	protected P_State state;
	
	// ��һغ϶�����
	protected Player_ProcessIF process;

	// �����Ϊ
	protected Player_ActionIF action;
	
	// �����Ӧ����
	protected Player_RequestIF request;

	// ��Ҵ���
	protected Player_TriggerIF trigger;

	// ��ҵĹ��ܺ�����
	protected Player_FunctionIF function;
	
	// �¼ҵ�����
	protected AbstractPlayer nextPlayer;

	// AI������
	protected AIProcessService AIsvr;

	// ���ƿ���(�Ƿ����Լ��غ�)
	protected boolean isSkip = true;

	// �����غϽ׶�
	protected int stageNum = STAGE_END;
	
	// ��������ʾ���
	protected RefreshbleIF panel;

	/**
	 * ����
	 */
	public AbstractPlayer() {
		info = new P_Info();
		initial();
	}

	/**
	 *  ��ʼ��״̬
	 */
	protected void initial() {
		state = new P_State(info);
		action = new P_Action(this);
		request = new P_Request(this);
		trigger = new P_Trigger(this);
		process = new P_Process(this);
		function = new P_Function(this);
		AIsvr = new AIProcessService(this);
	}

	@Override
	public void process() {
		/*// �����AI�������AI�������process
		if (this.getState().isAI()) {
			AIsvr.start();
			return;
		}*/
		ViewManagement.getInstance().refreshAll();
		process.start();
	}

	/**
	 * ���󷽷� ���뼼��
	 */
	public abstract void loadSkills(String name);
	
	
	/**
	 * ˢ�����
	 * @return
	 */
	public void refreshView(){
		getPanel().refresh();
	}
	
	/**
	 * ����ˢ������
	 */
	public void updateCards(){
		Panel_Control pc = (Panel_Control) getPanel();
		Panel_HandCards ph = pc.getHand();
		//ph.updateCards();
		ph.carding();
	}
	/**
	 * �ȴ�ѡ��
	 * @return
	 */
	public AbstractCard toSelectCard(List<AbstractCard> list){
		if(getState().isAI()){
			int n = new Random().nextInt(list.size());
			return list.get(n);
		}else{
			getState().setSelectCard(null);
			while(getState().getSelectCard()==null){
				
			}
			return getState().getSelectCard();
		}
	}
	
	/**
	 * �Ƿ�ӵ��ĳ����
	 * @return
	 */
	public boolean hasCard(int type){
		for (int i = 0; i < getState().getCardList().size(); i++) {
			if(getState().getCardList().get(i).getType()==type)return true;
		}
		return false;
	}
	
	/**
	 * ��ȡͬ�������Ｏ��
	 * @return
	 */
	public List<AbstractPlayer> getSameCountryPlayers(){
		List<AbstractPlayer> result = new ArrayList<AbstractPlayer>();
		AbstractPlayer p = getNextPlayer();
		while(p!=this){
			if(p.getInfo().getCountry()==getInfo().getCountry()){
				result.add(p);
			}
			p = p.getNextPlayer();
		}
		return result;
	}
	
	/**
	 * �����Ƿ���������Ӧ״̬
	 * ����Ϊtype
	 * @param isRequest
	 * @param type
	 */
	public void setRequest(boolean isRequest,int type){
		getState().setRequest(isRequest);
		getRequest().setCurType(type);
	}
	
	//-----------------------------------
	public P_Info getInfo() {
		return info;
	}

	public void setInfo(P_Info info) {
		this.info = info;
	}

	public P_State getState() {
		return state;
	}

	public void setState(P_State state) {
		this.state = state;
	}

	public Player_ActionIF getAction() {
		return action;
	}

	public void setAction(Player_ActionIF action) {
		this.action = action;
	}

	public Player_RequestIF getRequest() {
		return request;
	}

	public void setRequest(Player_RequestIF request) {
		this.request = request;
	}

	public Player_TriggerIF getTrigger() {
		return trigger;
	}

	public void setTrigger(Player_TriggerIF trigger) {
		this.trigger = trigger;
	}

	public AbstractPlayer getNextPlayer() {
		return nextPlayer;
	}

	public void setNextPlayer(AbstractPlayer nextPlayer) {
		this.nextPlayer = nextPlayer;
	}

	public boolean isSkip() {
		return isSkip;
	}

	public void setSkip(boolean isSkip) {
		this.isSkip = isSkip;
	}

	public RefreshbleIF getPanel() {
		return panel;
	}

	public void setPanel(RefreshbleIF panel) {
		this.panel = panel;
	}

	public Player_ProcessIF getProcess() {
		return process;
	}

	public void setProcess(P_Process process) {
		this.process = process;
	}

	public int getStageNum() {
		return stageNum;
	}

	public void setStageNum(int stageNum) {
		this.stageNum = stageNum;
	}

	public Player_FunctionIF getFunction() {
		return function;
	}

	public void setFunction(Player_FunctionIF function) {
		this.function = function;
	}

}