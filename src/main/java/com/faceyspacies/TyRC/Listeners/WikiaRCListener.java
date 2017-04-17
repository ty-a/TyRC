package com.faceyspacies.TyRC.Listeners;

import org.json.JSONException;
import org.pircbotx.Colors;
import org.pircbotx.MultiBotManager;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.faceyspacies.TyRC.TyRC;
import com.faceyspacies.TyRC.Containers.DiscussionsFeedEntry;
import com.faceyspacies.TyRC.Containers.DiscussionsFeedEntry.Actions;

public class WikiaRCListener extends ListenerAdapter {

	private MultiBotManager manager;
	private TyRC main;
	
	public WikiaRCListener(MultiBotManager manager, TyRC main) {
		this.manager = manager;
		this.main = main;
	}
	
	@Override
	public void onMessage(MessageEvent e) {
		if(!main.isReady()) {
			return;
		}
		try {
			DiscussionsFeedEntry in = new DiscussionsFeedEntry(e.getMessage());
			
			String out = Colors.DARK_GREEN + "[[User:";
		
			out += in.getUser() + "]]" + Colors.NORMAL;
			
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
				
				out += "(" + in.getSize() + ") ";
				
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
				
				out += "(" + in.getSize() + ") ";

				break;
			case DISCUSSION_THREAD:
				switch(in.getAction()) {
				case CREATED:
					out += " created thread ";
					break;
				case DELETED:
					out += " deleted thread ";
					break;
				case UNDELETED:
					out += " undeleted thread ";
					break;
				case MOVED:
					out += " moved thread ";
					break;
				case MODIFIED:
					out += " edited thread ";
					break;
				default:
					break;
				}
				
				if(in.getAction() == Actions.CREATED) {
					out += Colors.TEAL + "[[" + in.getTitle() + "]] " + Colors.NORMAL;
					out += "(" + in.getSize() + ") ";
				} else {
					out += Colors.TEAL + "[[" + in.getTitle() + "]] " + Colors.NORMAL;
				}
				
				break;
			}
			
			out += Colors.DARK_BLUE + in.getUrl() + Colors.NORMAL + " : ";
			
			out += Colors.OLIVE + in.getSnippet();
			manager.getBotById(0).send().message(main.getReportChannel(), out);
			
		} catch (JSONException e1) {
			System.out.print(e1.getClass() + ": " + e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e1) {
			System.out.print(e1.getClass() + ": " + e1.getMessage());
			e1.printStackTrace();
		}
		
	}
}
