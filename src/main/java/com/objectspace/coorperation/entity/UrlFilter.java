package com.objectspace.coorperation.entity;

import java.io.Serializable;

/** 
* @Description: FilterChainDefinitionMap的数据库配置对象类
* @Author: NoCortY
* @Date: 2019/10/4
*/
public class UrlFilter implements Serializable {

    private static final long serialVersionUID = 1976428971042800899L;
    private String url;
    private String filterName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    @Override
    public String toString() {
        return "UrlFilter{" +
                "url='" + url + '\'' +
                ", filterName='" + filterName + '\'' +
                '}';
    }
}
