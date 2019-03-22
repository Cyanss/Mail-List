package com.cyan;

import java.io.*;
import java.util.ArrayList;

public class TextFile {
	static String filePath="directory.txt";								//通讯录文件路径
	private static String line;											//逐行读取得变量
	private  ArrayList<String> list = new ArrayList<String>();			//创建ArrayList表对象，<String> 为泛型，即表里存的数据的类型是String
	
	//下边是属性封装自动生成的函数
	public  ArrayList<String> getList() {
		return list;
	}
	public  void setList(ArrayList<String> list1) {
		list = list1;
	}
	
    public final void WriteFile(String  str) {										//清空写入文件方法

        try {    		
        	 File f = new File(filePath);			
             FileWriter fw = new FileWriter(f);
             BufferedWriter bw = new BufferedWriter(fw);
             bw.write(str);
             bw.close();
             fw.close();															//结束写入
        } catch (IOException e1) {
            e1.printStackTrace();
        }      
    }

    public final void AddFile(String  str) {										//不清空添加文件方法
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath,true);										//载入文件路径
            fw.write("\n"+str);														//重启一行写入
            fw.close();																//结束写入
        } catch (IOException e1) {
            e1.printStackTrace();
        }      
    }

	public  final void readFile() throws IOException {  							//直接抛出异常， 
    	BufferedReader br = new BufferedReader(new InputStreamReader(				//构建读取的对象
    			new FileInputStream(filePath)));									//载入路径
    	for (line = br.readLine(); line !=null; line = br.readLine()) {				//for循环逐行读取
    			list.add(line);																							
  		}
    br.close();																											
    }
	public  final void ForPathreadFile(String path) throws IOException {  			//带参读取函数	
    	BufferedReader br = new BufferedReader(new InputStreamReader(				//构建读取的对象
    			new FileInputStream(path)));										
    	for (line = br.readLine(); line !=null; line = br.readLine()) {				//for循环逐行读取
    			list.add(line);														//把读取的内容放到list表中
    	}
   	
    br.close();																		//结束读取
    }

}