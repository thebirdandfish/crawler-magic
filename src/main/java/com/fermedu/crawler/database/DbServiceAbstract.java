package com.fermedu.crawler.database;

import com.fermedu.crawler.entity.EntityGeneric;
import com.guguskill.common.util.ClassReflectionUtil;
import com.guguskill.common.util.CopyUtil;
import com.guguskill.common.util.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Program: book-crawler
 * @Create: 2020-12-16 02:26
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Slf4j
public abstract class DbServiceAbstract<T extends EntityGeneric> {

    protected abstract JpaRepository<T, Long> getRepository();

    protected abstract String[] getSecondaryKeyArray();

    private Example<T> exampleOf(T anyKeyFieldMatching) {
        List<String> keyList = new ArrayList<>(Arrays.asList(this.getSecondaryKeyArray()));
        keyList.add("id");

        //创建匹配器，即如何使用查询条件
        List<String> declaredFields = ClassReflectionUtil.getDeclaredFieldNames(anyKeyFieldMatching.getClass());

        // ignore all but three fields: id, bookSourceName, bookSourceBaseUrl
        String[] ignoredPaths = declaredFields.stream()
                .filter(field -> !keyList.contains(field))
                .toArray(String[]::new);

        //创建实例
        ExampleMatcher matcherFull = ExampleMatcher
                .matchingAny()
                .withIgnorePaths(ignoredPaths)
                .withIgnoreNullValues();
        //创建实例
        Example<T> ex = Example.of(anyKeyFieldMatching, matcherFull);

        return ex;
    }

    public List<T> addOrUpdateList(List<T> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            // do nothing
        } else {
            List<T> resultList = entityList.stream().map(e ->
                    this.addOrUpdateOne(e)).collect(Collectors.toList());

            return resultList;
        }

        return null;
    }

    public T addOrUpdateOne(T entity) {
        T existing = this.findOne(entity);
        if (existing == null) {
            /** 新增 */
            return getRepository().save(entity);
        } else {
            /** 更新 */
            CopyUtil.copyNonNullProperties(entity, existing);
            return getRepository().save(existing); // 只更新部分不为null的字段
        }
    }

    public T findOne(T example) {
        List<T> list = this.findList(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            T first = list.stream().findFirst().orElse(null);
            return first;
        }
    }

    public T findById(Long id) {
        T result = this.getRepository().findById(id).orElseGet(null);

        return result;
    }

    public List<T> findList(T anyKeyFieldMatching) {
        return this.findList(anyKeyFieldMatching, null);
    }

    public List<T> findList(T anyKeyFieldMatching, Sort sort) {
        Example<T> ex = this.exampleOf(anyKeyFieldMatching);
        //查询
        List<T> ls = getRepository().findAll(ex);

        if (CollectionUtils.isEmpty(ls)) {
            return null;
        }

        return ls;
    }

    public Page<T> findPage(T anyKeyFieldMatching, int page, int size, Sort sort) {
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Example<T> ex = this.exampleOf(anyKeyFieldMatching);

        Page<T> result = getRepository().findAll(ex, pageRequest);
        return result;
    }

    public Page<T> findRecentUpdatePage(int page, int size) {

        Sort sort = Sort.by(Sort.Order.desc("updateTime"),
                Sort.Order.desc("createTime"));
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        Page<T> result = getRepository().findAll(pageRequest);
        return result;
    }

    public Page<T> findRecentUpdatePage(T anyKeyFieldMatching, int page, int size) {

        Sort sort = Sort.by(Sort.Order.desc("updateTime"),
                Sort.Order.desc("createTime"));
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Example<T> ex = this.exampleOf(anyKeyFieldMatching);

        Page<T> result = getRepository().findAll(ex, pageRequest);
        return result;
    }
}
