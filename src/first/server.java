package first;
import java.io.*;
import java.net.*;
public class server {
	public static int portNo=3333;
	public static void main(String[]args)throws IOException
	{
	ServerSocket server=new ServerSocket(portNo);
	while(true){
	Socket client=server.accept();
	new ServerThread(client).start();}
	}
	}
	class ServerThread extends Thread{
	Socket client=null;
	ServerThread(Socket client){
	this.client=client;}
	public void run(){
	int x=0;int y=0;int sum=0;try
	{
	DataInputStream input=new DataInputStream(client.getInputStream());
	x=input.readInt();y=input.readInt();
	}
	catch(IOException e){
	e.printStackTrace();
	}
	sum=x+y;
	try{
	DataOutputStream output=new DataOutputStream((client.getOutputStream()));
	output.writeInt(sum);//output.flush();
	}
	catch(IOException e) {
	e.printStackTrace();}
	//output.write(sum);
	try{
	client.close();
	}catch(IOException e) {
		e.printStackTrace();
		
	}
	}
	}

