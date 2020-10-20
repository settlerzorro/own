package sanguosha2.core.player.query_listener;

import sanguosha2.core.player.PlayerComplete;
import sanguosha2.core.player.query.PlayerStatusQuery;

public interface PlayerStatusQueryListener<T extends PlayerStatusQuery> {
	
	public Class<T> getQueryClass();
	
	public void onQuery(T query, PlayerComplete player);

}
