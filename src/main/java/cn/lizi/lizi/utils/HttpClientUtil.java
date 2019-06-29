package cn.lizi.lizi.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Slf4j
public class HttpClientUtil {

    private BasicCookieStore basicCookieStore;
    private CloseableHttpClient httpClient;

    public HttpClientUtil() {
        this.basicCookieStore = new BasicCookieStore();
        this.httpClient = HttpClients.custom()
                .setDefaultCookieStore(basicCookieStore)
                .build();
    }

    /***
     *  GET 请求 用于直接请求接口 返回JSON数据
     * @param url
     * @param headers
     * @param cookies
     * @return
     */
    public  String doGet(String url, Map<String,String> headers, Map<String,String> cookies){

        HttpGet httpGet = new HttpGet(url);

        try{
            if(headers != null){
                for (String header: headers.keySet()) {
                    httpGet.setHeader(header,headers.get(header));
                }
            }
            if(cookies != null){
                for (String cookieKey: cookies.keySet()) {
                    BasicClientCookie cookie = new BasicClientCookie(cookieKey, cookies.get(cookieKey));
                    //cookie.setVersion(0);        //设置版本
                    //cookie.setDomain("/pms/");   //设置范围
                    //cookie.setPath("/");         //设置路径
                    basicCookieStore.addCookie(cookie);
                }
            }

            CloseableHttpResponse execute = httpClient.execute(httpGet);

            return  EntityUtils.toString(execute.getEntity(), "UTF-8").trim();

        }catch (IOException io){
            log.info("get请求错误:" + io.toString() );
        }
        return null;
    }

    /***
     *  POST 请求 用于直接请求接口 返回JSON数据
     * @param url
     * @param parameters
     * @param headers
     * @param cookies
     * @return
     */
    public  String doPost(String url,Map<String,String> parameters, Map<String,String> headers, Map<String,String> cookies){

        HttpPost httpPost = new HttpPost(url);

        try{
            //设置请求参数
            if(parameters != null){

                List<NameValuePair> params = new ArrayList();
                for (String parameter: parameters.keySet()) {
                    params.add(new BasicNameValuePair(parameter, parameters.get(parameter)));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
            }

            //设置heder
            if(headers != null){
                for (String header: headers.keySet()) {
                    httpPost.setHeader(header,headers.get(header));
                }
            }

            //设置cookies
            if(cookies != null){
                for (String cookieKey: cookies.keySet()) {
                    BasicClientCookie cookie = new BasicClientCookie(cookieKey, cookies.get(cookieKey));
                    //cookie.setVersion(0);        //设置版本
                    //cookie.setDomain("/pms/");   //设置范围
                    //cookie.setPath("/");         //设置路径
                    basicCookieStore.addCookie(cookie);
                }
            }
            CloseableHttpResponse execute = httpClient.execute(httpPost);

            return  EntityUtils.toString(execute.getEntity(), "UTF-8").trim();

        }catch (Exception e){
            log.info("post请求失败:" + e.toString());
        }
        return null;
    }


    /***
     *  get请求用于解析HTML
     * @param url
     * @param headers
     * @param cookies
     * @return
     */
    public Document getDocument (String url,Map<String,String> headers, Map<String,String> cookies){

        HttpGet httpGet = new HttpGet(url);
        //设置代理IP，设置连接超时时间 、 设置 请求读取数据的超时时间 、 设置从connect Manager获取Connection超时时间、
        HttpHost proxy = new HttpHost("182.61.200.100", 80);
        RequestConfig requestConfig = RequestConfig.custom()
                .setProxy(proxy)
                .setConnectTimeout(10000)
                .setSocketTimeout(10000)
                .setConnectionRequestTimeout(3000)
                .build();
        httpGet.setConfig(requestConfig);
        try{
            if(headers != null){
                for (String header: headers.keySet()) {
                    httpGet.setHeader(header,headers.get(header));
                }
            }
            if(cookies != null){
                for (String cookieKey: cookies.keySet()) {
                    BasicClientCookie cookie = new BasicClientCookie(cookieKey, cookies.get(cookieKey));
                    //cookie.setVersion(0);        //设置版本
                    //cookie.setDomain("/pms/");   //设置范围
                    //cookie.setPath("/");         //设置路径
                    basicCookieStore.addCookie(cookie);
                }
            }

            CloseableHttpResponse execute = httpClient.execute(httpGet);
            String contents = EntityUtils.toString(execute.getEntity(),"gbk");

            return  Jsoup.parse(contents);

        }catch (IOException io){
            log.info("get请求错误:" + io.toString() );
            io.printStackTrace();
        }
        return null;
    }

    /***
     *  post请求用于解析HTMl
     * @param url
     * @param parameters
     * @param headers
     * @param cookies
     * @return
     */
    public  Document postDocument(String url,Map<String,String> parameters, Map<String,String> headers, Map<String,String> cookies){

        HttpPost httpPost = new HttpPost(url);

        try{
            //设置请求参数
            if(parameters != null){

                List<NameValuePair> params = new ArrayList();
                for (String parameter: parameters.keySet()) {
                    params.add(new BasicNameValuePair(parameter, parameters.get(parameter)));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
            }

            //设置heder
            if(headers != null){
                for (String header: headers.keySet()) {
                    httpPost.setHeader(header,headers.get(header));
                }
            }

            //设置cookies
            if(cookies != null){
                for (String cookieKey: cookies.keySet()) {
                    BasicClientCookie cookie = new BasicClientCookie(cookieKey, cookies.get(cookieKey));
                    //cookie.setVersion(0);        //设置版本
                    //cookie.setDomain("/pms/");   //设置范围
                    //cookie.setPath("/");         //设置路径
                    basicCookieStore.addCookie(cookie);
                }
            }
            CloseableHttpResponse execute = httpClient.execute(httpPost);
            String contents = EntityUtils.toString(execute.getEntity(),"gbk");

            return  Jsoup.parse(contents);

        }catch (Exception e){
            log.info("post请求失败:" + e.toString());
        }
        return null;
    }


    /**
     * @从制定URL下载文件并保存到指定目录
     * @param filePath 文件将要保存的目录
     * @param method 请求方法，包括POST和GET
     * @param url 请求的路径
     * @return
     */

    public File saveUrlAs(String url, String filePath, String fileName, String method){
        //System.out.println("fileName---->"+filePath);
        //创建不同的文件夹目录
        File file=new File(filePath);
        //判断文件夹是否存在
        if (!file.exists())
        {
            //如果文件夹不存在，则创建新的的文件夹
            file.mkdirs();
        }
        FileOutputStream fileOut = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try
        {
            // 建立链接
            URL httpUrl=new URL(url);
            conn=(HttpURLConnection) httpUrl.openConnection();
            //以Post方式提交表单，默认get方式
            conn.setRequestMethod(method);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // post方式不能使用缓存
            conn.setUseCaches(false);
            //连接指定的资源
            conn.connect();
            //获取网络输入流
            inputStream=conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            //判断文件的保存路径后面是否以/结尾
            if (!filePath.endsWith("/")) {

                filePath += "/";

            }
            //写入到文件（注意文件保存路径的后面一定要加上文件的名称）
            fileOut = new FileOutputStream(filePath+fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fileOut);

            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            //保存文件
            while(length != -1)
            {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            bos.close();
            bis.close();
            conn.disconnect();
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("抛出异常！！");
        }

        return file;
    }

}