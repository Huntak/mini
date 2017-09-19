package funtion;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

public class Client extends JFrame implements ActionListener, ItemListener {

	private JPanel beforLogin;
	private JPanel top;
	private JPanel center;
	private JPanel bottom;
	private JButton btlogin;
	private JComboBox laSeatNo2;
	private JLabel laID;
	private JLabel laPW;
	private JTextField tfID;
	private JTextField tfPW;

	private String seatNo;

	private JPanel afterLogin;
	private JLabel userNumLb;
	private JLabel userNameLb;
	private JLabel userIdLb;
	private JLabel startTimeLb;
	private JLabel finishTimeLb;
	private JLabel fareTypeLb;
	private JLabel usingTimeLb;
	private JLabel serviceLb;
	private JLabel usingPriceLb;
	private JLabel productPriceLb;
	private JLabel totalPriceLb;

	private JLabel userNumLb2;
	private JLabel userNameLb2;
	private JLabel userIdLb2;
	private JLabel startTimeLb2;
	private JLabel finishTimeLb2;
	private JLabel fareTypeLb2;
	private JLabel usingTimeLb2;
	private JLabel serviceLb2;
	private JLabel usingPriceLb2;
	private JLabel productPriceLb2;
	private JLabel totalPriceLb2;

	private Socket client;
	private DataInputStream getClientDataInStream;
	private DataOutputStream getClientDataOutStream;
	private String answer;
	private String[] answerArray;

	private Boolean loginYN;
	private String pwd;
	private int hour, minute, second;
	private String startTime = null;
	private Boolean finish = false;

	public Client() {

		this.setBounds(800, 10, 600, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1, 1));

		beforLogin = new JPanel(new GridLayout(3, 1));
		beforLogin.setBounds(800, 10, 600, 500);

		top = new JPanel(new FlowLayout());
		center = new JPanel(new GridLayout(2, 2));
		bottom = new JPanel(new BorderLayout());

		String[] nums = { "No.0", "No.1", "No.2", "No.3", "No.4", "No.5", "No.6", "No.7", "No.8", "No.9" };
		laSeatNo2 = new JComboBox(nums);
		laSeatNo2.setSelectedIndex(0);

		seatNo = (String) laSeatNo2.getSelectedItem();
		laSeatNo2.setFont(new Font("휴먼모음T", Font.PLAIN, 50));
		laSeatNo2.addItemListener(this);
		top.add(laSeatNo2, BorderLayout.CENTER);

		laID = new JLabel("     아이디 : ");
		laID.setFont(new Font("휴먼모음T", Font.PLAIN, 40));
		center.add(laID, BorderLayout.CENTER);
		tfID = new JTextField(20);
		tfID.setFont(new Font("휴먼모음T", Font.PLAIN, 20));
		tfID.setText("Seowanwon");
		center.add(tfID);
		laPW = new JLabel("     패스워드 : ");
		laPW.setFont(new Font("휴먼모음T", Font.PLAIN, 40));
		center.add(laPW);
		tfPW = new JTextField(20);
		tfPW.setFont(new Font("휴먼모음T", Font.PLAIN, 20));
		
		tfPW.setText("SeoPW");

		center.add(tfPW);

		btlogin = new JButton("로그인");
		btlogin.setFont(new Font("휴먼모음T", Font.PLAIN, 40));
		btlogin.addActionListener(this);
		bottom.add(btlogin);

		beforLogin.add(top, BorderLayout.NORTH);
		beforLogin.add(center, BorderLayout.CENTER);
		beforLogin.add(bottom, BorderLayout.SOUTH);

		afterLogin = new JPanel(new GridLayout(11, 2));
		afterLogin.setBounds(800, 10, 600, 500);

		Font a = new Font("휴먼모음T", Font.PLAIN, 30);
		userNumLb = new JLabel();
		userNumLb.setFont(a);
		userNameLb = new JLabel();
		userNameLb.setFont(a);
		userIdLb = new JLabel();
		userIdLb.setFont(a);
		startTimeLb = new JLabel();
		startTimeLb.setFont(a);
		finishTimeLb = new JLabel();
		finishTimeLb.setFont(a);
		fareTypeLb = new JLabel();
		fareTypeLb.setFont(a);
		usingTimeLb = new JLabel();
		usingTimeLb.setFont(new Font("휴먼모음T", Font.PLAIN, 25));
		serviceLb = new JLabel();
		serviceLb.setFont(a);
		usingPriceLb = new JLabel();
		usingPriceLb.setFont(a);
		productPriceLb = new JLabel();
		productPriceLb.setFont(a);
		totalPriceLb = new JLabel();
		totalPriceLb.setFont(a);

		userNumLb2 = new JLabel("    PC 번호");
		userNumLb2.setFont(a);
		userNameLb2 = new JLabel("    이름");
		userNameLb2.setFont(a);
		userIdLb2 = new JLabel("    아이디");
		userIdLb2.setFont(a);
		startTimeLb2 = new JLabel("    시작시간");
		startTimeLb2.setFont(a);
		finishTimeLb2 = new JLabel("    종료시간");
		finishTimeLb2.setFont(a);
		fareTypeLb2 = new JLabel("    요금제");
		fareTypeLb2.setFont(a);
		usingTimeLb2 = new JLabel("    사용시간");
		usingTimeLb2.setFont(a);
		serviceLb2 = new JLabel("    서비스");
		serviceLb2.setFont(a);
		usingPriceLb2 = new JLabel("    PC사용금액");
		usingPriceLb2.setFont(a);
		productPriceLb2 = new JLabel("    상품판매");
		productPriceLb2.setFont(a);
		totalPriceLb2 = new JLabel("    계산할 금액");
		totalPriceLb2.setFont(a);

		afterLogin.add(userNumLb2);
		afterLogin.add(userNumLb);
		afterLogin.add(userNameLb2);
		afterLogin.add(userNameLb);
		afterLogin.add(userIdLb2);
		afterLogin.add(userIdLb);
		afterLogin.add(startTimeLb2);
		afterLogin.add(startTimeLb);
		afterLogin.add(finishTimeLb2);
		afterLogin.add(finishTimeLb);
		afterLogin.add(fareTypeLb2);
		afterLogin.add(fareTypeLb);

		afterLogin.add(usingTimeLb2);
		afterLogin.add(usingTimeLb);
		afterLogin.add(serviceLb2);
		afterLogin.add(serviceLb);
		afterLogin.add(usingPriceLb2);
		afterLogin.add(usingPriceLb);
		afterLogin.add(productPriceLb2);
		afterLogin.add(productPriceLb);
		afterLogin.add(totalPriceLb2);
		afterLogin.add(totalPriceLb);

		add(beforLogin, new GridLayout(1, 1));

		this.setVisible(true);
		loginYN = false;
	}

	public static void main(String[] args) {
		new Client();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		tryConnecting();

		if (answer.equals("아이디 없음")) {

			JOptionPane.showMessageDialog(getContentPane(), "아이디와 패스워드를 확인하세요.");

		} else {

			Thread keepConnecting77 = new KeepConnecting();
			keepConnecting77.start();
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() instanceof JComboBox) {
			seatNo = (String) laSeatNo2.getSelectedItem();
		}

	}

	public void tryConnecting() {
		try {
			client = new Socket("127.0.0.1", 11100);
			getClientDataInStream = new DataInputStream(client.getInputStream());
			getClientDataOutStream = new DataOutputStream(client.getOutputStream());
			pwd = tfPW.getText();
			if (loginYN == false) {
				getClientDataOutStream
						.writeUTF(tfID.getText() + "," + tfPW.getText() + "," + laSeatNo2.getSelectedItem());
			} else if (loginYN == true) {
				getClientDataOutStream.writeUTF(userIdLb.getText() + "," + pwd + "," + userNumLb.getText());
				// getClientDataOutStream = new
				// DataOutputStream(client.getOutputStream());
				getClientDataOutStream.writeUTF(startTime);
			}

			answer = getClientDataInStream.readUTF();
			System.out.println(answer.toString());
			if (answer.equals("접속종료")) {
				System.exit(0);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		answerArray = answer.split(",");
	}

	public void connectingServer() {

		tryConnecting();

		if (loginYN == false)
			startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new GregorianCalendar().getTime());

		loginYN = true;
		beforLogin.setVisible(false);
		this.remove(beforLogin);
		this.add(afterLogin);
		userNumLb.setText(seatNo);

		userNameLb.setText(answerArray[2]);

		userIdLb.setText(answerArray[0]);
		startTimeLb.setText(startTime);
		startTimeLb.setFont(new Font("휴먼모음T", Font.PLAIN, 25));

		finishTimeLb.setText("");

		if (answerArray[10].equals("true")) {
			fareTypeLb.setText("선불");
			int time = Integer.parseInt(answerArray[5]) - 1;
			if (time < 60) {
				hour = 0;
				minute = 0;
				second = time;
			} else if (time > 59 && time < 3600) {
				hour = 0;
				minute = time / 60;
				second = time % 60;
			} else if (time > 3599) {
				hour = time / 3600;
				minute = ((time - (3600 * hour)) / 60);
				second = time - (3600 * hour) - (60 * minute);
			}
			usingTimeLb.setText("남은시간 : " + hour + "시간" + minute + "분" + second + "초");

		} else {
			fareTypeLb.setText("후불");
			int time = Integer.parseInt(answerArray[6]) + 1;
			if (time < 60) {
				hour = 0;
				minute = 0;
				second = time;
			} else if (time > 59 && time < 3600) {
				hour = 0;
				minute = time / 60;
				second = time % 60;
			} else if (time > 3599) {
				hour = time / 3600;
				minute = ((time - (3600 * hour)) / 60);
				second = time - (3600 * hour) - (60 * minute);
			}
			usingTimeLb.setText("이용시간 : " + hour + "시간" + minute + "분" + second + "초");

		}

		serviceLb.setText("");

		usingPriceLb.setText(answerArray[7]);

		productPriceLb.setText(answerArray[8]);

		totalPriceLb.setText(answerArray[9]);

	}

	public class KeepConnecting extends Thread {
		@Override
		public void run() {
			while (true) {
				connectingServer();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		}

	}

}