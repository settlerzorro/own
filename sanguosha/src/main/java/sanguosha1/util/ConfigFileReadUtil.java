package sanguosha1.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import sanguosha1.Main;
import sanguosha1.data.enums.Country;
import sanguosha1.gui.select.ProxyPlayer;
import sanguosha1.player.impl.P_Info;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * ��ȡ������Ϣ����
 * 
 * @author user
 * 
 */

public class ConfigFileReadUtil {

	/**
	 * �������ļ��ж�ȡָ���������Ϣ
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static P_Info getInfoFromXML(String playerName) {
		P_Info info = new P_Info();
		// ��ȡ���ڵ�
		Element root = getRoot("config/character.xml");
		// ��ȡ���ڵ��µ�ָ������ڵ�
		Element e = null;
		for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
			Element elm = (Element) it.next();
			String code = elm.attribute("id").getText();
			if (code.equals(playerName)) {
				e = elm;
				break;
			}
		}
		
		// ��ȡ����ڵ��������ӽڵ�
		String str_name = e.element("name").getText();
		String str_sex = e.element("sex").getText();
		String str_country = e.element("country").getText();
		String str_maxhp = e.element("maxhp").getText();
		String str_img = e.element("headimg").getText();
		String str_immuneCard = e.element("immuneCard").getText();
		// ����Ϣ��װ��һ��info����
		info = new P_Info();
		// ����
		info.setName(str_name);
		// �Ա�
		// info.setSex(Integer.parseInt(str_sex) == 1 ? true : false);
		info.setSex(Boolean.valueOf(str_sex));
		// ��������
		info.setCountry(Enum.valueOf(Country.class, str_country));
		// ��������
		info.setMaxHP(Integer.parseInt(str_maxhp));
		// ͷ������
		info.setHeadImg(ImgUtil.getJpgImgByName(str_img));
		// ������
		List<Integer> cards = new ArrayList<Integer>();
		String[] ss = str_immuneCard.split(",");
		for (int i = 0; i < ss.length; i++) {
			if(ss[i].isEmpty())break;
			int n = Integer.parseInt(ss[i]);
			cards.add(n);
		}

		info.setImmuneCard(cards);
		// ����
		Element eSkill = e.element("skill");
		for (Iterator<Element> it = eSkill.elementIterator(); it.hasNext();) {
			Element elm = (Element) it.next();
			if (elm.getName().equals("active")) {
				String str_skillname = elm.getText();
				if (str_skillname != null) {
					info.getSkillName().add(str_skillname);
				}
			}

		}
		/*
		 * if(eSkill.element("active")!=null){ String str_skillname =
		 * eSkill.element("active").getText();
		 * 
		 * }
		 */
		return info;
	}

	/**
	 * ����ʱ������
	 * ��ȡ�佫�б�
	 */
	public static Properties getCharacterList() {
		Properties p = new Properties();
		try {
			String url = Main.class.getResource(
					"config/characterlist.properties").toString()
					.split(":/", 2)[1];
			File f = new File(url);
			InputStream in = new BufferedInputStream(new FileInputStream(f));
			p.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}

	/**
	 * ��ȡ���б�
	 */
	public static Properties getCardList() {
		Properties p = new Properties();
		try {
			p.load(ClassLoader.getSystemResourceAsStream("config"
					+ "/cardlist.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}

	/**
	 * ��ȡ����ʱ�������ӵ����б�
	 */
	public static Properties getTestCardList(){

		Properties p = new Properties();
		try {
			p.load(ClassLoader.getSystemResourceAsStream("config"
					+ "/testlist.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	
	}
	/**
	 * ��XML�л�ȡ�佫�ļ����б�
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getSkillListFromXML(String playerName) {
		List<String> skillList = new ArrayList<String>();
		Element root = getRoot("config/character.xml");
		
		// ��ȡ���ڵ��µ�ָ������ڵ�
		Element e = null;
		for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
			Element elm = (Element) it.next();
			String code = elm.attribute("id").getText();
			if (code.equals(playerName)) {
				e = elm;
				break;
			}
		}
		Element skills = e.element("skill");
		if (skills != null) {
			for (Iterator<Element> it = skills.elementIterator(); it.hasNext();) {
				Element elm = (Element) it.next();
				skillList.add(elm.getName() + "," + elm.getText());
			}
		}
		return skillList;
	}

	
	/**
	 * ��XML�л�ȡ���д������
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<ProxyPlayer> getProxyPlayersFromXML(){
		List<ProxyPlayer> ppList = new ArrayList<ProxyPlayer>();
		Element root = getRoot("config/character.xml");
		for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
			Element elm = (Element) it.next();
			String id = elm.attribute("id").getText();
			Element img = elm.element("headimg");
			String imgName = img.getText();
			ppList.add(new ProxyPlayer(id, imgName));
		}	
		return ppList;
	}
	
	/**
	 * ��xml�ж�ȡ�Ƶ�������Ϣ
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Properties getCardInfoFromXML(int id){
		Properties p = new Properties();
		Element root = getRoot("config/card.xml");
		//Element e =null;
		String clazz = null;
		for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
			Element elm = (Element) it.next();
			String code = elm.attribute("id").getText();
			int codeInt = Integer.parseInt(code);
			//��⵽ƥ��
			if (codeInt == id) {
				//class
				clazz = elm.attribute("class").getText();
				//name
				String name = elm.element("name").getText();
				String type = elm.element("type").getText();
				String targetType = elm.element("targetType").getText();
				String needRange = elm.element("needRange").getText();	
				String img = elm.element("img").getText();
				String ef_img = elm.element("ef_img").getText();
				p.put("class", clazz);
				p.put("name", name);
				p.put("type", type);
				p.put("targetType", targetType);
				p.put("needRange", needRange);
				p.put("img", img);
				p.put("ef_img", ef_img);
				//����
				String cardType = elm.attribute("cardtype").getText();
				if(cardType.equals("equipment")){
					String att = elm.element("att").getText();
					String def = elm.element("def").getText();
					String eqType = elm.element("eqType").getText();
					p.put("att", att);
					p.put("def", def);
					p.put("eqType", eqType);
				}
				break;
			}
		}
		return p;
	}
	
	/*
	 * �ڲ�ͨ�÷�������ȡ���ڵ�
	 */
	private static Element getRoot(String fileName){
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			BufferedReader r = new BufferedReader(new InputStreamReader(
					ClassLoader
							.getSystemResourceAsStream(fileName),
					"utf-8"));
			doc = reader.read(r);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		// ��ȡ���ڵ�
		Element root = doc.getRootElement();
		return root;
	}
}
