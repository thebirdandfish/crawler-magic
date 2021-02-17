package com.fermedu.crawler.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Program: book-crawler
 * @Create: 2021-01-22 22:12
 * @Author: JustThink
 * @Description: 表示rule开关的实体类
 * @Include:
 **/
@Entity
@Data
@DynamicUpdate // 更改过的column会update
public class RuleControl implements Serializable, EntityGeneric {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * rule
     */
    private String domain;
    @Enumerated(EnumType.STRING)
    private BookRule.Status ruleStatus; // rule默认初始关闭
    @Column(columnDefinition = "bit(1) default false", nullable = false)
    private boolean reset; // 重置set和queue，重新爬取


    @CreationTimestamp
    private Date createTime; // 规则创建时间
    @UpdateTimestamp
    private Date updateTime;
}
