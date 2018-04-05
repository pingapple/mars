package com.franklin.mars.controller;


import com.franklin.mars.domain.Girl;
import com.franklin.mars.domain.Result;
import com.franklin.mars.service.GirlService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(maxAge = 3600)
@RestController
@Api("GirlController相关接口")
public class GirlController {
    @Autowired
    private GirlService girlService;

    //  返回女生列表
    @ApiOperation(value = "列表接口", notes = "返回列表")
    @RequestMapping(value = "/girls", method = RequestMethod.GET)
    public Result<PageInfo> girlList() {

        PageHelper.startPage(2, 2);
        List<Girl> list = girlService.girlList();
        PageInfo pageInfo = new PageInfo(list);
        return Result.setOk(pageInfo);
    }

    //  返回女生列表
    @RequestMapping(value = "/girls/age/{age}", method = RequestMethod.GET)
    public Result<List<Girl>> girlsByAge(Girl girl) {
        return Result.setOk(girlService.findGirls(girl));
    }

    @RequestMapping(value = "/girls/findById/{id}", method = RequestMethod.GET)
    public Result<Girl> findById(@PathVariable("id") Integer id) {
        System.out.println(id);
        return Result.setOk(girlService.findById(id));
    }


    //查询id对应的对象
    @RequestMapping(value = "/girls/id/{id}", method = RequestMethod.GET)
    public Result<List<Girl>> girlFindOne(Girl girl) {
        return Result.setOk(girlService.findGirls(girl));
    }

    /**
     * 添加一个对象
     *
     * @return girl
     */
    @RequestMapping(value = "/girls", method = RequestMethod.POST)
    public Result<Girl> girlAdd(Girl girl) {
        return Result.setOk(girlService.addGirl(girl));
    }

    /**
     * 更新一个对象
     *
     * @param girl
     * @return
     */
    @RequestMapping(value = "/girls/{id}", method = RequestMethod.PUT)
    public Result<Girl> girlUpdate(Girl girl) {
        return Result.setOk(girlService.updateGirl(girl));
    }

    /**
     * 删除一个对象
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/girls/{id}", method = RequestMethod.DELETE)
    public Result<Girl> girlDelete(@PathVariable("id") Integer id) {
        return Result.setOk(girlService.deleteGirl(id));
    }


}
