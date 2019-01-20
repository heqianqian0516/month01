package model;



import com.google.gson.Gson;

import java.util.Map;

import utils.RetrofitMessage;

public class ModelImpl implements Imodel {
    @Override
    public void requestPost(String url, final Map<String, String> map, final Class clazz, final MyCallBack myCallBack) {
        RetrofitMessage.getIntance().post(url, map, new RetrofitMessage.Httplistener() {
            @Override
            public void onSuccess(String data) {
                try {
                    Object o = new Gson().fromJson(data, clazz);
                    if(myCallBack!=null){
                        myCallBack.onSuccess(o);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    if(myCallBack!=null){
                        myCallBack.onFail(e.getMessage());
                    }
                }
            }

            @Override
            public void onFail(String error) {
                if(myCallBack!=null){
                    myCallBack.onFail(error);
                }
            }
        });
    }

    @Override
    public void requestGet(String url, Class clazz, MyCallBack myCallBack) {

    }
}
