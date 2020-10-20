package sanguosha2.core.player.query_listener;

import sanguosha2.core.player.PlayerComplete;
import sanguosha2.core.player.query.PlayerAttackLimitQuery;

public class ChuKoNuPlayerAttackLimitQueryListener extends AbstractPlayerAttackLimitQueryListener<PlayerAttackLimitQuery> {

	@Override
	public Class<PlayerAttackLimitQuery> getQueryClass() {
		return PlayerAttackLimitQuery.class;
	}

	@Override
	public void onQuery(PlayerAttackLimitQuery query, PlayerComplete player) {
		query.setLimit(Integer.MAX_VALUE);
	}

}
