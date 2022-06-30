package ren.chenhq.bilipod.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import ren.chenhq.bilipod.config.bean.*;
import ren.chenhq.bilipod.config.utils.ConfigUtils;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class ConfigManager {
    private Config config;

    private final Map<String, Feed> feedMap;


    private ConfigManager(Config config) {
        this.config = config;

        feedMap = new HashMap<>();
        for (Feed feed : config.getFeeds()) {
            if (feedMap.containsKey(feed.getName())) {
                log.warn("Duplicate feed found, will skip for name: {}", feed.getName());
            } else {
                feedMap.put(feed.getName(), feed);
            }
        }
    }

    public static ConfigManager init(Environment environment) throws IOException {
        String configPath = environment.getProperty("config.path");
        log.info("Initializing config manager with config path: {}", configPath);
        Config config = ConfigUtils.loadConfig(configPath);
        if (config == null) {
            log.error("Failed to init config manager from path: {}", configPath);
            throw new IOException("Failed to init config manager from path: " + configPath);
        }
        return new ConfigManager(config);
    }

    private Feed getConfig(String feedName) {
        return feedMap.get(feedName);
    }

    public String getName(String feedName) {
        Feed feed = getConfig(feedName);
        if (Objects.isNull(feed)) {
            return null;
        }
        return feed.getName();
    }

    public String getDescription(String feedName) {
        Feed feed = getConfig(feedName);
        if (Objects.isNull(feed)) {
            return null;
        }
        return feed.getDescription();
    }

    public String getAuthor(String feedName) {
        Feed feed = getConfig(feedName);
        if (Objects.isNull(feed)) {
            return null;
        }
        return feed.getAuthor();
    }

    public String getId(String feedName) {
        Feed feed = getConfig(feedName);
        if (Objects.isNull(feed)) {
            return null;
        }
        return feed.getId();
    }

    public Integer getPageSize(String feedName) {
        Feed feed = getConfig(feedName);
        if (Objects.isNull(feed)) {
            return null;
        }
        return feed.getPageSize();
    }

    public Duration getUpdatePeriod(String feedName) {
        Feed feed = getConfig(feedName);
        if (Objects.isNull(feed)) {
            return null;
        }
        return ConfigUtils.parseConfigPeriodStr(feed.getUpdatePeriod());
    }

    public FormatType getFormat(String feedName) {
        Feed feed = getConfig(feedName);
        if (Objects.isNull(feed)) {
            return null;
        }
        return ConfigUtils.parseConfigFormat(feed.getFormat());
    }

    public KeepPolicy getKeepPolicy(String feedName) {
        Feed feed = getConfig(feedName);
        if (Objects.isNull(feed)) {
            return null;
        }
        return feed.getKeepPolicy();
    }

    public FilterPolicy getFilterPolicy(String feedName) {
        Feed feed = getConfig(feedName);
        if (Objects.isNull(feed)) {
            return null;
        }
        return feed.getFilterPolicy();
    }

    public String getCover(String feedName) {
        Feed feed = getConfig(feedName);
        if (Objects.isNull(feed)) {
            return null;
        }
        return feed.getCover();
    }
}
