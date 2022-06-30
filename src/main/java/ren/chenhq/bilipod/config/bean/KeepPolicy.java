package ren.chenhq.bilipod.config.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ren.chenhq.bilipod.config.utils.ConfigUtils;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeepPolicy {
    private String type;
    private String value;

    public KeepPolicyType getKeepPolicyType() {
        return ConfigUtils.parseConfigKeepPolicy(type);
    }

    public Integer getEpisodeValue() {
        if (KeepPolicyType.episode == getKeepPolicyType()) {
            return Integer.parseInt(value);
        }
        return null;
    }

    public Duration getDurationValue() {
        if (KeepPolicyType.time == getKeepPolicyType()) {
            return ConfigUtils.parseConfigPeriodStr(value);
        }
        return null;
    }
}
