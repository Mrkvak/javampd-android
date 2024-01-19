package org.bff.javampd;


import dagger.Binds;
import org.bff.javampd.monitor.*;
import dagger.Module;

/**
 * Initializes the DI bindings
 *
 * @author bill
 */
@Module
public abstract class MPDMonitorModule {
  @Binds
  abstract StandAloneMonitor bindStandaloneMonitor(MPDStandAloneMonitor mpdClass);
  @Binds
  abstract OutputMonitor bindOutputMonitor(MPDOutputMonitor mpdClass);
  @Binds
  abstract TrackMonitor bindTrackMonitor(MPDTrackMonitor mpdClass);
  @Binds
  abstract ConnectionMonitor bindConnectionMonitor(MPDConnectionMonitor mpdClass);
  @Binds
  abstract VolumeMonitor bindVolumeMonitor(MPDVolumeMonitor mpdClass);
  @Binds
  abstract PlayerMonitor bindPlayerMonitor(MPDPlayerMonitor mpdClass);
  @Binds
  abstract BitrateMonitor bindBitrateMonitor(MPDBitrateMonitor mpdClass);
  @Binds
  abstract PlaylistMonitor bindPlaylistMonitor(MPDPlaylistMonitor mpdClass);
  @Binds
  abstract ErrorMonitor bindErrorMonitor(MPDErrorMonitor mpdClass);
}
