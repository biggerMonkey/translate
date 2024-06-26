package pers.biggermonkey.translate.common;

import com.google.gson.Gson;
import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author: huangwenjun16
 * @date: 2023/9/8 09:49
 * @description:
 */
public class HttpUtils {
    private static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    private static RequestConfig requestConfig;
    private static CloseableHttpClient client = HttpClientBuilder.create()
            .setConnectionManager(cm)
            .setDefaultRequestConfig(requestConfig)
            .build();

    static {
        //连接池最大生成连接数200
        cm.setMaxTotal(200);
        // 默认设置route最大连接数为20
        cm.setDefaultMaxPerRoute(20);
        requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(5)
                .setSocketTimeout(5)
                .setConnectTimeout(5)
                .build();
    }

    public static CloseableHttpClient getInstance() {
        return client;
    }

    public static String get(String url, Map<String, String> header, Map<String, Object> param) {

        //获得http客户端
        CloseableHttpClient client = getInstance();

        //参数
        StringBuilder params = new StringBuilder();
        if (MapUtils.isNotEmpty(param)) {
            for (Map.Entry<String, Object> paramEntry : param.entrySet()) {
                params.append(paramEntry.getKey()).append("=").append(encode(paramEntry.getValue())).append("&");
            }
        }
        //创建get请求
        HttpGet httpGet = new HttpGet(url + "?" + params);
        if (MapUtils.isNotEmpty(header)) {
            for (Map.Entry<String, String> headerEntry : header.entrySet()) {
                httpGet.setHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }
        //响应模型
        CloseableHttpResponse response = null;
        try {
            //有客户端指定get请求
            response = client.execute(httpGet);
            //从响应模型中获取响应体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                return EntityUtils.toString(responseEntity);
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //释放资源
//                if (client != null) {
//                    client.close();
//                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String encode(Object input) {
        if (input == null) {
            return "";
        }
        String inputStr = String.valueOf(input);
        try {
            return URLEncoder.encode(inputStr, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return inputStr;
    }

    public String post(String url) {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", ContentType.APPLICATION_JSON.toString());
        return postBody(url, header, null);
    }

    public static String postForm(String url, Map<String, String> header, Map<String, String> body) {
        //获取Http客户端
        CloseableHttpClient client = getInstance();
        //创建Post请求
        HttpPost httpPost = new HttpPost(url);
        if (body == null) {
            body = new HashMap<>();
        }
        List<NameValuePair> parameters = new ArrayList<>();
        for (Map.Entry<String, String> paramEntry : body.entrySet()) {
            parameters.add(new BasicNameValuePair(paramEntry.getKey(), paramEntry.getValue()));
        }
        CloseableHttpResponse response = null;
        try {
            HttpEntity httpEntity = new UrlEncodedFormEntity(parameters, "utf-8");
            httpPost.setEntity(httpEntity);
            if (MapUtils.isNotEmpty(header)) {
                for (Map.Entry<String, String> headerEntry : header.entrySet()) {
                    httpPost.setHeader(headerEntry.getKey(), headerEntry.getValue());
                }
            }
            response = client.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity == null) {
                return null;
            }
            return EntityUtils.toString(responseEntity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
//                if (client != null) {
//                    client.close();
//                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    public static String postBody(String url, Map<String, String> header, Map<String, Object> body) {
        //获取Http客户端
        CloseableHttpClient client = getInstance();
        //创建Post请求
        HttpPost httpPost = new HttpPost(url);
        if (body == null) {
            body = new HashMap<>();
        }
        StringEntity stringEntity = new StringEntity(new Gson().toJson(body), "utf-8");
        httpPost.setEntity(stringEntity);
        if (MapUtils.isNotEmpty(header)) {
            for (Map.Entry<String, String> headerEntry : header.entrySet()) {
                httpPost.setHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            return EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
//                if (client != null) {
//                    client.close();
//                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String postBody(String url, Map<String, Object> body) {
        return postBody(url, null, body);
    }


    public static String post(String requestUrl, String accessToken, String params)
            throws Exception {
        String contentType = "application/x-www-form-urlencoded";
        return HttpUtils.post(requestUrl, accessToken, contentType, params);
    }

    public static String post(String requestUrl, String accessToken, String contentType, String params)
            throws Exception {
        String encoding = "UTF-8";
        if (requestUrl.contains("nlp")) {
            encoding = "GBK";
        }
        return HttpUtils.post(requestUrl, accessToken, contentType, params, encoding);
    }

    public static String post(String requestUrl, String accessToken, String contentType, String params, String encoding)
            throws Exception {
        String url = requestUrl + "?access_token=" + accessToken;
        return HttpUtils.postGeneralUrl(url, contentType, params, encoding);
    }

    public static String postJson(String requestUrl, Map<String, String> uriParams, Map<String, String> body)
            throws Exception {
        StringBuilder url = new StringBuilder(requestUrl + "?");
        for (Map.Entry<String, String> uriParam : uriParams.entrySet()) {
            url.append(uriParam.getKey()).append("=").append(uriParam.getValue()).append("&");
        }
        return HttpUtils.postGeneralUrl(url.toString(), "application/json", new Gson().toJson(body), "UTF-8");
    }


    public static String postGeneralUrl(String generalUrl, String contentType, String params, String encoding)
            throws Exception {
        URL url = new URL(generalUrl);
        // 打开和URL之间的连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        // 设置通用的请求属性
        connection.setRequestProperty("Content-Type", contentType);
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);

        // 得到请求的输出流对象
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.write(params.getBytes(encoding));
        out.flush();
        out.close();

        // 建立实际的连接
        connection.connect();
        // 获取所有响应头字段
        Map<String, List<String>> headers = connection.getHeaderFields();
        // 遍历所有的响应头字段
        for (String key : headers.keySet()) {
            System.err.println(key + "--->" + headers.get(key));
        }
        // 定义 BufferedReader输入流来读取URL的响应
        BufferedReader in = null;
        in = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), encoding));
        String result = "";
        String getLine;
        while ((getLine = in.readLine()) != null) {
            result += getLine;
        }
        in.close();
        System.err.println("result:" + result);
        return result;
    }
}
