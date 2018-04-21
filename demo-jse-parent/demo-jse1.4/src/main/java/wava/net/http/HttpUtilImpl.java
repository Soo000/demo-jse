package wava.net.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 发送 HTTP 请求接口
 * @author Ke.Wang
 * @version 1.0.1_20161110_10_beta
 */
public class HttpUtilImpl implements HttpUtil {
	
	private static final String DATA_TYPE_TEXT = "text";
	private static final String DATA_TYPE_JSON = "json";
	private static final String DATA_TYPE_XML = "xml";
	private static final String DATA_TYPE_HTML = "html";
	private static final Map<String, String> dataType = new HashMap<String, String>();
	
	static {
		dataType.put("text", "text");
		dataType.put("json", "json");
		dataType.put("xml", "xml");
		dataType.put("html", "html");
	}
	
	/**
	 * 发送 Get 请求
	 */
	@Override
	public String sendGet(String url) throws HttpUtilException {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("Content-Type", "application/json");
		properties.put("Charset", "UTF-8");
		properties.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		return sendGet(url, properties);
	}
	
	/**
	 * 发送 Get请求
	 */
	public String sendGet(String url, String dataType) throws HttpUtilException {
		return sendGet(url, dataType, "UTF-8");
	}
	
	/**
	 * 发送 Get请求
	 */
	public String sendGet(String url, String dataType, String charset) throws HttpUtilException {
		if (dataType == null) {
			throw new HttpUtilException("参数 dataType 不能为空！");
		}
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		
		// 设置 contentType
		dataType = dataType.toLowerCase();
		if (!dataType.contains(dataType)) {
			throw new HttpUtilException("参数 dataType 值不合法！");
		}
		
		String contentType = dataType;
		if (DATA_TYPE_TEXT.equalsIgnoreCase(dataType)) {
			contentType = "application/text";
		} else if (DATA_TYPE_JSON.equalsIgnoreCase(dataType)) {
			contentType = "application/json";
		} else if (DATA_TYPE_XML.equalsIgnoreCase(dataType)) {
			contentType = "text/xml";
		} else if (DATA_TYPE_HTML.equalsIgnoreCase(dataType)) {
			contentType = "text/html";
		}
		properties.put("Content-Type", contentType);
		properties.put("Charset", charset);
		
		return sendGet(url, properties);
	}

	/**
	 * 发送 Get 请求
	 */
	@Override
	public String sendGet(String uri, Map<String, String> properties) throws HttpUtilException {
		String result = "";

		if (uri == null || uri.length() <= 0) {
			throw new RuntimeException("请求地址 url 不能为空！");
		}
		
		try {
			// 1. 使用URL封装 url, 通过openConnection()获取 HttpURLConnection 对象
			URL url = new URL(uri);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			// 2. 设置请求方法, GET or POST, 需大写 
			httpURLConnection.setRequestMethod("GET");
			// 3. 设置输入或输出，以及其它
			httpURLConnection.setDoInput(true); // 读取http资源，需设置成true
			httpURLConnection.setDoOutput(false); // 上传资源，需设置成true
			httpURLConnection.setUseCaches(false); // 是否使用缓存
			// 4. 设置http请求头
			if (properties != null) {
				Iterator<String> keys = properties.keySet().iterator();
				while (keys.hasNext()) {
					String key = keys.next();
					String value = properties.get(key);
					httpURLConnection.setRequestProperty(key, value);
				}
			}
			
			httpURLConnection.setConnectTimeout(1000 * 30); // 连接主机超时时间
			httpURLConnection.setReadTimeout(1000 * 60); // 从主机读取数据超时时间
			
			// 5. 输入流
			InputStream inputStream = httpURLConnection.getInputStream();
			InputStreamReader inputStreamReaer = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReaer);
			String strLine = "";
			StringBuffer stringBuffer = new StringBuffer();
			while ( (strLine = bufferedReader.readLine()) != null) {
				stringBuffer.append(strLine);
			}
			result = stringBuffer.toString();
			// 6. 输出流
			//OutputStream outputStream = httpURLConnection.getOutputStream();
			// 7. 关闭流
			inputStream.close();
			//outputStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new HttpUtilException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new HttpUtilException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpUtilException(e.getMessage());
		}
		
		return result;
	}

	/**
	 * 发送 Post 请求
	 */
	@Override
	public String sendPost(String url, String postContent) throws RuntimeException {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("Charset", "UTF-8"); // 设置http头参数
		properties.put("Content-Type", "application/json"); // 设置http头参数
		
		return sendPost(url, properties, postContent);
	}
	
	/**
	 * 发送 Post 请求
	 */
	@Override
	public String sendPost(String url, String postContent, String dataType) throws HttpUtilException {
		return sendPost(url, postContent, dataType, "UTF-8");
	}
	
	/**
	 * 发送 Post 请求
	 */
	@Override
	public String sendPost(String url, String postContent, String dataType, String charset) throws HttpUtilException {
		if (dataType == null) {
			throw new HttpUtilException("参数 dataType 不能为空！");
		}
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		
		// 设置 contentType
		dataType = dataType.toLowerCase();
		if (!dataType.contains(dataType)) {
			throw new HttpUtilException("参数 dataType 值不合法！");
		}
		
		String contentType = dataType;
		if (DATA_TYPE_TEXT.equalsIgnoreCase(dataType)) {
			contentType = "application/text";
		} else if (DATA_TYPE_JSON.equalsIgnoreCase(dataType)) {
			contentType = "application/json";
		} else if (DATA_TYPE_XML.equalsIgnoreCase(dataType)) {
			contentType = "text/xml";
		} else if (DATA_TYPE_HTML.equalsIgnoreCase(dataType)) {
			contentType = "text/html";
		}
		properties.put("Content-Type", contentType);
		properties.put("Charset", charset);
		
		return sendPost(url, properties, postContent);
	}
	
	/**
	 * 发送 Post 请求
	 */	
	@Override
	public String sendPost(String url, Map<String, String> properties, String postContent) throws RuntimeException {
		String result = "";
		
		if (url == null || url.length() <= 0) {
			throw new RuntimeException("@@@ 请求地址 url 不能为空！");
		}
		
		HttpURLConnection httpURLConnection = null;
		PrintWriter printWriter = null;  
        BufferedReader bufferedReader = null;
        StringBuffer responseResult = new StringBuffer(); 
		try {
			// 使用URL封装 url, 通过openConnection()获取 HttpURLConnection 对象
			URL oUrl = new URL(url);
			httpURLConnection = (HttpURLConnection) oUrl.openConnection();
			// 设置请求方法, GET or POST, 需大写 
			httpURLConnection.setRequestMethod("POST");
			// 设置通用的请求属性  
			httpURLConnection.setRequestProperty("accept", "*/*");  
			httpURLConnection.setRequestProperty("connection", "Keep-Alive");  
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(postContent.length()));  
			// 设置输入或输出，以及其它
			httpURLConnection.setDoOutput(true); // 上传资源，需设置成true
			httpURLConnection.setDoInput(true); // 读取http资源，需设置成true
			httpURLConnection.setUseCaches(false); // 是否使用缓存
			//httpURLConnection.setConnectTimeout(5000); //连接超时 单位毫秒
			//httpURLConnection.setReadTimeout(5000); //读取超时 单位毫秒
			// 设置http请求头
			if (properties != null) {
				Iterator<String> keys = properties.keySet().iterator();
				while (keys.hasNext()) {
					String key = keys.next();
					String value = properties.get(key);
					httpURLConnection.setRequestProperty(key, value);
				}
			}
			
			// 获取URLConnection对象对应的输出流  
			printWriter = new PrintWriter(httpURLConnection.getOutputStream());  
            // 发送请求参数  
            printWriter.write(postContent.toString());
            // flush输出流的缓冲  
            printWriter.flush();  
            // 根据ResponseCode判断连接是否成功  
            int responseCode = httpURLConnection.getResponseCode();  
            if (responseCode != 200) {  
            	return "ERROR";
            }
            
            // 定义BufferedReader输入流来读取URL的ResponseData  
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));  
            String line;  
            while ((line = bufferedReader.readLine()) != null) {  
                responseResult.append(line);  
            } 

            result = responseResult.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {  
            httpURLConnection.disconnect();
            try {  
                if (printWriter != null) {  
                    printWriter.close();  
                }  
                if (bufferedReader != null) {  
                    bufferedReader.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }
		
		return result;
	}

}
