package model;

import java.util.Map;

public interface Imodel {
    //post
    void requestPost(String url, Map<String, String> map, Class clazz, MyCallBack myCallBack);
    //get
    void requestGet(String url, Class clazz, MyCallBack myCallBack);
}
