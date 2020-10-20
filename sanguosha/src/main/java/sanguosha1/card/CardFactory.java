package sanguosha1.card;

import sanguosha1.card.equipment.AbstractEquipmentCard;
import sanguosha1.data.enums.Colors;
import sanguosha1.data.enums.EquipmentType;
import sanguosha1.util.ConfigFileReadUtil;
import sanguosha1.util.ImgUtil;

import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * �ƵĹ�����
 * 
 * @author user
 * 
 */
public class CardFactory {

	/**
	 * �������һ���� ����Ϊ�ļ��ж�ȡ����һ����ֵ�� key = id value = ������ɫ��ֵ���͵��ַ���
	 * value������3�����������üӹ���������1����
	 */
	public static AbstractCard newCard(String key, String value) {
		int id = Integer.parseInt(key);
		String[] str = value.split(",");
		int color = Integer.parseInt(str[0]);
		Colors c = null;
		switch (color) {
		case 1:
			c = Colors.HEITAO;
			break;
		case 2:
			c = Colors.HONGXIN;
			break;
		case 3:
			c = Colors.FANGKUAI;
			break;
		case 4:
			c = Colors.MEIHUA;
			break;
		default:
			c = Colors.HEITAO;
			break;
		}
		int num = Integer.parseInt(str[1]);
		int type = Integer.parseInt(str[2]);
		AbstractCard res = newCard(type);
		res.setId(id);
		res.setNumber(num);
		res.setColor(c);
		res.setColorImg(ImgUtil.getColorImg(c));
		//if(res instanceof AbstractEquipmentCard)System.out.println(res.toString());
		return res;
	}
	/*
	 * ����һ���� �ڲ��ӹ�
	 * ��2013��6��11�տ�ʼ ���á�
	 * �������XML�������ɡ�
	 * @return
	 */
	/*private static AbstractCard createCard(int type, int id, int number,
			Colors color) {
		switch (type) {
		case Const_Game.SHA:
			return new Card_Sha(id, number, color);
		case Const_Game.SHAN:
			return new Card_Shan(id, number, color);
		case Const_Game.TAO:
			return new Card_Tao(id, number, color);
		case Const_Game.WANJIANQIFA:
			return new Card_WanJianQiFa(id, number, color);
		case Const_Game.QINGGANGJIAN:
			return new Card_QingGangJian(id, number, color);
		case Const_Game.BAGUAZHEN:
			return new Card_BaGuaZhen(id, number, color);
		case Const_Game.JUEDOU:
			return new Card_JueDou(id, number, color);
		case Const_Game.GUOHECHAIQIAO:
			return new Card_GuoHeChaiQiao(id, number, color);
		case Const_Game.WUZHONGSHENGYOU:
			return new Card_WuZhongShengYou(id, number, color);
		case Const_Game.SHUNSHOUQIANYANG:
			return new Card_ShunShouQianYang(id, number, color);
		case Const_Game.TAOYUANJIEYI:
			return new Card_TaoYuanJieYi(id, number, color);
		case Const_Game.WUGUFENGDENG:
			return new Card_WuGuFengDeng(id, number, color);
		case Const_Game.GUANSHIFU:
			return new Card_GuanShiFu(id, number, color);
		case Const_Game.CIXIONGSHUANGGUJIAN:
			return new Card_CiXiongShuangGuJian(id, number, color);
		case Const_Game.NANMANRUQIN:
			return new Card_NanManRuQin(id, number, color);
		case Const_Game.ZHUGELIANNU:
			return new Card_ZhuGeLianNu(id, number, color);
		case Const_Game.LEBUSISHU:
			return new Card_LeBuSiShu(id, number, color);
		case Const_Game.SHANDIAN:
			return new Card_ShanDian(id, number, color);
		case Const_Game.JIEDAOSHAREN:
			return new Card_JieDaoShaRen(id, number, color);
		case Const_Game.FANGTIANHUAJI:
			return new Card_FangTianHuaJi(id, number, color);
		case Const_Game.QILINGONG:
			return new Card_QiLinGong(id, number, color);
		case Const_Game.QINGLONGYANYUEDAO:
			return new Card_QingLongYanYueDao(id, number, color);
		case Const_Game.ZHANGBASHEMAO:
			return new Card_ZhangBaSheMao(id, number, color);
		case Const_Game.DILU:
			return new Card_DefHorse(id, number, color);
		case Const_Game.DAWAN:
			return new Card_AttHorse(id, number, color);
		case Const_Game.WUXIEKEJI:
			return new Card_WuXieKeJi(id, number, color);
		}
		return null;
	}*/

	/**
	 * ����type����ֵ
	 * ��ȡXML�е�������Ϣ�����÷�������һ���Ƶ�ʵ��
	 */
	@SuppressWarnings("unchecked")
	public static AbstractCard newCard(int typeID){
		AbstractCard c = null;
		Object obj = null;
		Properties p = ConfigFileReadUtil.getCardInfoFromXML(typeID);
		String clazz = (String) p.get("class");
		String name = (String) p.get("name");
		int type = Integer.parseInt((String) p.get("type"));
		
		int targetType = Integer.parseInt((String) p.get("targetType"));
		boolean needRange = Boolean.parseBoolean((String) p.get("needRange"));
		String img = (String) p.get("img");
		String ef_img = (String) p.get("ef_img");
		//��������ֵ
		try {
			Constructor con = Class.forName(clazz).getConstructor();
			 obj= con.newInstance();
		} catch (Exception e){
			e.printStackTrace();
		}
		c = (AbstractCard)obj;
		//װ��
		if(c instanceof AbstractEquipmentCard){
			int att = Integer.parseInt((String)p.get("att"));
			int def = Integer.parseInt((String)p.get("def"));
			EquipmentType eqType = Enum.valueOf(EquipmentType.class, (String)p.get("eqType"));
			AbstractEquipmentCard aec = (AbstractEquipmentCard) c;
			aec.setAttDistance(att);
			aec.setDefDistance(def);
			aec.setEquipmentType(eqType);
			aec.name = name;
			aec.type = type;
			aec.targetType = targetType;
			aec.needRange = needRange;
			if(img!=null && img.length()>0)aec.cardImg = ImgUtil.getPngImgByName(img);
			if(ef_img!=null && ef_img.length()!=0)aec.effectImage = ImgUtil.getPngImgByName(ef_img);
		//	System.out.println(aec.getName()+aec.getAttDistance()+aec.getTargetType());
			return aec;
		}
		c.name = name;
		c.type = type;
		c.targetType = targetType;
		c.needRange = needRange;
		if(img!=null && img.length()>0)c.cardImg = ImgUtil.getPngImgByName(img);
		if(ef_img!=null && ef_img.length()!=0)c.effectImage = ImgUtil.getPngImgByName(ef_img);
		return c;
	}
	
	/**
	 * ����id ��ֵ ��ɫ ����һ����ָ�����͵���
	 */
	public static AbstractCard newCard(int id,int num,Colors color,int typeID){
		AbstractCard c = newCard(typeID);
		c.setId(id);
		c.setNumber(num);
		c.setColor(color);
		return c;
	}
}
