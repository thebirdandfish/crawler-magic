package com.fermedu.crawler.entity.book;

import com.fermedu.crawler.entity.ExtractedEntity;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Program: book-crawler
 * @Create: 2020-12-14 20:16
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class BookContent implements Serializable, ExtractedEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long ruleId;
    private String contentUrl;

    /** extracted */
    private String contentChapterCode; // 从url提取
    private String contentName;
    private String contentCode; // nullable 如果一个章节分多页， 从url提取
    @Column(columnDefinition="text")
    private String contentTextJsonArray;

    /** generated */
    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;
}
