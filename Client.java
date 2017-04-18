package com.xspace.Application.Fileserver;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTree;
import javax.swing.JScrollPane;

public class Client {
	//all stuff
	private static JFrame f = new JFrame();
	private static int WIDTH = 400;
	private static int HEIGHT = 500;
	private static JTextField port;
	private static JTextField Ip;
	//real smart drag of me
	public static Socket clientSocket = null;
	private static JTextField path_write;

	public static void main(String[] args) throws UnknownHostException, IOException {
		//jframe config
		f.setTitle("Client");
		f.setSize(WIDTH, HEIGHT);
		f.getContentPane().setLayout(null);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		
		port = new JTextField();
		port.setBounds(133, 70, 114, 19);
		f.getContentPane().add(port);
		port.setColumns(10);
		
		JButton connect = new JButton("Connect");
		connect.setBounds(133, 101, 117, 25);
		f.getContentPane().add(connect);
		
		JLabel label = new JLabel("");
		label.setVisible(false);
		label.setBounds(12, 164, 376, 15);
		f.getContentPane().add(label);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(57, 72, 70, 15);
		f.getContentPane().add(lblPort);
		
		Ip = new JTextField();
		Ip.setBounds(133, 40, 114, 19);
		f.getContentPane().add(Ip);
		Ip.setColumns(10);
		
		JLabel Ip1 = new JLabel("Ip:");
		Ip1.setBounds(57, 42, 70, 15);
		f.getContentPane().add(Ip1);
		
		JButton disconnect = new JButton("Disconnect");
		disconnect.setBounds(133, 101, 117, 25);
		f.getContentPane().add(disconnect);
		
		JButton send = new JButton("Send");
		send.setEnabled(false);
		send.setVisible(false);
		send.setBounds(12, 101, 117, 25);
		f.getContentPane().add(send);
		
		path_write = new JTextField();
		path_write.setEnabled(false);
		path_write.setBounds(102, 186, 227, 19);
		f.getContentPane().add(path_write);
		path_write.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 219, 213, 184);
		f.getContentPane().add(scrollPane);
		//jtree config
		File file = new File("/");
		FileTreeModel model = new FileTreeModel(file);
		JTree tree = new JTree();
		tree.setModel(model);
		scrollPane.setViewportView(tree);
		
		//send button
		send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//write to an array 
				String files1 = path_write.getText();
				File path = new File(files1);
				long length = path.length();
		        byte[] bytes = new byte[16 * 1024];
		        InputStream in = null;
				try {
					in = new FileInputStream(path);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        OutputStream out = null;
		        DataOutputStream dos = null;
				try {
					out = clientSocket.getOutputStream();
					OutputStream os= clientSocket.getOutputStream();
					//send name.extension
					dos = new DataOutputStream(os);
					dos.writeUTF(files1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//send array
		        int count;
		        try {
					while ((count = in.read(bytes)) > 0) {
					    try {
							out.write(bytes, 0, count);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        //info about no error
		        label.setText("Successfully sent " + path + " to " + clientSocket.getRemoteSocketAddress());
		        try {
					dos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		disconnect.addActionListener(new ActionListener() {
			
			@Override
			//disconnect
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					clientSocket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				disconnect.setVisible(false);
				connect.setVisible(true);
				if (clientSocket.isClosed()) {
				label.setText("");
				label.setText("Disconnecting from " + clientSocket.getRemoteSocketAddress());
				}
			}
		});
		disconnect.setVisible(false);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
		
		
		connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//connection button
				BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
				int port_num = Integer.parseInt(port.getText());
				String ip = Ip.getText();
				try {
					clientSocket = new Socket(ip, port_num);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					label.setText("Error host " + ip + ":" + port.getText() + " does not exist");
			        label.setVisible(true);
			        SwingUtilities.updateComponentTreeUI(f);
				}
		        try {
					DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        try {
					BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        //enable all
		        String server_ip = "" + clientSocket.getRemoteSocketAddress();
		        String s = server_ip.replaceAll("localhost/", "");
		        label.setText("Now connected to " + s);
		        label.setVisible(true);
		        SwingUtilities.updateComponentTreeUI(f);
		        connect.setVisible(false);
		        disconnect.setVisible(true);
				send.setEnabled(true);
				send.setVisible(true);
				path_write.setEnabled(true);
			}
			
		});
	}
}
