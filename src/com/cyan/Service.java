package com.cyan;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Service  {
	static boolean isSearch=false;										//��ѯ״̬����
	static boolean isSort=false;										//����״̬����
	ArrayList<String> Search;		
	static int searchpage=0,searchpagenub;
	static ArrayList<String> Name=new ArrayList<String>() ;				//���ֱ�
	static ArrayList<String> PhoneNumber=new ArrayList<String>() ;		//�绰��
	static ArrayList<String> ZipCode=new ArrayList<String>() ;			//�ʱ��
	static ArrayList<String> E_Mail=new ArrayList<String>() ;			//�����
	static ArrayList<String> Address=new ArrayList<String>() ;			//��ַ��
																		//����Щ��䵱��ʱ���ݿ�
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
		if(isSort) {													//���������״̬����ձ�û�����ݲ���
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
					String[] aryStrings = tempString.split("#");		//����API�������գ��ŷָ��ַ���
					Name.add(aryStrings[0]);							//��ӵ���Ӧ�ı���
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
			 JOptionPane.showMessageDialog(null, "ͨѶ¼Ϊ�գ�","��ʾ", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public static void TurnJframe(JFrame jFrame) {		//ת�����ڵķ�װ����
		jFrame.dispose();					//ͨ��jFrame����رյ�ǰ��Ӵ���
		try {
			isReSet=true;			//�������и���isReSet���Ƿ�����AppjFrame����ֵ
			isSearch=false;			/*ͨ��NEW����AppJframe�Ĺ��캯��������ֻ�ǽ����ڹرգ��ڴ��е����ݲ�û����գ�����Service
											�ĺܶ������Ǿ�̬�����Գ���ʹ�ã�����ֻ���ʼ��AppJframe�Ĳ�����Service���еı�����ֵ
											����˵�����Դ��ڣ�ÿһ��ArrayList����Ȼ�����ţ����TextFile����Ҫ�ڴ˶�ȡ����	
											*/	
			new AppJframe();		
									
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
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
