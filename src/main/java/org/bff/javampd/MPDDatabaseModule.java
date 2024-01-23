package org.bff.javampd;

import dagger.Binds;
import dagger.Module;
import org.bff.javampd.album.AlbumDatabase;
import org.bff.javampd.album.MPDAlbumDatabase;
import org.bff.javampd.artist.ArtistDatabase;
import org.bff.javampd.artist.MPDArtistDatabase;
import org.bff.javampd.database.MPDMusicDatabase;
import org.bff.javampd.database.MusicDatabase;
import org.bff.javampd.file.FileDatabase;
import org.bff.javampd.file.MPDFileDatabase;
import org.bff.javampd.genre.GenreDatabase;
import org.bff.javampd.genre.MPDGenreDatabase;
import org.bff.javampd.playlist.MPDPlaylistDatabase;
import org.bff.javampd.playlist.PlaylistDatabase;
import org.bff.javampd.song.MPDSongDatabase;
import org.bff.javampd.song.MPDSongSearcher;
import org.bff.javampd.song.SongDatabase;
import org.bff.javampd.song.SongSearcher;
import org.bff.javampd.year.DateDatabase;
import org.bff.javampd.year.MPDDateDatabase;

/**
 * Initializes the DI bindings
 *
 * @author bill
 */
@Module
public abstract class MPDDatabaseModule {
  @Binds
  abstract ArtistDatabase bindArtist(MPDArtistDatabase mpdClass);

  @Binds
  abstract AlbumDatabase bindAlbum(MPDAlbumDatabase mpdClass);

  @Binds
  abstract SongDatabase bindSong(MPDSongDatabase mpdClass);

  @Binds
  abstract GenreDatabase bindGenre(MPDGenreDatabase mpdClass);

  @Binds
  abstract PlaylistDatabase bindPlaylist(MPDPlaylistDatabase mpdClass);

  @Binds
  abstract FileDatabase bindFile(MPDFileDatabase mpdClass);

  @Binds
  abstract DateDatabase bindDate(MPDDateDatabase mpdClass);

  @Binds
  abstract MusicDatabase bindMusic(MPDMusicDatabase mpdClass);

  @Binds
  abstract SongSearcher bindSongSearcher(MPDSongSearcher mpdClass);
}
