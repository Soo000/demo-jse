package wava.nio.channel.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {

	private ByteBuffer buff = ByteBuffer.allocate(8);
	private IntBuffer intBuff = buff.asIntBuffer();
	
	private SocketChannel clientSocketChannel = null;
	
	/**
	 * ���ӷ�����
	 * @throws IOException
	 */
	public SocketChannel connect() throws IOException {
		return SocketChannel.open(new InetSocketAddress("127.0.0.1", 1031));
	}
	
	public int getSum(int a, int b) throws IOException {
		int result = 0;
		
		clientSocketChannel = connect();
		
		sendRequest(a, b);
		
		result = receiveResult();
		return result;
	}
	
	/**
	 * ��������
	 * @param a
	 * @param b
	 * @throws IOException 
	 */
	private void sendRequest(int a, int b) throws IOException {
		buff.clear();
		intBuff.put(0, a);
		intBuff.put(1, b);
		clientSocketChannel.write(buff);
		System.out.println("�������� a + b = " + a + " + " + b);
	}
	
	/**
	 * ���շ�������������
	 * @return
	 * @throws IOException 
	 */
	private int receiveResult() throws IOException {
		buff.clear();
		clientSocketChannel.read(buff);
		return intBuff.get(0);
	}

	public static void main(String[] args) {
		try {
			int result = new NIOClient().getSum(56, 34);
			System.out.println("������ result = " + result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
