package org.bff.javampd;

import dagger.Component;
import javax.inject.Singleton;
import org.bff.javampd.admin.Admin;
import org.bff.javampd.art.ArtworkFinder;
import org.bff.javampd.command.CommandExecutor;
import org.bff.javampd.database.MusicDatabase;
import org.bff.javampd.monitor.StandAloneMonitor;
import org.bff.javampd.player.Player;
import org.bff.javampd.playlist.Playlist;
import org.bff.javampd.server.ServerProperties;
import org.bff.javampd.server.ServerStatus;
import org.bff.javampd.song.SongSearcher;
import org.bff.javampd.statistics.ServerStatistics;
import org.bff.javampd.statistics.StatsConverter;

@Singleton
@Component(modules = {MPDModule.class, MPDDatabaseModule.class, MPDMonitorModule.class})
public interface MPDComponent {
  StatsConverter statsConverter();

  Player player();

  Playlist playlist();

  Admin admin();

  ServerStatistics serverStatistics();

  ServerProperties serverProperties();

  ServerStatus serverStatus();

  MusicDatabase musicDatabase();

  SongSearcher songSearcher();

  CommandExecutor commandExecutor();

  ArtworkFinder artworkFinder();

  StandAloneMonitor standaloneMonitor();
}
