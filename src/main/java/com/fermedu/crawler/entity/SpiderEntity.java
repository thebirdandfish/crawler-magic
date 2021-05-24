package com.fermedu.crawler.entity;

import com.alibaba.fastjson.JSON;
import com.fermedu.crawler.enumeration.SpiderStatusEnum;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2020-12-31 22:59
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Entity
@Data
@DynamicInsert
@DynamicUpdate // 更改过的column会update
public class SpiderEntity implements Serializable, EntityGeneric {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** rule */
    private String domain; // 对应webmagic给的domain

    /** 本机 */
    private String ipAddress;
    private int port = -1;

    /* spider  */
    private String uuid; // spiderUuid 通过uuid生成的随机字符串

    @Enumerated(EnumType.STRING)
    private SpiderStatusEnum spiderStatus; // spider的状态。初始为关闭
    private int leftPageCount = -1;
    private int totalPageCount = -1;
    private int successPageCount = -1;
    private int errorPageCount = -1;
    @Column(columnDefinition = "text") // bookUrlList String json 对应长文本
    private String errorPagesListJson;
    private int thread = -1;
    private Date startTime;
    private int pagePerSecond = -1;
    /**
     * spider 被操作的记录
     */
    private Date lastInit; // 上一次收到创建指令的时间
    private Date lastStop; // 上一次收到停止指令的时间

    @CreationTimestamp
    private Date createTime; // 规则创建时间
    @UpdateTimestamp
    private Date updateTime;

    /**
     * json 转 list
     */
    public List<String> getErrorPagesList() {
        return JSON.parseArray(this.errorPagesListJson, String.class);
    }

}
