package second;
import java.awt.*;  
import java.awt.event.*;  
public class Face extends Frame{
	  private static final long serialVersionUID = 1L;  
	   Button clientBtn, serverBtn;  
	    TextArea ta;  
	    TextField tfaddress, tfport, tftype;  
	   Label lbl1,lbl2,lbl3;  
	    int port;  
	   Client client;  
	    Server server;  
	    boolean iamserver;  
	    static Face frm;  
	    public Face() {  
	          // 实例化组件  
	        clientBtn = new Button("客户端");  
	        serverBtn = new Button("服务器");  
	        ta = new TextArea("", 10, 50, TextArea.SCROLLBARS_BOTH);  
	        lbl1 = new Label("IP地址:");  
	        tfaddress = new TextField("127.0.0.1", 10);  
	       lbl2 = new Label("端口:");  
	        tfport = new TextField("8080");  
	        lbl3 = new Label("发送内容:");  
	        tftype = new TextField(40);  
	        tftype.addKeyListener(new TFListener());  
	        ta.setEditable(false);  
	        //向容器中加入以上组件  
	        setLayout(new   FlowLayout());  
	        add(lbl1);  
	        add(tfaddress);  
	        add(lbl2);  
	        add(tfport);  
	       add(clientBtn);  
	       add(serverBtn);  
	        add(ta);  
	       add(lbl3);  
	        add(tftype);  
	        //设置格式  
	        setLocation(400, 250);                //窗口显示再屏幕的位置坐标  
	        setSize(400, 300);                      //设置窗体大小  
	        setTitle("基于Socket和多线程编程的聊天程序");  
	        this.setVisible(true);                   //设置窗体可见  
	        //事件响应  
	        clientBtn.addActionListener(new ActionListener() {  
	            public void actionPerformed(ActionEvent e) {  
	                port = Integer.parseInt(tfport.getText());  
	               client = new Client(tfaddress.getText(), port, frm);  
	                client.start();  
	                tfaddress.setEnabled(false);  
	                tfport.setEnabled(false);  
	                serverBtn.setEnabled(false);  
	                clientBtn.setEnabled(false);  
	            }  
	       });  
	        serverBtn.addActionListener(new ActionListener() {  
	            public void actionPerformed(ActionEvent e) {  
	                port = Integer.parseInt(tfport.getText());  
	                server = new Server(port, frm);  
	                server.start();  
	                iamserver = true;  
	                tfaddress.setText("成为服务器");  
	               tfaddress.setEnabled(false);  
	                tfport.setEnabled(false);  
	                serverBtn.setEnabled(false);  
	                clientBtn.setEnabled(false);  
	            }  
	        });  
	        addWindowListener(new WindowAdapter() {  
	            public void windowClosing(WindowEvent e) {  
	                System.exit(0);  
	            }          });     }  
	      public static void main(String[] args) {        //主方法  
	             // TODO Auto-generated method stub  
	             
	             frm = new Face();  
	      }  
	      private class TFListener implements KeyListener {  
	        public void keyPressed(KeyEvent e) {  
	          if (e.getKeyCode() == KeyEvent.VK_ENTER) { //按Enter输出显示聊天内容  
	                ta.append(">" + tftype.getText() + "\n");  
	               if (iamserver)  
	                  server.dataout(tftype.getText());  
	                else  
	                    client.dataout(tftype.getText());  
	                tftype.setText("");  
	            }  
	        }  
	        public void keyTyped(KeyEvent e) {  
	        }  
	        public void keyReleased(KeyEvent e) {  
	        }   
	        } 
}
