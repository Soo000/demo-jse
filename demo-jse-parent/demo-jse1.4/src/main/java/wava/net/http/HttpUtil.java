package wava.net.http;

import java.util.Map;

/**
 * 发送 HTTP 请求接口
 * @author Ke.Wang
 * @version 1.0.1_20161110_10_beta
 */
public interface HttpUtil {

	public String sendGet(String url) throws HttpUtilException;
	
	public String sendGet(String url, String dataType) throws HttpUtilException;
	
	public String sendGet(String url, String dataType, String charset) throws HttpUtilException;
	
	public String sendGet(String url, Map<String, String> properties) throws HttpUtilException;
	
	public String sendPost(String uri, String postContent) throws HttpUtilException;
	
	public String sendPost(String uri, String postContent, String dataType) throws HttpUtilException;
	
	public String sendPost(String uri, String postContent, String dataType, String charset) throws HttpUtilException;
	
	public String sendPost(String uri, Map<String, String> properties, String postContent) throws HttpUtilException;
	
}
