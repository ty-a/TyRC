package com.faceyspacies.TyRC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.pircbotx.Configuration;
import org.pircbotx.MultiBotManager;
import org.pircbotx.PircBotX;
import org.pircbotx.cap.SASLCapHandler;
import com.faceyspacies.TyRC.Listeners.FreenodeListener;
import com.faceyspacies.TyRC.Listeners.WikiaRCListener;

/**
 * Hello world!
 */
public class TyRC {

  private MultiBotManager manager;
  private PircBotX freenode;
  private PircBotX wikiaRC;
  private boolean readyToSend;
  private Properties config;

  public static void main(String[] args) {
    TyRC tyrc = new TyRC();
    tyrc.start();
  }

  public void start() {
    loadConfig();
    readyToSend = false;
    connectToIRC();
  }

  public void connectToIRC() {

    manager = new MultiBotManager();

    Configuration freenodeConfig = new Configuration.Builder()
        .setName(config.getProperty("reportnick", "TyRC"))
        .setAutoNickChange(true)
        .setAutoReconnect(true)
        .setLogin("tybot")
        .setRealName("TyRC")
        .addAutoJoinChannel(config.getProperty("reportchannel", "#tybot"))
        .addListener(new FreenodeListener(manager, this))
        .addServer("irc.freenode.net")
        .addCapHandler(new SASLCapHandler(config.getProperty("reportusername", null),
                                          config.getProperty("reportpassword", null)))
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
        .addServer(config.getProperty("feednetwork", null),
                   Integer.parseInt(config.getProperty("feedport", null)))
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

  private void loadConfig() {
    config = new Properties();
    InputStream input = null;

    try {
      File settingsFileLocation = new File("tyrc.properties");
      input = new FileInputStream(settingsFileLocation);

      config.load(input);
      System.out.print(config);

    } catch (FileNotFoundException e) {
      System.out.println("tyrc.properties not found; did you rename tyrc.properties.sample?");
      System.exit(0);
    } catch (IOException e) {
      System.out.println("IOException loading tyrc.properties. Do you have permissions to read it?");
      System.exit(0);
    }
  }

  @SuppressWarnings("unused")
  private void saveConfig() {
    try {
      config.store(new FileOutputStream("tyrc.properties"), null);
    } catch (FileNotFoundException e) {
      System.out.println("tyrc.properties was deleted while program was running. PANIC!");
    } catch (IOException e) {
      System.out.println("Unable to save properties file, probs due to file permissions!");
    }
  }

  public String getReportChannel() {
    return config.getProperty("reportchannel");
  }
}
