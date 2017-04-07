package com.faceyspacies.TyRC.Listeners;

import org.json.JSONException;
import org.pircbotx.MultiBotManager;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.faceyspacies.TyRC.Containers.DiscussionsFeedEntry;

public class WikiaRCListener extends ListenerAdapter {

	private MultiBotManager manager;
	
	public WikiaRCListener(MultiBotManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void onMessage(MessageEvent e) {
		System.out.println(e.toString());
		try {
			DiscussionsFeedEntry in = new DiscussionsFeedEntry(e.getMessage());
			
			String out = "[[";
		
			out += in.getUser() + "]]";
			
			switch (in.getType()) {
			case DISCUSSION_REPLY:
				switch(in.getAction()) {
				case CREATED:
					out += " replied ";
					break;
				case DELETED:
					out += " deleted reply ";
					break;
				case UNDELETED:
					out += " undeleted reply ";
					break;
				case MOVED:
					// will never get here
					throw new Exception("Action MOVED with a DISCUSSION_REPLY. Should not happen.");
				case MODIFIED:
					out += " edited reply ";
					break;
				default:
					break;
				}
				
				out += in.getUrl() + " : ";
				
				out += in.getSnippet();
				break;
			case DISCUSSION_REPORT:
				switch(in.getAction()) {
				case CREATED:
					out += " reported post ";
					break;
				case DELETED:
					out += " deleted report ";
					break;
				case UNDELETED:
					out += " undeleted report ";
					break;
				case MOVED:
					// will never get here
					throw new Exception("Action MOVED with a DISCUSSION_REPORT. Should not happen.");
				case MODIFIED:
					out += " edited report "; // can this even happen?
					break;
				default:
					break;
				}
				
				out += in.getUrl() + " : ";
				
				out += in.getSnippet();
				break;
			case DISCUSSION_THREAD:
				switch(in.getAction()) {
				case CREATED:
					out += " created thread [[" + in.getTitle() + "]] ";
					break;
				case DELETED:
					out += " deleted thread [[" + in.getTitle() + "]] ";
					break;
				case UNDELETED:
					out += " undeleted thread [[" + in.getTitle() + "]] ";
					break;
				case MOVED:
					out += " moved thread [[" + in.getTitle() + "]] ";
				case MODIFIED:
					out += " edited thread [[" + in.getTitle() + "]] ";
					break;
				default:
					break;
				}
				out += in.getUrl();
				
				break;
			}
			
			
			
			manager.getBotById(0).send().message("#tybot", out);
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
