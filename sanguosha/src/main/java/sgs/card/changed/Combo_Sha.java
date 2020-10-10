package sgs.card.changed;

import sgs.card.AbstractCard;
import sgs.card.ComboCardIF;
import sgs.card.base.Card_Sha;
import sgs.data.constant.Const_Game;
import sgs.player.AbstractPlayer;
import sgs.util.ImgUtil;

import java.awt.*;
import java.util.List;

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
