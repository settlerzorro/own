package sanguosha1;
/**
 * �����������
 * ��ֻ��һ��������ʵ���ҵ��ࡿ
 * ������������ʵ������µ�ע�ͺۼ� ����ʲô��û�С�
 */

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import sanguosha1.util.ConfigFileReadUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Properties;

public class Test {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		//����xml��ȡcard����
		//���б����ȡһ��int
		//��������int��ȡxml
		//��ȡclass��ʵ������return
		int type = 1;
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			BufferedReader r = new BufferedReader(new InputStreamReader(
					ClassLoader
							.getSystemResourceAsStream("config/card.xml"),
					"utf-8"));
			doc = reader.read(r);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		// ��ȡ���ڵ�
		Element root = doc.getRootElement();
		// ��ȡ���ڵ��µ�ָ������ڵ�
		Element e = root.element("card");
		System.out.println(e.getName());
		String clazz = null;
		for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
			Element elm = (Element) it.next();
			String code = elm.attribute("id").getText();
			int codeInt = Integer.parseInt(code);
			if (codeInt == type) {
				System.out.println("ѡ����" + elm.element("name").getText());
				clazz = elm.attribute("class").getText();
				System.out.println(clazz);
				break;
			}
		}
		
		Properties p = ConfigFileReadUtil.getCardInfoFromXML(1);
		System.out.println(p.get("targetType"));
		for (Object key : p.keySet()) {
			System.out.println((String)key+"--"+p.get(key));
		}
		
		/*AbstractCard c = CardFactory.newCard(1);
		System.out.println(c.getName());*/
		return ;
		// UI
		
		 /* JFrame f = new JFrame(); Panel_Select ps = new Panel_Select();
		  f.setSize(Const_UI.FRAME_WIDTH,Const_UI.FRAME_HEIGHT);
		  f.setLayout(null);
		  f.add(ps);
		  f.setDefaultCloseOperation(3);  
		  f.setVisible(true);*/
		 

		/*
		 * File file = new
		 * File(Test.class.getResource("").toString()+"files/test.txt");
		 * System.out.println(file.getAbsolutePath());
		 * 
		 * 
		 * System.out.println("1"+Thread.currentThread().getContextClassLoader().
		 * getResource(""));
		 * 
		 * System.out.println("2"+Test.class.getClassLoader().getResource(""));
		 * 
		 * System.out.println("3"+ClassLoader.getSystemResource(""));
		 * System.out.println(Test.class.getResource(""));
		 */
		// System.out.println(Test.class.getResource("files/test.txt").toString().substring(6));
		// //Class�ļ�����·��
		// System.out.println(new File("/").getAbsolutePath());
		// System.out.println(System.getProperty("user.dir"));
		// System.out.println(ConfigFileReadService.class.getResource(""));
		/*
		 * InputStream in =
		 * Test.class.getResourceAsStream("config/character.xml");
		 * System.out.println(in.toString());
		 */
		// String url =
		// Main.class.getResource("img/"+"P_caocao"+".jpg").toString().substring(6);
		/*
		 * //System.out.println(url); System.out.println(
		 * System.getProperty("user.dir"));
		 * System.out.println(Test.class.getResource(""));; for (String string :
		 * ConfigFileReadUtil.getSkillListFromXML("caocao")) {
		 * System.out.println(string);
		 * 
		 * }
		 */

		/*
		 * AbstractCard c = new Card_QingGangJian(); EquipmentCardIF eq =
		 * (EquipmentCardIF) c; AbstractCard c2 = (AbstractCard) eq;
		 * System.out.println(c2);
		 */
		/*SAXReader reader2 = new SAXReader();
		Document doc2 = null;
		P_Info info = new P_Info();
		try {
			BufferedReader r = new BufferedReader(new InputStreamReader(
					ClassLoader
							.getSystemResourceAsStream("config/character.xml"),
					"utf-8"));
			doc = reader.read(r);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		// ��ȡ���ڵ�
		Element root2 = doc.getRootElement();
		// ��ȡ���ڵ��µ�ָ������ڵ�
		Element e2 = root.element("character");*/
		/*
		 * System.out.println(e.elementByID("caocao")); Element eid =
		 * root.elementByID("caocao"); System.out.println(eid);
		 * System.out.println(eid.element("name").getText());
		 */
		/*for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
			Element elm = (Element) it.next();
			String code = elm.attribute("id").getText();
			if (code.equals("lvmeng")) {
				System.out.println("ѡ����" + elm.element("name").getText());
				break;
			}
		}*/
		
		//ConfigFileReadUtil.getProxyPlayersFromXML();
	}

	
}
