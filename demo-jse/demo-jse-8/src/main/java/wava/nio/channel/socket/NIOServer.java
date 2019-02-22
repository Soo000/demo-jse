package wava.nio.channel.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 
 * @author Soosky
 *
 */
public class NIOServer {

	private ByteBuffer buff = ByteBuffer.allocate(1024);
	private IntBuffer intBuff = buff.asIntBuffer();
	private SocketChannel clientSocketChannel = null;
	private ServerSocketChannel serverSocketChannel = null;
	
	/**
	 * 打开通道
	 * @throws IOException
	 */
	public void openChannel() throws IOException {
		// ��һ�� ServerSocketChannel ͨ��
		serverSocketChannel = ServerSocketChannel.open();
		// �趨�˿�
		//serverSocketChannel.socket().bind(new InetSocketAddress(1031));
		serverSocketChannel.bind(new InetSocketAddress(1031));
		serverSocketChannel.configureBlocking(false);
		System.out.println("����ͨ���Ѿ���");
	}
	
	/**
	 * 等待请求并处理
	 * @throws IOException 
	 */
	public void waitReqConn() throws IOException {
		while (true) {
			clientSocketChannel = serverSocketChannel.accept();
			if (clientSocketChannel != null) {
				System.out.println("�µ����Ӽ���");
				// 处理请求
				processReq();
				// 关闭通道
				clientSocketChannel.close();
			}
		}
	}

	/**
	 * 启动服务
	 */
	public void start() throws IOException {
		// 打开通道
		openChannel();
		// 等待请求并连接
		waitReqConn();
		
		clientSocketChannel.close();
		
		System.out.println("服务启动");
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	private void processReq() throws IOException {
		System.out.println("��ʼ��ȡ�ʹ���ͻ�������");
		buff.clear();
		clientSocketChannel.read(buff);
		int result = intBuff.get(0) + intBuff.get(1);
		buff.flip();
		buff.clear();
		// �޸���ͼ��ԭ��Ļ�����Ҳ��仯
		intBuff.put(0, result);
		// ���ͻ��˷��ʹ���������
		clientSocketChannel.write(buff);
		System.out.println("��ʼ��ȡ�ʹ���ͻ����������");
	}
	
	public static void main(String[] args) {
		try {
			new NIOServer().start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
