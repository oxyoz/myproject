package me.wangolf.adapter;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年3月4日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年3月4日
 * 
 * 描述 ：社区首页 置顶adapter
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meigao.mgolf.R;

import java.util.ArrayList;

import me.wangolf.bean.community.CommunityHotPostsEntity;
import me.wangolf.bean.community.CommunityTagEntity;

public class CommunitySendTagAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<CommunityTagEntity> tag_list;

	public CommunitySendTagAdapter(Context context) {
		super();
		this.context = context;
	}

	public ArrayList<CommunityTagEntity> getTag_list() {
		return tag_list;
	}

	public void setTag_list(ArrayList<CommunityTagEntity> tag_list) {
		this.tag_list = tag_list;
	}

	@Override
	public int getCount() {

		return tag_list == null ?0 : tag_list.size();
	}

	@Override
	public Object getItem(int position) {

		return tag_list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 加载或复用item界面
		ViewHolder holder = null;

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.item_comm_tag_list, null);
			holder.mName = (TextView) convertView.findViewById(R.id.comm_hot_title);
           holder.mLi= (LinearLayout) convertView.findViewById(R.id.item_comm_hot_li);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 设数据

		CommunityTagEntity bean = tag_list.get(position);
		holder.mName.setText(bean.getName());
		return convertView;
	}

	class ViewHolder {
//		private ImageView hot_ico;
		private TextView mName;
		private LinearLayout mLi;
//		private TextView comm_list_title;
//		private TextView comm_list_content;
//		private TextView comm_name;
//		private TextView comm_time;
//		private TextView comm_reply;
//		private TextView comm_visit;
	}
}
