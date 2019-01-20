package utils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitMessage {
    private final String Base_URL="http://www.zhaoapi.cn/product/";
    private static RetrofitMessage intance;
    private final BaseApis baseApis;

    private RetrofitMessage() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10,TimeUnit.SECONDS);
        builder.readTimeout(10,TimeUnit.SECONDS);
        builder.writeTimeout(10,TimeUnit.SECONDS);
        builder.addInterceptor(interceptor);
        builder.retryOnConnectionFailure(true);
        OkHttpClient build = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Base_URL)
                .client(build)
                .build();
        baseApis = retrofit.create(BaseApis.class);
    }

    public static RetrofitMessage getIntance() {
        if (intance == null){
            synchronized (RetrofitMessage.class){
                intance = new RetrofitMessage();
            }
        }
        return intance;
    }
    /**
     * get请求
     * */
    public RetrofitMessage get(String url,Httplistener httplistener){
            baseApis.get(url)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getObserver(httplistener));
        return intance;
    }
    /**
     * post请求
     * */
    public RetrofitMessage post(String url, Map<String,String> map, Httplistener httplistener){
        if(map==null){
            map = new HashMap<>();
        }
        baseApis.post(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(httplistener));
        return intance;
    }
    /**
     * 定义观察者
     * */
    private Observer getObserver(final Httplistener httplistener){
        Observer observer = new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if(httplistener!=null){
                    httplistener.onFail(e.getMessage());
                }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                    try {
                        String data = responseBody.string();
                        if(httplistener!=null){
                            httplistener.onSuccess(data);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        if(httplistener!=null){
                            httplistener.onFail(e.getMessage());
                        }
                    }
            }


        };
        return observer;
    }
    //定义接口
    public interface Httplistener{
        void onSuccess(String data);
        void onFail(String error);
    }
}
