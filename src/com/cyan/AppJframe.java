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
	
	final Service service=new Service();									//定义并初始化Service对象
	JTextField jTextField; 													//定义JTextField对象
	static int Page=0;														//定义并初始化页面变量Page
	final int pagenub;														//定义页面总数
	final JFrame jFrame = new JFrame();										//定义JFrame对象并初始化
	final String nations1[]={"按  姓  名","按手机号"};							//定义字符串数组
	final String nations2[]={"查找姓名","查找电话","查找邮编","查找邮箱","查找地址"};	//定义字符串数组
	static JComboBox<String> jcb=new JComboBox<String>();					//定义选项卡组建对象，使用泛型
	static JComboBox<String> jcb1=new JComboBox<String>();
	final String leftPath=".\\images\\left.png";							//定义图片路径
	final String rightPath=".\\images\\right.png";
	ImageIcon  leftimage= new ImageIcon(leftPath); 							//定义图片对象并初始化
	ImageIcon  rightimage= new ImageIcon(rightPath);
	final String[] columnNames = {"","姓名","手机号"};   						//表格表头栏
	String[][] rowData = {  												//表格内容栏，共8行
            {"1","", ""},  {"2","", ""},  {"3","", ""},  {"4","", ""},  
            {"5","", ""},  {"6","", ""},  {"7","", ""},  {"8","", ""}
            };  
	DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames); //创建表格模板
	    @SuppressWarnings("serial")
	    final JTable jtable=new JTable(tableModel){							//重构JTable方法，使单元格不可编辑
	         public boolean isCellEditable(int row, int column) { 
	        	 return false;
	         }	        
	    };
    AppJframe()throws IOException{											//构造函数，初始化属性
    	if(Service.isReSet) {												//判断isReSet（是否重新载入数据）的值
    		Page=0;															//如果为真，重新初始化Page
    		Service.isReSet=false;											//还原isReSet的值
    	}
    	if(service.getLength()%8==0) {										//Length是TextFile类中读取数据后放入到的List表中的长度
    		pagenub=service.getLength()/8-1;								//这里也就是指数据的条数，如果是8的倍数，总页数pagenub-1
    	}else {																//因为int类型整除后会舍去余数，pagenub和Page后面是要和
    		pagenub=service.getLength()/8;									//数组或者List表的下标关联的，因此比实际小1
    	}
    		SetJframe();													//调用Jframe建立函数
    		SetTable();   													//调用表格内容设置函数
    }
    void SetJframe() {														//Jframe建立函数

    JPanel panel = new JPanel();  											
    panel.setBounds(40, 120, 255, 260);
    GridBagLayout gridbag = new GridBagLayout();  							//使用GridBag布局设计
    GridBagConstraints c = new GridBagConstraints();  						//GridBag约束
    jFrame.setLayout(gridbag);  											//在Jframe添加布局


    jtable.getTableHeader().setFont(new   java.awt.Font(null,   1,   18));   //设置表头字体
    jtable.getTableHeader().setForeground(Color.black);						//设置表头背景
    JScrollPane jscrollpanel= new JScrollPane(jtable);						//定义滚动条，表格每一行放到一个滚动条中
    jtable.setFont(new   java.awt.Font(null,   1,   14));					//设置表格内容字体
    jtable.setPreferredScrollableViewportSize(new Dimension(250, 260));		//设置表格的大小  
    jtable.setRowHeight(28);												//设置每行的高度为28  
    jtable.setRowSelectionAllowed(true);									//设置可否被选择.默认为false  
    jtable.setShowGrid(true);												//是否显示网格线  
    jtable.setShowHorizontalLines(true);									// 是否显示水平的网格线  
    jtable.setShowVerticalLines(true);										// 是否显示垂直的网格线  
    jtable.addMouseListener(new MouseAdapter(){ 							
    	public void mouseClicked(MouseEvent e){     						//双击时间监听
    		if(e.getClickCount() == 2){ 									//判断点击次数为2
              int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); 	//获得行位置
              int temp=(int)jtable.getValueAt(row, 0)-1;					//临时变量获取第row行的第一列的字符串，
              if (Service.isSearch) {										//将其转换为Int型，减1做下标
            	  if(temp>=service.Search.size()) {							//查询状态下的处理，同样判断isSearch（是否是查询状态）
            		  		//判断temp的值是不是大于查询到的结果中的Search（List表单）的大小，如果大于，说明双击了空白位置，弹窗提示用户
	                	 JOptionPane.showMessageDialog(jFrame, "没有信息！","提示", JOptionPane.INFORMATION_MESSAGE);
	                 }
	                 else {													//如果不是，将双击位置的下标，返回到查询结果中，查找双击数据
	                	 int temp1=Integer.parseInt(service.Search.get(temp));//正常情况下的下标，说明，Search存放的是正常情况下数据的序号
	                	 new EditJframe(temp1);								//将数据的序号传到编辑窗口，再编辑窗口显示详细数据，用户可以修改删除
	                	 jFrame.dispose();									//关闭当前窗口
	                 }	  		
              }else {														//如果不是查询状态，执行正常状态的双击事件
				  if(temp>=service.getLength()) {							//temp为转换的数据下标，同样判断是否用户双击了空白区域
	                	 JOptionPane.showMessageDialog(jFrame, "没有信息！","提示", JOptionPane.INFORMATION_MESSAGE);
	                 }
	                 else {													//没有双击空白区域，说明双击了数据列，将转换的下标传到编辑窗口，
	                	 new EditJframe(temp);								//再编辑窗口显示详细数据
	                	 jFrame.dispose();
	                 }	  
              }                             		
    		}
    	}
	});
    jtable.doLayout();  													//加载设计布局，意思就是显示表格的意思
    jtable.setBackground(Color.white);  									//表格背景
    jtable.setColumnModel(getColumn(jtable,0,40));							//调用重构的函数设置每列的列列宽
    jtable.setColumnModel(getColumn(jtable,1,80));							//第二列80像素
    jtable.setColumnModel(getColumn(jtable,2,150));							//第三列150像素，手机号列要长一点

    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();			//表格模板的初始化
    tcr.setHorizontalAlignment(SwingConstants.CENTER);						//表格的纵向位置，纵向居中
    jtable.setDefaultRenderer(Object.class, tcr);  							//将属性添加到表格
	panel.add( jscrollpanel);												//将滚动条放进Panel,滚动条放的是表格内容
    addComponent(jFrame, panel, gridbag, c);  								//重写的addComponent的函数，主要就是将gridbag布局和PaneL结合起来，
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();		//panel中放的表格
	jFrame.setBounds(((int)dimension.getWidth() - 320) / 2, ((int)dimension.getHeight() -520) / 2, 340,460);
	jFrame.setResizable(false);
	jFrame.setLayout(null);
			
	
	jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JLabel label= new JLabel("通讯录");//标题
	label.setFont(new   java.awt.Font(null,3,20));
	label.setBounds(135,10, 200, 30);
	jFrame.add(label);	
	
	jcb=new JComboBox<String>(nations1);									//选项卡
	jcb.setFont(new   java.awt.Font(null,   1,   14));
	jcb.setBounds(30, 50, 90, 25);
	jFrame.add(jcb);
	
	JButton sortButton  = new JButton("排序");
	sortButton.setFont(new java.awt.Font(null,   1,   14));
	sortButton.setBounds(150,50, 70, 25);
	
	JButton addButton  = new JButton("添加");
	addButton.setFont(new java.awt.Font(null,   1,   14));
	addButton.setBounds(250,50, 70, 25);
	

	jcb1=new JComboBox<String>(nations2);
	jcb1.setFont(new   java.awt.Font(null,   1,   14));
	jcb1.setBounds(30, 85, 90, 25);
	jFrame.add(jcb1);
	
	
	jTextField=new JTextField();													//文本框
	jTextField.setFont(new java.awt.Font(null,   1,   14));
	jTextField.setBounds(130, 85, 110, 25);
	jFrame.add(jTextField);
	
	JButton searchButton  = new JButton("查找");
	searchButton.setFont(new java.awt.Font(null,   1,   12));
	searchButton.setBounds(250,85, 70, 25);

	
	JButton leftButton  = new JButton(leftimage);
	leftButton.setBounds(10,190, 25, 80);
	JButton rightButton  = new JButton(rightimage);
	rightButton.setBounds(300,190, 25, 80);
	
	JButton readButton  = new JButton("读取");
	readButton.setFont(new java.awt.Font(null,   1,   14));
	readButton.setBounds(40,390, 70, 30);
			
	
	JButton saveButton = new JButton("保存");
	saveButton.setFont(new java.awt.Font(null,   1,   14));
	saveButton.setBounds(130, 390, 70, 30);
	
	
	JButton quitButton= new JButton("退出");
	quitButton.setFont(new java.awt.Font(null,   1,   14));
	quitButton.setBounds(220,390, 70, 30);
	
	sortButton.addActionListener(new ActionListener() {								//排序按钮事件
		public void actionPerformed(ActionEvent e) {
			if(jcb.getSelectedItem()==nations1[0]) {								//判断第一个选项卡中选择的选项，第一个是按名字，第二个是，按手机号
				try {																//这里先判断按名字
					SortNameText();													//调用名字排序的方法
				} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
				}				
			}
			if(jcb.getSelectedItem()==nations1[1]){									//判断第二个按手机号，这里不使用Else是方便添加其他选项
				try {																//当然这样做也可以保证在逻辑处理不全面的条件下程序可以运行
					SortPhoneNumberText();							//所谓的逻辑处理不全面是指，未作处理的一种情况，或者最容易忽略而为处理的情况
				} catch (IOException e1) {							//或者是极小概率发生的未处理情况，例如，抛硬币，有正面，反面，还有立着的一种情况
					// TODO 自动生成的 catch 块							//虽然概率上基本不可能，这只是个例子，大概就这意思，但程序是死的，逻辑不完整它会报错
					e1.printStackTrace();
				}
			}
		}
	});
	addButton.addActionListener(new ActionListener() {				//添加按钮的事件
		public void actionPerformed(ActionEvent e) {				//关闭当前窗口，跳转到添加数据的窗口
			new AddJframe();
			jFrame.dispose();
		}
	});
	searchButton.addActionListener(new ActionListener() {			//查询按钮事件
		public void actionPerformed(ActionEvent e) {
			if(jTextField.getText().length()==0) {					//判断文本框是否为空，这就是极易忽略的情况，如果用户，没有输入查询信息，而点到了查询按钮所做的处理
				JOptionPane.showMessageDialog(null, "请输入要查询的信息！","提示", JOptionPane.INFORMATION_MESSAGE);
			}else {	
				service.Search=new ArrayList<String>();				//这里需要用到 Search 表存放数据序列，就是转化的下标，初始化一下
				if ((String)jcb1.getSelectedItem()==nations2[0]) {	//判断第二个选项卡选定的内容，第一个是查询名字，其他的不再解释
					searchText(Service.Name);						//调用查询函数，将Name表传进去
					searchSetTable(service.Search);					//查询后，表格的数据要相应的显示查询的结果，这里调用查询结果的表格设置函数
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
				Service.isSearch=true;									//开始查询了，查询状态改成True，其他地方判断，进行不同的处理
			}
		}
	});
	leftButton.addActionListener(new ActionListener() {					//向左翻页的按钮事件
		public void actionPerformed(ActionEvent e) {
			if(Service.isSearch) {										//同样，在查询状态下，翻页是要翻查询的结果的，理论上查询结果是不止一条的，
				if(Service.searchpage==0) {								//可能多于8条，这时就需要翻页，当然实际没有这么多，这是完整逻辑，可以说是无用功
					JOptionPane.showMessageDialog(jFrame, "当前为第一页，无法向前翻页！","提示", JOptionPane.INFORMATION_MESSAGE);
				}							//searchpage是查询状态下的页面变量，判断是否为零，就是判断是否需要向左翻页
				else {
					Service.searchpage--;								//自减1，相应的查询状态的表格设置函数会做出相应的数据显示
					searchSetTable(service.Search);						//查询状态的表格设置函数
				}	
			}else {														//正常浏览情况下
				if(Page==0) {
					JOptionPane.showMessageDialog(jFrame, "当前为第一页，无法向前翻页！","提示", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					Page--;
					SetTable();
				}
			}
		}
	});
	rightButton.addActionListener(new ActionListener() {				//向右翻页按钮事件
		public void actionPerformed(ActionEvent e) {
			if(Service.isSearch) {
				if(Service.searchpage==Service.searchpagenub) {
					JOptionPane.showMessageDialog(jFrame, "当前为最后一页，无法向后翻页！","提示", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					Service.searchpage++;
					searchSetTable(service.Search);
				}	
			}else {
				if(Page==pagenub) {
					JOptionPane.showMessageDialog(jFrame, "当前为最后一页，无法向后翻页！","提示", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					Page++;
					SetTable();
				}	
			}
			
		}
	});
	readButton.addActionListener(new ActionListener() {						//读取文件按钮事件
			public void actionPerformed(ActionEvent e) {
				JFileChooserText();											//调用文件读取函数
			}
		});
	saveButton.addActionListener(new ActionListener(){						//保存文件按钮事件
		public void actionPerformed(ActionEvent e) {
			EditJframe.SaveText();											//因为EditJframe类中的保存文件函数可以复用，就省力调用，不在重新写了
			JOptionPane.showMessageDialog(jFrame, "保存成功！","提示", JOptionPane.INFORMATION_MESSAGE);
		
			}							
	});
	quitButton.addActionListener(new ActionListener() {						//退出按钮事件
		public void actionPerformed(ActionEvent e) {
			if(Service.isSearch){											//退出同样要判断是否是查询状态，
				Service.isSearch=false;						//例如，用户查询完结果了，只有一条，想要正常浏览其他数据时就需要退出查询状态，
				Service.isReSet=true;						//也就是说，查询状态点击退出，不会退出程序，只会退出查询状态，然后让isReSet，重新加载一遍数据
				Service.TurnJframe(jFrame);					//窗口转变函数，我发现程序中会用到很多此的窗口转换，就把那一段代码封装成了函数，多次使用
			}else {
				jFrame.dispose();							//正常情况下关闭程序
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

	String path1 = ".\\images\\chakan.jpg";							//让图片作为标签来添加背景，这是利用了将这个图片标签放大遮盖住其他的，就达成了背景的效果
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
	public void searchSetTable(ArrayList<String> list) {	//查询转台的表格设置	
		if(list.size()==0) {								//判断传进来的LiSt参数的大小，如果为零，说明没有数据，表格设置为空就行
			for(int j=7;j>=list.size() ;j--) {				//list参数其实就是Search表，
				jtable.setValueAt(j+1, j%8,0 );				//使用递减方法添加，方便
				jtable.setValueAt(" ", j%8,1 );
				jtable.setValueAt(" ", j%8,2 );
			}	
			JOptionPane.showMessageDialog(jFrame, "未查到任何信息！","提示", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			if(list.size()%8==0) {							//判断查询结果是不是8的倍数，前面解释过了，整除会舍去余数，当是8的倍数时得到结果会大1
				Service.searchpagenub=list.size()/8-1;	
			}else {
				Service.searchpagenub=list.size()/8;
			}
			for(int i=Service.searchpage*8;i<list.size();i++) {			//此程序的所有List表都是放的字符串，尽管是123要用的时候是要转换类型的
				int temp=Integer.parseInt(list.get(i));					//字符串转换函数
				jtable.setValueAt(i%8+Service.searchpage*8+1, i%8,0 );		//设置表格内容，不多解释
				if(!list.get(i).isEmpty()) {								//有一种情况List表为空，不多解释
					jtable.setValueAt(Service.Name.get(temp),i%8,1 );
					jtable.setValueAt(Service.PhoneNumber.get(temp),i%8,2 );				
				}
				if(i+1==(Service.searchpage+1)*8) {							//当设置了8行之后，当前页已经满了，不能设置了，要跳出循环
					break;
				} 
			}
			if((Service.searchpage+1)*8>=list.size()&&list.size()%8!=0) {	//这个条件是，查询数据的总条数不是8的倍数，（是8的倍数就没空白的了）
				for(int j=(Service.searchpagenub+1)*8;j>list.size() ;j--) {	// 并且总页数乘8大于数据总数（searchpagenub是查询结果的总页，是下标计算值，加1为额转换）
					jtable.setValueAt(j, (j-1)%8,0 );
					jtable.setValueAt(" ", (j-1)%8,1 );
					jtable.setValueAt(" ", (j-1)%8,2 );
				}	
			}
		}
	}	
	public void searchText(ArrayList<String> list){							//查询结果添加到Search表中的函数
		for(int i=0;i<list.size();i++) {
			int result=list.get(i).indexOf(jTextField.getText());			//indexOf函数用来判断存不存在用户输入的词条，
			if(result==-1) {												//是省略查询，就是不完全查询，比如我查个刘，有刘的都能查出来
				continue;													//返回值为-1就是没有continue继续查下一条
			}else {
				service.Search.add(i+"");									//将下标添加到Search表，+“”是转换为字符串的最简单方法
			}						
		}				
	}
	public void SortPhoneNumberText() throws IOException {					//手机号排序算法函数，简单的讲就是先把手机号的表PhoneNumber拿出来，
		TextFile textFile1=new TextFile();									//申请一个Long类型的数组，将手机号转换为一个长整形，然后用Arrays.sort
		long[] temp=new long[Service.PhoneNumber.size()];					//对数组排序，排完之后数组已经是排列好的数组了，
		for(int i=0;i<Service.PhoneNumber.size();i++) {						//然后和原表PhoneNumber按排好的顺序比较查找，获取原表的序号，在对Name
			temp [i]=Long.parseLong(Service.PhoneNumber.get(i)); 		 	//等其他表读取，清空文件，重新写入排好的数据
		}																	//你肯定看不懂):(
		Arrays.sort(temp);
		for (int i = 0; i < temp.length; i++) {								//双循环比较
			for (int j = 0; j < Service.PhoneNumber.size(); j++) {
				if(temp[i]==Long.parseLong(Service.PhoneNumber.get(j))) {
					String string=Service.Name.get(j)+"#"+Service.PhoneNumber.get(j)+"#"+
							Service.ZipCode.get(j)+"#"+Service.E_Mail.get(j)+"#"+
							Service.Address.get(j);
					if(i==0) {												//清空，并写入第一条
						textFile1.WriteFile(string);		
					}else {													//写入到其余的，为什么这样是因为第一个写入方法有回车，第二个没回车，
						textFile1.AddFile(string);							//全部用第一个，文件中最后会多一行空白行，这样文件读取会读到一条空的
					}														//添加到list表中是会出IO异常的
				}
			}		
		}
		Service.isSort=true;												//排序了，打开排序状态
		 try {
				textFile1.ForPathreadFile(TextFile.filePath);				//使用临时的文件处理类对象访问，这样当用户连续多次点击时，是不会有数据残留的
				int length=textFile1.getList().size();
				 Service.textExpain(length,textFile1);						//调用函数分割数据，
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}	
		Service.TurnJframe(jFrame);
	}
	public void SortNameText() throws IOException {							//名字排序，名字排序不经过Service类的处理，直接在文件处理类TextFile
		TextFile textFilesFile=new TextFile();								//获取到List表中后直接排序，重写入，因此也不需要排序状态
		Comparator<Object> com=Collator.getInstance(java.util.Locale.CHINA);//名字排序按中文首字母排序
		textFilesFile.readFile();
	    Collections.sort(textFilesFile.getList(),com); 						//排序函数
	     for(int i=0;i<textFilesFile.getList().size();i++){
	    	 if(i==0) {
	    		 textFilesFile.WriteFile(textFilesFile.getList().get(i));	//重写入
				}else {
					textFilesFile.AddFile(textFilesFile.getList().get(i));
				}									 	    	 
        }   
	     Service.TurnJframe(jFrame);
	}
	public void SetTable(){													//正常情况的表格设置
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
	public void JFileChooserText() {								//读取文件的函数
		TextFile textFile1=new TextFile();							//文件处理类TextFile临时对象初始化
		  JFileChooser fileChooser = new JFileChooser();			//创建文件选择器
		  															//设置文件选择器的初始目录为当前目录
		  fileChooser.setCurrentDirectory(new File("."));			
		  fileChooser.setAcceptAllFileFilterUsed(false);			
		  final String  fileENames = ".txt";						//定义扩展名字符串
		  
		  															//设置文件选择器选择显示所有文件
		fileChooser.addChoosableFileFilter(new FileFilter() {
		   public boolean accept(File file) {
		     return true;
		   }
		   public String getDescription() {
		     return "所有文件(*.*)";
		   }
		  });
		  
		fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {	//加载文件选择器事件	 
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
		 fileChooser.showDialog(null, null);									//文件选择器窗口
		 try {
			textFile1.ForPathreadFile(fileChooser.getSelectedFile().getPath());	//获取文件选择器选择文件的路径
			int length=textFile1.getList().size();								//载入路径读取文件，不自动保存
			 Service.textExpain(length,textFile1);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		 JOptionPane.showMessageDialog(jFrame, "读取成功！","提示", JOptionPane.INFORMATION_MESSAGE);
		 Service.TurnJframe(jFrame);
	}

	private void addComponent(JFrame frame, JComponent comp,  //重构函数，添加布局
        GridBagLayout gridbag, GridBagConstraints c) {
        	gridbag.setConstraints(comp, c);  
        	frame.add(comp);
        
    	} 
	public static TableColumnModel getColumn(JTable table,int col  ,int width) {  //重写表格列宽函数
	    TableColumnModel columns = table.getColumnModel();  
	        TableColumn column = columns.getColumn(col); 
	        column.setPreferredWidth(width);      
	    return columns;  
	}  
	public static void main(String[] args)throws IOException {					//程序入口
		new AppJframe();	
	}
}

