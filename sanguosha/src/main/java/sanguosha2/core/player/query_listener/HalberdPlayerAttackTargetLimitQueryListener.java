package sanguosha2.core.player.query_listener;

import sanguosha2.core.player.PlayerComplete;
import sanguosha2.core.player.query.PlayerAttackTargetLimitQuery;

public class HalberdPlayerAttackTargetLimitQueryListener extends AbstractPlayerAttackLimitQueryListener<PlayerAttackTargetLimitQuery> {

	@Override
	public Class<PlayerAttackTargetLimitQuery> getQueryClass() {
		return PlayerAttackTargetLimitQuery.class;
	}

	@Override
	public void onQuery(PlayerAttackTargetLimitQuery query, PlayerComplete player) {
		if (player.getHandCount() == 1) {
			query.addTargetLimit(2);
		}
	}

}
