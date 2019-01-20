package presenter;

import java.util.Map;

public interface Ipresenter {
    //post
    void reqyestPost(String url, Map<String, String> map, Class clazz);
    //get
    void requestGet(String url, Class clazz);
}
