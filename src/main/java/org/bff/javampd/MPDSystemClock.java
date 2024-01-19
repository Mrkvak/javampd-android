package org.bff.javampd;

import lombok.NoArgsConstructor;

import javax.inject.Inject;
import java.time.LocalDateTime;

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
