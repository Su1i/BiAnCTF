//
//
package com.suli.bianctf.domain.vo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户查询实体
 * </p>
 */
@Data
public class SysUserQueryVo implements Serializable {

	/**
	 * 用户ID
	 */
	@TableId
	private Long userId;

	/**
	 * 用户邮箱
	 */
	@TableField("email")
	private String email;
	/**
	 * 用户昵称
	 */
	@TableField("user_name")
	private String userName;

	/**
	 * 真实姓名
	 */
	@TableField("real_name")
	private String realName;

	/**
	 * 学号/工号
	 */
	@TableField("student_id")
	private String studentId;

	/**
	 * 学院
	 */
	@TableField("college")
	private String college;

	/**
	 * 专业
	 */
	@TableField("speciality")
	private String speciality;

	/**
	 * 年级
	 */
	@TableField("grade")
	private String grade;

	/**
	 * 身份证号
	 */
	@TableField("identity_card")
	private String identityCard;

	/**
	 * 手机号码
	 */
	@TableField("phone_number")
	private String phoneNumber;

	/**
	 * 用户性别（0男 1女 2未知）
	 */
	@TableField("sex")
	private String sex;

	/**
	 * 头像地址
	 */
	@TableField("avatar")
	private String avatar;

	/**
	 * 用户类型（sys_user系统用户）
	 */
	@TableField("user_type")
	private String userType;

	/**
	 * 帐号状态（0正常 1停用）
	 */
	@TableField("status")
	private String status;

	/**
	 * 删除标志（0代表存在 1代表删除）
	 */
	@TableLogic
	@TableField("del_flag")
	private String delFlag;

	/**
	 * 最后登录IP
	 */
	@TableField("login_ip")
	private String loginIp;

	/**
	 * 最后登录时间
	 */
	@TableField("login_date")
	private Date loginDate;

	/**
	 * 创建者
	 */
	@TableField("create_by")
	private String createBy;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 更新者
	 */
	@TableField("update_by")
	private String updateBy;

	/**
	 * 更新时间
	 */
	@TableField("update_time")
	private Date updateTime;

	/**
	 * 备注
	 */
	@TableField("remark")
	private String remark;

}

