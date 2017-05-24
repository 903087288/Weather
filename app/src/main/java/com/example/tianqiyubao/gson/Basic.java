package com.example.tianqiyubao.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 田保哲 on 2017/5/8.
 */

public class Basic {
    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public String weatherId;
    public Update update;
    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
