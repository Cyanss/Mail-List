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

public class AddJframe {
	JTextField jTextField,jTextField1,jTextField2,jTextField3;	//文本框
	JTextArea textArea;											//文本域
	final JFrame jFrame = new JFrame();
	AddJframe(){												//无参构造函数
		SetEditJframe();
		SetText();
	}
	void SetEditJframe() {
		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();		//获取屏幕分辨率
			jFrame.setBounds(((int)dimension.getWidth() - 320) / 2, ((int)dimension.getHeight() -520) / 2, 340,460);//设置窗口大小，和出现位置
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
			
			
			JButton sureButton = new JButton("确认添加");
			sureButton.setFont(new java.awt.Font(null,   1,   14));
			sureButton.setBounds(40, 390, 110, 25);
			
			
			JButton cacelButton= new JButton("取消");
			cacelButton.setFont(new java.awt.Font(null,   1,   14));
			cacelButton.setBounds(240,390, 70, 25);
																		//确认修改按钮监听
			sureButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(jTextField.getText().length()==0				//这里判断每一个文本域是否为空，获取文本域的字符串长度判断是否为0，
							||jTextField1.getText().length()==0		//这种方法要记一下，||是或 ，&&是且，用在判断中，另外还有|，&用在什么地方我忘了
							||jTextField2.getText().length()==0
							||jTextField3.getText().length()==0
							||textArea.getText().length()==0) {
						JOptionPane.showMessageDialog(jFrame, "信息不完整！","提示", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						GetText();
						AddText();			
						JOptionPane.showMessageDialog(jFrame, "添加成功！","提示", JOptionPane.INFORMATION_MESSAGE);
						Service.TurnJframe(jFrame);
					}				
				}				
			});
			//取消按钮监听事件
			cacelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Service.TurnJframe(jFrame);
				}
			});
			jFrame.add(sureButton);
			jFrame.add(cacelButton);
			String path1 = ".\\images\\chakan.jpg";	//添加背景
			ImageIcon  image= new ImageIcon(path1); //定义图片对象，读取图像
			JLabel label11 = new JLabel(image);		//将图片以一个标签的形式添加，此方式不合法，但是习惯了
			label11.setBounds(0, 0, 340, 460);		//设置标签位置和大小
			jFrame.add(label11);					//添加到jFrame框架里
			jFrame.setLocationRelativeTo(null);  
			jFrame.setResizable(false);				//是否可最大化
	       
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
			jFrame.setVisible(true); 
	}
	public void AddText() {			//添加数据的函数封装
									/*封装的大概意思就是把代码较长的部分，或者多次使用的部分，放到
									 * 函数中，来避免大量的重复代码，优化代码和讯转效率*/
		int i=Service.Name.size()-1;
		String string=Service.Name.get(i)+"#"+Service.PhoneNumber.get(i)+"#"+
				Service.ZipCode.get(i)+"#"+Service.E_Mail.get(i)+"#"+
				Service.Address.get(i);
		Service.textFile.AddFile(string);
		/*这里说一下，Name是在Service类中定义的一个静态ArrayList对象，
		 * 再跨类调用静态属性时要使用（类名+.）来调用，显然i中得到的是Name这个List的大小，
		 * 减去1是要用i来做下标，不减就会溢出，之后就是将各个List表里添加的字符串转换为#间隔的字符串，
		 * 增加到text文件中，当然textFile也是静态的，这里是习惯，一半情况Service的属性都需要是静态，
		 * 只有静态属性，不分全局和局部，可以在程序的整个生命周期里一直使用，其他的会在某些时候被释放掉 
		 */
	}

	public void SetText() {			//初始化几个文本域为空
		jTextField.setText("");
		jTextField1.setText("");
		jTextField2.setText("");
		jTextField3.setText("");
		textArea.setText("");
	}
	public void GetText() {			//将文本域里的内容添加到Service类中的Name等list表中
		Service.Name.add(jTextField.getText());
		Service.PhoneNumber.add(jTextField1.getText());
		Service.ZipCode.add(jTextField2.getText());
		Service.E_Mail.add(jTextField3.getText());
		Service.Address.add(textArea.getText());
	}
}
