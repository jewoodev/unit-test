package part2.chapter7.application.service;

public class Precondition {
    public static void requires(boolean isSatisfied) {
        if (!isSatisfied) throw new IllegalArgumentException();
    }
}
