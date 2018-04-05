package com.franklin.mars.dao;

import com.franklin.mars.domain.Girl;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


@Mapper
@CacheConfig(cacheNames = "girls")
public interface GirlDao {

    /**
     * 插入一条
     *
     * @param girl
     */

    void saveGirl(Girl girl);
    /**
     * 更新一条
     *
     * @param girl
     */
    @CachePut(key = "#p0")
    void updateGirl(Girl girl);

    /**
     * 删除一条  真正的删除 不是伪删除
     *
     * @param id
     */
    @CacheEvict(key = "#p0", allEntries = true)
    void deleteGirl(Integer id);

    /**
     * 找到对应的对象
     *
     * @param
     * @return
     */
    <T> List<T> findGirls(Girl girl);

    @Cacheable(key = "#p0")
    Girl findById(@Param("id") Integer id);




}
