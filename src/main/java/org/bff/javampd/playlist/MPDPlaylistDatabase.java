package org.bff.javampd.playlist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.bff.javampd.command.CommandExecutor;
import org.bff.javampd.database.DatabaseProperties;
import org.bff.javampd.database.TagLister;
import org.bff.javampd.song.MPDSong;
import org.bff.javampd.song.SongConverter;
import org.bff.javampd.song.SongDatabase;

/**
 * MPDPlaylistDatabase represents a playlist database to a {@link org.bff.javampd.server.MPD}. To
 * obtain an instance of the class you must use the {@link
 * org.bff.javampd.database.MusicDatabase#getPlaylistDatabase()} method from the {@link
 * org.bff.javampd.server.MPD} connection class.
 *
 * @author Bill
 */
public class MPDPlaylistDatabase implements PlaylistDatabase {
  private final SongDatabase songDatabase;
  private final CommandExecutor commandExecutor;
  private final DatabaseProperties databaseProperties;
  private final TagLister tagLister;
  private final SongConverter songConverter;

  @Inject
  public MPDPlaylistDatabase(
      SongDatabase songDatabase,
      CommandExecutor commandExecutor,
      DatabaseProperties databaseProperties,
      TagLister tagLister,
      SongConverter songConverter,
      PlaylistSongConverter playlistSongConverter) {
    this.songDatabase = songDatabase;
    this.commandExecutor = commandExecutor;
    this.databaseProperties = databaseProperties;
    this.tagLister = tagLister;
    this.songConverter = songConverter;
  }

  @Override
  public Collection<MPDSavedPlaylist> listSavedPlaylists() {
    List<MPDSavedPlaylist> playlists = new ArrayList<>();

    listPlaylists()
        .forEach(
            s -> {
              MPDSavedPlaylist playlist = MPDSavedPlaylist.builder(s).build();
              playlist.setSongs(listPlaylistSongs(s));
              playlists.add(playlist);
            });
    return playlists;
  }

  @Override
  public Collection<String> listPlaylists() {
    return tagLister.listInfo(TagLister.ListInfoType.PLAYLIST);
  }

  @Override
  public Collection<MPDSong> listPlaylistSongs(String playlistName) {
    List<String> response =
        commandExecutor.sendCommand(databaseProperties.getListSongs(), playlistName);

    List<MPDSong> songList =
        songConverter.getSongFileNameList(response).stream()
            .map(song -> new ArrayList<>(songDatabase.searchFileName(song)).get(0))
            .collect(Collectors.toList());

    // Handle web radio streams
    songList.addAll(
        songConverter.getSongFileNameList(response).stream()
            .filter(stream -> Pattern.compile("http.+").matcher(stream.toLowerCase()).matches())
            .map(song -> MPDPlaylistSong.builder().file(song).title(song).build())
            .collect(Collectors.toList()));

    return songList;
  }

  @Override
  public int countPlaylistSongs(String playlistName) {
    List<String> response =
        commandExecutor.sendCommand(databaseProperties.getListSongs(), playlistName);

    return response.size();
  }
}
