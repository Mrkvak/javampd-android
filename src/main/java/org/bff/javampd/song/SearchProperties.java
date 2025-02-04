package org.bff.javampd.song;

import javax.inject.Inject;
import lombok.NoArgsConstructor;
import org.bff.javampd.server.MPDProperties;

/**
 * @author bill
 */
@NoArgsConstructor(onConstructor_ = @Inject)
public class SearchProperties extends MPDProperties {
  private enum Command {
    FIND("db.find"),
    SEARCH("db.search"),
    WINDOW("db.window");

    private final String key;

    Command(String key) {
      this.key = key;
    }

    public String getKey() {
      return key;
    }
  }

  public String getFind() {
    return getPropertyString(Command.FIND.getKey());
  }

  public String getWindow() {
    return getPropertyString(Command.WINDOW.getKey());
  }

  public String getSearch() {
    return getPropertyString(Command.SEARCH.getKey());
  }
}
