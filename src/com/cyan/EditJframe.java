package com.cyan;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EditJframe {
	JTextField jTextField,jTextField1,jTextField2,jTextField3;
	JTextArea textArea;
	final int rows;
	final JFrame jFrame = new JFrame();
	EditJframe(int row){				//构造函数
		rows=row;
		SetEditJframe();
		SetText(rows);
	}
	void SetEditJframe() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		jFrame.setBounds(((int)dimension.getWidth() - 320) / 2, ((int)dimension.getHeight() -520) / 2, 340,460);
		jFrame.setResizable(false);
		jFrame.setLayout(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label= new JLabel("通讯录");//标题
		label.setFont(new   java.awt.Font(null,3,20));
		label.setBounds(135,10, 200, 30);
		jFrame.add(label);	
		
		JLabel label1= new JLabel("姓名：");
		label1.setFont(new java.awt.Font(null,3,18));
		label1.setBounds(40,60, 200, 30);
		jFrame.add(label1);	
		 
		jTextField=new JTextField();
		jTextField.setFont(new java.awt.Font(null,3,16));
		jTextField.setBounds(100,62, 160, 30);
		jTextField.setForeground(Color.blue);		
		jFrame.add(jTextField);	
		
		JLabel label2= new JLabel("电话：");
		label2.setFont(new java.awt.Font(null,3,18));
		label2.setBounds(40,100, 200, 30);
		jFrame.add(label2);
		
		jTextField1=new JTextField();
		jTextField1.setFont(new java.awt.Font(null,3,16));
		jTextField1.setBounds(100,102, 160, 30);
		jTextField1.setForeground(Color.blue);;
		jFrame.add(jTextField1);	
		
		JLabel label3= new JLabel("邮编：");
		label3.setFont(new java.awt.Font(null,3,18));
		label3.setBounds(40,140, 200, 30);
		jFrame.add(label3);
		
		jTextField2=new JTextField();
		jTextField2.setFont(new java.awt.Font(null,3,16));
		jTextField2.setBounds(100,142, 160, 30);
		jTextField2.setForeground(Color.blue);
		jFrame.add(jTextField2);	
		
		JLabel label4= new JLabel("邮件：");
		label4.setFont(new java.awt.Font(null,3,18));
		label4.setBounds(40,180, 200, 30);
		jFrame.add(label4);
		
		jTextField3=new JTextField();
		jTextField3.setFont(new java.awt.Font(null,3,16));
		jTextField3.setBounds(100,182, 160, 30);
		jTextField3.setForeground(Color.blue);
		jFrame.add(jTextField3);	
		
		
		JLabel label5= new JLabel("地址：");
		label5.setFont(new java.awt.Font(null,3,18));
		label5.setBounds(40,220, 200, 30);
		jFrame.add(label5);
		
		textArea=new JTextArea();
		textArea.setFont(new java.awt.Font(null,3,16));
		
		textArea.setBounds(40,260, 260, 90);
		jFrame.add(textArea);
		
		
		JButton editButton = new JButton("保存修改");
		editButton.setFont(new java.awt.Font(null,   1,   14));
		editButton.setBounds(40, 390, 110, 25);
		
		
		JButton deleteButton= new JButton("删除");
		deleteButton.setFont(new java.awt.Font(null,   1,   14));
		deleteButton.setBounds(160,390, 70, 25);
		
		JButton backButton= new JButton("返回");
		backButton.setFont(new java.awt.Font(null,   1,   14));
		backButton.setBounds(240,390, 70, 25);
		
		editButton.addActionListener(new ActionListener() {			//修改按钮事件
		public void actionPerformed(ActionEvent e) {		
			if(jTextField.getText().length()==0
					||jTextField1.getText().length()==0
					||jTextField2.getText().length()==0
					||jTextField3.getText().length()==0
					||textArea.getText().length()==0) {
				JOptionPane.showMessageDialog(jFrame, "信息不完整！","提示", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				editText(rows);
				SaveText();
				JOptionPane.showMessageDialog(jFrame, "修改成功！","提示", JOptionPane.INFORMATION_MESSAGE);
				Service.TurnJframe(jFrame);
				}
			}
		});
		deleteButton.addActionListener(new ActionListener() {				//删除按钮事件
			public void actionPerformed(ActionEvent e) {					//选择弹框，选择是，删除，否，不做处理
			int result=	JOptionPane.showConfirmDialog(jFrame, "是否删除本条记录？", "提示" , JOptionPane.YES_NO_OPTION);
			switch (result) {
			case JOptionPane.YES_OPTION:
				deleteText(rows);
				SaveText();
				Service.TurnJframe(jFrame);
				break;

			case JOptionPane.NO_OPTION:
				break;
			}
			}
		});
		backButton.addActionListener(new ActionListener() {		//返回事件
			public void actionPerformed(ActionEvent e) {
				Service.TurnJframe(jFrame);
			}
		});
		jFrame.add(editButton);
		jFrame.add(deleteButton);
		jFrame.add(backButton);
		String path1 = ".\\images\\chakan.jpg";//添加背景
		ImageIcon  image= new ImageIcon(path1); 
		JLabel label11 = new JLabel(image);
		label11.setBounds(0, 0, 340, 460);
		jFrame.add(label11);
		jFrame.setLocationRelativeTo(null);  
		jFrame.setResizable(false);
       
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		jFrame.setVisible(true); 
	}
	public static void SaveText() {								//保存函数
		int n=Service.Name.size();
		for (int i=0;i<n;i++) {									//转换为#号间隔 的字符串写入
			String string=Service.Name.get(i)+"#"+Service.PhoneNumber.get(i)+"#"+
					Service.ZipCode.get(i)+"#"+Service.E_Mail.get(i)+"#"+
					Service.Address.get(i);
			if(i==0) {
				Service.textFile.WriteFile(string);		
			}else {
				Service.textFile.AddFile(string);
			}													
		}
	}
	public void deleteText(int row) {				//删除函数，将数据从相应的表中移除
		Service.Name.remove(row);
		Service.PhoneNumber.remove(row);
		Service.ZipCode.remove(row);
		Service.E_Mail.remove(row);
		Service.Address.remove(row);
	}
	public void editText(int row) {					//修改函数，将表中的数据进行相应修改
		Service.Name.set(row, jTextField.getText());
		Service.PhoneNumber.set(row,jTextField1.getText());
		Service.ZipCode.set(row,jTextField2.getText());
		Service.E_Mail.set(row,jTextField3.getText());
		Service.Address.set(row,textArea.getText());
	}
	public void SetText(int row) {					//将数据显示到文本框，或者文本域
		jTextField.setText(Service.Name.get(row));
		jTextField1.setText(Service.PhoneNumber.get(row));
		jTextField2.setText(Service.ZipCode.get(row));
		jTextField3.setText(Service.E_Mail.get(row));
		textArea.setText(Service.Address.get(row));
	}
	
}
