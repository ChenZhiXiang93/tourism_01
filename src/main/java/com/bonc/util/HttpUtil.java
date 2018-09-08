package com.bonc.util;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//Http请求的工具类  
public class HttpUtil {

	private static final int TIMEOUT_IN_MILLIONS = 20000;

	public interface CallBack {
		void onRequestComplete(String result);
	}

	/**
	 * 异步的Get请求
	 * 
	 * @param urlStr
	 * @param callBack
	 */
	public static void doGetAsyn(final String urlStr, final CallBack callBack) {
		new Thread() {
			public void run() {
				try {
					String result = doGet(urlStr);
					if (callBack != null) {
						callBack.onRequestComplete(result);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			};
		}.start();
	}

	/**
	 * 异步的Post请求
	 * 
	 * @param urlStr
	 * @param params
	 * @param callBack
	 * @throws Exception
	 */
	public static void doPostAsyn(final String urlStr, final String params, final CallBack callBack) throws Exception {
		new Thread() {
			public void run() {
				try {
					String result = doPost(urlStr, params);
					if (callBack != null) {
						callBack.onRequestComplete(result);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			};
		}.start();

	}

	/**
	 * Get请求，获得返回数据
	 * 
	 * @param urlStr
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String urlStr) {
		URL url = null;
		HttpURLConnection conn = null;
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		try {
			url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
			conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			if (conn.getResponseCode() == 200) {
				is = conn.getInputStream();
				baos = new ByteArrayOutputStream();
				int len = -1;
				byte[] buf = new byte[128];

				while ((len = is.read(buf)) != -1) {
					baos.write(buf, 0, len);
				}
				baos.flush();
				return baos.toString();
			} else {
				throw new RuntimeException(" responseCode is not 200 ... ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null){
					is.close();
				}
			} catch (IOException e) {
			}
			try {
				if (baos != null){
					baos.close();
				}
			} catch (IOException e) {
			}
			conn.disconnect();
		}

		return null;

	}

	public static String doPost(String url, String param) {
		Long startTime = System.currentTimeMillis();

		HttpClient httpclient = HttpClientBuilder.create().build();
		// http://192.168.1.252:8081/floating/msisdn/getAllList2
		HttpPost httpost = new HttpPost(url);
		String result = "";
		InputStream in = null;
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		// 设置参数
		if (!param.isEmpty()) {
			String[] parStr = param.split("&");
			for (int i = 0; i < parStr.length; i++) {
				if (parStr[i].split("=").length > 1) {
					nvps.add(new BasicNameValuePair(parStr[i].split("=")[0], parStr[i].split("=")[1]));
				} else {
					nvps.add(new BasicNameValuePair(parStr[i].split("=")[0], ""));
				}
			}
		}
		/*
		 * nvps.add(new BasicNameValuePair("IDToken1", "username"));
		 * nvps.add(new BasicNameValuePair("IDToken2", "password"));
		 */
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000)
				.setConnectionRequestTimeout(50000).build();
		httpost.setProtocolVersion(HttpVersion.HTTP_1_0);
		httpost.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
		httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
		httpost.setConfig(requestConfig);
		HttpResponse response;
		try {
			response = httpclient.execute(httpost);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				in = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in, Consts.UTF_8));
				String line = null;
				while ((line = reader.readLine()) != null) {
					result += line;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 * @throws Exception
	 */
	public static String doPost1(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("charset", "utf-8");
			conn.setUseCaches(false);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
			conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);

			if (param != null && !param.trim().equals("")) {
				// 获取URLConnection对象对应的输出流
				out = new PrintWriter(conn.getOutputStream());
				// 发送请求参数
				out.print(param);
				// flush输出流的缓冲
				out.flush();
			}
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	
	public static String doPostT(String sendurl, String data) {  
		  
        CloseableHttpClient client = HttpClients.createDefault();  
        HttpPost post = new HttpPost(sendurl);  
        StringEntity myEntity = new StringEntity(data,ContentType.APPLICATION_JSON);// 构造请求数据  
        post.setEntity(myEntity);// 设置请求体  
         String responseContent = null; // 响应内容  
        CloseableHttpResponse response = null;  
         try {  
             response = client.execute(post);  
             if (response.getStatusLine().getStatusCode() == 200) {  
                 HttpEntity entity = response.getEntity();  
                 responseContent = EntityUtils.toString(entity, "UTF-8");  
             }  
         } catch (ClientProtocolException e) {  
             e.printStackTrace();  
         } catch (IOException e) {  
             e.printStackTrace();  
         } finally {  
             try {  
                 if (response != null) {
					 response.close();
				 }
             } catch (IOException e) {  
                 e.printStackTrace();  
             } finally {  
                 try {  
                     if (client != null) {
						 client.close();
					 }
                 } catch (IOException e) {  
                     e.printStackTrace();  
                 }  
           }  
         }  
         return responseContent;  
     }  
}