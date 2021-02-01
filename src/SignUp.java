import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SignUp extends JFrame {
    private JLabel username,mobile,password;
    private JTextField ufield,mfield;
    private JPasswordField passfield;
    private JButton create,reset,back;
    private Container c;
    SignUp(){
        this.setTitle("Login Frame");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(300,100,400,400);
        this.setResizable(false);

        registration();
    }
    public void registration(){
        c=this.getContentPane();
        c.setLayout(null);

        username = new JLabel("Username");
        username.setBounds(53,105,60,20);
        c.add(username);
        ufield = new JTextField();
        ufield.setBounds(142,102,148,25);
        c.add(ufield);


        mobile = new JLabel("Mobile");
        mobile.setBounds(53,159,60,20);
        c.add(mobile);
        mfield = new JTextField();
        mfield.setBounds(142,157,148,25);
        c.add(mfield);


        password = new JLabel("Password");
        password.setBounds(53,213,60,20);
        c.add(password);
        passfield = new JPasswordField();
        passfield.setBounds(142,213,148,25);
        passfield.setEchoChar('*');
        c.add(passfield);

        create = new JButton("Create");
        create.setBounds(90,270,90,25);
        c.add(create);

        back = new JButton("Back");
        back.setBounds(0,0,70,25);
        c.add(back);


        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                LoginFrameOne login = new LoginFrameOne();
                login.setVisible(true);

            }
        });


        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = ufield.getText();
                String mob = mfield.getText();
                String pass = passfield.getText();

                try {
                    FileWriter writer = new FileWriter("C:\\Users\\ASUS\\IdeaProjects\\Client\\src\\data.txt",
                            true);
                        writer.write(name+" "+pass+"  "+mob);
                        writer.write("\n");
                        writer.close();

                }catch (IOException ea){
                    ea.printStackTrace();
                }

                JOptionPane.showMessageDialog(null,"Registered");

            }
        });


        reset = new JButton("Reset");
        reset.setBounds(206,270,80,25);
        c.add(reset);

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ufield.setText("");
                mfield.setText("");
                passfield.setText("");
            }
        });

    }
}
