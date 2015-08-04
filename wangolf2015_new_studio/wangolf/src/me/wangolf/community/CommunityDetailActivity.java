package me.wangolf.community;

/**
 * ============================================================
 * <p/>
 * 版权 ：美高传媒 版权所有 (c) 下午4:39:45
 * <p/>
 * 作者:copy
 * <p/>
 * 版本 ：1.0
 * <p/>
 * 创建日期 ： 下午4:39:45
 * <p/>
 * 描述 ：社区帖子详情页f
 * <p/>
 * <p/>
 * 修订历史 ：
 * <p/>
 * ============================================================
 */

import java.util.ArrayList;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import me.wangolf.ConstantValues;
import me.wangolf.adapter.CommunityDetailAdapter;
import me.wangolf.adapter.CommunityIndexTagAdapter;
import me.wangolf.adapter.GridImageAdapter;
import me.wangolf.bean.InfoEntity;
import me.wangolf.bean.community.CommunityDetailEntity;
import me.wangolf.bean.community.CommunityPostsEntity;
import me.wangolf.bean.community.ImgInfoEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.text.view.ExpandTabView;
import me.wangolf.usercenter.LoginActivity;
import me.wangolf.usercenter.UserInfoEditInfoActivity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.CommonDefine;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.FileUtils;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.ImageUtils;
import me.wangolf.utils.NoScrollGridView;
import me.wangolf.utils.ResizeLayout;
import me.wangolf.utils.ShareUtils;
import me.wangolf.utils.ShowPickUtils;
import me.wangolf.utils.ToastUtils;
import me.wangolf.utils.Xutils;
import me.wangolf.utils.viewUtils.PullToRefreshBase;
import me.wangolf.utils.viewUtils.PullToRefreshListView;
import me.wangolf.utils.viewUtils.PullToRefreshBase.OnRefreshListener;

public class CommunityDetailActivity extends AbsActivity implements OnClickListener {
    @ViewInject(R.id.common_back)
    private TextView common_back;
    @ViewInject(R.id.common_title)
    private TextView common_title;
    @ViewInject(R.id.common_bt)
    private TextView common_bt;
    @ViewInject(R.id.common_img)
    private ImageView common_img;
    @ViewInject(R.id.comm_li)
    private LinearLayout comm_li;
    @ViewInject(R.id.pull_refresh_list)
    private PullToRefreshListView mPullList;
    @ViewInject(R.id.c_d_img)
    private ImageView mBut_img;
    @ViewInject(R.id.iv_edit_cate)
    private ExpandTabView expandTabView;
    @ViewInject(R.id.gridview_image)
    private NoScrollGridView mGv;
    @ViewInject(R.id.c_d_send)
    private Button mSend;// 回帖
    @ViewInject(R.id.c_d_ed)
    private EditText mContent;// 回帖内容
    @ViewInject(R.id.resizelayout)
    private ResizeLayout mResizeLayout;
    @ViewInject(R.id.c_d_img)
    private ImageView mImg;
    @ViewInject(R.id.bt_praise)
    private Button mPraise;

    private CommunityDetailEntity bean;
    private GridImageAdapter gridImageAdapter;
    private CommunityDetailAdapter adapter;
    private String posts_id = "1";
    private String user_id;// 用户ID
    private int posts_user_id;// 楼主ID
    private String content;// 回帖内容
    private String img_list;// 回帖图片、
    private ArrayList<String> dataList;
    private ArrayList<String> tDataList;
    private int receive_id = 533;// 接收评论人id
    private int reply_id; // 评论id
    private int mFlag = -1;// 帖子回复0 帖子评论为1；
    private int mPosition;// 评论后滚动到第几
    private boolean isreply;
    private static final int BIGGER = 1;
    private static final int SMALLER = 2;
    private static final int MSG_RESIZE = 1;
    private boolean isfirst;
    private Dialog dialog;//加载页
    private String sharetitle;
    private String shareUrl;
    private String picfile;
    private String imagename;
    private String flag;
    // 用于设置侦听软键盘的显示隐藏
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_RESIZE: {

                    if (msg.arg1 == BIGGER) {
                        mImg.setVisibility(View.VISIBLE);
                        if (isreply | (mFlag == 1)) {
                            dataList.clear();
                            mGv.setVisibility(View.GONE);
                            gridImageAdapter.notifyDataSetChanged();
                            mContent.setText("");// 评论成功请空数据
                            mContent.setHint("说点什么吧...");
                        }
                        if (isfirst)
                            mFlag = 0;
                    } else {
                    }
                }
                break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_community_detail);
        ViewUtils.inject(this);

        if (adapter == null) {
            adapter = new CommunityDetailAdapter(this);
        } else {
            adapter.notifyDataSetChanged();
        }
        mPullList.getRefreshableView().setAdapter(adapter);
        mPullList.setPullLoadEnabled(false);
        // 滚动到底自动加载可用
        mPullList.setScrollLoadEnabled(true);
        // 得到实际的ListView 设置点击
        mPullList.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

        // 设置下拉刷新的listener
        mPullList.setOnRefreshListener(new OnRefreshListener<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                mFlag = -1;
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                // onLoaded(); // 加载完 关闭加载框

            }
        });
        initData();
    }

    @SuppressLint("NewApi")
    public void initData() {
        dialog = DialogUtil.getDialog(this);
        posts_id = getIntent().getStringExtra("posts_id");
        //Log.i("wangolf",posts_id);
        flag = getIntent().getStringExtra("flag");
        common_title.setText("帖子详情");
        common_bt.setText("更多");
        common_back.setVisibility(View.VISIBLE);
        common_bt.setVisibility(View.VISIBLE);
        common_back.setOnClickListener(this);
        common_bt.setOnClickListener(this);
        mBut_img.setOnClickListener(this);
        mSend.setOnClickListener(this);// 回帖
        mPraise.setOnClickListener(this);
        dataList = new ArrayList<String>();
        if (gridImageAdapter == null) {
            gridImageAdapter = new GridImageAdapter(this, dataList, loader, options);
        } else {
            gridImageAdapter.notifyDataSetChanged();
        }
        mGv.setAdapter(gridImageAdapter);
        user_id = ConstantValues.UID;
        // 获取布局的高度用于设置侦听软键盘的显示隐藏
        mResizeLayout.setOnResizeListener(new ResizeLayout.OnResizeListener() {
            public void OnResize(int w, int h, int oldw, int oldh) {
                int change = BIGGER;
                if (h < oldh) {
                    change = SMALLER;
                }
                Message msg = new Message();
                msg.what = 1;
                msg.arg1 = change;
                mHandler.sendMessage(msg);
            }
        });
        getData();
        initListener();

    }


    // 拿取帖子信息
    public void getData() {
        try {
            dialog.show();
            ServiceFactory.getCommunityEngineInstatice().getPostsDetail(posts_id, user_id, new IOAuthCallBack() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void getIOAuthCallBack(String result) {

                    if (result.equals(ConstantValues.FAILURE)) {
                        ToastUtils.showInfo(CommunityDetailActivity.this, ConstantValues.NONETWORK);
                    } else {
                        CommunityDetailEntity beans = GsonTools.changeGsonToBean(result, CommunityDetailEntity.class);
                        if ("1".equals(beans.getStatus())) {
                            ArrayList<CommunityDetailEntity> data = beans.getData();
                            adapter.setList(data);
                            bean = data.get(0);
                            adapter.notifyDataSetChanged();
                            posts_user_id = data.get(0).getUser_id();
                            posts_id = data.get(0).getId() + "";
                           // mPraise.setText("0".equals(bean.getIs_praise()) ? "点赞" : "已赞");
                            mPraise.setBackground("1".equals(bean.getIs_praise()) ?getResources().getDrawable(R.drawable.bt_input_select):getResources().getDrawable(R.drawable.bt_input_default));
                            sharetitle = bean.getTitle();
                           // Log.i("wangolf,",bean.getImg_list());
                            if(!CheckUtils.checkEmpty(bean.getImg_list())){
                                String [] imageurl= bean.getImg_list().split(",");
                                picfile=imageurl[0];
                            }
                            switch (mFlag) {
                                case 0:
                                    scrollToBottomListItem(adapter.getCount() + 1);
                                    break;
                                case 1:
                                    scrollToBottomListItem(mPosition);
                                    mFlag = 0;
                                    break;
                                case -1:
                                    isfirst = true;
                                    mFlag = 0;
                                    break;
                                default:
                                    break;
                            }
                            if("reply".equals(flag)){
                                //Log.i("wangolf","***********flag***");
                                flag="";
                                mContent.requestFocus();
                                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                            }
                            // scrollToBottomListItem(adapter.getCount() + 1);
                            dialog.cancel();
                            onLoaded();
                        }
                    }

                }
            });
        } catch (Exception e) {
            dialog.cancel();
            e.printStackTrace();
        }

    }

    // 帖子回复
    public void toPostsReply() {
        try {
            dialog.show();
            ServiceFactory.getCommunityEngineInstatice().toPostsReply(user_id, posts_user_id, posts_id, content, img_list, new IOAuthCallBack() {
                @Override
                public void getIOAuthCallBack(String result) {

                    if (result.equals(ConstantValues.FAILURE)) {
                        ToastUtils.showInfo(CommunityDetailActivity.this, ConstantValues.NONETWORK);
                    } else {
                        InfoEntity bean = GsonTools.changeGsonToBean(result, InfoEntity.class);
                        if ("1".equals(bean.getStatus())) {
                            mContent.setText("");// 评论成功请空数据
                            isreply = true;//评论成功
                            InputMethodManager();
                            getData();
                            ToastUtils.showInfo(CommunityDetailActivity.this, bean.getInfo());
                        }
                    }
                    dialog.cancel();
                }
            });
        } catch (Exception e) {
            dialog.cancel();
            e.printStackTrace();
        }
    }

    // 评论回复接口
    public void toMommentReply() {
        try {
            dialog.show();
            ServiceFactory.getCommunityEngineInstatice().toMommentReply(user_id, receive_id, reply_id, content, img_list, new IOAuthCallBack() {

                @Override
                public void getIOAuthCallBack(String result) {

                    if (result.equals(ConstantValues.FAILURE)) {
                        ToastUtils.showInfo(CommunityDetailActivity.this, ConstantValues.NONETWORK);
                    } else {
                        InfoEntity bean = GsonTools.changeGsonToBean(result, InfoEntity.class);
                        if ("1".equals(bean.getStatus())) {
                            getData();
                            InputMethodManager();
                            ToastUtils.showInfo(CommunityDetailActivity.this, bean.getInfo());

                        }
                        dialog.cancel();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            dialog.cancel();
        }

    }

    // ***图片上传
    public void loadPostsImg(ArrayList<String> avatar_file, int i) {
        dialog.show();
        ArrayList<String> images = ImageUtils.compressImages(avatar_file);//压缩图片
        try {
            ServiceFactory.getCommunityEngineInstatice().loadPostsImg(images, i, new IOAuthCallBack() {

                @Override
                public void getIOAuthCallBack(String result) {

                    if (result.equals(ConstantValues.FAILURE)) {
                        ToastUtils.showInfo(CommunityDetailActivity.this, ConstantValues.NONETWORK);
                    } else {

                        ImgInfoEntity bean = GsonTools.changeGsonToBean(result, ImgInfoEntity.class);
                        if ("1".equals(bean.getStatus())) {
                            img_list = bean.getData().get(0).getLogo();
                            toPostsReply();// 发帖
                            FileUtils.clearImage();//上传完成 清空图片
                        } else {
                            ToastUtils.showInfo(CommunityDetailActivity.this, bean.getInfo());
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 返回结果处理
    @SuppressWarnings("unchecked")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data == null) {
            return;
        }
        switch (resultCode) {
            case -1:
                // 拍照返回结果
                mGv.setVisibility(View.VISIBLE);
                String cameraImagePath = data.getStringExtra("cameraImagePath");
                for (int i = 0; i < dataList.size(); i++) {
                    String path = dataList.get(i);

                    if (path.contains("default")) {
                        dataList.remove(dataList.size() - 1);
                    }
                }
                dataList.add(cameraImagePath);
                if (dataList.size() < 10) {
                    dataList.add("camera_default");
                }
                gridImageAdapter.notifyDataSetChanged();
                break;
            case CommonDefine.TAKE_PICTURE_FROM_GALLERY:
                // 相册选择返回结果
                dataList.clear();
                mGv.setVisibility(View.VISIBLE);
                Bundle bundle2 = data.getExtras();
                tDataList = (ArrayList<String>) bundle2.getSerializable("dataList");
                if (tDataList != null) {
                    for (int i = 0; i < tDataList.size(); i++) {
                        String string = tDataList.get(i);
                        dataList.add(string);
                    }
                    if (dataList.size() < 9) {
                        dataList.add("camera_default");
                    }
                    gridImageAdapter.setDataList(dataList);
                    gridImageAdapter.notifyDataSetChanged();

                }
                break;
            case CommonDefine.DELETE_IMAGE:
                // 删除相片返回结果
                int position = data.getIntExtra("position", -1);
                dataList.remove(position);
                if (dataList.size() < 10) {
                    dataList.add(dataList.size(), "camera_default");
                    for (int i = 0; i < dataList.size(); i++) {
                        String path = dataList.get(i);
                        if (path.contains("default")) {
                            dataList.remove(dataList.size() - 2);
                        }
                    }
                }
                if (dataList.size() <= 1) {
                    mGv.setVisibility(View.GONE);
                }
                gridImageAdapter.notifyDataSetChanged();
                break;
            case ConstantValues.ORDERPRAC:
                user_id = ConstantValues.UID;
                toSend();
                break;
            case 1008:
                //举报

                Intent complain = new Intent(this, CommunityPostsComplain.class);
                complain.putExtra("posts_id", posts_id);
                startActivity(complain);
                break;
            case 1009:
                //分享
                if (!CheckUtils.checkEmpty(picfile)) {
                    Xutils.loadImage(picfile);
                    int p = picfile.lastIndexOf("/");
                    imagename = picfile.substring(p);
                }
                shareUrl = "";
                ShareUtils.showShareandUrl(sharetitle, shareUrl, this, CheckUtils.checkEmpty(imagename) ? "" : imagename);
                break;
            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    // ???
    private ArrayList<String> getIntentArrayList(ArrayList<String> dataList) {
        ArrayList<String> tDataList = new ArrayList<String>();
        for (String s : dataList) {
            if (!s.contains("default")) {
                tDataList.add(s);
            }
        }
        return tDataList;
    }

    // grdiview 点击事件的监听
    private void initListener() {
        mGv.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path = dataList.get(position);
                if (path.contains("default") && position == dataList.size() - 1 && dataList.size() - 1 != 10) {
                    Intent selectpir = new Intent(CommunityDetailActivity.this, SelectPicPopupWindow.class);
                    selectpir.putStringArrayListExtra("dataList", getIntentArrayList(dataList));
                    startActivityForResult(selectpir, 100);
                } else {
                    Intent intent = new Intent(mActThis, ImageDelActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("path", dataList.get(position));
                    startActivityForResult(intent, CommonDefine.DELETE_IMAGE);
                }
            }
        });
    }

    // 点击事件监控
    @Override
    public void onClick(View v) {


    switch(v.getId()){
        case R.id.c_d_img:
            // 选择相片
            toLogin();
            Intent selectpir = new Intent(this, SelectPicPopupWindow.class);
            selectpir.putStringArrayListExtra("dataList", getIntentArrayList(dataList));
            startActivityForResult(selectpir, 100);
            break;
        case R.id.common_back:
            finish();// 返回
            break;
        case R.id.c_d_send:

            if(toLogin())
            toSend();// 发送帖子
            break;
        case R.id.common_bt:
            // 择择rr报

            if(toLogin()){
            Intent more = new Intent(this, CommunityPostsShare.class);
            startActivityForResult(more, 1007);}

            break;
        case R.id.c_d_ed:

            if (toLogin()) {
                // 去登录
                Intent toLogin = new Intent(this, LoginActivity.class);
                toLogin.putExtra("flag", "orderPrac");
                startActivityForResult(toLogin, ConstantValues.ORDERPRAC);
            }

            break;
        case R.id.bt_praise:
            //toLogin();
            if (bean != null&toLogin())
                toPraise(bean);
            break;
        default:
            break;
    }

}private boolean  toLogin(){
        if (!ConstantValues.ISLOGIN | !ConstantValues.ISCOMPLETEINFO) {
            // 去登录
            if (!ConstantValues.ISLOGIN) {
                Intent toLogin = new Intent(this, LoginActivity.class);
                toLogin.putExtra("flag", "orderPrac");
                this.startActivity(toLogin);
            } else if (!ConstantValues.ISCOMPLETEINFO) {
               // ToastUtils.showInfo(this, "请先完成资料设置");
                ShowPickUtils.ShowDialogComm(this, "您尚未完善个人资料，请先完善后才能继续操作");
//                Intent editinfo = new Intent(this, UserInfoEditInfoActivity.class);
//                this.startActivity(editinfo);
            }
            return false;
        }
        else {
            return true;
        }

    }

    // 楼层
    public void toSend() {
        if (ConstantValues.ISLOGIN & ConstantValues.ISCOMPLETEINFO) {
            user_id = ConstantValues.UID;
            content = mContent.getText().toString().trim();
            if (CheckUtils.checkEmpty(content)) {
                ToastUtils.showInfo(this, "内容不能为空");
                return;
            }
            switch (mFlag) {
                case 0:
                    if (dataList != null & dataList.size() > 1) {
                        loadPostsImg(dataList, dataList.size() - 1);
                    } else {
                        toPostsReply();
                    }
                    break;
                case 1:
                    toMommentReply();
                    break;
                default:
                    break;
            }
        }else {
            // 去登录
            if (!ConstantValues.ISLOGIN) {
                Intent toLogin = new Intent(this, LoginActivity.class);
                toLogin.putExtra("flag", "orderPrac");
                this.startActivity(toLogin);
            } else if (!ConstantValues.ISCOMPLETEINFO) {
               // ToastUtils.showInfo(this, "请先完成资料设置");
                ShowPickUtils.ShowDialogComm(this, "请完善个人资料");

            }
        }


    }

    // 评论回调
    public void toCReply(int receive_id, int reply_id, String hint, int position) {

        this.receive_id = receive_id;
        this.reply_id = reply_id;
        mContent.setFocusable(true);
        mContent.setHint(hint);
        mContent.setFocusableInTouchMode(true);
        mContent.requestFocus();
        @SuppressWarnings("static-access")
        InputMethodManager inputManager = (InputMethodManager) mContent.getContext().getSystemService(this.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(mContent, 0);
        mFlag = 1;
        mPosition = position;
        mImg.setVisibility(View.GONE);

    }

    private void toPraise(CommunityDetailEntity bean) {
        String type = "0";
        for (int i = 0; i < bean.getPraise_info().size(); i++) {
            if (Integer.parseInt(ConstantValues.UID) == bean.getPraise_info().get(i).getPraise_user_id()) {
                type = "1";

            }
        }
        try {
            ServiceFactory.getCommunityEngineInstatice().toPraise(ConstantValues.UID, bean.getId(), type, new IOAuthCallBack() {
                @Override
                public void getIOAuthCallBack(String result) {
                    InfoEntity bean = GsonTools.jsonToBean(result, InfoEntity.class);
                    if ("1".equals(bean.getStatus())) {
                        ToastUtils.showInfo(CommunityDetailActivity.this, bean.getInfo());
                        getData();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 显示或隐软争键盘控作
    public void InputMethodManager() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm.hideSoftInputFromWindow(mContent.getWindowToken(), 0)) {
            imm.showSoftInput(mContent, 0);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            // 软键盘已弹出
            // dataList.clear();
            if (mFlag == 1 | isreply) {

                dataList.clear();
                mGv.setVisibility(View.GONE);
                gridImageAdapter.notifyDataSetChanged();
                mContent.setText("");// 评论成功请空数据
                mContent.setHint("说点什么吧...");
                mFlag = 0;
                isreply = false;
            }
        } else {

            // 软键盘未弹出
            if (isreply) {
                dataList.clear();
                mGv.setVisibility(View.GONE);
                gridImageAdapter.notifyDataSetChanged();
                mContent.setText("");// 评论成功请空数据
                isreply = false;
            }
        }
    }

    // 发送内容后自动滚动到最底
    private void scrollToBottomListItem(int count) {
        if (mPullList != null) {
            mPullList.getRefreshableView().setSelection(count);
        }
    }

    // 关闭刷新
    private void onLoaded() {
        mPullList.onPullDownRefreshComplete();
        mPullList.onPullUpRefreshComplete();
    }

  public  void  toEditUserInfo(){
      Intent editinfo = new Intent(this, UserInfoEditInfoActivity.class);
      this.startActivity(editinfo);
    }
}