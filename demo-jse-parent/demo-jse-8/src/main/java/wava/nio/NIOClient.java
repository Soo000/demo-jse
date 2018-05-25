package wava.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author Soosky
 *
 */
public class NIOClient {
	
	/**
	 * 缓冲区块大小
	 */
	private static int blockSize = 4096;
	
	/**
	 * 发送数据缓冲区
	 */
	private static ByteBuffer sendBuffer = ByteBuffer.allocate(blockSize);
	
	/**
	 * 接收数据缓冲区
	 */
	private static ByteBuffer recvBuffer = ByteBuffer.allocate(blockSize);
	
	/**
	 * 服务器地址
	 */
	private final static InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 1024);

	/**
	 * 构造函数
	 * @param port
	 * @throws IOException 
	 */
	public NIOClient() throws IOException {}
	
	public static void main(String[] args) throws IOException {
		// 获取 SocketChannel
		SocketChannel socketChannel = SocketChannel.open();
		// 设置是否阻塞
		socketChannel.configureBlocking(false);
		// 打开选择器
		Selector selector = Selector.open();
		// 向选择器注册连接事件
		socketChannel.register(selector, SelectionKey.OP_CONNECT);
		// 发起连接
		boolean isConnect = socketChannel.connect(serverAddress);
		System.out.println("isConnect = " + isConnect); 
		
		Set<SelectionKey> selectionKeys;
		Iterator<SelectionKey> iterator;
		SelectionKey selectionKey;
		SocketChannel client;
		String recvText;
		String sendText;
		int count;
		
		while (true) {
			selector.select();
			selectionKeys = selector.selectedKeys();
			iterator = selectionKeys.iterator();
			while (iterator.hasNext()) {
				selectionKey = iterator.next();
				if (selectionKey.isConnectable()) { // 如果可以连接
					System.out.println("客户端发起连接");
					// 获取客户端连接
					client = (SocketChannel) selectionKey.channel();
					if (client.isConnectionPending()) {
						client.finishConnect();
						System.out.println("完成连接操作");
						
						sendBuffer.clear();
						sendBuffer.put("Hello Server".getBytes());
						sendBuffer.flip();
						// 客户端发送缓冲区数据
						client.write(sendBuffer);
					}
					// 客户端注册读事件
					client.register(selector, SelectionKey.OP_READ);
				} else if (selectionKey.isReadable()) { // 如果是可读的
					client = (SocketChannel) selectionKey.channel();
					recvBuffer.clear();
					count = client.read(recvBuffer);
					if (count > 0) {
						recvText = new String(recvBuffer.array(), 0, count);
						System.out.println("客户端接收到服务器端的数据 recvText = " + recvText);
						// 注册写事件
						client.register(selector, SelectionKey.OP_WRITE);
					}
				} else if (selectionKey.isWritable()) { // 如果是写事件
					sendBuffer.clear();
					
					client = (SocketChannel) selectionKey.channel();
					sendText = "客户端发送给服务器端的数据 " + System.currentTimeMillis();
					sendBuffer.put(sendText.getBytes());
					// ?
					sendBuffer.flip();
					// 发送数据
					client.write(sendBuffer);
					
					System.out.println("客户端发送数据给服务端完成 sendText = " + sendText);
				}
			}
			
			selectionKeys.clear();
		}
	}
}
