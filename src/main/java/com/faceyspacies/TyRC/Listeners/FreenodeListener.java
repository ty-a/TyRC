package com.faceyspacies.TyRC.Listeners;

import org.pircbotx.MultiBotManager;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;

import com.faceyspacies.TyRC.TyRC;

public class FreenodeListener extends ListenerAdapter {

  private MultiBotManager manager;
  private TyRC main;

  public FreenodeListener(MultiBotManager manager, TyRC main) {
    this.manager = manager;
    this.main = main;
  }

  @Override
  public void onMessage(MessageEvent e) {
    System.out.println(e.toString());
  }

  @Override
  public void onJoin(JoinEvent e) {
    if (e.getUserHostmask().getHostname().equals(main.getHostmask())) {
      main.setIsReady(true);
    }
  }

}
