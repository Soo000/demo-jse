package wava.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {

	//private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, maximumPoolSize, keepAliveTime, unit, workQueue);
	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(1024);
			System.out.println("BIO Server started at 1024");
			
			while (true) {
				final Socket socket = serverSocket.accept();
				System.out.print("新客户端 " + socket + "建立了连接");
				new Thread(new Runnable() {
					
					private Socket mySocket = socket;
					
					@Override
					public void run() {
						System.out.println("IP: " + mySocket.getInetAddress().getHostAddress() + " 端口：" + mySocket.getPort());
					}
				}).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("服务器停止");
	}
	
}
