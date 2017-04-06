package com.faceyspacies.TyRC;

import org.pircbotx.Configuration;
import org.pircbotx.MultiBotManager;

/**
 * Hello world!
 *
 */
public class TyRC 
{
    public static void main( String[] args )
    {
        Configuration.Builder config = new Configuration.Builder()
        	.setName("TyRC")
        	.setAutoNickChange(true)
        	.setAutoReconnect(true)
        	.setLogin("tybot")
        	.setRealName("TyRC");
        
        MultiBotManager manager = new MultiBotManager();
        manager.addBot(config.buildForServer("irc.freenode.net"));
        
        manager.start();
    }
}
