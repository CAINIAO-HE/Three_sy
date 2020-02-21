package second;
import java.net.*;  
import java.io.*;  
import javax.swing.Timer;  
public class Client extends Thread {
	  Socket skt;                                  // ���ڿͻ��˵�����  
	    InetAddress host;                        // ������ַ  
	    int port;                                     // �˿ں�  
	    BufferedReader theInputStream;  
	    PrintStream theOutputStream;  
	    String readin;  
	    Face chat;  
	    public Client(String ip, int p, Face chat) {  
	        try {  
	            host = InetAddress.getByName(ip);            // ��ȡIP��ַ  
	            port = p;                                            // ��ȡ�˿ں�  
	            this.chat = chat;  
	        } catch (IOException e) {  
	            chat.ta.append(e.toString());  
	        }      }  
	    public void run() {  
	        try {  
	            chat.ta.append("׼�����ߣ��Ժ�");  
	           skt = new Socket(host, port);                     // �½�Socket����  
	           chat.ta.append("�ɹ�\n");                   // ������ĩβ����ַ���  
	            theInputStream = new BufferedReader(new InputStreamReader(skt.getInputStream()));  
	            theOutputStream = new PrintStream(skt.getOutputStream());  
	            while (true) {  
	                readin = theInputStream.readLine();  
	                chat.ta.append(readin + "\n");  
	            }  
	        } catch (SocketException e) {  
	            chat.ta.append("δ���ϣ�\n");  
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
