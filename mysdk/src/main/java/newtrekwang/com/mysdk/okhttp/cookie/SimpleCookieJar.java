package newtrekwang.com.mysdk.okhttp.cookie;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by WJX on 2018/1/28.
 * 适配OKhttp3的cookie 持久化配置对象
 */

public  class SimpleCookieJar implements CookieJar {
    // 这里只是用list缓存cookie
    private  final List<Cookie> allCookies = new ArrayList<>();

    /**
     * 存cookie
     * @param url
     * @param cookies
     */
    @Override
    public synchronized void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        allCookies.addAll(cookies);
    }

    /**
     * 加载cookie
     * @param url
     * @return
     */
    @Override
    public synchronized List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> result =  new ArrayList<>();
        for(Cookie cookie : allCookies){
            if (cookie.matches(url)){
                result.add(cookie);
            }
        }
        return result;
    }
}
