package com.faceyspacies.TyRC.Listeners;

import org.pircbotx.MultiBotManager;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class WikiaRCListener extends ListenerAdapter {

	private MultiBotManager manager;
	
	public WikiaRCListener(MultiBotManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void onMessage(MessageEvent e) {
		System.out.println(e.toString());
		
	}
}
