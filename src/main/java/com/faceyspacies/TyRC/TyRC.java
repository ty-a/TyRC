package com.faceyspacies.TyRC;

import org.pircbotx.Configuration;
import org.pircbotx.MultiBotManager;
import org.pircbotx.PircBotX;
import org.pircbotx.cap.SASLCapHandler;
import com.faceyspacies.TyRC.Listeners.FreenodeListener;
import com.faceyspacies.TyRC.Listeners.WikiaRCListener;

/**
 * Hello world!
 *
 */
public class TyRC 
{
	private MultiBotManager manager;
	private PircBotX freenode;
	private PircBotX wikiaRC;
	private boolean readyToSend;
	
    public static void main( String[] args )
    {
        TyRC tyrc = new TyRC();
        tyrc.start();
    }
    
    public void start() {
    	readyToSend = false;
    	connectToIRC();
    }
    
    public void connectToIRC() {
    	
    	manager = new MultiBotManager();
    	
    	Configuration freenodeConfig = new Configuration.Builder()
            	.setName("TyRC")
            	.setAutoNickChange(true)
            	.setAutoReconnect(true)
            	.setLogin("tybot")
            	.setRealName("TyRC")
            	.addAutoJoinChannel("#tybot")
    			.addListener(new FreenodeListener(manager, this))
    			.addServer("irc.freenode.net")
    			.addCapHandler(new SASLCapHandler("tybot", ""))
    			.buildConfiguration();    			
    	
    	Configuration wikiaConfig = new Configuration.Builder()
            	.setName("TyRC")
            	.setAutoNickChange(true)
            	.setAutoReconnect(true)
            	.setLogin("tybot")
            	.setRealName("TyRC")
    			.setName("TyRC")
    			.addListener(new WikiaRCListener(manager, this))
    			.addAutoJoinChannel("#discussionsfeed")
    			.addServer("source", port)
    			.buildConfiguration();
    
    	freenode = new PircBotX(freenodeConfig);
    	wikiaRC = new PircBotX(wikiaConfig);
    	
    	manager.addNetwork(freenode);
    	manager.addNetwork(wikiaRC);
    	
    	manager.start();
    	
    }

	public void setIsReady(boolean b) {
		readyToSend = b;
	}
	
	public boolean isReady() {
		return readyToSend;
	}
}
