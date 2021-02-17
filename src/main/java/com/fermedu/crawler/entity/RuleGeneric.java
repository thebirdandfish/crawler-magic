package com.fermedu.crawler.entity;

import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2021-02-03 23:57
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface RuleGeneric extends EntityGeneric {
    @Override
    Long getId();

    String getDomain();

    List<String> getAllUrlRegex();

    enum Status {
        ON(0),
        OFF(1);

        private int value;

        int getValue() {
            return value;
        }

        Status(int value) {
            this.value = value;
        }
    }
}
