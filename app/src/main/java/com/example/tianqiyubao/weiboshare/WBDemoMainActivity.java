package com.example.tianqiyubao.weiboshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;

import com.example.tianqiyubao.R;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.statistic.WBAgent;

/**
 * Created by dell on 2017/6/1.
 */

public class WBDemoMainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        //LogUtil.sIsLogEnable = true;
        //initLog();
        WbSdk.install(this,new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE));

        //分享微博功能
        this.findViewById(R.id.feature_share).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(WBDemoMainActivity.this,WBShareActivity.class));
            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();
        //统计应用启动时间
        WBAgent.onPageStart("WBDemoMainActivity");
        WBAgent.onResume(this);
    }
    @Override
    public void onPause(){
        super.onPause();
        //统计页面退出
        WBAgent.onPageEnd("WBDemoMainActivity");
        WBAgent.onPause(this);
    }
    @Override
   public void onDestroy(){
        super.onDestroy();
        //退出应用时关闭统计进程
        WBAgent.onKillProcess();
    }


    //初始化日志统计相关的数据
    public void initLog() {
        WBAgent.setAppKey(Constants.APP_KEY);
        WBAgent.setChannel("weibo"); //这个是统计这个app 是从哪一个平台down下来的  百度手机助手

        WBAgent.openActivityDurationTrack(false);
        try {
            //设置发送时间间隔 需大于90s小于8小时
            WBAgent.setUploadInterval(91000);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
