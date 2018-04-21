package wava.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO Server
 * @author Soosky
 *
 */
public class NIOServer {

	private int blockSize = 4096;
	
	/**
	 * 发送数据缓冲区
	 */
	private ByteBuffer sendBuffer = ByteBuffer.allocate(blockSize);
	
	/**
	 * 接收数据缓冲区
	 */
	private ByteBuffer recvBuffer = ByteBuffer.allocate(blockSize);
	
	/**
	 * 选择器
	 */
	private Selector selector;
	
	/**
	 * 构造函数
	 * @throws IOException 
	 */
	public NIOServer(int port) throws IOException {
		// 打开 ServerSocketChannel
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		// 配置是否阻塞
		serverSocketChannel.configureBlocking(false); 
		// 获取 ServerSocket
		ServerSocket serverSocket = serverSocketChannel.socket(); 
		// 绑定端口
		serverSocket.bind(new InetSocketAddress(port));
		// 打开选择器
		selector = Selector.open();
		// 将 serverSocketChannel 注册到选择器，并设置可以接收连接请求
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		System.out.println("NIO Server started on port " + port);
	}
	
	/**
	 * 监听
	 * @throws IOException 
	 */
	public void listen() throws IOException {
		while (true) {
			/*
			 * Selects a set of keys whose corresponding channels are ready for I/O operations.
			 * This method performs a blocking selection operation.
			 */
			int readyCount = selector.select();
			System.out.println("readyCount = " + readyCount);
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectionKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				iterator.remove();
				
				// 调用业务逻辑
				handleKey(selectionKey);
			}
		}
	}
	
	/**
	 * 业务逻辑
	 * @param selectionKey
	 * @throws IOException 
	 */
	public void handleKey(SelectionKey selectionKey) throws IOException {
		ServerSocketChannel serverSocketChannel = null;
		SocketChannel clientSocketChannel = null;
		String recvText;
		String sendText;
		int count = 0;
		
		if (selectionKey.isAcceptable()) { // 如果是一个可接收的事件
			System.out.println("接收到客户端连接请求");
			
			serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
			clientSocketChannel = serverSocketChannel.accept(); // 接收客户端请求
			System.out.println("客户端连接请求 clientSocketChannel = " + clientSocketChannel);
			clientSocketChannel.configureBlocking(false); // 设置客户端以非阻塞模式运行
			clientSocketChannel.register(selector, SelectionKey.OP_READ); // 客户注册到 selector
		} else if (selectionKey.isReadable()) { // 如果是一个读取客户端数据事件
			 // 获取客户端
			clientSocketChannel = (SocketChannel) selectionKey.channel();
			// 将客户端数据读到缓冲区
			count = clientSocketChannel.read(recvBuffer);
			if (count > 0) {
				recvText = new String(recvBuffer.array(), 0, count);
				System.out.println("服务端接收客户端的数据 recvText = " + recvText);
				
				// 客户端向 selector 注册写事件
				clientSocketChannel.register(selector, SelectionKey.OP_WRITE);
			}
		} else if (selectionKey.isWritable()) { // 如果是一个写事件
			// 首先清除缓冲区
			sendBuffer.clear();
			// 获取客户端
			clientSocketChannel = (SocketChannel) selectionKey.channel();
			// 要发送的数据
			sendText = "发送到客户端的数据 " + System.currentTimeMillis();
			// 将要发送的数据放到缓冲区中
			sendBuffer.put(sendText.getBytes());
			//
			sendBuffer.flip();
			// 向客户端发送缓冲区中的数据
			clientSocketChannel.write(sendBuffer);
			
			System.out.println("服务端发送数据给客户端 sendText = " + sendText);
			
			clientSocketChannel.register(selector, SelectionKey.OP_READ);
		}
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 1024;
		try {
			NIOServer nioServer = new NIOServer(port);
			nioServer.listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
