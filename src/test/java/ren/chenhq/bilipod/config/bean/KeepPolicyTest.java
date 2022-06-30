package ren.chenhq.bilipod.config.bean;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class KeepPolicyTest {


    @ParameterizedTest
    @MethodSource("episodeValueProvider")
    void getEpisodeValue(KeepPolicy policy, Integer expected) {
        assertEquals(expected, policy.getEpisodeValue());
    }

    public static Stream<Arguments> episodeValueProvider() {
        return Stream.of(
                Arguments.of(new KeepPolicy("episode", "1"), 1),
                Arguments.of(new KeepPolicy("time", "1"), null)
        );
    }

    @ParameterizedTest
    @MethodSource("durationValueProvider")
    void getDurationValue(KeepPolicy policy, Duration expected) {
        assertEquals(expected, policy.getDurationValue());
    }

    public static Stream<Arguments> durationValueProvider() {
        return Stream.of(
                Arguments.of(new KeepPolicy("episode", "1d"), null),
                Arguments.of(new KeepPolicy("time", "1d"), Duration.ofDays(1))
        );
    }
}