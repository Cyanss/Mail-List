package com.cyan;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


public class AppJframe {
	
	final Service service=new Service();									//���岢��ʼ��Service����
	JTextField jTextField; 													//����JTextField����
	static int Page=0;														//���岢��ʼ��ҳ�����Page
	final int pagenub;														//����ҳ������
	final JFrame jFrame = new JFrame();										//����JFrame���󲢳�ʼ��
	final String nations1[]={"��  ��  ��","���ֻ���"};							//�����ַ�������
	final String nations2[]={"��������","���ҵ绰","�����ʱ�","��������","���ҵ�ַ"};	//�����ַ�������
	static JComboBox<String> jcb=new JComboBox<String>();					//����ѡ��齨����ʹ�÷���
	static JComboBox<String> jcb1=new JComboBox<String>();
	final String leftPath=".\\images\\left.png";							//����ͼƬ·��
	final String rightPath=".\\images\\right.png";
	ImageIcon  leftimage= new ImageIcon(leftPath); 							//����ͼƬ���󲢳�ʼ��
	ImageIcon  rightimage= new ImageIcon(rightPath);
	final String[] columnNames = {"","����","�ֻ���"};   						//����ͷ��
	String[][] rowData = {  												//�������������8��
            {"1","", ""},  {"2","", ""},  {"3","", ""},  {"4","", ""},  
            {"5","", ""},  {"6","", ""},  {"7","", ""},  {"8","", ""}
            };  
	DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames); //�������ģ��
	    @SuppressWarnings("serial")
	    final JTable jtable=new JTable(tableModel){							//�ع�JTable������ʹ��Ԫ�񲻿ɱ༭
	         public boolean isCellEditable(int row, int column) { 
	        	 return false;
	         }	        
	    };
    AppJframe()throws IOException{											//���캯������ʼ������
    	if(Service.isReSet) {												//�ж�isReSet���Ƿ������������ݣ���ֵ
    		Page=0;															//���Ϊ�棬���³�ʼ��Page
    		Service.isReSet=false;											//��ԭisReSet��ֵ
    	}
    	if(service.getLength()%8==0) {										//Length��TextFile���ж�ȡ���ݺ���뵽��List���еĳ���
    		pagenub=service.getLength()/8-1;								//����Ҳ����ָ���ݵ������������8�ı�������ҳ��pagenub-1
    	}else {																//��Ϊint�������������ȥ������pagenub��Page������Ҫ��
    		pagenub=service.getLength()/8;									//�������List����±�����ģ���˱�ʵ��С1
    	}
    		SetJframe();													//����Jframe��������
    		SetTable();   													//���ñ���������ú���
    }
    void SetJframe() {														//Jframe��������

    JPanel panel = new JPanel();  											
    panel.setBounds(40, 120, 255, 260);
    GridBagLayout gridbag = new GridBagLayout();  							//ʹ��GridBag�������
    GridBagConstraints c = new GridBagConstraints();  						//GridBagԼ��
    jFrame.setLayout(gridbag);  											//��Jframe��Ӳ���


    jtable.getTableHeader().setFont(new   java.awt.Font(null,   1,   18));   //���ñ�ͷ����
    jtable.getTableHeader().setForeground(Color.black);						//���ñ�ͷ����
    JScrollPane jscrollpanel= new JScrollPane(jtable);						//��������������ÿһ�зŵ�һ����������
    jtable.setFont(new   java.awt.Font(null,   1,   14));					//���ñ����������
    jtable.setPreferredScrollableViewportSize(new Dimension(250, 260));		//���ñ��Ĵ�С  
    jtable.setRowHeight(28);												//����ÿ�еĸ߶�Ϊ28  
    jtable.setRowSelectionAllowed(true);									//���ÿɷ�ѡ��.Ĭ��Ϊfalse  
    jtable.setShowGrid(true);												//�Ƿ���ʾ������  
    jtable.setShowHorizontalLines(true);									// �Ƿ���ʾˮƽ��������  
    jtable.setShowVerticalLines(true);										// �Ƿ���ʾ��ֱ��������  
    jtable.addMouseListener(new MouseAdapter(){ 							
    	public void mouseClicked(MouseEvent e){     						//˫��ʱ�����
    		if(e.getClickCount() == 2){ 									//�жϵ������Ϊ2
              int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); 	//�����λ��
              int temp=(int)jtable.getValueAt(row, 0)-1;					//��ʱ������ȡ��row�еĵ�һ�е��ַ�����
              if (Service.isSearch) {										//����ת��ΪInt�ͣ���1���±�
            	  if(temp>=service.Search.size()) {							//��ѯ״̬�µĴ���ͬ���ж�isSearch���Ƿ��ǲ�ѯ״̬��
            		  		//�ж�temp��ֵ�ǲ��Ǵ��ڲ�ѯ���Ľ���е�Search��List�����Ĵ�С��������ڣ�˵��˫���˿հ�λ�ã�������ʾ�û�
	                	 JOptionPane.showMessageDialog(jFrame, "û����Ϣ��","��ʾ", JOptionPane.INFORMATION_MESSAGE);
	                 }
	                 else {													//������ǣ���˫��λ�õ��±꣬���ص���ѯ����У�����˫������
	                	 int temp1=Integer.parseInt(service.Search.get(temp));//��������µ��±꣬˵����Search��ŵ���������������ݵ����
	                	 new EditJframe(temp1);								//�����ݵ���Ŵ����༭���ڣ��ٱ༭������ʾ��ϸ���ݣ��û������޸�ɾ��
	                	 jFrame.dispose();									//�رյ�ǰ����
	                 }	  		
              }else {														//������ǲ�ѯ״̬��ִ������״̬��˫���¼�
				  if(temp>=service.getLength()) {							//tempΪת���������±꣬ͬ���ж��Ƿ��û�˫���˿հ�����
	                	 JOptionPane.showMessageDialog(jFrame, "û����Ϣ��","��ʾ", JOptionPane.INFORMATION_MESSAGE);
	                 }
	                 else {													//û��˫���հ�����˵��˫���������У���ת�����±괫���༭���ڣ�
	                	 new EditJframe(temp);								//�ٱ༭������ʾ��ϸ����
	                	 jFrame.dispose();
	                 }	  
              }                             		
    		}
    	}
	});
    jtable.doLayout();  													//������Ʋ��֣���˼������ʾ������˼
    jtable.setBackground(Color.white);  									//��񱳾�
    jtable.setColumnModel(getColumn(jtable,0,40));							//�����ع��ĺ�������ÿ�е����п�
    jtable.setColumnModel(getColumn(jtable,1,80));							//�ڶ���80����
    jtable.setColumnModel(getColumn(jtable,2,150));							//������150���أ��ֻ�����Ҫ��һ��

    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();			//���ģ��ĳ�ʼ��
    tcr.setHorizontalAlignment(SwingConstants.CENTER);						//��������λ�ã��������
    jtable.setDefaultRenderer(Object.class, tcr);  							//��������ӵ����
	panel.add( jscrollpanel);												//���������Ž�Panel,�������ŵ��Ǳ������
    addComponent(jFrame, panel, gridbag, c);  								//��д��addComponent�ĺ�������Ҫ���ǽ�gridbag���ֺ�PaneL���������
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();		//panel�зŵı��
	jFrame.setBounds(((int)dimension.getWidth() - 320) / 2, ((int)dimension.getHeight() -520) / 2, 340,460);
	jFrame.setResizable(false);
	jFrame.setLayout(null);
			
	
	jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JLabel label= new JLabel("ͨѶ¼");//����
	label.setFont(new   java.awt.Font(null,3,20));
	label.setBounds(135,10, 200, 30);
	jFrame.add(label);	
	
	jcb=new JComboBox<String>(nations1);									//ѡ�
	jcb.setFont(new   java.awt.Font(null,   1,   14));
	jcb.setBounds(30, 50, 90, 25);
	jFrame.add(jcb);
	
	JButton sortButton  = new JButton("����");
	sortButton.setFont(new java.awt.Font(null,   1,   14));
	sortButton.setBounds(150,50, 70, 25);
	
	JButton addButton  = new JButton("���");
	addButton.setFont(new java.awt.Font(null,   1,   14));
	addButton.setBounds(250,50, 70, 25);
	

	jcb1=new JComboBox<String>(nations2);
	jcb1.setFont(new   java.awt.Font(null,   1,   14));
	jcb1.setBounds(30, 85, 90, 25);
	jFrame.add(jcb1);
	
	
	jTextField=new JTextField();													//�ı���
	jTextField.setFont(new java.awt.Font(null,   1,   14));
	jTextField.setBounds(130, 85, 110, 25);
	jFrame.add(jTextField);
	
	JButton searchButton  = new JButton("����");
	searchButton.setFont(new java.awt.Font(null,   1,   12));
	searchButton.setBounds(250,85, 70, 25);

	
	JButton leftButton  = new JButton(leftimage);
	leftButton.setBounds(10,190, 25, 80);
	JButton rightButton  = new JButton(rightimage);
	rightButton.setBounds(300,190, 25, 80);
	
	JButton readButton  = new JButton("��ȡ");
	readButton.setFont(new java.awt.Font(null,   1,   14));
	readButton.setBounds(40,390, 70, 30);
			
	
	JButton saveButton = new JButton("����");
	saveButton.setFont(new java.awt.Font(null,   1,   14));
	saveButton.setBounds(130, 390, 70, 30);
	
	
	JButton quitButton= new JButton("�˳�");
	quitButton.setFont(new java.awt.Font(null,   1,   14));
	quitButton.setBounds(220,390, 70, 30);
	
	sortButton.addActionListener(new ActionListener() {								//����ť�¼�
		public void actionPerformed(ActionEvent e) {
			if(jcb.getSelectedItem()==nations1[0]) {								//�жϵ�һ��ѡ���ѡ���ѡ���һ���ǰ����֣��ڶ����ǣ����ֻ���
				try {																//�������жϰ�����
					SortNameText();													//������������ķ���
				} catch (IOException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
				}				
			}
			if(jcb.getSelectedItem()==nations1[1]){									//�жϵڶ������ֻ��ţ����ﲻʹ��Else�Ƿ����������ѡ��
				try {																//��Ȼ������Ҳ���Ա�֤���߼�����ȫ��������³����������
					SortPhoneNumberText();							//��ν���߼�����ȫ����ָ��δ�������һ����������������׺��Զ�Ϊ��������
				} catch (IOException e1) {							//�����Ǽ�С���ʷ�����δ������������磬��Ӳ�ң������棬���棬�������ŵ�һ�����
					// TODO �Զ����ɵ� catch ��							//��Ȼ�����ϻ��������ܣ���ֻ�Ǹ����ӣ���ž�����˼�������������ģ��߼����������ᱨ��
					e1.printStackTrace();
				}
			}
		}
	});
	addButton.addActionListener(new ActionListener() {				//��Ӱ�ť���¼�
		public void actionPerformed(ActionEvent e) {				//�رյ�ǰ���ڣ���ת��������ݵĴ���
			new AddJframe();
			jFrame.dispose();
		}
	});
	searchButton.addActionListener(new ActionListener() {			//��ѯ��ť�¼�
		public void actionPerformed(ActionEvent e) {
			if(jTextField.getText().length()==0) {					//�ж��ı����Ƿ�Ϊ�գ�����Ǽ��׺��Ե����������û���û�������ѯ��Ϣ�����㵽�˲�ѯ��ť�����Ĵ���
				JOptionPane.showMessageDialog(null, "������Ҫ��ѯ����Ϣ��","��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}else {	
				service.Search=new ArrayList<String>();				//������Ҫ�õ� Search �����������У�����ת�����±꣬��ʼ��һ��
				if ((String)jcb1.getSelectedItem()==nations2[0]) {	//�жϵڶ���ѡ�ѡ�������ݣ���һ���ǲ�ѯ���֣������Ĳ��ٽ���
					searchText(Service.Name);						//���ò�ѯ��������Name����ȥ
					searchSetTable(service.Search);					//��ѯ�󣬱�������Ҫ��Ӧ����ʾ��ѯ�Ľ����������ò�ѯ����ı�����ú���
				}	
				else if ((String)jcb1.getSelectedItem()==nations2[1]) {
					searchText(Service.PhoneNumber);
					searchSetTable(service.Search);
				}	
				else if ((String)jcb1.getSelectedItem()==nations2[2]) {
					searchText(Service.ZipCode);
					searchSetTable(service.Search);
				}	
				else if ((String)jcb1.getSelectedItem()==nations2[3]) {
					searchText(Service.E_Mail);
					searchSetTable(service.Search);
				}	
				else if ((String)jcb1.getSelectedItem()==nations2[4]) {
					searchText(Service.Address);
					searchSetTable(service.Search);
				}	
				Service.isSearch=true;									//��ʼ��ѯ�ˣ���ѯ״̬�ĳ�True�������ط��жϣ����в�ͬ�Ĵ���
			}
		}
	});
	leftButton.addActionListener(new ActionListener() {					//����ҳ�İ�ť�¼�
		public void actionPerformed(ActionEvent e) {
			if(Service.isSearch) {										//ͬ�����ڲ�ѯ״̬�£���ҳ��Ҫ����ѯ�Ľ���ģ������ϲ�ѯ����ǲ�ֹһ���ģ�
				if(Service.searchpage==0) {								//���ܶ���8������ʱ����Ҫ��ҳ����Ȼʵ��û����ô�࣬���������߼�������˵�����ù�
					JOptionPane.showMessageDialog(jFrame, "��ǰΪ��һҳ���޷���ǰ��ҳ��","��ʾ", JOptionPane.INFORMATION_MESSAGE);
				}							//searchpage�ǲ�ѯ״̬�µ�ҳ��������ж��Ƿ�Ϊ�㣬�����ж��Ƿ���Ҫ����ҳ
				else {
					Service.searchpage--;								//�Լ�1����Ӧ�Ĳ�ѯ״̬�ı�����ú�����������Ӧ��������ʾ
					searchSetTable(service.Search);						//��ѯ״̬�ı�����ú���
				}	
			}else {														//������������
				if(Page==0) {
					JOptionPane.showMessageDialog(jFrame, "��ǰΪ��һҳ���޷���ǰ��ҳ��","��ʾ", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					Page--;
					SetTable();
				}
			}
		}
	});
	rightButton.addActionListener(new ActionListener() {				//���ҷ�ҳ��ť�¼�
		public void actionPerformed(ActionEvent e) {
			if(Service.isSearch) {
				if(Service.searchpage==Service.searchpagenub) {
					JOptionPane.showMessageDialog(jFrame, "��ǰΪ���һҳ���޷����ҳ��","��ʾ", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					Service.searchpage++;
					searchSetTable(service.Search);
				}	
			}else {
				if(Page==pagenub) {
					JOptionPane.showMessageDialog(jFrame, "��ǰΪ���һҳ���޷����ҳ��","��ʾ", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					Page++;
					SetTable();
				}	
			}
			
		}
	});
	readButton.addActionListener(new ActionListener() {						//��ȡ�ļ���ť�¼�
			public void actionPerformed(ActionEvent e) {
				JFileChooserText();											//�����ļ���ȡ����
			}
		});
	saveButton.addActionListener(new ActionListener(){						//�����ļ���ť�¼�
		public void actionPerformed(ActionEvent e) {
			EditJframe.SaveText();											//��ΪEditJframe���еı����ļ��������Ը��ã���ʡ�����ã���������д��
			JOptionPane.showMessageDialog(jFrame, "����ɹ���","��ʾ", JOptionPane.INFORMATION_MESSAGE);
		
			}							
	});
	quitButton.addActionListener(new ActionListener() {						//�˳���ť�¼�
		public void actionPerformed(ActionEvent e) {
			if(Service.isSearch){											//�˳�ͬ��Ҫ�ж��Ƿ��ǲ�ѯ״̬��
				Service.isSearch=false;						//���磬�û���ѯ�����ˣ�ֻ��һ������Ҫ���������������ʱ����Ҫ�˳���ѯ״̬��
				Service.isReSet=true;						//Ҳ����˵����ѯ״̬����˳��������˳�����ֻ���˳���ѯ״̬��Ȼ����isReSet�����¼���һ������
				Service.TurnJframe(jFrame);					//����ת�亯�����ҷ��ֳ����л��õ��ܶ�˵Ĵ���ת�����Ͱ���һ�δ����װ���˺��������ʹ��
			}else {
				jFrame.dispose();							//��������¹رճ���
			}
		}
	});
	jFrame.add(sortButton);
	jFrame.add(addButton);
	jFrame.add(searchButton);
	jFrame.add(leftButton);
	jFrame.add(rightButton);
	jFrame.add(readButton);
	jFrame.add(saveButton);
	jFrame.add(quitButton);

	String path1 = ".\\images\\chakan.jpg";							//��ͼƬ��Ϊ��ǩ����ӱ��������������˽����ͼƬ��ǩ�Ŵ��ڸ�ס�����ģ��ʹ���˱�����Ч��
	ImageIcon  image= new ImageIcon(path1); 
	JLabel label11 = new JLabel(image);
	label11.setBounds(0, 0, 340, 460);
	jFrame.add(label11);
	jFrame.setLocationRelativeTo(null);  
	jFrame.setResizable(false);
   
	jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	jFrame.setVisible(true);  
	
	}	
	/**
	 * @param list
	 */
	public void searchSetTable(ArrayList<String> list) {	//��ѯת̨�ı������	
		if(list.size()==0) {								//�жϴ�������LiSt�����Ĵ�С�����Ϊ�㣬˵��û�����ݣ��������Ϊ�վ���
			for(int j=7;j>=list.size() ;j--) {				//list������ʵ����Search��
				jtable.setValueAt(j+1, j%8,0 );				//ʹ�õݼ�������ӣ�����
				jtable.setValueAt(" ", j%8,1 );
				jtable.setValueAt(" ", j%8,2 );
			}	
			JOptionPane.showMessageDialog(jFrame, "δ�鵽�κ���Ϣ��","��ʾ", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			if(list.size()%8==0) {							//�жϲ�ѯ����ǲ���8�ı�����ǰ����͹��ˣ���������ȥ����������8�ı���ʱ�õ�������1
				Service.searchpagenub=list.size()/8-1;	
			}else {
				Service.searchpagenub=list.size()/8;
			}
			for(int i=Service.searchpage*8;i<list.size();i++) {			//�˳��������List���Ƿŵ��ַ�����������123Ҫ�õ�ʱ����Ҫת�����͵�
				int temp=Integer.parseInt(list.get(i));					//�ַ���ת������
				jtable.setValueAt(i%8+Service.searchpage*8+1, i%8,0 );		//���ñ�����ݣ��������
				if(!list.get(i).isEmpty()) {								//��һ�����List��Ϊ�գ��������
					jtable.setValueAt(Service.Name.get(temp),i%8,1 );
					jtable.setValueAt(Service.PhoneNumber.get(temp),i%8,2 );				
				}
				if(i+1==(Service.searchpage+1)*8) {							//��������8��֮�󣬵�ǰҳ�Ѿ����ˣ����������ˣ�Ҫ����ѭ��
					break;
				} 
			}
			if((Service.searchpage+1)*8>=list.size()&&list.size()%8!=0) {	//��������ǣ���ѯ���ݵ�����������8�ı���������8�ı�����û�հ׵��ˣ�
				for(int j=(Service.searchpagenub+1)*8;j>list.size() ;j--) {	// ������ҳ����8��������������searchpagenub�ǲ�ѯ�������ҳ�����±����ֵ����1Ϊ��ת����
					jtable.setValueAt(j, (j-1)%8,0 );
					jtable.setValueAt(" ", (j-1)%8,1 );
					jtable.setValueAt(" ", (j-1)%8,2 );
				}	
			}
		}
	}	
	public void searchText(ArrayList<String> list){							//��ѯ�����ӵ�Search���еĺ���
		for(int i=0;i<list.size();i++) {
			int result=list.get(i).indexOf(jTextField.getText());			//indexOf���������жϴ治�����û�����Ĵ�����
			if(result==-1) {												//��ʡ�Բ�ѯ�����ǲ���ȫ��ѯ�������Ҳ�����������Ķ��ܲ����
				continue;													//����ֵΪ-1����û��continue��������һ��
			}else {
				service.Search.add(i+"");									//���±���ӵ�Search��+������ת��Ϊ�ַ�������򵥷���
			}						
		}				
	}
	public void SortPhoneNumberText() throws IOException {					//�ֻ��������㷨�������򵥵Ľ������Ȱ��ֻ��ŵı�PhoneNumber�ó�����
		TextFile textFile1=new TextFile();									//����һ��Long���͵����飬���ֻ���ת��Ϊһ�������Σ�Ȼ����Arrays.sort
		long[] temp=new long[Service.PhoneNumber.size()];					//��������������֮�������Ѿ������кõ������ˣ�
		for(int i=0;i<Service.PhoneNumber.size();i++) {						//Ȼ���ԭ��PhoneNumber���źõ�˳��Ƚϲ��ң���ȡԭ�����ţ��ڶ�Name
			temp [i]=Long.parseLong(Service.PhoneNumber.get(i)); 		 	//���������ȡ������ļ�������д���źõ�����
		}																	//��϶�������):(
		Arrays.sort(temp);
		for (int i = 0; i < temp.length; i++) {								//˫ѭ���Ƚ�
			for (int j = 0; j < Service.PhoneNumber.size(); j++) {
				if(temp[i]==Long.parseLong(Service.PhoneNumber.get(j))) {
					String string=Service.Name.get(j)+"#"+Service.PhoneNumber.get(j)+"#"+
							Service.ZipCode.get(j)+"#"+Service.E_Mail.get(j)+"#"+
							Service.Address.get(j);
					if(i==0) {												//��գ���д���һ��
						textFile1.WriteFile(string);		
					}else {													//д�뵽����ģ�Ϊʲô��������Ϊ��һ��д�뷽���лس����ڶ���û�س���
						textFile1.AddFile(string);							//ȫ���õ�һ�����ļ��������һ�пհ��У������ļ���ȡ�����һ���յ�
					}														//��ӵ�list�����ǻ��IO�쳣��
				}
			}		
		}
		Service.isSort=true;												//�����ˣ�������״̬
		 try {
				textFile1.ForPathreadFile(TextFile.filePath);				//ʹ����ʱ���ļ������������ʣ��������û�������ε��ʱ���ǲ��������ݲ�����
				int length=textFile1.getList().size();
				 Service.textExpain(length,textFile1);						//���ú����ָ����ݣ�
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}	
		Service.TurnJframe(jFrame);
	}
	public void SortNameText() throws IOException {							//���������������򲻾���Service��Ĵ���ֱ�����ļ�������TextFile
		TextFile textFilesFile=new TextFile();								//��ȡ��List���к�ֱ��������д�룬���Ҳ����Ҫ����״̬
		Comparator<Object> com=Collator.getInstance(java.util.Locale.CHINA);//����������������ĸ����
		textFilesFile.readFile();
	    Collections.sort(textFilesFile.getList(),com); 						//������
	     for(int i=0;i<textFilesFile.getList().size();i++){
	    	 if(i==0) {
	    		 textFilesFile.WriteFile(textFilesFile.getList().get(i));	//��д��
				}else {
					textFilesFile.AddFile(textFilesFile.getList().get(i));
				}									 	    	 
        }   
	     Service.TurnJframe(jFrame);
	}
	public void SetTable(){													//��������ı������
		int i=Page*8;
		for(;i<service.getLength();i++) {  		
			jtable.setValueAt(i%8+Page*8+1, i%8,0 );
			if(!Service.Name.get(i).isEmpty()) {
				jtable.setValueAt(Service.Name.get(i), i%8,1 );
				jtable.setValueAt(Service.PhoneNumber.get(i), i%8,2 );
			}
			if(i+1==(Page+1)*8) {
				break;
			} 
		}
		if((Page+1)*8>=service.getLength()&&service.getLength()%8!=0) {
			for(int j=(pagenub+1)*8;j>service.getLength() ;j--) {
				jtable.setValueAt(j, (j-1)%8,0 );
				jtable.setValueAt(" ", (j-1)%8,1 );
				jtable.setValueAt(" ", (j-1)%8,2 );
			}
		}
	}
	public void JFileChooserText() {								//��ȡ�ļ��ĺ���
		TextFile textFile1=new TextFile();							//�ļ�������TextFile��ʱ�����ʼ��
		  JFileChooser fileChooser = new JFileChooser();			//�����ļ�ѡ����
		  															//�����ļ�ѡ�����ĳ�ʼĿ¼Ϊ��ǰĿ¼
		  fileChooser.setCurrentDirectory(new File("."));			
		  fileChooser.setAcceptAllFileFilterUsed(false);			
		  final String  fileENames = ".txt";						//������չ���ַ���
		  
		  															//�����ļ�ѡ����ѡ����ʾ�����ļ�
		fileChooser.addChoosableFileFilter(new FileFilter() {
		   public boolean accept(File file) {
		     return true;
		   }
		   public String getDescription() {
		     return "�����ļ�(*.*)";
		   }
		  });
		  
		fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {	//�����ļ�ѡ�����¼�	 
		    public boolean accept(File file) { 		 
		    	if (file.getName().endsWith(fileENames) || file.isDirectory()) {		 
		    		return true;
		    	}		 
		    		return false;
		    }		 
		    public String getDescription() {		 
		    	return fileENames;
		    	}		 
		   	});
		 fileChooser.showDialog(null, null);									//�ļ�ѡ��������
		 try {
			textFile1.ForPathreadFile(fileChooser.getSelectedFile().getPath());	//��ȡ�ļ�ѡ����ѡ���ļ���·��
			int length=textFile1.getList().size();								//����·����ȡ�ļ������Զ�����
			 Service.textExpain(length,textFile1);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		 JOptionPane.showMessageDialog(jFrame, "��ȡ�ɹ���","��ʾ", JOptionPane.INFORMATION_MESSAGE);
		 Service.TurnJframe(jFrame);
	}

	private void addComponent(JFrame frame, JComponent comp,  //�ع���������Ӳ���
        GridBagLayout gridbag, GridBagConstraints c) {
        	gridbag.setConstraints(comp, c);  
        	frame.add(comp);
        
    	} 
	public static TableColumnModel getColumn(JTable table,int col  ,int width) {  //��д����п���
	    TableColumnModel columns = table.getColumnModel();  
	        TableColumn column = columns.getColumn(col); 
	        column.setPreferredWidth(width);      
	    return columns;  
	}  
	public static void main(String[] args)throws IOException {					//�������
		new AppJframe();	
	}
}

