package wava.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class BIOClient {
	
	public static void main(String[] args) {
		try {
			for (int i = 1024; i < 1026; i++) {
				Socket socket = new Socket();
				socket.connect(new InetSocketAddress("127.0.0.1", i));
				System.out.println("客户端 " + socket + " 连接完成!");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
