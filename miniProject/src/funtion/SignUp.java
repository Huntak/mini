package funtion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.*;

public class SignUp extends JDialog implements ActionListener{
	private JPanel p1, p2;
	private JTextField idTf, pwTf, nameTf, ageTf, phoneTf;
	private JButton okButton, cancelButton;
	private Members member;
	
	public SignUp(Frame parent, String str){
		super(parent, str, true);
		setBounds(0, 0, 200, 200);
		setLayout(new BorderLayout());
		setSize(300, 200);
		addWindowListener(new MyWinListener());
		
		p1 = new JPanel(new GridLayout(5, 2));
		idTf = new JTextField();
		pwTf = new JTextField();
		nameTf = new JTextField();
		ageTf = new JTextField();
		phoneTf = new JTextField();
		
		p1.add(new JLabel("새 아이디 : ", JLabel.CENTER));
		p1.add(idTf);
		p1.add(new JLabel("새 비밀번호 : ", JLabel.CENTER));
		p1.add(pwTf);
		p1.add(new JLabel("이름 : ", JLabel.CENTER));
		p1.add(nameTf);
		p1.add(new JLabel("나이 : ", JLabel.CENTER));
		p1.add(ageTf);
		p1.add(new JLabel("전화번호 : ", JLabel.CENTER));
		p1.add(phoneTf);
		
		p2 = new JPanel(new GridLayout(1, 2));
		okButton = new JButton("회원가입");
		okButton.addActionListener(this);
		cancelButton = new JButton("취소");
		cancelButton.addActionListener(this);
		
		p2.add(okButton);
		p2.add(cancelButton);
		
		add(p1, BorderLayout.CENTER);
		add(p2, BorderLayout.SOUTH);
		
		
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okButton){
			member = new Members(idTf.getText(), pwTf.getText(), nameTf.getText(), Integer.parseInt(ageTf.getText()), phoneTf.getText(), 0, 0, 0, 0, 0, true);
			dispose();
		}
		if(e.getSource() == cancelButton)
			dispose();
	}
	
	public Members newMember(){
		return member;
	}
	

	class MyWinListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			dispose();
		}
	}
	
}