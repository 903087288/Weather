package com.example.tianqiyubao.weiboshare;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.tianqiyubao.R;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.sina.weibo.sdk.statistic.WBAgent;
import com.sina.weibo.sdk.utils.Utility;

/**
 * Created by dell on 2017/6/1.
 */

public class WBShareActivity extends Activity implements View.OnClickListener,WbShareCallback {
    public static final  String KEY_SHARE_TYPE ="key_share_type";
    public static final int SHARE_CLIENT =1;
    public static final int SHARE_ALL_IN_ONE =2;
    /** 界面标题 */
    private TextView mTitleView;
    /** 分享图片 */
    private ImageView mImageView;
    /** 用于控制是否分享文本的 CheckBox */
    private CheckBox mTextCheckbox;
    /** 用于控制是否分享图片的 CheckBox */
    private CheckBox mImageCheckbox;
    /** 分享按钮 */
    private Button mSharedBtn;
    private WbShareHandler shareHandler;
    private int mShareType = SHARE_CLIENT;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        initViews();
        mShareType = getIntent().getIntExtra(KEY_SHARE_TYPE,SHARE_CLIENT);
        shareHandler = new WbShareHandler(this);
        shareHandler.registerApp();
    }

    @Override
    protected  void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        shareHandler.doResultIntent(intent,this);
    }

    /**
     * 用户点击分享按钮，唤起微博客户端进行分享。
     */
    @Override
    public void onClick(View v){
        if(R.id.share_to_btn == v.getId()){
            mSharedBtn.setText("share done");
            flag =1;
            sendMssage(mTextCheckbox.isChecked(),mImageCheckbox.isChecked());
        }
    }
    /**
     * 初始化界面。
     */
    private void initViews(){
        mTitleView =(TextView)findViewById(R.id.share_title);
        mTitleView.setText(R.string.weibosdk_demo_share_to);

        mTextCheckbox =(CheckBox)findViewById(R.id.share_text_checkbox);
        mImageCheckbox =(CheckBox)findViewById(R.id.shared_image_checkbox);
        mSharedBtn =(Button)findViewById(R.id.share_to_btn);
        mSharedBtn.setOnClickListener(this);
    }

    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。
     */
    private void sendMssage(boolean hasText,boolean hasImage){
        sendMultiMessage(hasText,hasImage);
    }

    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。
     */
    private void sendMultiMessage(boolean hasText,boolean hasImage){
        WeiboMultiMessage weiboMessage =new WeiboMultiMessage();
        if(hasText){
            weiboMessage.textObject = getTextObj();
        }
        if(hasImage){
            weiboMessage.imageObject = getImageObj();
        }
        weiboMessage.mediaObject = getWebpageObj();
        shareHandler.shareMessage(weiboMessage,mShareType == SHARE_CLIENT);
    }

    @Override
    public void onWbShareSuccess(){
        Toast.makeText(this,R.string.weibosdk_demo_toast_share_success,Toast.LENGTH_LONG).show();
    }
    @Override
    public void onWbShareFail() {
        Toast.makeText(this,getString(R.string.weibosdk_demo_toast_share_failed)+ "Error Message: ",
                Toast.LENGTH_LONG).show();
    }
    @Override
    public void onWbShareCancel() {
        Toast.makeText(this, R.string.weibosdk_demo_toast_share_canceled, Toast.LENGTH_LONG)
                .show();
    }

    /**
     * 获取分享的文本模板。
     */
    private String getSharedText() {
        int formatId =R.string.weibosdk_demo_share_text_template;
        String format =getString(formatId);
        String text =format;
        if(mTextCheckbox.isChecked() || mImageCheckbox.isChecked()){
        text="  ";
        }
        return  text;
    }
    /**
     * 创建文本消息对象。
     * @return 文本消息对象。
     */
    private TextObject getTextObj() {
        TextObject textObject = new TextObject();
        textObject.text = getSharedText();
        textObject.title = "xxxx";
        textObject.actionUrl = "http://www.baidu.com";
        return textObject;
    }

    /**
     * 创建图片消息对象。
     * @return 图片消息对象。
     */
    private ImageObject getImageObj() {
        ImageObject imageObject = new ImageObject();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.test);
        imageObject.setImageObject(bitmap);
        return imageObject;
    }

    /**
     * 创建多媒体（网页）消息对象。
     *
     * @return 多媒体（网页）消息对象。
     */
    private WebpageObject getWebpageObj() {
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title ="测试title";
        mediaObject.description = "测试描述";
        Bitmap  bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_logo);
        // 设置 Bitmap 类型的图片到视频对象里         设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        mediaObject.setThumbImage(bitmap);
        mediaObject.actionUrl = "http://news.sina.com.cn/c/2013-10-22/021928494669.shtml";
        mediaObject.defaultText = "Webpage 默认文案";
        return mediaObject;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
