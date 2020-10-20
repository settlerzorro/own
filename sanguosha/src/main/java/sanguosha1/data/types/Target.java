package sanguosha1.data.types;

import sanguosha1.player.AbstractPlayer;

import java.util.ArrayList;
import java.util.List;


/**
 * ��װ���Ƶ�ʹ��Ŀ�����
 * @author user
 *
 */
public class Target {
	//�洢����ģ��
	List<AbstractPlayer> list;
	//Ŀ������
	int limit =1;
	//�Ƿ���Ҫ���
	boolean needCheck ;
	
	//����
	public Target(int limit){
		list = new ArrayList<AbstractPlayer>();
		this.limit = limit;
	}
	
	/**
	 * ���һ��
	 */
	public void add(AbstractPlayer p){
		//����ظ��򷵻�
		if(list.contains(p))return;
		//���ﵽ������ɾ����һ�������
		if(list.size()>=limit){
			list.remove(0);
			list.add(p);
		}else{
			list.add(p);
		}
	}
    /**
     * �ж��Ƿ�Ϊ��
     * @return
     */
	public boolean isEmpty(){
		return (list==null||list.size()==0);
	}
	public List<AbstractPlayer> getList() {
		return list;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setList(List<AbstractPlayer> list) {
		this.list = list;
	}

	public boolean isNeedCheck() {
		return needCheck;
	}

	public void setNeedCheck(boolean needCheck) {
		this.needCheck = needCheck;
	}
	
	
}
