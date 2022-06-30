package ren.chenhq.bilipod.config.bean;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

@Data
public class FilterPolicy {
    private String withTitle;
    private String withOutTitle;
    private String withDescription;
    private String withOutDescription;

    private Pattern withTitlePattern;
    private Pattern withOutTitlePattern;
    private Pattern withDescriptionPattern;
    private Pattern withOutDescriptionPattern;

    private boolean initialized = false;

    public void init() {
        if (initialized) {
            return;
        }
        synchronized (this) {
            if (initialized) {
                return;
            }
            if (StringUtils.isNotBlank(withTitle)) {
                withTitlePattern = Pattern.compile(withTitle);
            }
            if (StringUtils.isNotBlank(withOutTitle)) {
                withOutTitlePattern = Pattern.compile(withOutTitle);
            }
            if (StringUtils.isNotBlank(withDescription)) {
                withDescriptionPattern = Pattern.compile(withDescription);
            }
            if (StringUtils.isNotBlank(withOutDescription)) {
                withOutDescriptionPattern = Pattern.compile(withOutDescription);
            }
            initialized = true;
        }
    }

    public Boolean filteredByTitle(String title) {
        init();
        if (withOutTitlePattern != null && withOutTitlePattern.matcher(title).matches()) {
            return true;
        }
        return withTitlePattern != null && !withTitlePattern.matcher(title).matches();
    }

    public Boolean filteredByDescription(String desc) {
        init();
        if (withOutDescriptionPattern != null && withOutDescriptionPattern.matcher(desc).matches()) {
            return true;
        }
        return withDescriptionPattern != null && !withDescriptionPattern.matcher(desc).matches();
    }
}
