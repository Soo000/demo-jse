package wava.nio.channel.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件通道
 * @author Soosky
 */
public class DemoFileChannel {

	public static void copy(String sourceFile, String targetFile) {
		try {
			FileInputStream fis = new FileInputStream(sourceFile);
			FileOutputStream fos = new FileOutputStream(targetFile);
			
			FileChannel readChannel = fis.getChannel();
			FileChannel writeChannel = fos.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			while(true) {
				buffer.clear();
				int length = readChannel.read(buffer);
				if (length == -1) {
					break;
				}
				buffer.flip();
				writeChannel.write(buffer);
			}
			
			readChannel.close();
			writeChannel.close();
			
			fis.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String srcFile = "D:\\tmp\\icon.jpg";
		String tarFile = "C:\\Users\\soosk\\Desktop\\aaa.jpg";
		copy(srcFile, tarFile);
	}
	
}
