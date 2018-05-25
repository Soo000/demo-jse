package wava.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServerSocket {
	
	public static void main(String[] args) {
		/*ServerSocketThread serverSocket1024 = new ServerSocketThread("Thread-1024", 1024);
		serverSocket1024.start();
		
		ServerSocketThread serverSocket1025 = new ServerSocketThread("Thread-1025", 1025);
		serverSocket1025.start();*/
		
		for (int i = 1024; i < 65536; i++) {
			ServerSocketThread serverSocket = new ServerSocketThread("Thread-" + i, i);
			serverSocket.start();
		}
		
		System.out.println("MultiSocketServer end");
	}

}

class ServerSocketThread extends Thread {

	private int port;
	
	public ServerSocketThread(String name, int port) {
		super(name);
		this.port = port;
	}
	
	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("ServerSocket listen " + port);
			Socket socket = serverSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
