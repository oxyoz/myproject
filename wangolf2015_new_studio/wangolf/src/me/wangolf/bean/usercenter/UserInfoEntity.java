package me.wangolf.bean.usercenter;

import java.util.ArrayList;

public class UserInfoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status;
	private String info;
	private String address; // 住址
	private int ballage; // 球龄
	private String nickname; // 昵称
	private String birthday; // 生日
	private String vip; // vip 0表示vip，1表示普通用户
	private String photo; // 头像 路径
	private String sex; // 性别 ,
	private String summy; // 个性签名
	private int uid; // 用户id
	private String username;// 用户名
	private String realname;// 真实名称
	private Double account;// 账户
	private String integration;// 积分
	private String mobile;// 手机号码

	private String rmoney;// 待还金额
	private Double vouchers;// 我的代金券
	private String vipcount;
	private String weixin_open_id;

	public String getWeixin_open_id() {
		return weixin_open_id;
	}

	public void setWeixin_open_id(String weixin_open_id) {
		this.weixin_open_id = weixin_open_id;
	}

	private String weixin_avatar;//微信头像
	private ArrayList<UserInfoEntity> data;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getBallage() {
		return ballage;
	}

	public void setBallage(int ballage) {
		this.ballage = ballage;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSummy() {
		return summy;
	}

	public void setSummy(String summy) {
		this.summy = summy;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}



	public Double getAccount() {
		return account;
	}

	public void setAccount(Double account) {
		this.account = account;
	}

	public String getIntegration() {
		return integration;
	}

	public void setIntegration(String integration) {
		this.integration = integration;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRmoney() {
		return rmoney;
	}

	public void setRmoney(String rmoney) {
		this.rmoney = rmoney;
	}



	public Double getVouchers() {
		return vouchers;
	}

	public void setVouchers(Double vouchers) {
		this.vouchers = vouchers;
	}

	public String getVipcount() {
		return vipcount;
	}

	public void setVipcount(String vipcount) {
		this.vipcount = vipcount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public ArrayList<UserInfoEntity> getData() {
		return data;
	}

	public void setData(ArrayList<UserInfoEntity> data) {
		this.data = data;
	}

	public String getWeixin_avatar() {
		return weixin_avatar;
	}

	public void setWeixin_avatar(String weixin_avatar) {
		this.weixin_avatar = weixin_avatar;
	}
}