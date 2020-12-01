package com.yanzhen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanzhen.model.Broadband;
import com.yanzhen.model.Building;
import org.springframework.stereotype.Component;

/**
 * @Author: xhh
 * @Date: 2020/11/28 22:28
 * @Version 1.0
 */
@Component("broadbandDao")
public interface BroadbandMapper extends BaseMapper<Broadband> {
}
