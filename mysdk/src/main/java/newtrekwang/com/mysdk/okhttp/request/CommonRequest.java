package newtrekwang.com.mysdk.okhttp.request;

import java.io.File;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by WJX on 2018/1/28.
 */

public class CommonRequest {

    /**
     * 可以带请求头的POST请求  post 表单
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static Request createPostRequest(String url, RequestParams params, RequestParams headers){
        FormBody.Builder mFormBodyBuild = new FormBody.Builder();
        if (params != null){
            for (Map.Entry<String,String> entry : params.urlParams.entrySet()){
                mFormBodyBuild.add(entry.getKey(),entry.getValue());
            }
        }
        //添加请求头
        Headers.Builder mHeaderBuider  = new Headers.Builder();
        if (headers != null){
            for(Map.Entry<String,String> entry: headers.urlParams.entrySet()){
                mHeaderBuider.add(entry.getKey(),entry.getValue());
            }
        }
        FormBody formBody = mFormBodyBuild.build();
        Headers mHeader = mHeaderBuider.build();
        Request request = new Request.Builder().url(url)
                .post(formBody)
                .headers(mHeader)
                .build();
        return request;
    }

    /**
     * 不带请求头的POST请求
     * @param url
     * @param params
     * @return
     */
    public static Request createPostRequest(String url, RequestParams params) {
        return createPostRequest(url,params,null);
    }


        /**
         * 不带请求头的Get请求 相当与querry
         * @param url
         * @param params
         * @return
         */
    public static Request createGetRequest(String url,RequestParams params){
        return createGetRequest(url,params,null);
    }

    /**
     * 可以带请求头的Get请求
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static Request createGetRequest(String url, RequestParams params, RequestParams headers) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        //添加请求头
        Headers.Builder mHeaderBuild = new Headers.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.urlParams.entrySet()) {
                mHeaderBuild.add(entry.getKey(), entry.getValue());
            }
        }
        Headers mHeader = mHeaderBuild.build();
        return new Request.Builder().
                url(urlBuilder.substring(0, urlBuilder.length() - 1))//去掉最后一个‘&’
                .get()
                .headers(mHeader)
                .build();
    }

    /**
     * @param url
     * @param params
     * @return
     */
    public static Request createMonitorRequest(String url, RequestParams params) {
        StringBuilder urlBuilder = new StringBuilder(url).append("&"); // 区别在url是&
        if (params != null && params.hasParams()) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return new Request.Builder().url(urlBuilder.substring(0, urlBuilder.length() - 1)).get().build();
    }
//  File type
    private static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");

    /**
     * 文件上传请求
     * @param url
     * @param params
     * @return
     */
    public static Request createMultiPostRequest(String url,RequestParams params){
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
        bodyBuilder.setType(MultipartBody.FORM);
        if(params != null){
            for(Map.Entry<String,Object> entry: params.fileParams.entrySet()){
                if (entry.getValue() instanceof File){
                    bodyBuilder.addPart(Headers.of("Content-Dispositon","form-data: name = \""+entry.getKey()+"\""), RequestBody.create(FILE_TYPE, (File) entry.getValue()));
                }else if (entry.getValue() instanceof  String ){
                    RequestBody.create(null, (String) entry.getValue());
                }
            }
        }
        return new Request.Builder().url(url).post(bodyBuilder.build()).build();
    }
}
