package com.tlms.rabitmq.test;


import java.io.Serializable;
import java.util.Date;

/**
 * @author wen
 * @date 创建时间：2017年7月27日 上午9:16:42
 *
 */
public class InterviewEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 审批成功状态
	 */
	public static final String STATE_PASS = "2";

	/**
	 * 成功状态
	 */
	public static final String SEND_STATE_UNSENT = "0";

	/**
	 * 成功状态
	 */
	public static final String SEND_STATE_SENT = "1";

	/**
	 * id
	 */
	private Long id;
	/**
	 * 业务编号
	 */
	private String code;
	/**
	 * 客户姓名
	 */
	private String surname;
	/**
	 * 身份证号
	 */
	private String idcard;
	/**
	 * 手机号
	 */
	private String telephone;
	/**
	 * 文件下载地址
	 */
	private String archives;
	/**
	 * 面签发送状态地址
	 */
	private String callback;
	/**
	 * 文件下载需要的令牌
	 */
	private String token;

	/**
	 * 面签状态
	 */
	private String state;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 审核状态
	 */
	private String pstate;

	/**
	 * 审核描述
	 */
	private String depict;

	/**
	 * 发送状态
	 */
	private String sendState = SEND_STATE_UNSENT;

	/**
	 * 发送时间
	 */
	private Date sendTime;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            要设置的 id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return 业务编号
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param 业务编号
	 *            要设置的 code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return 客户姓名
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param 客户姓名
	 *            要设置的 surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return 身份证号
	 */
	public String getIdcard() {
		return idcard;
	}

	/**
	 * @param 身份证号
	 *            要设置的 idcard
	 */
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	/**
	 * @return 手机号
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param 手机号
	 *            要设置的 telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return 文件下载地址
	 */
	public String getArchives() {
		return archives;
	}

	/**
	 * @param 文件下载地址
	 *            要设置的 archives
	 */
	public void setArchives(String archives) {
		this.archives = archives;
	}

	/**
	 * @return 面签发送状态地址
	 */
	public String getCallback() {
		return callback;
	}

	/**
	 * @param 面签发送状态地址
	 *            要设置的 callback
	 */
	public void setCallback(String callback) {
		this.callback = callback;
	}

	/**
	 * @return 文件下载需要的令牌
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param 文件下载需要的令牌
	 *            要设置的 token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return 审核状态
	 */
	public String getPstate() {
		return pstate;
	}

	/**
	 * @param 审核状态
	 *            要设置的 pstate
	 */
	public void setPstate(String pstate) {
		this.pstate = pstate;
	}

	/**
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param 创建时间
	 *            要设置的 createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return 面签状态
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param 面签状态
	 *            要设置的 state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param 备注
	 *            要设置的 remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return 审核描述
	 */
	public String getDepict() {
		return depict;
	}

	/**
	 * @param 审核描述
	 *            要设置的 depict
	 */
	public void setDepict(String depict) {
		this.depict = depict;
	}

	/**
	 * @return 发送状态
	 */
	public String getSendState() {
		return sendState;
	}

	/**
	 * @param 发送状态
	 *            要设置的 sendState
	 */
	public void setSendState(String sendState) {
		this.sendState = sendState;
	}

	/**
	 * @return 发送时间
	 */
	public Date getSendTime() {
		return sendTime;
	}

	/**
	 * @param 发送时间
	 *            要设置的 sendTime
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

}
