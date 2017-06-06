package com.example.tianqiyubao.weiboshare;

/**该类定义了微博授权时所需要的参数。
 * Created by dell on 2017/6/1.
 */

public interface Constants {
    public static final String APP_KEY      = "2045436852";
    public static final String REDIRECT_URL = "http://www.sina.com";
    public static final String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";
}

