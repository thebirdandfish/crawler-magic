package com.fermedu.crawler.entity;

import com.guguskill.common.util.SimHashUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2020-12-11 02:26
 * @Author: JustThink
 * @Description: 对于页面内抓取的url，有xpath（尽量宽泛，保证目标url是子集；xpath仅仅用来匹配标签，不抽取属性）优先xpath+links+regex，没有xpath就用regex
 * @Include:
 **/
@Entity
@Data
@DynamicUpdate // 更改过的column会update
public class BookRule implements Serializable, EntityGeneric, RuleGeneric {

    @Id
    private Long id;

    /**
     * preset
     */
    private String bookSourceName; // preset 网站名
    private String domain; // preset 主页url http://m.xx31xs.org 不能以/结尾，可以带端口，可以是http或者https
    public void setDomain(String domain) {
        this.domain = domain;
        this.id = SimHashUtil.simHash(domain);
    }
    private String bookSourceBaseUrlRegex; // preset basic regex "https://www.youtube.com" does NOT end with "/"
    private boolean enable = true; // preset 默认true， 生效
    private String httpUserAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko, By Black Prism) Chrome/99.0 Safari/537.36"; // 默认
    private Integer weight = 0;

    /**
     * 网站首页
     */
    private String indexUrlRegex;
    private String indexPageSiteTitle = "//head/title/text()"; // 首页的网站名

    /**
     * 分类页面（展示各个分类）
     */
    private String catePageCateNameInNode = "/*/text()"; // xpath 分类页面（展示各个分类） 分类名
    private String catePageCateNodeXpath; // 提取包含url和cateName的标签（接下来抽取name和url）
    private String cateUrlRegex; // regex
    private String cateCodeInCateUrl; // regex 对应分类编码，从cateUrl中提取，对应每个分类，例如1 ,2, 4,C1

    /**
     * 单本书籍介绍页面，页面显示标题，作者，简介，最近章节等
     */
    private String bookUrlRegex; // regex 对应 bookPage的Url
    private String bookPageCateNodeXpath; // nullable xpath 对应 bookPage的Url e.g. "\"//ol/li/a[@href*=\"list\"]"
    private String bookPageCateNameInNode = "/*/text()"; // xpath e.g. "//ol/li/a[@href*=\"list\"]/text()"
    private String bookPageBookStatus; // 完结，连载
    private String bookPageBookAuthor; // xpath
    private String bookPageBookCover; // 书籍封面 url 的xpath路径
    private String bookPageBookIntro; // my
    private String bookPageBookName; // 书籍名 indexPage 和 bookPage获取
    private String bookPageBookLatestChapter; // my 最近文章

    /**
     * 展示全部章节后（即便需要分页）
     */
    private String chapterListUrlRegex; // regex helpUrl 章节列表页可能还有 章节下一列表 ，统一为regex规则
    private String chapterListPageChapterNameInNode = "/*/text()"; // xpath 章节列表页 ，每个章节的名字
    private String chapterListPageChapterNodeXpath; // 提取包含url和chapterName的标签（接下来抽取name和url）
    private String chapterUrlRegex; // regex targetUrl
    private String bookCodeInChapterUrl; // regex 中chapterUrl中提取章节的序数

    /**
     * 单个章节内容（即便需要分页）
     */
    private String contentUrlRegex; // 需要包含尽可能多的url，包含chapterUrl点进去之后的contentUrl，包含contenPage中的下一页按钮url
    private String contentPageContentName; // contentPage的标题，对应之前chapter的名字
    private String bookCodeInContentUrl; // 从当前contentPage的url中提取chapterCode
    private String chapterCodeInContentUrl; // nullable 如果一个章节分成多页 从当前content Page的url中提取contentCode
    private String contentPageContentEachText;

    /**
     * 搜索书籍页面，展示多本匹配书籍
     */
//    private String ruleSearchAuthor;
//    private String ruleSearchCoverUrlXpath="//*";
//    private String ruleSearchCoverUrlRegex;


    @Override
    public List<String> getAllUrlRegex() {
        ArrayList<String> allUrlRegex = new ArrayList<>();

        allUrlRegex.add(this.getCateUrlRegex());
        allUrlRegex.add(this.getBookUrlRegex());
        allUrlRegex.add(this.getChapterListUrlRegex());
        allUrlRegex.add(this.getChapterUrlRegex());
        allUrlRegex.add(this.getContentUrlRegex());

        return allUrlRegex;
    }
}
