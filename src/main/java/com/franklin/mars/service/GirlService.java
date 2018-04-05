package com.franklin.mars.service;


import com.franklin.mars.domain.Girl;

import java.util.List;


public interface GirlService {

    List<Girl> girlList();

    Girl addGirl(Girl girl);

    Girl updateGirl(Girl girl);

    Girl deleteGirl(Integer id);

    Girl findById(Integer id);

    List<Girl> findGirls(Girl girl);
}
