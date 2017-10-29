package cn.edu.hist.partymanage.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
*@Auther 宋民举
*@Email 860080937@qq.com
*@Date 2017年5月4日
*@Description :
*/
public class FileUtils {
	public static boolean copyFile(File src,File to){
		InputStream in = null;
		try {
			in = new FileInputStream(src);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return copyFile(in , to);
	}
	public static boolean copyFile(InputStream in,File to){
		boolean b = true;
		OutputStream out = null;
		try {
			out = new FileOutputStream(to);
			byte[] buffer = new byte[32];
			int len = 0;
			while((len = in.read(buffer))>0){
				out.write(buffer, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			b=false;
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return b;
		
	}
}
