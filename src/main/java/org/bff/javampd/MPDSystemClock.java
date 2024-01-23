package org.bff.javampd;

import java.time.LocalDateTime;
import javax.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class MPDSystemClock implements Clock {
  @Override
  public LocalDateTime now() {
    return LocalDateTime.now();
  }

  @Override
  public LocalDateTime min() {
    return LocalDateTime.MIN;
  }
}
