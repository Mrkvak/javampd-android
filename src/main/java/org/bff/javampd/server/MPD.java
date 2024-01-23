package org.bff.javampd.server;

import java.net.InetAddress;
import java.util.Optional;
import javax.inject.Singleton;
import lombok.Builder;
import lombok.Getter;
import org.bff.javampd.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MPD represents a connection to a MPD server. The commands are maintained in a properties file
 * called mpd.properties.
 *
 * <p>Uses the builder pattern for construction.
 *
 * <p>Defaults are:
 *
 * <p>server - localhost port - 6600 no timeout no password
 *
 * @author Bill
 */
@Getter
@Singleton
public class MPD extends MPDServer {
  private static final Logger LOGGER = LoggerFactory.getLogger(MPD.class);

  private int port = 6600;
  private String server = "localhost";

  private final int timeout;
  private final String password;
  private final InetAddress address;

  @Builder
  public MPD(String server, Integer port, int timeout, String password) {
    this.server = Optional.ofNullable(server).orElse(this.server);
    this.port = Optional.ofNullable(port).orElse(this.port);
    this.timeout = timeout;
    this.password = password;

    try {
      this.address = InetAddress.getByName(this.server);
      init();
      authenticate();
    } catch (Exception e) {
      throw new MPDConnectionException(e);
    }
  }

  void init() {
    // var injector =
    //        Guice.createInjector(new MPDModule(), new MPDDatabaseModule(), new
    // MPDMonitorModule());

    MPDComponent mpdComponent = DaggerMPDComponent.create();
    bind(mpdComponent);

    setMpd(this);
    authenticate();
    // injector.getInstance(ConnectionMonitor.class).setServer(this);
  }

  private void bind(MPDComponent component) {
    serverProperties = component.serverProperties();
    player = component.player();
    admin = component.admin();
    serverStatistics = component.serverStatistics();
    serverStatus = component.serverStatus();
    musicDatabase = component.musicDatabase();
    songSearcher = component.songSearcher();
    commandExecutor = component.commandExecutor();
    artworkFinder = component.artworkFinder();
    standAloneMonitor = component.standaloneMonitor();
    playlist = component.playlist();
  }

  void authenticate() {
    if (usingPassword()) {
      super.authenticate(this.password);
    }
  }

  private boolean usingPassword() {
    return this.password != null && !"".equals(this.password);
  }
}
