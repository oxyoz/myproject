package me.wangolf.service.impl;

import com.lidroid.xutils.http.RequestParams;

import me.wangolf.ConstantValues;
import me.wangolf.service.IBallService;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.Xutils;

public class BallServiceImpl implements IBallService
{

	static String BaseUrl = ConstantValues.BaseApi;
	
	private RequestParams params = null;

	/**
	 * 
	 * court_id
	 * 
	 *  通过球场id查看球场标签信息
	 * 
	 * */
	@Override
	public void getBallDetail(String court_id, IOAuthCallBack iOAuthCallBack) throws Exception 
	{
//		params = new RequestParams();
//		params.addBodyParameter("m", "Ball");
//		params.addBodyParameter("a", "balldetail");
//		params.addBodyParameter("ballid", ballid);
//		Xutils.getDataFromServer(params, iOAuthCallBack);
		
		String api = BaseUrl + "webCourt/tags?"				
				+ "&court_id=" + court_id;	
		
		Xutils.getDataFromServer(api, iOAuthCallBack);
		
	}
	
	
	/**
	 * 
	 * getBallInfo
	 * 
	 *  通过球场id查看球场详细信息
	 * 
	 * */
	@Override
	public void getBallInfo(String court_id, String date, IOAuthCallBack iOAuthCallBack) throws Exception
	{
		
		String api = BaseUrl + "webCourt/detail?"				
				+ "date=" + date
				+ "&court_id=" + court_id;	
		
		Xutils.getDataFromServer(api, iOAuthCallBack);

	}
	
	
	/**
	 * findBallList
	 * 
	 *  最新修改的按球场名称模糊查询球场的方法
	 * 
	 * */
	
	@Override
	public void findBallList(String court_name, IOAuthCallBack iOAuthCallBack) throws Exception 
	{
//		params = new RequestParams();
//		params.addBodyParameter("m", "Ball");
//		params.addBodyParameter("a", "findBallList");
//		params.addBodyParameter("ballname", ballname);
//		Xutils.getDataFromServer(params, iOAuthCallBack);

		String api = BaseUrl + "webCourt/search?"				
				+ "&court_name=" + court_name;	
		
		Xutils.getDataFromServer(api, iOAuthCallBack);
		
	}
	
	
	/***
	 * 
	 *  findBallSearch
	 *  最新的按地理位置搜索球场方法
	 * 
	 * */
	@Override
	public void findBallSearch(String cityid, String date, String longitude, String latitude, IOAuthCallBack iOAuthCallBack) throws Exception 
	{
		String api = BaseUrl + "webCourt/list?"
				+ "&city_id=" + cityid
				+ "&longitude=" + longitude
				+ "&latitude=" + latitude
				+ "&date=" + date;	

		Xutils.getDataFromServer(api, iOAuthCallBack);
					
	}
	

	
	
	@Override
	public void findBallSearch(String cityid, String date, String ballname, String longitude, String latitude, String order, String type,
			IOAuthCallBack iOAuthCallBack) throws Exception 
	{
		params = new RequestParams();
		
		params.addBodyParameter("m", "Ball");
		
		params.addBodyParameter("a", "ballsearch");
		
		params.addBodyParameter("cityid", cityid + "");
		
		params.addBodyParameter("date", date);
		
		params.addBodyParameter("longitude", longitude);
		
		params.addBodyParameter("latitude", latitude);
		
		params.addBodyParameter("ballname", ballname);
		
		params.addBodyParameter("order", order);
		
		params.addBodyParameter("type", type);
		
		Xutils.getDataFromServer(params, iOAuthCallBack);

	}


}
