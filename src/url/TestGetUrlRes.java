package url;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class TestGetUrlRes {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			String url = sc.nextLine();
			String fileName = sc.nextLine();
			boolean flag = getUrlRes(url, fileName);
			if(flag){
				System.out.println("���ɳɹ�");
			}else{
				System.out.println("����ʧ��");
			}
		}
		sc.close();
	}
	
	private static String filePath = "f:\\zMusic\\";
	private static String fileSuffix = ".mp3";
	private static boolean getUrlRes(String url, String fileName){
		InputStream cin = null;
		FileOutputStream fout = null;
		try {
			//1. ��url�л�ȡ�ֽ���
			URL urlObj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
			cin = con.getInputStream();
			
			//2. �����ļ�
			File f = new File(filePath + fileName + fileSuffix);
			if(!f.exists()){
				f.createNewFile();
			}
			fout = new FileOutputStream(f);
			
			//3.��д�ļ�
			byte[] buffer = new byte[1024]; 
			int len = 0;
			while ((len = cin.read(buffer)) != -1) { 
				fout.write(buffer, 0, len); 
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				fout.close();
				cin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
