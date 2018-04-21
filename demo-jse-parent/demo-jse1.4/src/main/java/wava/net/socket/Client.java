package wava.net.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		try {
			// 1. 建立一个Socket对象
			Socket socket = new Socket("127.0.0.1", 1024);
			// 2. 获取输出流，用于写入数据
			OutputStream outputStream = socket.getOutputStream();
			// 3. 将输出流转化为字符输出流
			Writer writer = new PrintWriter(outputStream);
			// 4. 写入数据
			writer.write("你好，我是客户端数据。");
			writer.flush();
			writer.close();
			outputStream.close();
			
			// 客户端读取服务器端数据
			// 读取客户端的输入流
			InputStream inputStream = socket.getInputStream();
			// 将输入流转化为字符输入流
			Reader reader = new InputStreamReader(inputStream);
			// 创建字符输入流缓存流
			BufferedReader bufferedReader = new BufferedReader(reader);
			
			System.out.println("BEGIN 开始读取服务器端数据：");
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			System.out.println("END 结束读取服务器端数据");
			
			bufferedReader.close();
			reader.close();
			inputStream.close();
			
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
