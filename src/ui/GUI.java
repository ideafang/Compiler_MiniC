package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import parser.*;

public class GUI {
	public static void main(String[] args) throws FileNotFoundException {
		TestGUI ui = new TestGUI();
		ui.init();
	}
}

class TestGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	static String fileName = null;

	parser parser;

	static int errorNum = 0;

	public TestGUI() throws FileNotFoundException {
		super("编译原理实习");
		parser = new parser(new FileInputStream("code.c"));
	}

	public void init() {
		this.setBounds(300, 300, 600, 900);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
		});
		layoutUI();
		this.setVisible(true);
		setLocationRelativeTo(null);
	}

	private void layoutUI() {

		// 对象实例化
		JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
		// 容器
		Container container = this.getLayeredPane();
		// 对象化面板
		JPanel combop = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();

		FileDialog d1 = new FileDialog(this, "打开", FileDialog.LOAD);

		Font font = new Font("SansSerif", Font.BOLD, 20);

		tab.add(p1, "源文件");
		tab.add(p5, "词法分析");
		tab.add(p2, "语法分析");
		tab.add(p3, "语义分析");

		tab.setFont(font);

		p1.setLayout(new BorderLayout());
		p2.setLayout(new BorderLayout());
		p3.setLayout(new BorderLayout());
		p4.setLayout(new BorderLayout());
		p5.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JTextArea errorMessage = new JTextArea(10, 10);
		panel.add(errorMessage, BorderLayout.CENTER);
		JScrollPane scrollPanee = new JScrollPane(errorMessage);
		scrollPanee.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPanee);
		JLabel console = new JLabel("控制台");
		console.setFont(font);
		errorMessage.setFont(font);
		panel.add(console, BorderLayout.NORTH);

		Button btnNewButton = new Button("打开");
		btnNewButton.setFont(font);
		combop.add(btnNewButton);

		Button btnNewButton_1 = new Button("保存");
		btnNewButton_1.setFont(font);
		combop.add(btnNewButton_1);

		Button btnNewButton_2 = new Button("分析");
		btnNewButton_2.setFont(font);
		combop.add(btnNewButton_2);

		JTextArea textArea = new JTextArea();
		textArea.setFont(font);
		p1.add(textArea);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		p1.add(scrollPane);

		JTextArea textArea2 = new JTextArea();
		textArea2.setFont(font);
		textArea2.setEditable(false);
		p2.add(textArea2);

		JScrollPane scrollPane2 = new JScrollPane(textArea2);
		scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		p2.add(scrollPane2);

		JTextArea textArea3 = new JTextArea();
		textArea3.setFont(font);
		textArea3.setEditable(false);
		p3.add(textArea3);

		JScrollPane scrollPane3 = new JScrollPane(textArea3);
		scrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		p3.add(scrollPane3);

		JTextArea textArea4 = new JTextArea();
		textArea4.setFont(font);
		textArea4.setEditable(false);
		p4.add(textArea4);

		JScrollPane scrollPane4 = new JScrollPane(textArea4);
		scrollPane4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		p4.add(scrollPane4);
		
		JTextArea textArea5 = new JTextArea();
		textArea5.setFont(font);
		textArea5.setEditable(false);
		p5.add(textArea5);

		JScrollPane scrollPane5 = new JScrollPane(textArea5);
		scrollPane5.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		p5.add(scrollPane5);

		container.setLayout(new BorderLayout());
		container.add(combop, BorderLayout.NORTH);
		container.add(tab, BorderLayout.CENTER);
		container.add(panel, BorderLayout.SOUTH);

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				d1.setVisible(true);
				System.out.println(d1.getDirectory() + d1.getFile());
				fileName = d1.getDirectory() + d1.getFile();
				File file = new File(fileName);
				Long filelength = file.length();
				byte[] filecontent = new byte[filelength.intValue()];
				try {
					FileInputStream in = new FileInputStream(file);
					in.read(filecontent);
					in.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				textArea.setText(new String(filecontent));
				errorMessage.setText("Information: OpenFile Success!");
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					FileOutputStream out = new FileOutputStream(fileName);
					out.write(textArea.getText().getBytes());
					out.close();
				} catch (IOException e3) {
				}
				errorMessage.setText("Information: SaveFile Success!");
			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				errorNum = parser.analyzer(fileName);
				parser.yufa(fileName);
				parser.cifa(fileName);
				
				File file1 = new File("cifa.txt");
				Long filelength1 = file1.length();
				byte[] filecontent1 = new byte[filelength1.intValue()];
				try {
					FileInputStream in = new FileInputStream(file1);
					in.read(filecontent1);
					in.close();
				} catch (FileNotFoundException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				textArea5.setText(new String(filecontent1));
				
				File file2 = new File("yufa.txt");
				Long filelength2 = file2.length();
				byte[] filecontent2 = new byte[filelength2.intValue()];
				try {
					FileInputStream in = new FileInputStream(file2);
					in.read(filecontent2);
					in.close();
				} catch (FileNotFoundException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				textArea2.setText(new String(filecontent2));
				
				File file3 = new File("yuyi.txt");
				Long filelength3 = file3.length();
				byte[] filecontent3 = new byte[filelength3.intValue()];
				try {
					FileInputStream in = new FileInputStream(file3);
					in.read(filecontent3);
					in.close();
				} catch (FileNotFoundException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				textArea3.setText(new String(filecontent3));

				if (errorNum != 0) {
					File file4 = new File("analyzer.txt");
					Long filelength4 = file4.length();
					byte[] filecontent4 = new byte[filelength4.intValue()];
					try {
						FileInputStream in = new FileInputStream(file4);
						in.read(filecontent4);
						in.close();
					} catch (FileNotFoundException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					errorMessage.setText("Compile finished\n");
					errorMessage.append(errorNum + "Error!\n\n");
					errorMessage.append(new String(filecontent4));
				}else {
					errorMessage.setText("Compile finished\n");
					errorMessage.append("success!\n\n");
				}
			}
		});
	}
}
