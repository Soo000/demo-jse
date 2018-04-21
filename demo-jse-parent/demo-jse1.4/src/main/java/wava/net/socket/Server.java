package wava.net.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) {
		try {
			// 1. 构建一个 ServerSocket 对象
			ServerSocket serverSocket = new ServerSocket(1024);
			// 2. 调用 accept方法进行端口监听，将会引起程序阻塞
			System.out.println("服务起端启动，监听1024端口");
			Socket socket = serverSocket.accept();
			// 3. 读取客户端的输入流
			InputStream inputStream = socket.getInputStream();
			// 4. 将输入流转化为字符输入流
			Reader reader = new InputStreamReader(inputStream);
			// 5. 创建字符输入流缓存流
			BufferedReader bufferedReader = new BufferedReader(reader);
			
			System.out.println("BEGIN 服务起端开始接收数据：");
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			System.out.println("END 服务起结束接收数据");
			
			bufferedReader.close();
			reader.close();
			inputStream.close();
			
			// 服务器端向客户端写数据
			// 获取输出流，用于写入数据
			OutputStream outputStream = socket.getOutputStream();
			// 将输出流转化为字符输出流
			Writer writer = new PrintWriter(outputStream);
			// 写入数据
			writer.write("你好，我是服务器端数据。");
			
			writer.flush();
			writer.close();
			outputStream.close();
			
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
