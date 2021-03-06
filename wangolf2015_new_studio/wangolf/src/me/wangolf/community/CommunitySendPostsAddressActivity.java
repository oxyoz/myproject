package me.wangolf.community;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import java.util.ArrayList;

import me.wangolf.ConstantValues;
import me.wangolf.adapter.CommunityPostsAddressListAdapter;
import me.wangolf.adapter.CommunitySendTagAdapter;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.community.CommunityTagEntity;
import me.wangolf.bean.community.RangeNameEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.CommonUtil;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.ToastUtils;

/**
 * Created by Administrator on 2015/6/8.
 */
public class CommunitySendPostsAddressActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    @ViewInject(R.id.common_back)
    private TextView mBack;
    @ViewInject(R.id.common_title)
    private TextView mTitle;
    @ViewInject(R.id.common_bt)
    private TextView mBt;
    @ViewInject(R.id.lsit_tag)
    private ListView mTag;
    @ViewInject(R.id.tv_no_address)
    private TextView mNoAddress;
    private CommunityPostsAddressListAdapter adapter;
    private int page = 1;//页数
    private int number = 10;//分页外数
    private boolean isRefresh;// 是否下拉刷新
    private int posts_id;
    private StringBuffer tags_name = new StringBuffer();//标签名称(若多选则以空格隔开,卢tags_name=高尔夫 活动)
    private StringBuffer tags_id = new StringBuffer();//标签ID(若多选则以半角逗号隔开，如tags_id=1,3) ;//标签ID(若多选则以半角逗号隔开，如tags_id=1,3)
    private String city_id;
    private String longitude;
    private String latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_send_address_list);
        View head = View.inflate(this,R.layout.item_address_list_head,null);

        ViewUtils.inject(this);
        ViewUtils.inject(this,head);
        if (adapter == null) {
            adapter = new CommunityPostsAddressListAdapter(this);
        }
        mTag.addHeaderView(head);
        mTag.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        initData();
    }


    @Override
    public void initData() {
        mTitle.setText("选择地址");
        //mBt.setText("完成");
        // mBt.setVisibility(View.VISIBLE);
        mBack.setVisibility(View.VISIBLE);
        mTitle.setVisibility(View.VISIBLE);
        mBack.setOnClickListener(this);
       // city_id=ConstantValues.cityid+"";
        longitude=ConstantValues.LONGITUDE;
        latitude =ConstantValues.LATITUDE;
        mTag.setOnItemClickListener(this);
        mNoAddress.setOnClickListener(this);

        getData();
    }

    @Override
    public void getData() {
        try {
            ServiceFactory.getCommunityEngineInstatice().getRangeName(city_id, longitude, latitude, new IOAuthCallBack() {
                @Override
                public void getIOAuthCallBack(String result) {
                  //  Log.i("wangolf",result);
                    if (result.equals(ConstantValues.FAILURE)) {
                        ToastUtils.showInfo(CommunitySendPostsAddressActivity.this, ConstantValues.NONETWORK);
                    } else {
                        RangeNameEntity bean = GsonTools.changeGsonToBean(result, RangeNameEntity.class);
                        if (bean.getStatus() == 1) {
                            ArrayList<RangeNameEntity> data = bean.getData();
                            adapter.setmList(data);
                            adapter.notifyDataSetChanged();
                        }
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.tv_no_address:
                Intent intent = new Intent();
                intent.putExtra("rengName", "不显示所在位置");
                setResult(1005, intent);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (CommonUtil.isNetworkAvailable(this) == 0) {
            ToastUtils.showInfo(this, ConstantValues.NONETWORK);
        } else {
            RangeNameEntity bean = (RangeNameEntity) adapter.getItem(position-1);
            Intent intent = new Intent();
            intent.putExtra("rengName", bean.getRange_name());
            intent.putExtra("longitude", bean.getLongitude());
            intent.putExtra("latitude", bean.getLatitude());
              setResult(1005, intent);
            finish();
        }
    }
}
