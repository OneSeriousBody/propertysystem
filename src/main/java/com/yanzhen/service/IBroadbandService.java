package com.yanzhen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.yanzhen.model.Broadband;
import com.yanzhen.model.Building;

/**
 * @Author: xhh
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
