package pl.sklep;

import java.util.Optional;

public class Util {

    public static Optional<Long> parseLong(String s) {
        try {
            return Optional.of(Long.parseLong(s));
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }

    public static Optional<Integer> parseInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }

    public static Optional<Boolean> parseBool(String s) {
        if ("true".equalsIgnoreCase(s)) {
            return Optional.of(true);
        } else if ("false".equalsIgnoreCase(s)) {
            return Optional.of(false);
        } else {
            return Optional.empty();
        }
    }

}
