package com.coderwzt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.coderwzt.model.Broadband;
import com.coderwzt.model.Building;

/**
 * @Author: coderwzt
 * @Date: 2020/11/28 22:29
 * @Version 1.0
 */
public interface IBroadbandService extends IService<Broadband> {
    /**
     * 添加宽带
     * @param broadband
     */
    void addBroadband(Broadband broadband);

    /**
     * 获取最近一次办理宽带的记录
     * @param id
     * @return
     */
    Broadband getLateBroadband(Integer id);

    PageInfo<Broadband> queryBroadbandByOwnerId(Integer page, Integer limit, Broadband broadband);
}
