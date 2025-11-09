package iostream;
// 조금 녹화를 늦게 시작했는데 지금 상황이 파일 입출력에서 텍스트파일 FileReader로 읽고 그걸 받아서 다시 FileWriter로 내보내려는 상황

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test02 {
	public static void main(String[] args) {
		String filePath = "C:\\HUR\\resource\\";
		String fileName = "text.txt";
		File file = new File(filePath + fileName);
		
		System.out.println("파일: " + file);
		FileReader fr = null;
		try {
			 fr = new FileReader(file);
			System.out.println(" --- 파일 내용 --- ");
			
			char[] cbuf = new char[10];
			
			while(true) {
				int count = fr.read(cbuf, 0, 10);
				if(count <= 0) {
					break;
				}
				for(int i=0;i<count;i++) {
					System.out.print(cbuf[i]);
				}
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}	
