package com.xspace.Application.Fileserver;

import javax.swing.JFrame;

import javax.naming.directory.DirContext;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import com.xspace.Application.Klocka.Klocka;
import com.xspace.Application.Test.Test2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.TextArea;
import java.awt.Panel;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;

public class Manager {
	private static JFrame f = new JFrame();
	private JFrame frame;
	public static TextArea term;
	private static JTextField term_input;
	private static JButton Stop;
	private static JLabel image;
	private static boolean serveron = false;
	public static JLabel time = null;
	private static Process p = null;
	public static String dir = "/";
	private static JTextField Username;
	private static JPasswordField password;
	private static String lang_name = "eng";

	public static void main(String[] args) {
		f.setTitle("Manager");
		f.getContentPane().setLayout(null);
		f.setSize(800, 800);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JButton Start = new JButton("Start");
		Start.setBounds(137, 173, 117, 25);
		f.getContentPane().add(Start);
		
		term = new TextArea();
		term.setFont(new Font("FreeMono", Font.BOLD | Font.ITALIC, 12));
		term.setForeground(Color.GREEN);
		term.setBackground(Color.BLACK);
		term.setEditable(false);
		term.setBounds(257, 10, 396, 463);
		f.getContentPane().add(term);
		
		term_input = new JTextField();
		term_input.setEnabled(false);
		term_input.setBounds(290, 479, 310, 19);
		f.getContentPane().add(term_input);
		term_input.setColumns(10);
		
		Stop = new JButton("Stop");
		Stop.setBounds(137, 243, 117, 25);
		f.getContentPane().add(Stop);
		Stop.setEnabled(false);
		
		image = new JLabel(new ImageIcon("/home/ludvig/workspace/Xspace/src/com/xspace/Application/Fileserver/image.png"));
		image.setBounds(109, 543, 739, 173);
		f.getContentPane().add(image);
		
		time = new JLabel("");
		time.setBounds(717, 44, 195, 15);
		f.getContentPane().add(time);
		
		

		
		
		Thread two = new Thread(){
	          public void run() {
	        	  try {
					Klocka.main(args);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	          }  	      
	          };
	      two.start();
		
		
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Start.addActionListener(new ActionListener() {
			
			@Override
			//just for look
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				term.setText("\nServer Starting.");
				SwingUtilities.updateComponentTreeUI(f);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				term.setText(term.getText() + "\nServer Starting..");
				SwingUtilities.updateComponentTreeUI(f);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				term.setText(term.getText() + "\nServer Starting...");
				SwingUtilities.updateComponentTreeUI(f);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				term_input.setEnabled(true);
				Start.setEnabled(false);
				Stop.setEnabled(true);
			}
			});
		term_input.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent et) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					//make commands >command
					term.setText(term.getText() + "\n" + dir + ">" + term_input.getText());
					SwingUtilities.updateComponentTreeUI(f);
					try {
						Thread.sleep(500);
					} catch (InterruptedException ey) {
						// TODO Auto-generated catch block
						ey.printStackTrace();
					}
					// command code
					//
						if (term_input.getText().contains("ssh") && term_input.getText().contains("@")) {
							term.setText(term.getText() + "\nConnecting to ip");
							SwingUtilities.updateComponentTreeUI(f);
						}
						if (term_input.getText().contains("ssh") && !term_input.getText().contains("@")) {
							term.setText(term.getText() + "\nUsage ssh name@ip");
							SwingUtilities.updateComponentTreeUI(f);
						}
						if (term_input.getText().contains("help")) {
							term.setText(term.getText() + "\nCommands:\n\nssh remote connet to computer\n\nhelp show this text\n\nsort sorts files in Downloads\n\nserver starts a socket server on your ip at 6789\n\nclient starts client to socket you started\n\nip returns your external ip\n\nclear clears the screen");
							SwingUtilities.updateComponentTreeUI(f);
						}
						if (term_input.getText().contains("sort")) {
							term.setText(term.getText() + "\nSorting Downloads.");
							try {
								Thread.sleep(500);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							term.setText(term.getText() + "\nSorting Downloads..");
							try {
								Thread.sleep(500);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							Test2.main(args);
							SwingUtilities.updateComponentTreeUI(f);
						}
						if (term_input.getText().contains("server")) {
							term.setText(term.getText() + "\nStarting socket.");
							try {
								Thread.sleep(500);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							term.setText(term.getText() + "\nStarting socket..");
							try {
								Thread.sleep(500);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							term.setText(term.getText() + "\nStarting socket...");
							try {
								Thread.sleep(500);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							Thread one = new Thread(){
						          public void run() {
						        	  try {
										Dummy.main(args);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
						          }
						      };
						      one.start();
						
							SwingUtilities.updateComponentTreeUI(f);
						}
						if (term_input.getText().contains("client") || serveron == true) {
							try {
								Client.main(args);
							} catch (UnknownHostException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							SwingUtilities.updateComponentTreeUI(f);
						}
						if (term_input.getText().contains("ip")) {
							URL whatismyip = null;
							try {
								whatismyip = new URL("http://checkip.amazonaws.com");
							} catch (MalformedURLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							BufferedReader in = null;
							try {
								in = new BufferedReader(new InputStreamReader(
								                whatismyip.openStream()));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							String ip = null;
							try {
								ip = in.readLine();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} //you get the IP as a String
							term.setText(term.getText() + "\n" + ip);
						}
						if (term_input.getText().contains("clear")) {
							term.setText(null);
						}
						if (term_input.getText().contains("cd") && term_input.getText().contains("/")) {
							dir = term_input.getText().replaceAll("cd", "").replaceAll(" ", "");
						}
						if (term_input.getText().contains("cd") && !term_input.getText().contains("/")) {
							term.setText(term.getText() + "\nUsage cd path");
						}
						if (term_input.getText().contains("dir")) {
							File folder = new File(dir);
							File[] listOfFiles = folder.listFiles();

							    for (int i = 0; i < listOfFiles.length; i++) {
							      if (listOfFiles[i].isFile()) {
							        //System.out.println("File " + listOfFiles[i].getName());
							        term.setText(term.getText() + "\n " + listOfFiles[i].getName());
							      } else if (listOfFiles[i].isDirectory()) {
							        //System.out.println("Directory " + listOfFiles[i].getName());
							        term.setText(term.getText() + "\n" + listOfFiles[i].getName());
							      }
							      
							    }
							    if (term_input.getText().contains("lang")) {
									String lang = term_input.getText();
									lang = lang.replaceAll("lang", "");
									lang_name = lang;
								}
						}
						term_input.setText("");
				}
			}
		});
		Stop.addActionListener(new ActionListener() {
			
			@Override
			//just for look
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				term.setText(term.getText() + "\nShuting down.");
				SwingUtilities.updateComponentTreeUI(f);
				try {
					Thread.sleep(500);
				} catch (InterruptedException er) {
					// TODO Auto-generated catch block
					er.printStackTrace();
				}
				term.setText(term.getText() + "\nShuting down..");
				SwingUtilities.updateComponentTreeUI(f);
				try {
					Thread.sleep(500);
				} catch (InterruptedException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}
				term.setText(term.getText() + "\nShuting down...");
				SwingUtilities.updateComponentTreeUI(f);
				try {
					Thread.sleep(500);
				} catch (InterruptedException ef) {
					// TODO Auto-generated catch block
					ef.printStackTrace();
				}
				Stop.setEnabled(false);
				term_input.setEnabled(false);
				Start.setEnabled(true);
				Dummy.stop = true;
			}
		});	
		
	}
}