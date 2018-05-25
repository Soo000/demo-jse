package wava.net.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

public class DemoURL {

	public static void main(String[] args) {
		try {
			// URL 类为统一资源定位符类，用于定位网络上的一个资源
			URL url = new URL("http://www.baidu.com");
			// 从该资源中获取输入流
			InputStream inputStream = url.openStream();
			// 将输入流转化成字符流
			Reader reader = new InputStreamReader(inputStream);
			// 创建字符输入流缓存流
			BufferedReader bufferedReader = new BufferedReader(reader);
			
			System.out.println("开始读取数据：");
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			System.out.println("结束读取数据");
			
			bufferedReader.close();
			reader.close();
			inputStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
