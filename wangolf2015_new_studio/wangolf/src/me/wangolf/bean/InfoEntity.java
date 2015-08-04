package me.wangolf.bean;

import java.util.ArrayList;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年1月26日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年1月26日
 * 
 * 描述 ： 返回的状态
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
public class InfoEntity {
	private String status;
	private String info;
	private ArrayList<InfoEntity> data;

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

	public ArrayList<InfoEntity> getData() {
		return data;
	}

	public void setData(ArrayList<InfoEntity> data) {
		this.data = data;
	}



}