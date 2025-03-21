package com.maodou.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import com.maodou.domain.BaseEntity;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户对象 sys_user
 *
 * @author ruoyi
 */
@Setter
@Getter
@Schema(description = "用户对象")
@ToString
@TableName("sys_user")
public class SysUser extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId
    private Long userId;

    /**
     * 部门ID
     */
//    private Long deptId;

    /**
     * 用户账号
     */
    @Schema(description = "用户名")
    @TableField("user_name")
    private String username;

    /**
     * 用户昵称
     */
    @TableField("nick_name")
    private String nickname;

    /**
     * 用户邮箱
     */
//    private String email;

    /**
     * 手机号码
     */
//    private String phonenumber;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 密码
     */
    @Schema(description = "用户密码")
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 最后登录IP
     */
//    private String loginIp;

    /**
     * 最后登录时间
     */
//    private Date loginDate;

    /**
     * 角色对象
     */
//    private List<SysRole> roles;

    /**
     * 角色组
     */
//    private Long[] roleIds;

    /**
     * 岗位组
     */
//    private Long[] postIds;

    /**
     * 角色ID
     */
//    private Long roleId;

    public SysUser() {

    }

    public SysUser(Long userId) {
        this.userId = userId;
    }

    public boolean isAdmin() {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }


}
