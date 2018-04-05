package com.franklin.mars.service.impl;

import com.franklin.mars.dao.GirlDao;
import com.franklin.mars.domain.Girl;
import com.franklin.mars.service.GirlService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;


@Service
public class GirlServiceImpl implements GirlService, Serializable {


    @Resource
    private GirlDao girlDao;

    @Override
    public List<Girl> girlList() {
        return girlDao.findGirls(null);
    }

    @Override
    public List<Girl> findGirls(Girl girl) {
        return girlDao.findGirls(girl);
    }

    @Override
    @Transactional
    public Girl addGirl(Girl girl) {
        girlDao.saveGirl(girl);
        return girl;
    }

    @Override
    @Transactional
    public Girl updateGirl(Girl girl) {
        girlDao.updateGirl(girl);
        return girl;
    }

    @Override
    @Transactional
    public Girl deleteGirl(Integer id) {
        girlDao.deleteGirl(id);
        return null;
    }

    @Override
    public Girl findById(Integer id) {
        return girlDao.findById(id);
    }
}
