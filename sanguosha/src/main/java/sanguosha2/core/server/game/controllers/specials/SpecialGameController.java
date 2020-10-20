package sanguosha2.core.server.game.controllers.specials;

import sanguosha2.core.server.game.controllers.GameController;
import sanguosha2.utils.EnumWithNextStage;

public interface SpecialGameController extends GameController {
	
	public static enum SpecialStage implements EnumWithNextStage<SpecialStage> {
		TARGET_LOCKED,
		NEUTRALIZATION,
		EFFECT,
		EFFECT_TAKEN;
	}
	
	public void onNeutralized();
	
	public void onNeutralizationCanceled();

}
