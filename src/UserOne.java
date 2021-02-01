import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class UserOne extends JFrame {
    private JButton sendButton;
    private JTextField textField;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private Container c;

    String name = "Rashed";

    BufferedReader reader;
    BufferedWriter writer;

    UserOne() {

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Rashed's chat box");
        this.setResizable(false);
        this.setBounds(150,100,400,500);

        initComponent();
    }

    public void initComponent() {
        c = this.getContentPane();
        c.setLayout(null);


        textArea = new JTextArea();
        textArea.setBounds(0, 0, 400, 434);
        textArea.setEditable(false);
        textArea.setBackground(Color.orange);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        c.add(textArea);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(0, 0, 400, 434);
        c.add(scrollPane);


        textField = new JTextField();
        textField.setBounds(01, 434, 307, 30);
        c.add(textField);

        sendButton = new JButton("Send");
        sendButton.setBounds(312, 434, 70, 30);
        c.add(sendButton);


        try {
            Socket socket = new Socket("127.0.0.1", 9090);

            InputStreamReader ireader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(ireader);

            OutputStreamWriter owriter = new OutputStreamWriter(socket.getOutputStream());
            writer = new BufferedWriter(owriter);


            writer.write(name + "\n" );
            writer.flush();

            sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String msg = textField.getText()+"\n";
                            textField.setText("");
                            writer.write(msg);
                            writer.flush();

                    } catch (IOException ea) {
                        ea.printStackTrace();
                    }
                }
            });


            Thread thread = new Thread(){
                public void run(){
                    try{
                        String line = reader.readLine() + "\n";
                        while (line != null){
                            textArea.append(line);
                            line = reader.readLine() + "\n";
                        }
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
            };
            thread.start();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}



