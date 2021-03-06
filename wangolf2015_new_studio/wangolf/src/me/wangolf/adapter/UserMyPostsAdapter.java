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
 * 描述 ：我的帖子adapter
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.util.ArrayList;

import com.meigao.mgolf.R;

import me.wangolf.bean.community.CommunityPostsEntity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserMyPostsAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<CommunityPostsEntity> posts_list;

	public UserMyPostsAdapter(Context context) {
		super();
		this.context = context;
	}

	public ArrayList<CommunityPostsEntity> getPosts_list() {
		return posts_list;
	}

	public void setPosts_list(ArrayList<CommunityPostsEntity> posts_list) {
		this.posts_list = posts_list;
	}

	@Override
	public int getCount() {
		return posts_list == null ? 0 : posts_list.size();
	}

	@Override
	public Object getItem(int position) {

		return posts_list.get(position);
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
			convertView = View.inflate(context, R.layout.item_comm_list, null);
			holder.comm_list_title = (TextView) convertView.findViewById(R.id.comm_list_title);
			holder.comm_list_content = (TextView) convertView.findViewById(R.id.comm_list_content);
			holder.comm_name = (TextView) convertView.findViewById(R.id.comm_name);
			holder.comm_time = (TextView) convertView.findViewById(R.id.comm_time);
			holder.comm_reply = (TextView) convertView.findViewById(R.id.comm_reply);
			holder.comm_visit = (TextView) convertView.findViewById(R.id.comm_visit);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 设数据
		CommunityPostsEntity data_list = posts_list.get(position);
		holder.comm_list_title.setText(data_list.getTitle());
		holder.comm_list_content.setText(data_list.getContent());
		holder.comm_name.setText(data_list.getName());
		holder.comm_time.setText(data_list.getUpdate_time());
		holder.comm_reply.setText(data_list.getReply_count() + "");
		holder.comm_visit.setText(data_list.getVisit_count() + "");

		return convertView;
	}

	class ViewHolder {
		private ImageView hot_ico;
		private TextView hot_title;
		private TextView comm_list_title;
		private TextView comm_list_content;
		private TextView comm_name;
		private TextView comm_time;
		private TextView comm_reply;
		private TextView comm_visit;
	}
}
