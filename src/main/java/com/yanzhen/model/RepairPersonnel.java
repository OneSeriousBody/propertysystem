package com.yanzhen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: xhh
 * @Date: 2020/11/27 21:16
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RepairPersonnel {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer no;

    private String phone;
}
