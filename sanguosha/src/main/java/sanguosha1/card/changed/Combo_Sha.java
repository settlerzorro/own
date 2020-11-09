package sanguosha1.card.changed;

import java.awt.Image;
import java.util.List;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.ComboCardIF;
import sanguosha1.card.base.Card_Sha;
import sanguosha1.data.constant.Const_Game;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.util.ImgUtil;

/**
 * ×éºÏµÄ¡¾É±¡¿
 * @author user
 *
 */
public class Combo_Sha implements ComboCardIF{
	List<AbstractCard> realCardList;
	public Combo_Sha(List<AbstractCard> list){
		this.realCardList = list;
	}
	@Override
	public int getCardType() {
		return Const_Game.SHA;
	}

	@Override
	public List<AbstractCard> getRealCards() {
		return realCardList;
	}

	@Override
	public void use(AbstractPlayer p, AbstractPlayer toP) {
		new Card_Sha().executeSha(p, toP);
	}
	
	public Image getEffectImage(){
		return ImgUtil.getPngImgByName("ef_sha");
	}
}
