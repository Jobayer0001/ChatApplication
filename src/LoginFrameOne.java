import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;


class LoginFrameOne extends JFrame {
    private JLabel label1,label2;
    private JTextField field1;
    private JPasswordField field2;
    private JButton login,signup;
    private Container c;
    LoginFrameOne()
    {
        this.setTitle("Login Frame");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(200,100,400,400);

        initComponent();
    }

    public void initComponent(){
        c=this.getContentPane();
        c.setLayout(null);

        label1 = new JLabel("Username");
        label1.setBounds(53,105,60,20);
        c.add(label1);


        field1 = new JTextField();
        field1.setBounds(142,102,148,25);
        c.add(field1);


        label2 = new JLabel("Password");
        label2.setBounds(53,159,60,20);
        c.add(label2);


        field2 = new JPasswordField();
        field2.setBounds(142,157,148,25);
        field2.setEchoChar('*');
        c.add(field2);

        login = new JButton("Login");
        login.setBounds(100,250,70,25);
        c.add(login);

        signup = new JButton("Sign Up");
        signup.setBounds(206,250,80,25);
        c.add(signup);



        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = field1.getText();
                String pass = field2.getText();

                String np = name+" "+pass;

                if(name.equals("") && pass.equals("")){
                    JOptionPane.showMessageDialog(null,"Invalid username or password");
                }
                else{
                    int i;
                    String[] sp;

                    try{
                        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ASUS\\IdeaProjects\\ChatApplication\\src\\data.txt"));
                        String line = reader.readLine();
                        while (line!=null){
                             sp = line.split("  ");
                            for(i=0;i<sp.length;i++){
                                if(sp[i].equals(np)){
                                    JOptionPane.showMessageDialog(null,"Successfully login");
                                    dispose();
                                    UserOne one = new UserOne();
                                    one.setVisible(true);
                                }

                            }

                           line = reader.readLine();
                        }
                        reader.close();
                        if(line==null){
                            JOptionPane.showMessageDialog(null,"Invalid username or password");
                        }

                    }catch (Exception ee){
                        ee.printStackTrace();
                    }
                }
            }
        });



        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                SignUp reg = new SignUp();
                reg.setVisible(true);
            }
        });

    }

}

