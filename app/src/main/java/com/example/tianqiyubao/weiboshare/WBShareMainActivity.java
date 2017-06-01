package com.example.tianqiyubao.weiboshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tianqiyubao.R;

/**
 * Created by dell on 2017/6/1.
 */

public class WBShareMainActivity extends Activity {
    /** 微博分享按钮 */
    private Button mShareButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_main);
        initialize();
    }
    /**
     * 初始化 UI 和微博接口实例 。
     */
    private void initialize() {
        /**
         * 初始化 UI
         */
        // 设置提示文本
        // 设置微博客户端相关信息
        //String installInfo = getString(isInstalledWeibo ? R.string.weibosdk_demo_has_installed_weibo : R.string.weibosdk_demo_has_installed_weibo);
        //((TextView)findViewById(R.id.weibosdk_demo_is_installed_weibo)).setText(installInfo);
        //((TextView)findViewById(R.id.weibosdk_demo_support_api_level)).setText("\t" + supportApiLevel);

        // 设置注册按钮对应回调
        // 设置分享按钮对应回调
        mShareButton = (Button) findViewById(R.id.share_to_weibo);
        mShareButton.setEnabled(true);
        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WBShareMainActivity.this, WBShareActivity.class);
                i.putExtra(WBShareActivity.KEY_SHARE_TYPE, WBShareActivity.SHARE_CLIENT);
                startActivity(i);
            }
        });

    }
}
