package com.gkh.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class HttpClientUtil {

	/**
	 * get请求
	 * @param url
	 * @param param
	 * @return
	 */
	public static String get(String url, String param) {
		HttpClient client = new HttpClient();
		if(!StringUtils.isEmpty(param)){
			url = url + "?" + param;
		}
		GetMethod method = new GetMethod(url);
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader br = null;
		try {
			client.executeMethod(method);
			br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			method.releaseConnection();
		}
		return stringBuffer.toString();
	}
	
	/**
	 * post请求
	 *
	 * @param url
	 * @param param
	 * @return
	 */
	public static String post(String url, List<NameValuePair> param) {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		NameValuePair[] parametersBody = param.toArray(new NameValuePair[param.size()]);
		method.setRequestBody(parametersBody);
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader br = null;
		try {
			client.executeMethod(method);
			br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			method.releaseConnection();
		}
		return stringBuffer.toString();
	}

	/**
	 * 发送json
	 * 
	 * @return
	 */
	public static String postJson(String url, String json) {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader br = null;
		try {
			RequestEntity requestEntity = new StringRequestEntity(json, "text/xml", "UTF-8");
			method.setRequestEntity(requestEntity);
			method.releaseConnection();
			client.executeMethod(method);
			br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			method.releaseConnection();
		}
		return stringBuffer.toString();
	}
	
	/**
	 * 发送xml
	 * 
	 * @return
	 */
	public static String postXml(String url, String xml) {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader br = null;
		try {
			RequestEntity requestEntity = new StringRequestEntity(xml, "text/xml", "UTF-8");
			method.setRequestEntity(requestEntity);
			method.releaseConnection();
			client.executeMethod(method);
			br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			method.releaseConnection();
		}
		return stringBuffer.toString();
	}
}
