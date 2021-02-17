package com.fermedu.crawler.monitor;

import com.fermedu.crawler.database.RuleDbFacade;
import com.guguskill.common.enumeration.ResultEnum;
import com.guguskill.common.exception.GuInternalException;
import com.guguskill.common.util.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.monitor.SpiderStatusMXBean;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Program: book-crawler
 * @Create: 2021-01-02 20:56
 * @Author: JustThink
 * @Description: 从程序里面实时查询所有/单个spider的状态，并且更新
 * @Include:
 **/
@Service
@Slf4j
public class SpiderDenServiceImpl implements SpiderDenService {

    @Autowired
    private SpiderMonitor spiderMonitor;

    @Autowired
    private RuleDbFacade ruleDbFacadeImpl;

    /**
     * 利用反射的方法，把对象中private属性提出
     */
    private List<SpiderStatusMXBean> getSpiderStatusMXBeanListByInvocation() {
        try {
            Field spiderStatuses = SpiderMonitor.class.getDeclaredField("spiderStatuses");
            spiderStatuses.setAccessible(true); // 原本是private， 设置可以访问
            List<SpiderStatusMXBean> list = (List<SpiderStatusMXBean>) spiderStatuses.get(spiderMonitor);

            return list;
        } catch (Exception e) {
            String eMsg = ResultEnum.INVOCATION_FAILED.getMessage()
                    + this.getClass().getSimpleName() + " getSpiderStatusMXBeanListByInvocation"
                    + "failed to get private spiderStatuses \n"
                    + "spiderMonitor: " + spiderMonitor;
            log.error(eMsg);
            throw new GuInternalException(ResultEnum.INVOCATION_FAILED.getCode(), eMsg);
        }
    }

    /**
     * 强转list
     */
    private List<CrawlerSpiderStatusMXBean> getSpiderStatuses() {
        List<SpiderStatusMXBean> list = this.getSpiderStatusMXBeanListByInvocation();
        if (ListUtil.isEmpty(list)) {
            log.trace("当前jvm没有任何爬虫");
            return null;
        } else {
            List<CrawlerSpiderStatusMXBean> converted = list.stream()
                    .map(each -> (CrawlerSpiderStatusMXBean) each).collect(Collectors.toList());

            log.trace("当前jvm有以下爬虫: " + converted);
            return converted;
        }
    }

    /***
     * @Description querySpiderStatuses 查询所有spider 的状态。如果spider为空null，说明该spider不存在，视为Stopped
     * @Params * @param
     * @Return java.util.List<com.fermedu.crawler.monitor.FermSpiderStatusMXBean>  map的key与rule表一一对应，value可能为空
     **/
    @Override
    public Map<String, CrawlerSpiderStatusMXBean> getAllSpiderStatuses() {
        Map<String, CrawlerSpiderStatusMXBean> spiderStatuses = new HashMap<>();

        List<String> allDomain = ruleDbFacadeImpl.findAllDomain();

        allDomain.stream().forEach(domain -> {
            CrawlerSpiderStatusMXBean spiderStatusByDomain = this.getSpiderStatusByDomain(domain);
            spiderStatuses.put(domain, spiderStatusByDomain);
        });

        return spiderStatuses;
    }

    /***
     * @Description querySpiderStatusBySpiderUuid
     * @Params * @param spiderUuid spider的uuid
     * @Return com.fermedu.crawler.monitor.FermSpiderStatusMXBean
     **/
    @Override
    public CrawlerSpiderStatusMXBean getSpiderStatusByDomain(String domain) {
        List<CrawlerSpiderStatusMXBean> spiderStatuses = this.getSpiderStatuses();

        /** 有对应spiderStatus，返回之 */
        if (domain != null && !ListUtil.isEmpty(spiderStatuses)) {
            for (CrawlerSpiderStatusMXBean each : spiderStatuses) {
                if (domain.equals(each.getDomain())) {
                    return each;
                }
            }
        }

        /** 没有对应spiderStatus，不实例化，直接返回null */
        return null; // 当前爬虫还没有创建，或者已经被摧毁
    }
}
