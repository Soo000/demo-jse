package wava.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 
 * @author Soosky
 *
 */
public class NIOSelectorClient {

	/**
	 * 选择器
	 */
	private Selector selector;
	
	/**
	 * 输入缓冲区
	 */
	private ByteBuffer inBuff = ByteBuffer.allocate(1024);
	
	/**
	 * 客户端通道
	 */
	private SocketChannel socketChannel;
	
	int keys = 0;
	
	/**
	 * 构造函数
	 */
	public NIOSelectorClient() {
		try {
			// 初始化
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 进行监听
	 * @throws IOException 
	 */
	public void listen() throws IOException {
		while (true) {
			// 从选择器上选择就绪的通道，返回就绪通道的个数。改方法会阻塞 
			keys = selector.select();
			if (keys > 0) {
				// 获取选择器上所有就绪的键集
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while (iterator.hasNext()) {
					SelectionKey selectionKey = iterator.next();
					if (selectionKey.isConnectable()) { // 如果是可以连接的
						// 获取与服务器相同的通道
						SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
						// 如果正在连接
						if (socketChannel.isConnectionPending()) {
							// 如果正在连接，就设置为连接完成
							socketChannel.finishConnect();
							System.out.println("客户端连接完成");
						}
						
						// 注册一个写事件
						//socketChannel.register(selector, SelectionKey.OP_WRITE);
						socketChannel.register(selector, SelectionKey.OP_READ);
					} else if (selectionKey.isReadable()) { // 如果在通道上是可以读的
						// 获取与服务器相同的通道
						SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
						inBuff.clear();
						int len = socketChannel.read(inBuff);
						String msg = new String(inBuff.array(), 0, len);
						System.out.println("客户端读取到服务端数据：" + msg);
						// 注册一个读事件
						socketChannel.register(selector, SelectionKey.OP_WRITE);
					} else if (selectionKey.isWritable()) { // 如果在通道上是可以写的
						// 获取与服务器相同的通道
						SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
						
						String msg = "Hello";
						socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
						
						// 注册一个读事件
						socketChannel.register(selector, SelectionKey.OP_READ);
					}
				}
			}
		}
	}
	
	/**
	 * 初始化
	 * @throws IOException 
	 */
	private void init() throws IOException {
		// 打开选择器
		selector = Selector.open();
		// 打开客户端通道，此时没有连接
		socketChannel = SocketChannel.open();
		// 设置客户端通道为非阻塞模式
		socketChannel.configureBlocking(false);
		// 连接服务器
		socketChannel.connect(new InetSocketAddress("127.0.0.1", 1024));
		// 向选择器注册一个连接事件
		socketChannel.register(selector, SelectionKey.OP_CONNECT);
	}
	
	/**
	 * 入口
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new NIOSelectorClient().listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
