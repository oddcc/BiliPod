package ren.chenhq.bilipod.config.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import ren.chenhq.bilipod.config.bean.Config;
import ren.chenhq.bilipod.config.bean.FormatType;
import ren.chenhq.bilipod.config.bean.KeepPolicyType;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ConfigUtils {

    private ConfigUtils() {
    }

    private static final Pattern PERIOD_STR_PATTERN = Pattern.compile("(?<day>\\d*d)?(?<hour>\\d*h)?(?<minutes>\\d*m)?");

    private static final ObjectMapper MAPPER = new ObjectMapper(new YAMLFactory());

    /**
     * @param periodStr examples: "60m", "4h", "2h45m", "1d15h20m"
     * @return Duration of the string, "1d15h20m" is 1 day, 15 hours, 20 minutes
     */
    public static Duration parseConfigPeriodStr(String periodStr) {
        if (StringUtils.isBlank(periodStr)) {
            return null;
        }
        Matcher matcher = PERIOD_STR_PATTERN.matcher(periodStr);
        if (!matcher.matches()) {
            return null;
        }
        int day = matcher.group("day") == null ? 0 : Integer.parseInt(matcher.group("day").replace("d", ""));
        int hour = matcher.group("hour") == null ? 0 : Integer.parseInt(matcher.group("hour").replace("h", ""));
        int minutes = matcher.group("minutes") == null ? 0 : Integer.parseInt(matcher.group("minutes").replace("m", ""));
        return Duration.ofDays(day).plusHours(hour).plusMinutes(minutes);
    }

    public static FormatType parseConfigFormat(String formatStr) {
        if (StringUtils.isBlank(formatStr)) {
            return null;
        }
        switch (formatStr) {
            case "video":
                return FormatType.video;
            case "audio":
                return FormatType.audio;
            default:
                return null;
        }
    }

    public static KeepPolicyType parseConfigKeepPolicy(String keepPolicyStr) {
        if (StringUtils.isBlank(keepPolicyStr)) {
            return null;
        }
        switch (keepPolicyStr) {
            case "episode":
                return KeepPolicyType.episode;
            case "time":
                return KeepPolicyType.time;
            default:
                return null;
        }
    }

    /**
     * Load and parse config from config file.
     * @param configPath config file path
     * @return Parsed config
     */
    public static Config loadConfig(String configPath) throws IOException {
        File configFile = new File(configPath);
        if (!configFile.exists()) {
            log.error("Config file not found: {}", configPath);
            return null;
        }
        return MAPPER.readValue(configFile, Config.class);
    }
}
