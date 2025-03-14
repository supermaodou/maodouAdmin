package com.maodou.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity基类
 *
 * @author ruoyi
 */
@Setter
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 搜索值
     */
//    @JsonIgnore
//    private String searchValue;

    /**
     * 创建者
     */
//    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
//    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 请求参数
     */
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    private Map<String, Object> params;

//    public String getSearchValue() {
//        return searchValue;
//    }

//    public String getCreateBy() {
//        return createBy;
//    }

    public Date getCreateTime() {
        return createTime;
    }

//    public String getUpdateBy() {
//        return updateBy;
//    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getRemark() {
        return remark;
    }

//    public Map<String, Object> getParams() {
//        if (params == null) {
//            params = new HashMap<>();
//        }
//        return params;
//    }

}
