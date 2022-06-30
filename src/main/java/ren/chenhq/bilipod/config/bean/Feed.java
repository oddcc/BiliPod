package ren.chenhq.bilipod.config.bean;

import lombok.Data;

@Data
public class Feed {
    private String name;
    private String description;
    private String author;
    private String id;
    private Integer pageSize;
    private String updatePeriod;
    private String format;
    private KeepPolicy keepPolicy;
    private FilterPolicy filterPolicy;
    private String cover;
}
