package ren.chenhq.bilipod.config.bean;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FilterPolicyTest {

    private FilterPolicy filterPolicy;


    @BeforeEach
    public void beforeAll() {
        this.filterPolicy = new FilterPolicy();
        filterPolicy.setWithTitle(".*东北.*");
        filterPolicy.setWithOutTitle(".*面.*");
        filterPolicy.setWithDescription(".*支持.*");
        filterPolicy.setWithOutDescription(".*广告.*");
    }

    @ParameterizedTest
    @MethodSource("titleProvider")
    void filteredByTitle(String title, boolean expected) {
        assertEquals(expected, filterPolicy.filteredByTitle(title));
    }

    public static Stream<Arguments> titleProvider() {
        return Stream.of(
                Arguments.of("这菜量，确定是东北菜？", false),
                Arguments.of("点不燃的宜宾燃面，挽不回兄弟情的李庄白肉", true),
                Arguments.of("面是东北人的美食，支持东北人的美食", true)
        );
    }

    @ParameterizedTest
    @MethodSource("descProvider")
    void filteredByDescription(String desc, boolean expected) {
        assertEquals(expected, filterPolicy.filteredByDescription(desc));
    }

    public static Stream<Arguments> descProvider() {
        return Stream.of(
                Arguments.of("点击广告", true),
                Arguments.of("感谢支持", false),
                Arguments.of("点击广告，支持up主", true)
        );
    }
}