package wava.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 使用选择器的 NIO 服务端 
 * @author Soosky
 *
 */
public class NIOSelectorServer {
	
	/**
	 * 唯一单例
	 */
	private static NIOSelectorServer nioSelectorServer = new NIOSelectorServer();
	
	/**
	 * 缓冲区
	 */
	private ByteBuffer buff = ByteBuffer.allocate(1024);

	/**
	 * 选择器
	 */
	private Selector selector;
	
	/**
	 * 服务端通道 
	 */
	private ServerSocketChannel serverSocketChannel;
	
	private int keys = 0;
	
	/**
	 * 构造函数
	 */
	private NIOSelectorServer() {
		try {
			// 初始化
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 监听事件
	 * @throws IOException 
	 */
	public void listen() throws IOException {
		System.out.println("服务器启动，开始监听事件");
		while (true) {
			// 选择器至少选择一个通道（通道相应的I/O已经就绪），返回就绪通道到个数。改方法为阻塞方法
			keys = selector.select();
			// 获取就绪的通道键集
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			if (keys > 0) {
				while (iterator.hasNext()) {
					SelectionKey selectionKey = iterator.next();
					iterator.remove();
					
					if (selectionKey.isAcceptable()) { // 如果客户端请求连接
						// 服务端通道
						serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
						// 客户端通道
						SocketChannel socketChannel = serverSocketChannel.accept();
						// 设置客户端通道为非阻塞模式
						socketChannel.configureBlocking(false);
						
						// 给客户端发送消息
						String msg = "Connected";
						socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
						// 还需要读取客户端发送过来的数据，所以需要注册一个读数据操作
						socketChannel.register(selector, SelectionKey.OP_READ);
					} else if (selectionKey.isReadable()) { // 如果可以读
						SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
						buff.clear();
						int len = socketChannel.read(buff);
						System.out.println("服务器读读取到客户消息长度：len = " + len);
						String msg = new String(buff.array(), 0, len);
						System.out.println("服务端读取到客户端发过来的消息：" + msg);
					}
					
				}
			}
			
			System.out.println("没有可用的键集");
		}
	}
	
	/**
	 * 初始化
	 * @throws IOException
	 */
	private void init() throws IOException {
		// 打开选择器
		this.selector = Selector.open();
		// 打开服务端通道
		this.serverSocketChannel = ServerSocketChannel.open();
		// 给 socket 绑定 IP 和 端口
		this.serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 1024));
		// 配置通道为非阻塞模式
		this.serverSocketChannel.configureBlocking(false);
		// 注册服务端通道到选择器，并监听有客户连接时触发
		this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
	}
	
	public static NIOSelectorServer getNioSelectorServer() {
		return nioSelectorServer;
	}

	/**
	 * 入口
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			NIOSelectorServer.getNioSelectorServer().listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
