package sanguosha1.player.impl;

import java.lang.reflect.Constructor;
import java.util.List;

import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.Player_ActionIF;
import sanguosha1.player.Player_FunctionIF;
import sanguosha1.player.Player_TriggerIF;
import sanguosha1.skills.LockingSkillIF;
import sanguosha1.skills.SkillIF;
import sanguosha1.util.ConfigFileReadUtil;


/**
 * 具体的玩家类
 * 
 * @author user
 * 
 */
public class Player extends AbstractPlayer {
	/**
	 * 构造器 根据info 构建一个具体人物
	 */
	public Player(String name) {
		// 获取信息
		this.info = ConfigFileReadUtil.getInfoFromXML(name);
		// 初始化
		initial();
		// 载入技能
		loadSkills(name);
	}

	/**
	 * 载入技能
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void loadSkills(String name) {
		// 获取技能列表
		List<String> list = ConfigFileReadUtil.getSkillListFromXML(name);
		// 迭代解析技能
		for (String s : list) {
			s=s.trim();
			String type = s.split(",")[0];
			String clazz = s.split(",")[1];
			try {
				Constructor con = Class.forName(clazz).getConstructor(
						AbstractPlayer.class);
				Object obj = con.newInstance(this);
				//如果是触发类型
				if (type.equals("trigger")) {
					setTrigger((Player_TriggerIF) obj);
				}
				//如果是回合型
				if(type.equals("process")){
					setProcess((P_Process) obj);
				}
				//如果是动作型
				if(type.equals("action")){
					setAction((Player_ActionIF) obj);
				}				
				//如果是函数型
				if(type.equals("function")){
					setFunction((Player_FunctionIF) obj);
				}
				if(obj instanceof LockingSkillIF){
					LockingSkillIF ls = (LockingSkillIF) con.newInstance(this);
					this.getState().getLockingSkill().add(ls);
					//this.info.lockingSkill = ls.getName();
				}
			} catch (Exception e) {
				System.out.println(getInfo().getName() + "技能载入错误");
				e.printStackTrace();
			}
		}
		//载入主动技能
		for (int i = 0; i < getInfo().getSkillName().size(); i++) {			
			String strSkillName = this.getInfo().getSkillName().get(i);
			if(strSkillName!=null){
				try {
					Constructor con = Class.forName(strSkillName).getConstructor(AbstractPlayer.class);
					SkillIF skill = (SkillIF) con.newInstance(this);
					getState().getSkill().add(skill);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("载入主动技能异常");
				}
			}
		}
	}

}
