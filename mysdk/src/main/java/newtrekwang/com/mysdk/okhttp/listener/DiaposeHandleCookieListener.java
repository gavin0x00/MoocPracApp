package newtrekwang.com.mysdk.okhttp.listener;

import java.util.ArrayList;

/**
 * Created by dell on 2018/1/28.
 */

public interface DiaposeHandleCookieListener extends DisposeDataListener {
    /**
     * 获取到cookie
     * @param cookieStrLists
     */
    public void onCookie(ArrayList<String> cookieStrLists);
}
