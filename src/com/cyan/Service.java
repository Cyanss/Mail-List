package com.cyan;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Service  {
	static boolean isSearch=false;										//查询状态变量
	static boolean isSort=false;										//排序状态变量
	ArrayList<String> Search;		
	static int searchpage=0,searchpagenub;
	static ArrayList<String> Name=new ArrayList<String>() ;				//名字表
	static ArrayList<String> PhoneNumber=new ArrayList<String>() ;		//电话表
	static ArrayList<String> ZipCode=new ArrayList<String>() ;			//邮编表
	static ArrayList<String> E_Mail=new ArrayList<String>() ;			//邮箱表
	static ArrayList<String> Address=new ArrayList<String>() ;			//地址表
																		//用这些表充当临时数据库
	public static TextFile textFile=new TextFile();
	private static int length;
	static boolean isReSet=false;
	Service()throws IOException{
		
		textFile.readFile();
		if(isReSet) {
			length=Name.size();
		}else {
			length=textFile.getList().size();
		}
		
		if(Name.size()==0) {
			textExpain(length,textFile);	
		}	
	}
	public static void textExpain(int length,TextFile textFile) throws IOException{
		if(isSort) {													//如果是排序状态，清空表，没有数据残留
			Name.clear();
			PhoneNumber.clear();
			ZipCode.clear();
			E_Mail.clear();
			Address.clear();
			isSort=false;
		}
		if(length!=0) {
			for (int i=0;i<length;i++) {
				String tempString=textFile.getList().get(i);
				if(tempString.length()!=0) {
					String[] aryStrings = tempString.split("#");		//调用API方法按照＃号分隔字符串
					Name.add(aryStrings[0]);							//添加到相应的表中
					PhoneNumber.add(aryStrings[1]);
					ZipCode.add(aryStrings[2]);
					E_Mail.add(aryStrings[3]);
					Address.add(aryStrings[4]);
				}
				else {
					continue;
				}
			}		
		}
		else {
			 JOptionPane.showMessageDialog(null, "通讯录为空！","提示", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public static void TurnJframe(JFrame jFrame) {		//转换窗口的封装函数
		jFrame.dispose();					//通过jFrame对象关闭当前添加窗口
		try {
			isReSet=true;			//服务类中更改isReSet（是否重设AppjFrame）的值
			isSearch=false;			/*通过NEW调用AppJframe的构造函数，由于只是将窗口关闭，内存中的数据并没有清空，而且Service
											的很多属性是静态，可以持续使用，所以只需初始化AppJframe的参数，Service类中的变量有值
											或者说属性仍存在，每一个ArrayList都仍然保存着，因此TextFile不需要在此读取数据	
											*/	
			new AppJframe();		
									
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		Service.length = length;
	}
}
