package com.fermedu.crawler.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Program: book-crawler
 * @Create: 2020-12-18 20:17
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Entity
@Data
@DynamicInsert
@DynamicUpdate // 更改过的column会update
public class Replaced implements EntityGeneric<Long> {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String fromRegex;

    private String intoString;
}
