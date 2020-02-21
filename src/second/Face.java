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
	          // ʵ�������  
	        clientBtn = new Button("�ͻ���");  
	        serverBtn = new Button("������");  
	        ta = new TextArea("", 10, 50, TextArea.SCROLLBARS_BOTH);  
	        lbl1 = new Label("IP��ַ:");  
	        tfaddress = new TextField("127.0.0.1", 10);  
	       lbl2 = new Label("�˿�:");  
	        tfport = new TextField("8080");  
	        lbl3 = new Label("��������:");  
	        tftype = new TextField(40);  
	        tftype.addKeyListener(new TFListener());  
	        ta.setEditable(false);  
	        //�������м����������  
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
	        //���ø�ʽ  
	        setLocation(400, 250);                //������ʾ����Ļ��λ������  
	        setSize(400, 300);                      //���ô����С  
	        setTitle("����Socket�Ͷ��̱߳�̵��������");  
	        this.setVisible(true);                   //���ô���ɼ�  
	        //�¼���Ӧ  
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
	                tfaddress.setText("��Ϊ������");  
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
	      public static void main(String[] args) {        //������  
	             // TODO Auto-generated method stub  
	             
	             frm = new Face();  
	      }  
	      private class TFListener implements KeyListener {  
	        public void keyPressed(KeyEvent e) {  
	          if (e.getKeyCode() == KeyEvent.VK_ENTER) { //��Enter�����ʾ��������  
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
