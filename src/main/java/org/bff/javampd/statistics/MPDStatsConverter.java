package org.bff.javampd.statistics;

import static org.bff.javampd.command.CommandExecutor.COMMAND_TERMINATION;
import static org.bff.javampd.statistics.Statistic.*;

import java.util.EnumMap;
import java.util.List;
import javax.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(onConstructor_ = @Inject)
public class MPDStatsConverter implements StatsConverter {

  @Override
  public MPDStatistics convertResponseToStats(List<String> list) {
    var iterator = list.iterator();
    var props = new EnumMap<Statistic, String>(Statistic.class);

    String line;
    while (iterator.hasNext()) {
      line = iterator.next();

      if (!isStreamTerminated(line)) {
        props.put(lookup(line), line.split(":")[1]);
      }
    }

    return MPDStatistics.builder()
        .artists(Integer.parseInt(checkProp((props.get(ARTISTS)))))
        .albums(Integer.parseInt(checkProp(props.get(ALBUMS))))
        .tracks(Integer.parseInt(checkProp(props.get(SONGS))))
        .databasePlaytime(Long.parseLong(checkProp(props.get(DBPLAYTIME))))
        .lastUpdateTime(Long.parseLong(checkProp(props.get(DBUPDATE))))
        .playtime(Long.parseLong(checkProp(props.get(PLAYTIME))))
        .uptime(Long.parseLong(checkProp(props.get(UPTIME))))
        .build();
  }

  private String checkProp(String s) {
    return s == null ? "0" : s.trim();
  }

  private boolean isStreamTerminated(String line) {
    return line == null || COMMAND_TERMINATION.equalsIgnoreCase(line);
  }
}
