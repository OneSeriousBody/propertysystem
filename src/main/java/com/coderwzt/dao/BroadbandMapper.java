package com.coderwzt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderwzt.model.Broadband;
import com.coderwzt.model.Building;
import org.springframework.stereotype.Component;

/**
 * @Author: coderwzt
 * @Date: 2020/11/28 22:28
 * @Version 1.0
 */
@Component("broadbandDao")
public interface BroadbandMapper extends BaseMapper<Broadband> {
}
