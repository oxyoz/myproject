package me.wangolf.service.impl;

import com.lidroid.xutils.http.RequestParams;

import me.wangolf.ConstantValues;
import me.wangolf.service.ICollegeService;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.Xutils;

public class CollegeServiceImpl implements ICollegeService
{
	public RequestParams	params	= null;

	static String			BaseUrl	= ConstantValues.BaseApi;

	@Override
	public void getKnowledge(String type, String size, String lastid, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Information");
		params.addBodyParameter("a", "knowledge");
		params.addBodyParameter("size", size);
		if (!"-1".equals(lastid))
		{
			params.addBodyParameter("lastid", lastid);
		}
		params.addBodyParameter("type", type);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	/**
	 * 
	 * getInfoDetail
	 * 
	 * 最新修改的获取新闻详情(包括高球课堂跟高球常识)数据的方法
	 * 
	 * **/
	@Override
	public void getInfoDetail(String knowid, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		// params = new RequestParams();
		// params.addBodyParameter("m", "Information");
		// params.addBodyParameter("a", "infoDetail");
		// params.addBodyParameter("knowid", knowid);
		// Xutils.getDataFromServer(params, iOAuthCallBack);

		String api = BaseUrl + "webInformation/detail?" + "&information_id=" + knowid;

		Xutils.getDataFromServer(api, iOAuthCallBack);

	}

	
	@Override
	public void getCoachList(String rangeid, String sorttype, String sort, String page, String number, String collegeid, String latitude, String longitude, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Coach");
		params.addBodyParameter("a", "CoachList");
		params.addBodyParameter("rangeid", rangeid);
		params.addBodyParameter("sorttype", sorttype);
		params.addBodyParameter("sort", sort);
		params.addBodyParameter("page", page);
		params.addBodyParameter("number", number);
		params.addBodyParameter("collegeid", collegeid);
		params.addBodyParameter("latitude", latitude);
		params.addBodyParameter("longitude", longitude);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	@Override
	public void getCoachInfo(String coachid, String uuid, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Coach");
		params.addBodyParameter("a", "CoachInfo");
		params.addBodyParameter("uuid", uuid);
		params.addBodyParameter("coachid", coachid);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	
	
	/* (非 Javadoc) 
	* <p>Title: getCollegeList</p> 
	* <p>Description: 学院列表</p> 
	* @param sort_type
	* @param sort
	* @param latitude
	* @param longitude
	* @param page
	* @param number
	* @param iOAuthCallBack
	* @throws Exception 
	* @see me.wangolf.service.ICollegeService#getCollegeList(int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, me.wangolf.service.IOAuthCallBack) 
	*/
	@Override
	public void getCollegeList(int sort_type, int sort, String latitude, String longitude, String page, String number, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		
		params.addBodyParameter("m", "Coach");
		
		params.addBodyParameter("a", "CollegeList");
		
		params.addBodyParameter("sorttype", sort_type + "");
		
		params.addBodyParameter("sort", sort + "");
		
		params.addBodyParameter("latitude", latitude);
		
		params.addBodyParameter("longitude", longitude);
		
		params.addBodyParameter("page", page);
		
		params.addBodyParameter("number", number);
		
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void getCollgetInfo(String collegeid, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Coach");
		params.addBodyParameter("a", "College");
		params.addBodyParameter("collegeid", collegeid);
		Xutils.getDataFromServer(params, iOAuthCallBack);
	}

	@Override
	public void UpCoachZan(String coachid, String uuid, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		params = new RequestParams();
		params.addBodyParameter("m", "Coach");
		params.addBodyParameter("a", "praise");
		params.addBodyParameter("coachid", coachid);
		params.addBodyParameter("uuid", uuid);
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}

	/**
	 * 
	 * getNewsTags
	 * 
	 * 最新修改的获取高球教学数据的方法
	 * 
	 * **/
	@Override
	public void getNewsTags(IOAuthCallBack iOAuthCallBack)
	{
		// params = new RequestParams();
		// params.addBodyParameter("m", "Information");
		// params.addBodyParameter("a", "newsTags");
		// Xutils.getDataFromServer(params, iOAuthCallBack);

		String api = BaseUrl + "webInformation/newsTags?";

		Xutils.getDataFromServer(api, iOAuthCallBack);

	}

	/**
	 * 
	 * getNewsList
	 * 
	 * 最新修改的获取新闻列表(包括高球课堂跟高球常识)数据的方法
	 * 
	 * **/
	@Override
	public void getNewsList(String tags_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		// params = new RequestParams();
		// params.addBodyParameter("m", "Information");
		// params.addBodyParameter("a", "newsList");
		// params.addBodyParameter("tags_id", tags_id);
		// params.addBodyParameter("page", page + "");
		// params.addBodyParameter("number", number + "");
		// Xutils.getDataFromServer(params, iOAuthCallBack);

		String api = BaseUrl + "webInformation/list?" + "&tags_id=" + tags_id + "&page=" + page + "&number=" + number;

		Xutils.getDataFromServer(api, iOAuthCallBack);

	}

}
