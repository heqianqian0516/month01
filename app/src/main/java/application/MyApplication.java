package application;

import android.app.Application;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //设置磁盘缓存
        DiskCacheConfig config  = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryName("gimages")
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory())
                .build();
        //设置磁盘缓存的配置
        ImagePipelineConfig pipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(config)
                .build();
        Fresco.initialize(this,pipelineConfig);
    }
}
