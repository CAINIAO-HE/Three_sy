package first;
import java.net.*;
import java.io.*;
public class client {
	public static int portNo=3333;
	public static void main(String[]args)throws IOException{
	InetAddress addr=InetAddress.getByName("localhost");
	Socket socket=new Socket(addr,portNo);
	DataOutputStream output=new DataOutputStream(socket.getOutputStream());
	output.writeInt(12);output.writeInt(23);
	DataInputStream input=new DataInputStream(socket.getInputStream());
	int sum=input.readInt();socket.close();
	System.out.println("sum is:"+sum);
	}
}
