package presenter;


import java.util.Map;

import model.ModelImpl;
import model.MyCallBack;
import view.Iview;

public class PresenterImpl implements Ipresenter {
    private Iview mIview;
    private ModelImpl model;

    public PresenterImpl(Iview iview) {
        mIview = iview;
        model = new ModelImpl();
    }

    /**
     * post
     */
    @Override
    public void reqyestPost(String url, Map<String, String> map, Class clazz) {
        model.requestPost(url, map, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object o) {
                mIview.requestData(o);
            }

            @Override
            public void onFail(String error) {
                mIview.requestFail(error);
            }
        });
    }

    /**
     * get
     */
    @Override
    public void requestGet(String url, Class clazz) {

    }

    public void onDetach() {
        if (mIview != null) {
            mIview = null;
        }
        if (model != null) {
            model = null;
        }
    }
}
