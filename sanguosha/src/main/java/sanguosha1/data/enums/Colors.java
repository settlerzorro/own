package sanguosha1.data.enums;


/**
 * ��ɫö��
 */
public enum Colors {
		HEITAO, HONGXIN, MEIHUA, FANGKUAI;
		//��дtoString
		@Override
		public String toString() {
			switch (this) {
			case HEITAO:
				return "����";
			case HONGXIN:
				return "����";
			case MEIHUA:
				return "÷��";
			case FANGKUAI:
				return "����";
			}
			return null;
		}
	}

