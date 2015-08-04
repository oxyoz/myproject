package me.wangolf.shop;

import java.util.Date;
import java.util.HashMap;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.adapter.ShopColorAdapter;
import me.wangolf.bean.shop.InfoPro;
import me.wangolf.bean.shop.OrderInfo;
import me.wangolf.bean.usercenter.OrderpayBean;
import me.wangolf.bean.usercenter.RespUserAdrrEntity;
import me.wangolf.dao.CityDao;
import me.wangolf.usercenter.AddressListActivity;
import me.wangolf.usercenter.OrderPayActivity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.DateFormatUtils;
import me.wangolf.utils.TelUtils;
import me.wangolf.utils.ToastUtils;
import me.wangolf.utils.Xutils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ProAtrrActivity extends Activity implements OnClickListener, OnItemClickListener {

	private InfoPro bean;
	private String[] colors;
	private String[] sizes;
	private String color; // 用户选择的色
	private String size;// 用户选择的size
	@ViewInject(R.id.pro_attr_img)
	private ImageView pro_attr_img; // 图片
	@ViewInject(R.id.tv_pro_name)
	private TextView tv_pro_name; // 名称
	@ViewInject(R.id.tv_pro_atrr_price)
	private TextView tv_pro_atrr_price;// 价格
	@ViewInject(R.id.tv_pro_atrr_count)
	private TextView tv_pro_atrr_count;// 数量 库存
	@ViewInject(R.id.tv_num)
	private TextView tv_num;// 选择购买的数量显示
	private int number = 1; // 购买数量
	@ViewInject(R.id.atrrlayout)
	private LinearLayout atrrlayout; // 动态添加
	@ViewInject(R.id.atrrsize)
	private LinearLayout atrrsize; // 尺
	@ViewInject(R.id.numAdd)
	private TextView numAdd;// 增加数量
	@ViewInject(R.id.numSubtraction)
	private TextView numSubtraction;// 减少数量
	@ViewInject(R.id.select_add)
	private RelativeLayout select_add;// 选择收货地址
	@ViewInject(R.id.tvname)
	private TextView tvname; // 收货人姓名
	@ViewInject(R.id.tv_mobile)
	private TextView tv_mobile;// 收货人电话
	@ViewInject(R.id.common_back)
	private Button common_back; // 后退
	@ViewInject(R.id.common_title)
	private TextView common_title;// 标题
	@ViewInject(R.id.common_bt)
	private TextView common_bt;// 电话
	@ViewInject(R.id.tv_address)
	private TextView tv_address;
	@ViewInject(R.id.tv_remarkes)
	private EditText tv_remarkes;//备注

	@ViewInject(R.id.btsubmit)
	private Button btsubmit;// 提交订单
	private boolean flag;
	private OrderpayBean order_bean;// 订单bean
	private Double order_amount;// 订单总价格

	@ViewInject(R.id.gv_color)
	private GridView gv_color;
	@ViewInject(R.id.gv_size)
	private GridView gv_size;
	private InfoPro data;
	private ShopColorAdapter coloradapter;
	private ShopColorAdapter sizeadapter;
	private RespUserAdrrEntity address;
	private String procitxian;// 详细地址
	private StringBuffer product_color;// 商品属性
	private StringBuffer product_size;// 商品属性

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_pro_atrr);
		ViewUtils.inject(this);
		if (!flag)
			initData();
	}

	public void initData() {
		tv_num.setText(number + "");
		common_title.setText(ConstantValues.SHOP_ATRRINFO);
		common_back.setVisibility(0);
		common_bt.setVisibility(0);
		common_bt.setText(ConstantValues.SHOP_ATRRINFO_PHONE);
		bean = (InfoPro) getIntent().getSerializableExtra("bean");
		if (bean != null) {
			if (getIntent().getStringExtra("color") != null) {
				colors = getIntent().getStringExtra("color").replace("\"", "").replace("[", "").replace("]", "").split(",");
				coloradapter = new ShopColorAdapter(getApplicationContext(), colors);
				gv_color.setAdapter(coloradapter);
			} else {
				atrrlayout.setVisibility(8);
			}

			if (getIntent().getStringExtra("size") != null) {
				sizes = getIntent().getStringExtra("size").replace("\"", "").replace("[", "").replace("]", "").split(",");
				sizeadapter = new ShopColorAdapter(getApplicationContext(), sizes);
				gv_size.setAdapter(sizeadapter);
			} else {
				atrrsize.setVisibility(8);
			}
			data = bean.getData().get(0);
			String url;
			if (data.getProimg().indexOf(",") != -1) {
				url = data.getProimg().substring(0, data.getProimg().indexOf(","));
			} else {
				url = data.getProimg();
			}
			Xutils.getBitmap(getApplicationContext(), pro_attr_img, url);
			tv_pro_name.setText(data.getProname());
			tv_pro_atrr_price.setText("￥" + data.getPronprice());
			tv_pro_atrr_count.setText("库存" + data.getLave() + "件");
			order_amount = data.getPronprice(); // 初始总价格
			common_back.setOnClickListener(this);
			numAdd.setOnClickListener(this);
			numSubtraction.setOnClickListener(this);
			select_add.setOnClickListener(this);
			common_bt.setOnClickListener(this);
			btsubmit.setOnClickListener(this);

		}
		gv_color.setOnItemClickListener(this);
		gv_size.setOnItemClickListener(this);

	}

	public void getData() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.common_back:
			finish();
			break;
		case R.id.numAdd:
			if (number < data.getLave())
				number = number + 1;
			tv_num.setText(number + "");
			tv_pro_atrr_price.setText(number * data.getPronprice() + "");
			order_amount = number * data.getPronprice();
			break;
		case R.id.numSubtraction:
			if (number > 1)
				number = number - 1;
			tv_num.setText(number + "");
			tv_pro_atrr_price.setText(number * data.getPronprice() + "");
			order_amount = number * data.getPronprice();
			break;
		case R.id.select_add:
			Intent intent = new Intent(getApplicationContext(), AddressListActivity.class);
			intent.putExtra("type", "proattr");
			startActivityForResult(intent, ConstantValues.ADDRESS_CODE);
			break;
		case R.id.common_bt:
			TelUtils.tel(this, ConstantValues.TEL_PHONE);
			break;

		case R.id.btsubmit:
			if (data.getLave() < 1) {
				ToastUtils.showInfo(this, "抱歉，库存不足");
				return;
			}
			toAddProductOrder();
			break;
		default:
			break;
		}

	}

	// 提交订单
	public void toAddProductOrder() {
		OrderInfo order = new OrderInfo();
		order.setUid("");
		order.setCid(data.getCid());
		order.setProid(data.getProid());
		product_color = new StringBuffer();
		order.setNumber(number + "");
		product_size = new StringBuffer();
		if (address == null) {
			Toast.makeText(getApplicationContext(), "请选择收货地址", 0).show();
			return;
		} else {
			order.setMobile(address.getMobile());
			order.setAddress(address.getAddress());
			order.setCusname(address.getConsignee());
			order.setZip(address.getZip());

		}
		if (colors != null) {
			if (CheckUtils.checkEmpty(color)) {
				Toast.makeText(getApplicationContext(), "请选择商品颜色", 0).show();
				return;
			} else {
				product_color.append("颜色:");
				product_color.append(color);
				product_color.append(",");
			}
		}
		if (sizes != null) {
			if (CheckUtils.checkEmpty(size)) {
				Toast.makeText(getApplicationContext(), "请选择商品尺寸", 0).show();
				return;
			} else {
				product_size.append("尺寸:");
				product_size.append(size);
			}
		}
		order_bean = new OrderpayBean();
		order_bean.setType("3");// 1代表球球
		order_bean.setSupplier_id(data.getCid());// 供应商ID
		order_bean.setUser_id(ConstantValues.UID);// 用户ID
		order_bean.setConsumer_num(number);// 数量加1
		order_bean.setProduct_id(data.getProid());// 产品ID
		order_bean.setOrder_amount(order_amount);// 总价格
		order_bean.setBook(tv_remarkes.getText().toString().trim());// 备注
		order_bean.setMobiel(address.getMobile());// 订单电话号码
		order_bean.setConsumer_name(address.getConsignee());// 取系人姓名
		order_bean.setAddress(procitxian);// 收货地址
		order_bean.setZip(address.getZip());// 邮编(购买商品选填项)
		String product_attr = product_color.toString() + product_size.toString();
		// String attr = product_attr.toString().substring(0,
		// product_attr.toString().lastIndexOf(","));
		order_bean.setProduct_attr(product_attr);// 商品属性(购买商品选填项)
		String date = null;
		try {
			date = DateFormatUtils.formatDetail(new Date());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		order_bean.setArrival_time(date);
		Intent intent = new Intent(this, OrderPayActivity.class);
		intent.putExtra("order_bean", order_bean);
		this.startActivity(intent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (data == null) {
			return;
		}
		if (requestCode == ConstantValues.ADDRESS_CODE) {
			address = (RespUserAdrrEntity) data.getSerializableExtra("address");
			tvname.setText(address.getConsignee());
			tv_mobile.setText(address.getMobile());
			procitxian = findCityName(address.getRid1(), address.getRid2(), address.getRid3(), address.getRid4()) + address.getAddress();
			tv_address.setText(procitxian);// 详细地址
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		switch (arg0.getId()) {
		case R.id.gv_size: // 选择商品尺寸
			size = (String) sizeadapter.getItem(arg2);
			sizeadapter.setClickTemp(arg2);
			sizeadapter.notifyDataSetChanged();
			break;
		case R.id.gv_color: // 选择商品色
			color = (String) coloradapter.getItem(arg2);
			coloradapter.setClickTemp(arg2);
			coloradapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
	}

	// 通过城市ID查名
	public String findCityName(String rid1, String rid2, String rid3, String rid4) {
		HashMap<String, String> map = new CityDao(getApplicationContext()).getResUserAdrr(rid1, rid2, rid3, rid4);
		String provice = map.get("province") == null ? "" : map.get("province");
		String city = map.get("city") == null ? "" : map.get("city");
		String xian = map.get("xian") == null ? "" : map.get("xian");
		String procitxian = provice + " " + city + " " + xian;
		return procitxian;
	}
}