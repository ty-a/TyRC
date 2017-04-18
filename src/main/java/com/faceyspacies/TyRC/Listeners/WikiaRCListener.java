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
    if (!main.isReady()) {
      return;
    }
    try {
      DiscussionsFeedEntry in = new DiscussionsFeedEntry(e.getMessage());

      StringBuilder out = new StringBuilder(Colors.DARK_GREEN + "[[User:");
      out.append(in.getUser()).append("]]" + Colors.NORMAL);

      switch (in.getType()) {
        case DISCUSSION_REPLY:
          switch (in.getAction()) {
            case CREATED:
              out.append(" replied ");
              break;
            case DELETED:
              out.append(" deleted reply ");
              break;
            case UNDELETED:
              out.append(" undeleted reply ");
              break;
            case MOVED:
              // will never get here
              throw new Exception("Action MOVED with a DISCUSSION_REPLY. Should not happen.");
            case MODIFIED:
              out.append(" edited reply ");
              break;
            default:
              break;
          }

          out.append("(").append(in.getSize()).append(") ");

          break;
        case DISCUSSION_REPORT:
          switch (in.getAction()) {
            case CREATED:
              out.append(" reported post ");
              break;
            case DELETED:
              out.append(" deleted report ");
              break;
            case UNDELETED:
              out.append(" undeleted report ");
              break;
            case MOVED:
              // will never get here
              throw new Exception("Action MOVED with a DISCUSSION_REPORT. Should not happen.");
            case MODIFIED:
              out.append(" edited report "); // can this even happen?
              break;
            default:
              break;
          }

          out.append("(").append(in.getSize()).append(") ");

          break;
        case DISCUSSION_THREAD:
          switch (in.getAction()) {
            case CREATED:
              out.append(" created thread ");
              break;
            case DELETED:
              out.append(" deleted thread ");
              break;
            case UNDELETED:
              out.append(" undeleted thread ");
              break;
            case MOVED:
              out.append(" moved thread ");
              break;
            case MODIFIED:
              out.append(" edited thread ");
              break;
            default:
              break;
          }

          if (in.getAction() == Actions.CREATED) {
            out.append(Colors.TEAL + "[[").append(in.getTitle()).append("]] " + Colors.NORMAL);
            out.append("(").append(in.getSize()).append(") ");
          } else {
            out.append(Colors.TEAL + "[[").append(in.getTitle()).append("]] " + Colors.NORMAL);
          }

          break;
      }

      out.append(Colors.DARK_BLUE).append(in.getUrl()).append(Colors.NORMAL + " : ");

      out.append(Colors.OLIVE).append(in.getSnippet());
      manager.getBotById(0).send().message(main.getReportChannel(), out.toString());

    } catch (JSONException e1) {
      System.out.print(e1.getClass() + ": " + e1.getMessage());
      e1.printStackTrace();
    } catch (Exception e1) {
      System.out.print(e1.getClass() + ": " + e1.getMessage());
      e1.printStackTrace();
    }

  }
}
