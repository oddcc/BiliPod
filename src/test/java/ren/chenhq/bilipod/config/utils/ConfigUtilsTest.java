package ren.chenhq.bilipod.config.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ren.chenhq.bilipod.config.bean.FormatType;
import ren.chenhq.bilipod.config.bean.KeepPolicyType;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ConfigUtilsTest {
    @ParameterizedTest
    @MethodSource("parseConfigPeriodStrParameters")
    void parseConfigPeriodStr(String periodStr, Duration expected) {
        assertEquals(expected, ConfigUtils.parseConfigPeriodStr(periodStr), "parseConfigPeriodStr(" + periodStr + ")");
    }

    public static Stream<Arguments> parseConfigPeriodStrParameters() {
        return Stream.of(
                Arguments.of("", null),
                Arguments.of("60m", Duration.ofMinutes(60)),
                Arguments.of("12h", Duration.ofHours(12)),
                Arguments.of("1d30h20m", Duration.ofDays(1).plusHours(30).plusMinutes(20))
        );
    }

    @ParameterizedTest
    @MethodSource("parseConfigFormatParameters")
    void parseConfigFormat(String formatStr, FormatType expected) {
        assertEquals(expected, ConfigUtils.parseConfigFormat(formatStr), "parseConfigFormat(" + formatStr + ")");
    }

    public static Stream<Arguments> parseConfigFormatParameters() {
        return Stream.of(
                Arguments.of("video", FormatType.video),
                Arguments.of("audio", FormatType.audio),
                Arguments.of("unknown", null)
        );
    }

    @ParameterizedTest
    @MethodSource("parseConfigKeepPolicyParameters")
    void parseConfigKeepPolicy(String keepPolicyStr, KeepPolicyType expected) {
        assertEquals(expected, ConfigUtils.parseConfigKeepPolicy(keepPolicyStr), "parseConfigKeepPolicy(" + keepPolicyStr + ")");
    }

    public static Stream<Arguments> parseConfigKeepPolicyParameters() {
        return Stream.of(
                Arguments.of("episode", KeepPolicyType.episode),
                Arguments.of("time", KeepPolicyType.time),
                Arguments.of("unknown", null)
        );
    }
}