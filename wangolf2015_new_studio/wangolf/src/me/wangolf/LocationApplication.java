package me.wangolf;


import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.CommonUtil;
import me.wangolf.utils.SharedPreferencesUtils;
import me.wangolf.utils.ToastUtils;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;
import com.example.topnewgrid.db.SQLHelper;
import com.feelwx.ubk.sdk.api.UBKAd;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;

public class LocationApplication extends Application 
{
	public LocationClient mLocationClient;
	
	public GeofenceClient mGeofenceClient;
	
	public MyLocationListener mMyLocationListener;
	
	public BMapManager mBMapManager = null;
	
	public TextView mLocationResult, logMsg;
	
	public TextView trigger, exit;
	
	public Vibrator mVibrator;
	
	public boolean m_bKeyRight = true;
	
	private static LocationApplication mAppApplication;
	
	private SQLHelper sqlHelper;

	

	
	
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		
		UBKAd.initialize(this);
		
		UBKAd.setDebug(true); // 调试模式，正式发布注释改行
		
		mLocationClient = new LocationClient(getApplicationContext());
		
		mMyLocationListener = new MyLocationListener();
		
		mLocationClient.registerLocationListener(mMyLocationListener);
		
		mGeofenceClient = new GeofenceClient(getApplicationContext());

		mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
		
		mAppApplication = this;
		
		ConstantValues.isFirstRun = SharedPreferencesUtils.getBoolean(this, "isFirstRun");
		
		if (CommonUtil.isNetworkAvailable(getApplicationContext()) == 0) 
		{
			ToastUtils.showInfo(this, ConstantValues.NONETWORK);
		} 
		else
		{
			// ChannelManage.initData();// 初始化频道数据存入数据库
			// ChannelManage.getTitleandId();// 初始化标题在数据库拿
		}

	}

	public class MyLocationListener implements BDLocationListener
	{

		@Override
		public void onReceiveLocation(BDLocation location)
		{
			// Receive Location
			StringBuffer sb = new StringBuffer(256);
			
			sb.append("time : ");
			
			sb.append(location.getTime());
			
			sb.append("\nerror code : ");
			
			sb.append(location.getLocType());
			
			sb.append("\nlatitude : ");
			
			sb.append(location.getLatitude());
			
			sb.append("\nlontitude : ");
			
			sb.append(location.getLongitude());
			
			sb.append("\nradius : ");
			
			sb.append(location.getRadius());
			
			if (location.getLocType() == BDLocation.TypeGpsLocation) 
			{
				sb.append("\nspeed : ");
				
				sb.append(location.getSpeed());
				
				sb.append("\nsatellite : ");
				
				sb.append(location.getSatelliteNumber());
				
				sb.append("\ndirection : ");
				
				sb.append(location.getDirection());
				
				sb.append("\naddr : ");
				
				sb.append(location.getAddrStr());
								
			} 
			else if (location.getLocType() == BDLocation.TypeNetWorkLocation) 
			{
				sb.append("\naddr : ");
				
				sb.append(location.getAddrStr());				
				
				sb.append("\noperationers : ");
				
				sb.append(location.getOperators());
			}
			
			logMsg(sb.toString());
			
			ConstantValues.LATITUDE = location.getLatitude() + "";// 初始化定位坐标
			
			ConstantValues.LONGITUDE = location.getLongitude() + "";
			
			final String add = location.getAddrStr();
			
			if (!CheckUtils.checkEmpty(add)) 
			{								
				ConstantValues.loactionadd = add.substring(add.indexOf("省") + 1);
				
				ConstantValues.LOACTIONCITY = location.getCity().replace("市", "");				
			}
			
			ConstantValues.isloaction = true;
			
		}

	}

	public static LocationApplication getInstance()
	{
		return mAppApplication;
	}

	public void initEngineManager(Context context) {
		if (mBMapManager == null) {
			mBMapManager = new BMapManager(context);
		}

		if (!mBMapManager.init(new MyGeneralListener())) {
			// Toast.makeText(MgApplication.getInstance().getApplicationContext(),
			// "BMapManager  初始化错误!", Toast.LENGTH_LONG).show();
		}
	}

	// 常用事件监听，用来处理通常的网络错误，授权验证错误等
	public static class MyGeneralListener implements MKGeneralListener {

		@Override
		public void onGetNetworkState(int iError) {
			if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
				// Toast.makeText(MgApplication.getInstance().getApplicationContext(),
				// "您的网络出错啦！",
				// Toast.LENGTH_LONG).show();
			} else if (iError == MKEvent.ERROR_NETWORK_DATA) {
				// Toast.makeText(MgApplication.getInstance().getApplicationContext(),
				// "输入正确的检索条件！",
				// Toast.LENGTH_LONG).show();
			}
			// ...
		}

		@Override
		public void onGetPermissionState(int iError) 
		{
			// 非零值表示key验证未通过
			if (iError != 0) 
			{
				// 授权Key错误：
				// Toast.makeText(MgApplication.getInstance().getApplicationContext(),
				// "请在 MgApplication.java文件输入正确的授权Key,并检查您的网络连接是否正常！error: "+iError,
				// Toast.LENGTH_LONG).show();
				LocationApplication.getInstance().m_bKeyRight = false;
			} 
			else 
			{
				LocationApplication.getInstance().m_bKeyRight = true;
				// Toast.makeText(MgApplication.getInstance().getApplicationContext(),
				// "key认证成功", Toast.LENGTH_LONG).show();
			}
		}
	}

	
	public void logMsg(String str) 
	{
		try 
		{
			if (mLocationResult != null)
				
				mLocationResult.setText(str);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/** 获取Application */
	public static LocationApplication getApp() 
	{
		return mAppApplication;
	}

	/** 获取数据库Helper */
	public SQLHelper getSQLHelper() 
	{
		if (sqlHelper == null)
			sqlHelper = new SQLHelper(mAppApplication);
		
		return sqlHelper;
	}

	
	/** 摧毁应用进程时候调用 */
	public void onTerminate() 
	{
		UBKAd.destory();
		
		if (sqlHelper != null)
			sqlHelper.close();
		
		super.onTerminate();
	}

	public void clearAppCache(){}

	
}
