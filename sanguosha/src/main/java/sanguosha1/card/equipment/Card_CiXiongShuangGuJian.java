package sanguosha1.card.equipment;

import javax.swing.SwingUtilities;

import sanguosha1.card.AbstractCard;
import sanguosha1.data.constant.Const_Game;
import sanguosha1.data.enums.Colors;
import sanguosha1.gui.main.Panel_Control;
import sanguosha1.gui.main.Panel_HandCards;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ViewManagement;

/**
 * 雌雄双股剑
 * @author user
 *
 */
public class Card_CiXiongShuangGuJian extends AbstractWeaponCard{
	final boolean MOPAI = true;
	final boolean QIPAI = false;
	boolean flag ;
	AbstractCard card;
	
	Panel_Control pc;
	Panel_HandCards ph;
	
	public Card_CiXiongShuangGuJian(){
		
	}
	public Card_CiXiongShuangGuJian(int id, int number, Colors color) {
		super(id, number, color);
	}

	/**
	 * 杀前的技能
	 */
	@Override
	public void useSkillBeforeSha(AbstractPlayer p, AbstractPlayer target) {
		super.useSkillBeforeSha(p, target);
		//���Դ���
		if(p.getFunction().isSameSex(target)){
			return;
		}else{
			ViewManagement.getInstance().printBattleMsg(p.getInfo().getName()+"����"+getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(target.getState().getCardList().size()==0){
				execute(p,target,MOPAI);
				return;
			}else{
				if(target.getState().isAI()){
					execute(p,target,MOPAI);
				}else{
					pc = (Panel_Control) target.getPanel();
					ph = pc.getHand();
					SwingUtilities.invokeLater(ask);
					while(true){
						if(target.getState().getRes()==Const_Game.OK){
							if(ph.getSelectedList().isEmpty()){
								target.getState().setRes(0);
								continue;
							}else{
								card =ph.getSelectedList().get(0).getCard();
								target.getState().setRes(0);
								execute(p, target, QIPAI);
								break ;
							}
						}
						if(target.getState().getRes()==Const_Game.CANCEL){
							target.getState().setRes(0);
							execute(p, target, MOPAI);
							break ;
						}
					}
				}
				ViewManagement.getInstance().getPrompt().clear();
				return;
			}
		}
	}
	
	private void execute(AbstractPlayer p, AbstractPlayer target,boolean flag){
		if(flag){
			p.getAction().addOneCardFromList();
		}else{
			//target.getState().getCardList().get(0).throwIt(target);
			card.throwIt(target);
		}
	}
	Runnable ask = new Runnable() {
		
		@Override
		public void run() {
			
			ph.remindToUseALL();
			ph.disableClick();
			ph.enableOKAndCancel();
			ph.setTargetCheck(false);
			ViewManagement.getInstance().getPrompt().show_Message(
					"ѡ1���ƶ���,��ȡ����Է���1����" );

		}
		
	};
}
