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
	JTextField jTextField,jTextField1,jTextField2,jTextField3;	//�ı���
	JTextArea textArea;											//�ı���
	final JFrame jFrame = new JFrame();
	AddJframe(){												//�޲ι��캯��
		SetEditJframe();
		SetText();
	}
	void SetEditJframe() {
		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();		//��ȡ��Ļ�ֱ���
			jFrame.setBounds(((int)dimension.getWidth() - 320) / 2, ((int)dimension.getHeight() -520) / 2, 340,460);//���ô��ڴ�С���ͳ���λ��
			jFrame.setResizable(false);
			jFrame.setLayout(null);
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JLabel label= new JLabel("ͨѶ¼");//����
			label.setFont(new   java.awt.Font(null,3,20));
			label.setBounds(135,10, 200, 30);
			jFrame.add(label);	
			
			JLabel label1= new JLabel("������");
			label1.setFont(new java.awt.Font(null,3,18));
			label1.setBounds(40,60, 200, 30);
			jFrame.add(label1);	
			 
			jTextField=new JTextField();
			jTextField.setFont(new java.awt.Font(null,3,16));
			jTextField.setBounds(100,62, 160, 30);
			jTextField.setForeground(Color.blue);		
			jFrame.add(jTextField);	
			
			JLabel label2= new JLabel("�绰��");
			label2.setFont(new java.awt.Font(null,3,18));
			label2.setBounds(40,100, 200, 30);
			jFrame.add(label2);
			
			jTextField1=new JTextField();
			jTextField1.setFont(new java.awt.Font(null,3,16));
			jTextField1.setBounds(100,102, 160, 30);
			jTextField1.setForeground(Color.blue);;
			jFrame.add(jTextField1);	
			
			JLabel label3= new JLabel("�ʱࣺ");
			label3.setFont(new java.awt.Font(null,3,18));
			label3.setBounds(40,140, 200, 30);
			jFrame.add(label3);
			
			jTextField2=new JTextField();
			jTextField2.setFont(new java.awt.Font(null,3,16));
			jTextField2.setBounds(100,142, 160, 30);
			jTextField2.setForeground(Color.blue);
			jFrame.add(jTextField2);	
			
			JLabel label4= new JLabel("�ʼ���");
			label4.setFont(new java.awt.Font(null,3,18));
			label4.setBounds(40,180, 200, 30);
			jFrame.add(label4);
			
			jTextField3=new JTextField();
			jTextField3.setFont(new java.awt.Font(null,3,16));
			jTextField3.setBounds(100,182, 160, 30);
			jTextField3.setForeground(Color.blue);
			jFrame.add(jTextField3);	
			
			
			JLabel label5= new JLabel("��ַ��");
			label5.setFont(new java.awt.Font(null,3,18));
			label5.setBounds(40,220, 200, 30);
			jFrame.add(label5);
			
			textArea=new JTextArea();
			textArea.setFont(new java.awt.Font(null,3,16));
			
			textArea.setBounds(40,260, 260, 90);
			jFrame.add(textArea);
			
			
			JButton sureButton = new JButton("ȷ�����");
			sureButton.setFont(new java.awt.Font(null,   1,   14));
			sureButton.setBounds(40, 390, 110, 25);
			
			
			JButton cacelButton= new JButton("ȡ��");
			cacelButton.setFont(new java.awt.Font(null,   1,   14));
			cacelButton.setBounds(240,390, 70, 25);
																		//ȷ���޸İ�ť����
			sureButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(jTextField.getText().length()==0				//�����ж�ÿһ���ı����Ƿ�Ϊ�գ���ȡ�ı�����ַ��������ж��Ƿ�Ϊ0��
							||jTextField1.getText().length()==0		//���ַ���Ҫ��һ�£�||�ǻ� ��&&���ң������ж��У����⻹��|��&����ʲô�ط�������
							||jTextField2.getText().length()==0
							||jTextField3.getText().length()==0
							||textArea.getText().length()==0) {
						JOptionPane.showMessageDialog(jFrame, "��Ϣ��������","��ʾ", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						GetText();
						AddText();			
						JOptionPane.showMessageDialog(jFrame, "��ӳɹ���","��ʾ", JOptionPane.INFORMATION_MESSAGE);
						Service.TurnJframe(jFrame);
					}				
				}				
			});
			//ȡ����ť�����¼�
			cacelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Service.TurnJframe(jFrame);
				}
			});
			jFrame.add(sureButton);
			jFrame.add(cacelButton);
			String path1 = ".\\images\\chakan.jpg";	//��ӱ���
			ImageIcon  image= new ImageIcon(path1); //����ͼƬ���󣬶�ȡͼ��
			JLabel label11 = new JLabel(image);		//��ͼƬ��һ����ǩ����ʽ��ӣ��˷�ʽ���Ϸ�������ϰ����
			label11.setBounds(0, 0, 340, 460);		//���ñ�ǩλ�úʹ�С
			jFrame.add(label11);					//��ӵ�jFrame�����
			jFrame.setLocationRelativeTo(null);  
			jFrame.setResizable(false);				//�Ƿ�����
	       
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
			jFrame.setVisible(true); 
	}
	public void AddText() {			//������ݵĺ�����װ
									/*��װ�Ĵ����˼���ǰѴ���ϳ��Ĳ��֣����߶��ʹ�õĲ��֣��ŵ�
									 * �����У�������������ظ����룬�Ż������ѶתЧ��*/
		int i=Service.Name.size()-1;
		String string=Service.Name.get(i)+"#"+Service.PhoneNumber.get(i)+"#"+
				Service.ZipCode.get(i)+"#"+Service.E_Mail.get(i)+"#"+
				Service.Address.get(i);
		Service.textFile.AddFile(string);
		/*����˵һ�£�Name����Service���ж����һ����̬ArrayList����
		 * �ٿ�����þ�̬����ʱҪʹ�ã�����+.�������ã���Ȼi�еõ�����Name���List�Ĵ�С��
		 * ��ȥ1��Ҫ��i�����±꣬�����ͻ������֮����ǽ�����List������ӵ��ַ���ת��Ϊ#������ַ�����
		 * ���ӵ�text�ļ��У���ȻtextFileҲ�Ǿ�̬�ģ�������ϰ�ߣ�һ�����Service�����Զ���Ҫ�Ǿ�̬��
		 * ֻ�о�̬���ԣ�����ȫ�ֺ;ֲ��������ڳ������������������һֱʹ�ã������Ļ���ĳЩʱ���ͷŵ� 
		 */
	}

	public void SetText() {			//��ʼ�������ı���Ϊ��
		jTextField.setText("");
		jTextField1.setText("");
		jTextField2.setText("");
		jTextField3.setText("");
		textArea.setText("");
	}
	public void GetText() {			//���ı������������ӵ�Service���е�Name��list����
		Service.Name.add(jTextField.getText());
		Service.PhoneNumber.add(jTextField1.getText());
		Service.ZipCode.add(jTextField2.getText());
		Service.E_Mail.add(jTextField3.getText());
		Service.Address.add(textArea.getText());
	}
}
