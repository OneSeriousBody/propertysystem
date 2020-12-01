package com.yanzhen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Author: xhh
 * @Date: 2020/11/30 18:58
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Comment {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 业主id
     */
    private Integer ownerId;

    /**
     * 维修id
     */
    private Integer repairId;

    /**
     * 维修人员id
     */
    private Integer repairPersonnelId;

    /**
     * 评分
     */
    private Double score;

    /**
     * 评论内容
     */
    private String comment;

    /**
     * 评论时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date createTime;

    /**
     * 维修工姓名
     */
    @TableField(exist = false)
    private String repairPersonnelName;

    /**
     * 维修内容
     */
    @TableField(exist = false)
    private String repairContent;


}
