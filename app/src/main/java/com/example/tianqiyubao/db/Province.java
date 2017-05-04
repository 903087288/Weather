package com.example.tianqiyubao.db;

import org.litepal.crud.DataSupport;

/**
 * Created by 田保哲 on 2017/5/2.
 */

public class Province extends DataSupport {
    private int id;
    private String provinceName;
    private  int provinceCode;
    public int getId(){
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
   public String getProvinceName(){
       return provinceName;
   }
   public void setProvinceName(String provinceName){
       this.provinceName=provinceName;
   }
   public int getProvinceCode(){
       return provinceCode;
   }
   public void setProvinceCode(int provinceCode)

{
    this.provinceCode=provinceCode;
}

}
