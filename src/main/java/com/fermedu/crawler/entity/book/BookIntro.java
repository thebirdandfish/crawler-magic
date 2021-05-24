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
 * @Create: 2020-12-13 18:08
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class BookIntro implements Serializable, ExtractedEntity {
    /* preset */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long ruleId;
    private String bookUrl;
    /**
     * extracted
     */
    private String cateUrl;
    private String cateCode; // 分类的编码， 例如1 ，2 ， 15 ， "xiuzhen" 等
    private String cateName;
    private String bookAuthor;
    private String bookIntro;
    private String bookStatus; // 书籍状态 连载，完结
    private String bookCover;  // 书籍封面的url
    private String bookName;
    private String bookLatestChapter;
    private String bookChapterListUrl;

    /** generated */
    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;
}
