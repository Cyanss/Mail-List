package com.cyan;

import java.io.*;
import java.util.ArrayList;

public class TextFile {
	static String filePath="directory.txt";								//ͨѶ¼�ļ�·��
	private static String line;											//���ж�ȡ�ñ���
	private  ArrayList<String> list = new ArrayList<String>();			//����ArrayList�����<String> Ϊ���ͣ������������ݵ�������String
	
	//�±������Է�װ�Զ����ɵĺ���
	public  ArrayList<String> getList() {
		return list;
	}
	public  void setList(ArrayList<String> list1) {
		list = list1;
	}
	
    public final void WriteFile(String  str) {										//���д���ļ�����

        try {    		
        	 File f = new File(filePath);			
             FileWriter fw = new FileWriter(f);
             BufferedWriter bw = new BufferedWriter(fw);
             bw.write(str);
             bw.close();
             fw.close();															//����д��
        } catch (IOException e1) {
            e1.printStackTrace();
        }      
    }

    public final void AddFile(String  str) {										//���������ļ�����
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath,true);										//�����ļ�·��
            fw.write("\n"+str);														//����һ��д��
            fw.close();																//����д��
        } catch (IOException e1) {
            e1.printStackTrace();
        }      
    }

	public  final void readFile() throws IOException {  							//ֱ���׳��쳣�� 
    	BufferedReader br = new BufferedReader(new InputStreamReader(				//������ȡ�Ķ���
    			new FileInputStream(filePath)));									//����·��
    	for (line = br.readLine(); line !=null; line = br.readLine()) {				//forѭ�����ж�ȡ
    			list.add(line);																							
  		}
    br.close();																											
    }
	public  final void ForPathreadFile(String path) throws IOException {  			//���ζ�ȡ����	
    	BufferedReader br = new BufferedReader(new InputStreamReader(				//������ȡ�Ķ���
    			new FileInputStream(path)));										
    	for (line = br.readLine(); line !=null; line = br.readLine()) {				//forѭ�����ж�ȡ
    			list.add(line);														//�Ѷ�ȡ�����ݷŵ�list����
    	}
   	
    br.close();																		//������ȡ
    }

}