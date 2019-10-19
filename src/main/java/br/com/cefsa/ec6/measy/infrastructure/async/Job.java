package br.com.cefsa.ec6.measy.infrastructure.async;

import static java.lang.Thread.sleep;

public class Job {
  private Long intervalMillis = 1000L;
  private Integer repetitions = 0;
  private Runnable action;

  public Job(Runnable action) {
    this.action = action;
  }

  public Job runAfter(Long millis) {
    this.intervalMillis = millis;
    return this;
  }

  public Job repeat(Integer repetitions) {
    if (repetitions < -1)
      throw new IllegalArgumentException(
          "repetition parameter should be a positive integer, or -1 (indefinitely)");

    this.repetitions = repetitions;
    return this;
  }

  public void run() {
    Thread worker =
        new Thread(
            () -> {
              Integer iterations = 0;

              while (true) {

                try {
                  sleep(intervalMillis);
                  action.run();
                } catch (Exception e) {
                }

                if (repetitions == -1) continue;

                iterations++;
                if (iterations > repetitions) break;
              }
            });
    worker.start();
  }
}
