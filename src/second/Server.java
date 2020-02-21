package second;
import java.awt.*;  
import java.net.*;  
import java.io.*;  
public class Server extends Thread {
	ServerSocket skt;   // ServerSocket类监听进入的连接，为每个新的连接产生一个Socket对象        
	   Socket Client[ ]=new Socket[10];  
	   Socket Client1=null;  
	    int i = 0;  
	   TextArea in;  
	   int port,k=0,l=0;  
	   PrintStream theOutputStream;  
	   Face chat;  
	    public Server(int port, Face chat) {  
	        try {  
	           this.port = port;  
	           skt = new ServerSocket(port);  
	            this.chat = chat;  
	        } catch (IOException e) {  
	            chat.ta.append(e.toString());  
	        }     }  
	    public void run() {  
	       chat.ta.append("等待连线......");  
	       while (true) {  
	            try {  
	           Client[k] = skt.accept();                                                             //当有客户端连接时就新建一个子线程  
	          if (i < 2) {  
	             ServerThread server[] = new ServerThread[10];  
	              server[k]= new ServerThread(Client[k], this.chat, i);  
	                 l=server.length;  
	                server[k].start();  
	            chat.ta.append("客户端"+ Client[k].getInetAddress() + "已连线\n");  
	                 
	               for(int j=0;j<=k;j++) { 
	               theOutputStream = new PrintStream(server[k].getClient().getOutputStream()); } 
	               i = server[k].getI();  
	                k++;  
	           } else {  
	                //theOutputStream = new PrintStream(null);  
	            }  
	      } catch (SocketException e) {  
	            } catch (IOException e) {  
	                chat.ta.append(e.toString());  
	           }          }     }  
	    public void dataout(String data) {  
	        //for(int j=0;j<l;j++)  
	       theOutputStream.println(data);  
	    }  }  
	class ServerThread extends Thread {  
	   ServerSocket skt;  
	    Socket Client;  
	    TextArea in;  
	   int port,i;  
	    BufferedReader theInputStream;  
	    PrintStream theOutputStream;  
	    String readin;  
	   Face chat;  
	//服务端子线程  
	    public ServerThread(Socket s, Face chat, int i) {  
	        this.i = ++i;  
	       Client = s;  
	        this.chat = chat;  
	    }  
	    public int getI() {  
	        return this.i;  
	   }  
	   public Socket getClient() {  
	       return this.Client;  
	   }  
	    public void run() {  
	          try {  
	           theInputStream = new BufferedReader(new InputStreamReader(Client  
	                  .getInputStream()));  
	            theOutputStream = new PrintStream(Client.getOutputStream());  
	           while (true) {  
	                readin = theInputStream.readLine();  
	               chat.ta.append(readin + "\n");  
	          }  
	        } catch (SocketException e) {  
	            chat.ta.append("连线中断！\n");  
	                          // 设置组件可用性  
	           chat.clientBtn.setEnabled(true);  
	            chat.serverBtn.setEnabled(true);  
	           chat.tfaddress.setEnabled(true);  
	            chat.tfport.setEnabled(true);  
	           try {  
	               i--;
	               skt.close();  
	               Client.close();  
	            } catch (IOException err) {  
	               chat.ta.append(err.toString());  
	            }  
	        } catch (IOException e) {  
	           chat.ta.append(e.toString());  
	       }     }  
	    public void dataout(String data) {  
	       theOutputStream.println(data);  
	    } 
}
