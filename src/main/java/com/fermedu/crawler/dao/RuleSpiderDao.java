package com.fermedu.crawler.dao;

import com.fermedu.crawler.entity.RuleGeneric;
import com.fermedu.crawler.enumeration.SpiderStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Program: book-crawler
 * @Create: 2021-01-23 02:49
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Data
public class RuleSpiderDao implements Serializable {

    /** rule */
    private String domain;
    private RuleGeneric.Status ruleStatus; // rule默认初始关闭
    private boolean reset; // 重置set和queue，重新爬取

    /** 本机 */
    private String ipAddress;
    private int port = -1;

    /**
     * spider
     */
    private String uuid;
    private SpiderStatusEnum spiderStatus; // spider的状态。初始为关闭
    private int leftPageCount = -1;
    private int totalPageCount = -1;
    private int successPageCount = -1;
    private int errorPageCount = -1;
    private String errorPagesListJson;
    private int thread = -1;
    private Date startTime;
    private int pagePerSecond = -1;
    /**
     * spider 被操作的记录
     */
    private Date lastInit; // 上一次收到创建指令的时间
    private Date lastStop; // 上一次收到停止指令的时间

    /**
     * rule 和 spider的状态不符 ， 是否可以停止spider
     */
    public boolean ifReadyToStopSpider() {
        if (this.getLastStop() != null
                && (new Date().getTime() - this.getLastStop().getTime()) <= 60 * 1000) {
            return false; // 近期发布停止爬虫指令
        }

        if (this.getRuleStatus().equals(RuleGeneric.Status.ON)) {
            return false; // rule on
        } // rule off

        if (this.getSpiderStatus().equals(SpiderStatusEnum.STOPPED)) { // spider 1.init正在启动，2running爬虫正在运行
            return false;
        } else if (this.getSpiderStatus().equals(SpiderStatusEnum.UNCREATED)) { // spider stopped 1.爬虫未启动，
            return false; // spider已经关了
        }

        return true;
    }

    /**
     * rule 和 spider的状态不符 ， 是否可以运行spider
     */
    public boolean ifReadyToRunSpider() {
        /** lastInit? */
        if (this.getLastInit() != null
                && (new Date().getTime() - this.getLastInit().getTime()) <= 60 * 1000) {
            return false; // lastInit very recent
        } // lastInit very loing ago

        /** 确认 查询rule on ? */
        if (this.getRuleStatus().equals(RuleGeneric.Status.OFF)) {// rule on
            return false; // rule is OFF, thus do NOT run the spider
        }// rule is ON

        /** spider status? */
        if (this.getSpiderStatus().equals(SpiderStatusEnum.INIT)) { // 爬虫没有创建
            return false;
        } else if (this.getSpiderStatus().equals(SpiderStatusEnum.RUNNING)) { // rule on spider init
            return false;
        }
        return true;
    }
}
