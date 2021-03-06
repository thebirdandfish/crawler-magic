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
 * @Create: 2020-12-14 15:18
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class BookChapter implements Serializable, ExtractedEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long ruleId;
    private String chapterListUrl;

    /** extracted */
    private String chapterName;
    private String chapterUrl;
    private String chapterCode;

    /** generated */
    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;
}
