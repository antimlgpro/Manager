package com.xspace.Application.Fileserver;
import java.io.*;
import java.net.*;

public class Dummy {
	//just a dummy
	public static boolean stop = false;
	
    public static void main(String args[]) throws Exception {
    	InputStream in = null;
    	OutputStream out = null;
        ServerSocket welcomeSocket = new ServerSocket(6789);
        Manager.term.setText(Manager.term.getText() + "\nSocket has started");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        while(true) {
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Connection from " + connectionSocket.getRemoteSocketAddress());
            if (connectionSocket.getRemoteSocketAddress()!=null) {
            String s = "" + connectionSocket.getRemoteSocketAddress();
            String ip = s.substring(0, s.indexOf(":"));
            Manager.term.setText(Manager.term.getText() + "\nConnection from " + ip);
            }
            
            try {
                in = connectionSocket.getInputStream();
            } catch (IOException ex) {
                System.out.println("Can't get socket input stream. ");
                Manager.term.setText(Manager.term.getText() + "Can't get socket input stream. ");
            }
            DataInputStream dis = new DataInputStream(in);

            String name = dis.readUTF();
            System.out.println(name);
            Manager.term.setText(Manager.term.getText() + "\nReceived " + name + " from " + connectionSocket.getRemoteSocketAddress());
            String name2 = name.replaceAll("/home/ludvig/", "");




            try {
                out = new FileOutputStream("/home/ludvig/1" + name2);
            } catch (FileNotFoundException ex) {
                System.out.println("File not found. ");
                Manager.term.setText(Manager.term.getText() + "\nFile not found. ");
            }

            byte[] bytes = new byte[16*1024];

            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }
			if(stop==true) {
            dis.close();
            out.close();
            in.close();
            welcomeSocket.close();
            }
        }
        
    }
}
