package second;
import java.net.*;  
import java.io.*;  
import javax.swing.Timer;  
public class Client extends Thread {
	  Socket skt;                                  // 用于客户端的连接  
	    InetAddress host;                        // 主机地址  
	    int port;                                     // 端口号  
	    BufferedReader theInputStream;  
	    PrintStream theOutputStream;  
	    String readin;  
	    Face chat;  
	    public Client(String ip, int p, Face chat) {  
	        try {  
	            host = InetAddress.getByName(ip);            // 获取IP地址  
	            port = p;                                            // 获取端口号  
	            this.chat = chat;  
	        } catch (IOException e) {  
	            chat.ta.append(e.toString());  
	        }      }  
	    public void run() {  
	        try {  
	            chat.ta.append("准备连线，稍后！");  
	           skt = new Socket(host, port);                     // 新建Socket对象  
	           chat.ta.append("成功\n");                   // 缓冲区末尾添加字符串  
	            theInputStream = new BufferedReader(new InputStreamReader(skt.getInputStream()));  
	            theOutputStream = new PrintStream(skt.getOutputStream());  
	            while (true) {  
	                readin = theInputStream.readLine();  
	                chat.ta.append(readin + "\n");  
	            }  
	        } catch (SocketException e) {  
	            chat.ta.append("未连上！\n");  
	           chat.clientBtn.setEnabled(true);  
	           chat.serverBtn.setEnabled(true);  
	            chat.tfaddress.setEnabled(true);  
	           chat.tfport.setEnabled(true);  
	           try {  
	                skt.close();  
	           } catch (IOException err) {  
	                chat.ta.append(err.toString());  
	            }  
	        } catch (IOException e) {  
	            chat.ta.append(e.toString());  
	        }  
	    }  
	    public void dataout(String data) {  
	        theOutputStream.println(data);  
	    } 
}
