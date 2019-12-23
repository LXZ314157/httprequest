package cn.com.lxz.http.request.client;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * http请求客户端调测试用类
 */
public class HttpRequestTest {

    public static void main(String[]args){
        getRequestWithNoParam();//get请求 无参
        getRequestWithUrlParam();//get请求，参数格式：拼接url 含有header(token)
        getRequestWithUriParam();//get请求，参数格式：使用URI获得HttpGet
        postRequestWithNoParam();//POSt请求 无参
        postRequestWithUrlParam();//POST请求 参数格式：url拼接
//        postRequestWithJson();//post请求 json格式参数
    }


    /**
     * 1、GET请求，无参
     * @return
     */
    public static void getRequestWithNoParam(){
        String url = "http://localhost:8001/getRequestWithNoParam";
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try{
            httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(url);
            response = httpClient.execute(httpGet);
            System.out.println("响应状态码============："+response.getStatusLine().getStatusCode());
            System.out.println("响应内容==============："+EntityUtils.toString(response.getEntity(),StandardCharsets.UTF_8));//方式响应内容乱码
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //关闭连接资源
            try{
                if(httpClient!=null){
                    httpClient.close();
                }
                if(response!=null){
                    response.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 2、GET请求，参数格式：拼接url
     * @return
     */
    public static void getRequestWithUrlParam(){
        String url = "http://localhost:8001/getRequestWithUrlParam";
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try{
            httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(url+"?"+getUrlParam());
            httpGet.addHeader("token", UUID.randomUUID().toString().toLowerCase().replace("-",""));
            httpGet.setConfig(getRequestConfig());
            response = httpClient.execute(httpGet);
            System.out.println("响应状态码============："+response.getStatusLine().getStatusCode());
            System.out.println("响应内容==============："+EntityUtils.toString(response.getEntity(),StandardCharsets.UTF_8));//方式响应内容乱码
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //关闭连接资源
            try{
                if(httpClient!=null){
                    httpClient.close();
                }
                if(response!=null){
                    response.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    /**
     * 3、GET请求，参数格式：使用URI获得HttpGet
     * @return
     */
    public static void getRequestWithUriParam(){
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        URI uri = null;
        try{
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("name", "杰克"));
            params.add(new BasicNameValuePair("age", "18"));
            uri = new URIBuilder().setScheme("http").setHost("localhost")
                    .setPort(8001).setPath("/getRequestWithUriParam")
                    .setParameters(params).build();
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setConfig(getRequestConfig());
            response = httpClient.execute(httpGet);
            System.out.println("响应状态码============："+response.getStatusLine().getStatusCode());
            System.out.println("响应内容==============："+EntityUtils.toString(response.getEntity(),StandardCharsets.UTF_8));//方式响应内容乱码
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 4、POSt请求 无参
     */
    public static void postRequestWithNoParam(){
        String url = "http://localhost:8001/postRequestWithNoParam";
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try{
            httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);
            response = httpClient.execute(httpPost);
            System.out.println("响应状态码============："+response.getStatusLine().getStatusCode());
            System.out.println("响应内容==============："+EntityUtils.toString(response.getEntity(),StandardCharsets.UTF_8));//方式响应内容乱码
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //关闭连接资源
            try{
                if(httpClient!=null){
                    httpClient.close();
                }
                if(response!=null){
                    response.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 5、POST请求 参数格式：url拼接
     */
    public static void postRequestWithUrlParam(){
        String url = "http://localhost:8001/postRequestWithUrlParam";
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try{
            httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url+"?"+getUrlParam());
            httpPost.addHeader("token", UUID.randomUUID().toString().toLowerCase().replace("-",""));
            httpPost.setConfig(getRequestConfig());
            response = httpClient.execute(httpPost);
            System.out.println("响应状态码============："+response.getStatusLine().getStatusCode());
            System.out.println("响应内容==============："+EntityUtils.toString(response.getEntity(),StandardCharsets.UTF_8));//方式响应内容乱码
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //关闭连接资源
            try{
                if(httpClient!=null){
                    httpClient.close();
                }
                if(response!=null){
                    response.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 6、POST请求 参数格式：json
     */
    public static void postRequestWithJson(){
        String jsonbody = getJSonParam();
        String url = "http://localhost:8001/postRequestWithJson";
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);
            StringEntity stringEntity = new StringEntity(jsonbody,"UTF-8");//设置参数的编码格式 UTF-8
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Content-Type", "application/json");//设置参数的格式类型 json
            response = httpClient.execute(httpPost);
            System.out.println("响应状态码============："+response.getStatusLine().getStatusCode());
            System.out.println("响应内容==============："+EntityUtils.toString(response.getEntity(),StandardCharsets.UTF_8));//方式响应内容乱码
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭连接资源
            try{
                if(httpClient!=null){
                    httpClient.close();
                }
                if(response!=null){
                    response.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }



    public static String getJSonParam(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","杰克");
        jsonObject.put("age","20");
        return jsonObject.toString();
    }

    public static String getUrlParam(){
        StringBuffer sb = new StringBuffer();
        try {
            sb.append("name="+URLEncoder.encode("杰克","utf-8"));
            sb.append("&");
            sb.append("age=20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static RequestConfig getRequestConfig(){
        RequestConfig requestConfig = RequestConfig.custom()//配置信息
                .setConnectTimeout(2000)//设置连接超时时间（单位毫秒）
                .setConnectionRequestTimeout(2000)//设置请求超时时间（单位毫秒）
                .setSocketTimeout(2000)//socket读写时间（单位毫秒）
                .setRedirectsEnabled(true)//设置是否允许重定向(默认为true)
                .build();
        return requestConfig;
    }

}
