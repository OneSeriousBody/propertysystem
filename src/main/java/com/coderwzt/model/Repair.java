package com.coderwzt.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author coderwzt
 * @since 2020-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Repair对象", description="")
public class Repair implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private Owner owner;
    @TableField(exist = false)
    private Repairtype type;

    @TableField(exist = false)
    private Integer commentId;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String comId;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date comDate;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date handleDate;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date hopeDate;

    private String phone;

    private Double price;

    private String priceDetail;

    private Integer ownerId;

    private Integer personnelId;

    private Integer status;


    private String clr;
    @TableField(exist = false)
    private String personnelName;
    @TableField(exist = false)
    private String personnelPhone;

    private String remarks;


}
