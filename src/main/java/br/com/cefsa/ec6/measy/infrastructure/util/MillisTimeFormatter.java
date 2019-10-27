package br.com.cefsa.ec6.measy.infrastructure.util;

import java.util.concurrent.TimeUnit;

public class MillisTimeFormatter {

    public static String format(Integer millis) {
        return format(millis, new OptionalHourPartTimeFormattingStrategy());
    }

    public static String format(Integer millis, TimeFormattingStrategy strategy) {
        return format(Integer.toUnsignedLong(millis), strategy);
    }

    public static String format(Long millis) {
        return format(millis, new OptionalHourPartTimeFormattingStrategy());
    }

    public static String format(Long millis, TimeFormattingStrategy strategy) {
        return strategy.apply(millis);
    }

    public interface TimeFormattingStrategy {
        String apply(Long millis);
    }

    public static class OptionalHourPartTimeFormattingStrategy implements TimeFormattingStrategy {
        @Override
        public String apply(Long millis) {
            Long hourPart = TimeUnit.MILLISECONDS.toHours(millis);
            Long minutePart = TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1);
            Long secondPart = TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1);

            return hourPart > 0
                    ? String.format("%02d:%02d:%02d", hourPart, minutePart, secondPart)
                    : String.format("%02d:%02d", minutePart, secondPart);
        }
    }

}
