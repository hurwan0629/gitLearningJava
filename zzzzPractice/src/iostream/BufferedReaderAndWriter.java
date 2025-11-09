package iostream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedReaderAndWriter {
	public static void main(String[] args) {
		String filePath = "C:\\HUR\\resource\\";
		String fileNameToRead = "text.txt";
		String fileNameToWrite = "text-복사본.txt";
		File fileToRead = new File(filePath + fileNameToRead);
		File fileToWrite = new File(filePath + fileNameToWrite);
		
		System.out.println("파일: " + fileToRead);
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		try {
			// 읽기/쓰기 버퍼 객체 생성
			// + 파일 초기화
			br = new BufferedReader(new FileReader(fileToRead));
			bw = new BufferedWriter(new FileWriter(fileToWrite, true));
			
			// 복사 및 출력
			while(true) {
				String line;
				line = br.readLine();
				if(line==null) {
					break;
				}
				System.out.println(line);
				bw.write(line);
				bw.newLine();
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			// 입출력 완료 후 버퍼 리더/롸이터 닫기
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
