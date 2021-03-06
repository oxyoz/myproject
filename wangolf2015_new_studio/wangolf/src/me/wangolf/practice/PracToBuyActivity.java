package me.wangolf.practice;

import java.util.Date;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.usercenter.OrderpayBean;
import me.wangolf.usercenter.OrderPayActivity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.DateFormatUtils;
import me.wangolf.utils.TelUtils;
import me.wangolf.utils.ToastUtils;

public class PracToBuyActivity extends BaseActivity implements OnClickListener
{
    @ViewInject(R.id.common_back)
    private Button common_back; // 后退
    @ViewInject(R.id.common_title)
    private TextView common_title;// 标题
    @ViewInject(R.id.common_bt)
    private TextView common_bt;// 电话
    @ViewInject(R.id.tv_price)
    private TextView tv_price;// 价格
    @ViewInject(R.id.tv_date_tip2)
    private TextView tv_date_tip2;// 类型
    @ViewInject(R.id.tv_brief)
    private TextView tv_brief;// 练习场名称
    @ViewInject(R.id.tv_practice_name)
    private TextView mName;//练习场名称
    @ViewInject(R.id.tv_book)
    private TextView mBook;
    @ViewInject(R.id.tv_total_price)
    private TextView tv_total_price;// 总价格
    @ViewInject(R.id.subtraction)
    private TextView subtraction;// 减少
    @ViewInject(R.id.add)
    private TextView add;// 增加
    @ViewInject(R.id.tv_num)
    private TextView tv_num;// 购买数量
    @ViewInject(R.id.tv_person)
    private EditText tv_person;// 用户姓名
    @ViewInject(R.id.tv_contact)
    private EditText tv_contact;// 电话号码
    @ViewInject(R.id.btpay)
    private Button btpay;// 提交订单
    @ViewInject(R.id.tv_remarkes)
    private EditText tv_remarkes;// 备注

    private String rgname;// 练习场名称
    private String rgid;// 练习场ID
    private me.wangolf.bean.practice.PracDetailEntity_new.DataEntity.DistributorsEntity bean;// 分销商bean
    private String type;
    private int price;
    private int all_price;
    private int buy_num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.ac_prac_tobuy);
        
        ViewUtils.inject(this);
        
        initData();
    }

    @Override
    public void initData()
    {
        common_back.setVisibility(View.VISIBLE);
        
        common_bt.setVisibility(View.VISIBLE);
        
        common_title.setText(ConstantValues.PRACTICE_ORDER);
        
        common_bt.setBackgroundResource(R.drawable.bt_phone_selector);
        
        common_back.setOnClickListener(this);
        
        common_bt.setOnClickListener(this);
        
        subtraction.setOnClickListener(this);
        
        add.setOnClickListener(this);
        
        btpay.setOnClickListener(this);
        
        tv_contact.setText(ConstantValues.USER_MOBILE);
        
        rgname = getIntent().getStringExtra("rgname");
        
        rgid = getIntent().getStringExtra("rgid");
        
        bean = (me.wangolf.bean.practice.PracDetailEntity_new.DataEntity.DistributorsEntity) getIntent().getSerializableExtra("bean");
        
//      type = bean.getPrice_type() + "";
        
        mName.setText(rgname);
        
        mBook.setText(bean.getBook());
        
//        if ("1".equals(type)) 
//        {
//            tv_date_tip2.setText(bean.getPrice_description());
//        } 
//        else if ("2".equals(type)) 
//        {
//            tv_date_tip2.setText(bean.getPrice_tag());
//        }
        
        price = Integer.parseInt(bean.getPrice());
        
        tv_price.setText("￥" + price);
        
        tv_total_price.setText("￥" + price);
        
        all_price = price;

    }

    @Override
    public void getData() 
    {

    }

    @Override
    public void onClick(View v) 
    {
        switch (v.getId())
        {
            case R.id.common_back:
            	
                finish();
                
                break;
                
            case R.id.common_bt:
            	
                TelUtils.tel(this, ConstantValues.TEL_PHONE);
                
                break;
                
            case R.id.add:
            	
                buy_num = Integer.parseInt(tv_num.getText().toString());
                
                buy_num++;
                
                all_price = price * buy_num;
                
                tv_num.setText((buy_num) + "");
                
                tv_total_price.setText("￥" + all_price);
                
                break;
                
            case R.id.subtraction:
            	
                buy_num = Integer.parseInt(tv_num.getText().toString());
                
                if (buy_num > 1) 
                {
                    buy_num--;
                    
                    all_price = price * buy_num;
                    
                    tv_num.setText((buy_num) + "");
                    
                    tv_total_price.setText("￥" + all_price);
                }
                
                break;
                
            case R.id.btpay:
            	
                String date = null;
                
                try
                {
                    date = DateFormatUtils.formatDetail(new Date());
                    
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
                
                OrderpayBean order_bean = new OrderpayBean();
                
                order_bean.setType("0");
                
                order_bean.setUser_id(ConstantValues.UID);
                
                order_bean.setMobiel(ConstantValues.USER_MOBILE);
                
                if (CheckUtils.checkEmpty(tv_person.getText().toString())) 
                {
                    ToastUtils.showInfo(this, "请输入联系人姓名");
                    
                    return;
                }
                
                if (CheckUtils.checkEmpty(tv_contact.getText().toString().trim())|tv_contact.getText().toString().trim().length()!=11) 
                {
                    ToastUtils.showInfo(this, "请输入正确手机号");
                    
                    return;
                }
                
                    order_bean.setConsumer_name(tv_person.getText().toString().trim());
                    
                    order_bean.setConsumer_num(buy_num);
                    
                    order_bean.setProduct_id(Integer.valueOf(bean.getId()));
                    
                    order_bean.setOrder_amount((double) all_price);
                    
                    order_bean.setBook(tv_remarkes.getText().toString().trim());
                    
                    order_bean.setArrival_time(date);
                    
                    order_bean.setCourt_id(rgid);
                    
                    Intent order_pay = new Intent(this, OrderPayActivity.class);
                    
                    order_pay.putExtra("order_bean", order_bean);
                    
                    this.startActivity(order_pay);


                break;
            default:
                break;
        }

    }
}
