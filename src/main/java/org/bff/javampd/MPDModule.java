package org.bff.javampd;

import dagger.Binds;
import dagger.Module;
import org.bff.javampd.admin.Admin;
import org.bff.javampd.admin.MPDAdmin;
import org.bff.javampd.album.AlbumConverter;
import org.bff.javampd.album.MPDAlbumConverter;
import org.bff.javampd.art.ArtworkFinder;
import org.bff.javampd.art.MPDArtworkFinder;
import org.bff.javampd.command.CommandExecutor;
import org.bff.javampd.command.MPDCommandExecutor;
import org.bff.javampd.database.MPDTagLister;
import org.bff.javampd.database.TagLister;
import org.bff.javampd.player.MPDPlayer;
import org.bff.javampd.player.Player;
import org.bff.javampd.playlist.MPDPlaylist;
import org.bff.javampd.playlist.MPDPlaylistSongConverter;
import org.bff.javampd.playlist.Playlist;
import org.bff.javampd.playlist.PlaylistSongConverter;
import org.bff.javampd.server.MPDServerStatus;
import org.bff.javampd.server.ServerStatus;
import org.bff.javampd.song.MPDSongConverter;
import org.bff.javampd.song.SongConverter;
import org.bff.javampd.statistics.MPDServerStatistics;
import org.bff.javampd.statistics.MPDStatsConverter;
import org.bff.javampd.statistics.ServerStatistics;
import org.bff.javampd.statistics.StatsConverter;

/**
 * Initializes the DI bindings
 *
 * @author bill
 */
@Module
public abstract class MPDModule {
  @Binds
  abstract Admin bindAdmin(MPDAdmin mpdClass);

  @Binds
  abstract Player bindPlayer(MPDPlayer mpdClass);

  @Binds
  abstract Playlist bindPlaylist(MPDPlaylist mpdClass);

  @Binds
  abstract ServerStatus bindServerStatus(MPDServerStatus mpdClass);

  @Binds
  abstract ServerStatistics bindServerStatistics(MPDServerStatistics mpdClass);

  @Binds
  abstract CommandExecutor bindCommandExecutor(MPDCommandExecutor mpdClass);

  @Binds
  abstract TagLister bindTagLister(MPDTagLister mpdClass);

  @Binds
  abstract SongConverter bindSongConverter(MPDSongConverter mpdClass);

  @Binds
  abstract PlaylistSongConverter bindPlaylistSongConverter(MPDPlaylistSongConverter mpdClass);

  @Binds
  abstract AlbumConverter bindAlbumConverter(MPDAlbumConverter mpdClass);

  @Binds
  abstract ArtworkFinder bindArtworkFinder(MPDArtworkFinder mpdClass);

  @Binds
  abstract StatsConverter bindStatsConverter(MPDStatsConverter mpdClass);

  @Binds
  abstract Clock bindClock(MPDSystemClock mpdClass);
}
