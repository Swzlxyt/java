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
				System.out.println("生成成功");
			}else{
				System.out.println("生成失败");
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
			//1. 从url中获取字节流
			URL urlObj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
			cin = con.getInputStream();
			
			//2. 创建文件
			File f = new File(filePath + fileName + fileSuffix);
			if(!f.exists()){
				f.createNewFile();
			}
			fout = new FileOutputStream(f);
			
			//3.读写文件
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
