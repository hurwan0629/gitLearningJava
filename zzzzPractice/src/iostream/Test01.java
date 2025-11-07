package iostream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test01 {
	public static void main(String[] args) {
		String filePath = "C:\\HUR\\resource\\";
		String fileName = "Gwen.jpg";
		
		File file = new File(filePath + fileName);
		
		System.out.println("파일 이름: " + file.getName());
		try {
			System.out.println("상대경로: "  + file.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("존재여부: " + file.exists());
		System.out.println("파일유무: " + file.isFile());
		System.out.println("폴더유무: " + file.isDirectory());
		
		try {
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(filePath + "Gwen 복사본.jpg");
			byte[] buffer = new byte[1024];
			while(true) {
				int count = fis.read(buffer);
				fos.write(buffer);
				
				if(count<1024) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
