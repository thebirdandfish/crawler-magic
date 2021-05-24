package com.fermedu.crawler.entity.book;

import com.alibaba.fastjson.JSON;
import com.fermedu.crawler.entity.EntityGeneric;
import com.fermedu.crawler.entity.ExtractedEntity;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2020-12-11 17:41
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class BookSite implements Serializable, ExtractedEntity, EntityGeneric<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long ruleId;

    private String indexSiteTitle;

    @Column(columnDefinition = "text") // bookUrlList String json 对应长文本
    private String bookUrlListJson;

    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;

    public List<String> getBookUrlList() {
        return JSON.parseArray(this.bookUrlListJson, String.class);
    }

}
