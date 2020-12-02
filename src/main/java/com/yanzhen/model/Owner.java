package com.yanzhen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author kappy
 * @since 2020-11-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Owner对象", description="")
public class Owner implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String tel;

    private String sex;

    private String identity;

    private Integer houseId;

    private String email;

    private String remarks;

    private String password;

    @TableField(exist = false)
    private String broadband;

    @TableField(exist = false)
    private Double broadbandPrice;

    @TableField(exist = false)
    private Integer broadbandNum;

    @TableField(exist = false)
    private Integer broadbandFlag;

    @TableField(exist = false)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date intoDate;

    @TableField(exist = false)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date broadbandTime;

    @TableField(exist = false)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date broadbandOverTime;


    @TableField(exist = false)
    private String broadbandFlagTitle;

    //房屋信息
    @TableField(exist = false)
    private House house;




}
