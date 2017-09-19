package funtion;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import funtion.*;
import model.*;

public class MainCounter extends JFrame implements ActionListener {
	protected JPanel informPane, payPane, mainPane;
	protected JPanel sellPane, userInformPane1, userInformPane2, userInformPane3, userInformPane4;
	protected JLabel inUseMemberTf, totalMemberTf;
	protected int usingMember = 0;
	protected JButton signUpBtn; // getInfoBtn 버튼 추가. 나중에 기능만 살리고 없애야 함.
	protected SignUp signUpDialog;
	protected JComboBox productBtn, memberCb, $1000Cb, $500Cb, serviceCb;
	protected JButton calcBtn;
	protected JButton[] userBtn;
	protected JPanel[] userPane;
	protected JLabel[] userNumPLb, userIDPLb, usingTimePLb, totalPricePLb;
	protected int hour, minute, second;
	protected JLabel userNumLb, userNameLb, userIdLb, startTimeLb, finishTimeLb, fareTypeLb, usingTimeLb, serviceLb,
			usingPriceLb, productPriceLb, totalPriceLb;
	protected JTextArea memoTa;
	protected int pcPay = 0, productPay = 0, totalPay = 0;
	protected JLabel pcPayLb, productPayLb, totalPayLb;

	private ReadAndWriteMembers rwm;
	private Members member;

	private String sID, sPW, sSeatNo;
	private ServerSocket serverui;
	private Socket client;

	DataInputStream getClientDataInStream;
	DataOutputStream getClientDataOutStream;

	private boolean close = false;
	private boolean selected = false;
	private int num;

	public MainCounter() {
		super("피시방 카운터");

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		this.setBounds(0, 0, width - 500, height - 50);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		informPane = new JPanel(new GridLayout(5, 1));
		sellPane = new JPanel(new GridLayout(5, 2));

		inUseMemberTf = new JLabel("           " + usingMember);
		inUseMemberTf.setFont(new Font("휴먼모음T", Font.PLAIN, 20));
		totalMemberTf = new JLabel("/ 30");
		totalMemberTf.setFont(new Font("휴먼모음T", Font.PLAIN, 20));
		signUpBtn = new JButton("회원가입");
		signUpBtn.addActionListener(this);
		String[] product = { "라면(1500원)", "김밥(2000원)", "음료수(1000원)" };
		productBtn = new JComboBox(product);
		productBtn.setSelectedIndex(0);
		productBtn.addActionListener(this);
		String[] memberships = { "5,000원", "10,000원", "50,000원" };
		memberCb = new JComboBox(memberships);
		memberCb.setSelectedIndex(0);
		memberCb.addActionListener(this);
		String[] $1000 = { "1,000원", "2,000원", "3,000원", "4,000원" };
		$1000Cb = new JComboBox($1000);
		$1000Cb.setSelectedIndex(0);
		$1000Cb.addActionListener(this);
		String[] $500 = { "100원", "200원", "300원", "400원", "500원", "600원", "700원", "800원", "900원" };
		$500Cb = new JComboBox($500);
		$500Cb.setSelectedIndex(0);
		$500Cb.addActionListener(this);
		String[] service = { "+5분", "+10분", "+15분", "+20분", "+25분", "+30분" };
		serviceCb = new JComboBox(service);
		serviceCb.setSelectedIndex(0);
		serviceCb.addActionListener(this);

		sellPane.add(inUseMemberTf);
		sellPane.add(totalMemberTf);
		sellPane.add(signUpBtn);
		sellPane.add(productBtn);
		sellPane.add(memberCb);
		sellPane.add($1000Cb);
		sellPane.add($500Cb);
		sellPane.add(serviceCb);

		userInformPane1 = new JPanel(new GridLayout(6, 2));
		userInformPane2 = new JPanel(new GridLayout(4, 2));
		userInformPane3 = new JPanel(new GridLayout(1, 2));
		userInformPane4 = new JPanel(new GridLayout(3, 1));

		userNumLb = new JLabel();
		userNameLb = new JLabel();
		userIdLb = new JLabel();
		startTimeLb = new JLabel();
		finishTimeLb = new JLabel();
		fareTypeLb = new JLabel();
		usingTimeLb = new JLabel();
		serviceLb = new JLabel();
		usingPriceLb = new JLabel();
		productPriceLb = new JLabel();
		totalPriceLb = new JLabel();
		calcBtn = new JButton("계산하기");
		calcBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) // 우선은 일단은 사라지기만. 데이터는
														// 그대로 존재.
			{
				if (userIdLb.getText() != null && userIdLb.getText().length() > 0) {
					int result = JOptionPane.showOptionDialog(getParent(), "계산하시겠습니까?", "계산", JOptionPane.YES_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, new String[] { "확인", "취소" }, "확인");
					if (result == 0) {
						/*
						 * usingMember--; inUseMemberTf.setText("           " +
						 * usingMember);
						 */
						
						selected = false;
						
						pcPay += Integer.parseInt(usingPriceLb.getText());
						pcPayLb.setText(String.valueOf(pcPay) + "      ");
						productPay += Integer.parseInt(productPriceLb.getText());
						productPayLb.setText(String.valueOf(productPay) + "      ");
						totalPay = pcPay + productPay;
						totalPayLb.setText(String.valueOf(totalPay));

						// rwm.bringInfoFromID(userIdLb.getText().substring(3));
						userPane[Integer.parseInt(userNumLb.getText().substring(3))]
								.setBackground(Color.WHITE);
						
						userIDPLb[Integer.parseInt(userNumLb.getText().substring(3))].setText(null);
						usingTimePLb[Integer.parseInt(userNumLb.getText().substring(3))].setText(null);
						totalPricePLb[Integer.parseInt(userNumLb.getText().substring(3))].setText(null);

						userNumLb.setText(null);
						userNameLb.setText(null);
						userIdLb.setText(null);
						startTimeLb.setText(null);
						finishTimeLb.setText(null);
						fareTypeLb.setText(null);
						usingTimeLb.setText(null);
						serviceLb.setText(null);
						usingPriceLb.setText(null);
						productPriceLb.setText(null);
						totalPriceLb.setText(null);
						memoTa.setText(null);
						
						
						
						//member.setFinishTime(Integer.parseInt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new GregorianCalendar().getTime())));

						usingMember = 0;

						for (int i = 0; i < 30; i++) {
							if (userIDPLb[i].getText() != "" && userIDPLb[i].getText() != null
									&& userIDPLb[i].getText().length() > 0)
								usingMember++;
						}
						inUseMemberTf.setText("           " + usingMember);
						
						close = true;
						
					}
				}
			}
		});

		memoTa = new JTextArea();

		userInformPane1.add(new JLabel("PC 번호"));
		userInformPane1.add(userNumLb);
		userInformPane1.add(new JLabel("이름"));
		userInformPane1.add(userNameLb);
		userInformPane1.add(new JLabel("아이디"));
		userInformPane1.add(userIdLb);
		userInformPane1.add(new JLabel("시작시간"));
		userInformPane1.add(startTimeLb);
		userInformPane1.add(new JLabel("종료시간"));
		userInformPane1.add(finishTimeLb);
		userInformPane1.add(new JLabel("요금제"));
		userInformPane1.add(fareTypeLb);

		userInformPane2.add(new JLabel("사용시간"));
		userInformPane2.add(new JLabel("서비스"));
		userInformPane2.add(usingTimeLb);
		userInformPane2.add(serviceLb);
		userInformPane2.add(new JLabel("PC사용금액"));
		userInformPane2.add(new JLabel("상품판매"));
		userInformPane2.add(usingPriceLb);
		userInformPane2.add(productPriceLb);

		userInformPane3.add(new JLabel("계산할 금액"));
		userInformPane3.add(totalPriceLb);

		userInformPane4.add(calcBtn);
		userInformPane4.add(new JLabel("메모 : "));
		userInformPane4.add(memoTa);

		informPane.add(sellPane);
		informPane.add(userInformPane1);
		informPane.add(userInformPane2);
		informPane.add(userInformPane3);
		informPane.add(userInformPane4);

		payPane = new JPanel();

		pcPayLb = new JLabel("0" + "      ");
		productPayLb = new JLabel("0" + "      ");
		totalPayLb = new JLabel("0");

		payPane.add(new JLabel("<오늘의 수입 ♥>"));
		payPane.add(new JLabel("PC 요금 : "));
		payPane.add(pcPayLb);
		payPane.add(new JLabel("상품 요금 : "));
		payPane.add(productPayLb);
		payPane.add(new JLabel("합계 : "));
		payPane.add(totalPayLb);

		mainPane = new JPanel(new GridLayout(6, 5, 10, 10));

		userBtn = new JButton[30];
		for (int i = 0; i < userBtn.length; i++) {
			userBtn[i] = new JButton();
			userBtn[i].addActionListener(this);
		}

		userNumPLb = new JLabel[30];
		for (int i = 0; i < userNumPLb.length; i++) {
			userNumPLb[i] = new JLabel("No." + String.valueOf(i));
		}
		userIDPLb = new JLabel[30];
		for (int i = 0; i < userIDPLb.length; i++) {
			userIDPLb[i] = new JLabel();
		}
		usingTimePLb = new JLabel[30];
		for (int i = 0; i < usingTimePLb.length; i++) {
			usingTimePLb[i] = new JLabel();
			usingTimePLb[i].setFont(new Font("휴먼모음T", Font.PLAIN, 11));
		}
		totalPricePLb = new JLabel[30];
		for (int i = 0; i < totalPricePLb.length; i++) {
			totalPricePLb[i] = new JLabel();
		}

		userPane = new JPanel[30];
		for (int i = 0; i < userPane.length; i++) {
			userPane[i] = new JPanel(new GridLayout(5, 3));
			userPane[i].add(userNumPLb[i]);
			userPane[i].add(userIDPLb[i]);
			userPane[i].add(usingTimePLb[i]);
			userPane[i].add(totalPricePLb[i]);
			userPane[i].setBackground(Color.WHITE);
		}

		for (int i = 0; i < userBtn.length; i++) {
			userBtn[i].add(userPane[i]);
			mainPane.add(userBtn[i]);
		}

		add(mainPane, BorderLayout.CENTER);
		add(informPane, BorderLayout.WEST);
		add(payPane, BorderLayout.SOUTH);

		this.setVisible(true);

		member = new Members();
		rwm = new ReadAndWriteMembers();
		try {
			serverui = new ServerSocket(11100);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Thread countingMember = new CountingMember();
		countingMember.start();
		Thread thStartServer = new StartConnecting();
		thStartServer.start();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 30; i++) {
			if (e.getSource() == userBtn[i]) {
				selected = true;
				num = i;
				//sendI(i);
			}
		}
		if (e.getSource() == productBtn) {
			int result = JOptionPane.showConfirmDialog(getContentPane(),
					userNumLb.getText() + " PC에서 " + productBtn.getSelectedItem().toString() + "을 구입하시겠습니까 ?",
					"선불 금액 추가", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == 0) {
				int won = 0;
				if (productBtn.getSelectedItem().toString().equals("라면(1500원)"))
					won = 1500;
				else if (productBtn.getSelectedItem().toString().equals("김밥(2000원)"))
					won = 2000;
				else if (productBtn.getSelectedItem().toString().equals("음료수(1000원)"))
					won = 1000;
				member = rwm.bringInfoFromID(userIdLb.getText());
				member.setmEatingFare(member.getmEatingFare() + won);
			}
		} else if (e.getSource() == memberCb) {
			int result = JOptionPane.showConfirmDialog(getContentPane(),
					userNumLb.getText() + " PC에 " + memberCb.getSelectedItem().toString() + "을 추가하시겠습니까 ?", "선불 금액 추가",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == 0) {
				int won = 0;
				if (memberCb.getSelectedItem().toString().equals("5,000원"))
					won = Integer.parseInt(memberCb.getSelectedItem().toString().substring(0, 1)
							+ memberCb.getSelectedItem().toString().substring(2, 5));
				else
					won = Integer.parseInt(memberCb.getSelectedItem().toString().substring(0, 2)
							+ memberCb.getSelectedItem().toString().substring(3, 6));
				
				pcPay += won;
				pcPayLb.setText(String.valueOf(pcPay));
				
				member = rwm.bringInfoFromID(userIdLb.getText());
				if(member.getmSunOrHoo()){
					member.setmTimeSun(member.getmTimeSun() + (int) (won * 4.32));
					member.setmUsingFare(0);
				}
				else {
					int hooTime = ((int) (won * 4.32) - member.getmTimeHoo());
					if(hooTime >= 0){
						member.setmTimeSun(hooTime);
						int usingFare = member.getmUsingFare() - won;
						if(usingFare >= 0)
							member.setmUsingFare(usingFare);
						else
							member.setmUsingFare(0);
						member.setmSunOrHoo(true);
					}
					else{
						member.setmTimeHoo(-hooTime);
						int usingFare = member.getmUsingFare() - won;
						if(usingFare >= 0)
							member.setmUsingFare(usingFare);
						else
							member.setmUsingFare(0);
					}
				}
			}
		} else if (e.getSource() == $1000Cb) {
			int result = JOptionPane.showConfirmDialog(getContentPane(),
					userNumLb.getText() + " PC에 " + $1000Cb.getSelectedItem().toString() + "을 추가하시겠습니까 ?", "선불 금액 추가",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == 0) {
				int won = Integer.parseInt($1000Cb.getSelectedItem().toString().substring(0, 1)
						+ $1000Cb.getSelectedItem().toString().substring(2, 5));
				
				pcPay += won;
				pcPayLb.setText(String.valueOf(pcPay));
				
				member = rwm.bringInfoFromID(userIdLb.getText());
				if(member.getmSunOrHoo()){
					member.setmTimeSun(member.getmTimeSun() + (int) (won * 3.6));
					member.setmUsingFare(0);
				}
				else {
					int hooTime = ((int) (won * 3.6) - member.getmTimeHoo());
					if(hooTime >= 0){
						member.setmTimeSun(hooTime);
						int usingFare = member.getmUsingFare() - won;
						if(usingFare >= 0)
							member.setmUsingFare(usingFare);
						else
							member.setmUsingFare(0);
						member.setmSunOrHoo(true);
					}
					else{
						member.setmTimeHoo(-hooTime);
						int usingFare = member.getmUsingFare() - won;
						if(usingFare >= 0)
							member.setmUsingFare(usingFare);
						else
							member.setmUsingFare(0);
					}
				}
				
			}
		} else if (e.getSource() == $500Cb) {
			int result = JOptionPane.showConfirmDialog(getContentPane(),
					userNumLb.getText() + " PC에 " + $500Cb.getSelectedItem().toString() + "을 추가하시겠습니까 ?", "선불 금액 추가",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == 0) {
				int won = Integer.parseInt($500Cb.getSelectedItem().toString().substring(0, 3));
				
				pcPay += won;
				pcPayLb.setText(String.valueOf(pcPay));
				
				member = rwm.bringInfoFromID(userIdLb.getText());
				if(member.getmSunOrHoo()){
					member.setmTimeSun(member.getmTimeSun() + (int) (won * 3.6));
					member.setmUsingFare(0);
				}
				else {
					int hooTime = ((int) (won * 3.6) - member.getmTimeHoo());
					if(hooTime >= 0){
						member.setmTimeSun(hooTime);
						int usingFare = member.getmUsingFare() - won;
						if(usingFare >= 0)
							member.setmUsingFare(usingFare);
						else
							member.setmUsingFare(0);
						member.setmSunOrHoo(true);
					}
					else{
						member.setmTimeHoo(-hooTime);
						int usingFare = member.getmUsingFare() - won;
						if(usingFare >= 0)
							member.setmUsingFare(usingFare);
						else
							member.setmUsingFare(0);
					}
				}
			}
		} else if (e.getSource() == serviceCb) {
			int result = JOptionPane.showConfirmDialog(getContentPane(),
					userNumLb.getText() + " PC에 " + serviceCb.getSelectedItem().toString() + "을 추가하시겠습니까 ?", "서비스 추가",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == 0) {
				int service = Integer.parseInt(serviceCb.getSelectedItem().toString().substring(1, 3));
				member = rwm.bringInfoFromID(userIdLb.getText());
				member.setmTimeSun(member.getmTimeSun() + service * 60);
				if(member.getmSunOrHoo())
					member.setmTimeSun(member.getmTimeSun() + service * 60);
				else {
					int hooTime = (service * 60 - member.getmTimeHoo());
					if(hooTime >= 0){
						member.setmTimeSun(hooTime);
						member.setmSunOrHoo(true);
					}
					else
						member.setmTimeHoo(-hooTime);
				}
			}
		}

		if (e.getSource() == signUpBtn) {
			rwm.allMemberInfo(); // 삭제
			signUpDialog = new SignUp(this, "회원가입");
			rwm.addMember(signUpDialog.newMember());
			rwm.allMemberInfo(); // 삭제
		}
	}

	public void connectingClient() {
		try {
			client = serverui.accept();
			getClientDataInStream = new DataInputStream(client.getInputStream());
			getClientDataOutStream = new DataOutputStream(client.getOutputStream());

			String Id = getClientDataInStream.readUTF();
			if (close == true) {
				getClientDataOutStream.writeUTF("접속종료");
				close = false;

			} else {
				sID = Id.split(",")[0].toString();
				sPW = Id.split(",")[1].toString();
				sSeatNo = Id.split(",")[2].toString();

				if (rwm.udIsRight(sID)) {

					if (rwm.inforIsRight(sID, sPW)) {

						member = rwm.bringInfoFromID(sID);
						getClientDataOutStream.writeUTF(member.toString());
						System.out.println(member.toString());

						// 카운터 좌측에 사용자 정보뿌려주기
						int iSeatNo = Integer.parseInt(sSeatNo.substring(3));
						userPane[iSeatNo].setBackground(Color.CYAN);
						userNumPLb[iSeatNo].setText(sSeatNo);
						userIDPLb[iSeatNo].setText(member.getmID());
						totalPricePLb[iSeatNo].setText(String.valueOf(member.getmTotalFare()));

						member.setmTotalFare(member.getmEatingFare() + member.getmUsingFare());

						if (member.getmSunOrHoo() == true) {
							if (member.getmTimeSun() > 0) {
								member.setmTimeSun(member.getmTimeSun() - 1);
								int getmTimeSun = member.getmTimeSun();
								if (getmTimeSun < 60) {
									hour = 0;
									minute = 0;
									second = getmTimeSun;
								} else if (getmTimeSun > 59 && getmTimeSun < 3600) {
									hour = 0;
									minute = getmTimeSun / 60;
									second = getmTimeSun % 60;
								} else if (getmTimeSun > 3599) {
									hour = getmTimeSun / 3600;
									minute = ((getmTimeSun - (3600 * hour)) / 60);
									second = getmTimeSun - (3600 * hour) - (60 * minute);
								}
								usingTimePLb[iSeatNo].setText("남은시간 : " + hour + "시간" + minute + "분" + second + "초");
								totalPricePLb[iSeatNo].setText("총 요금 : " + String.valueOf(member.getmTotalFare()));
							} else {
								member.setmSunOrHoo(false);
							}
						} else {
							member.setmTimeHoo(member.getmTimeHoo() + 1);

							if (second % 10 == 0) {
								member.setmUsingFare(member.getmUsingFare() + 100);
							}

							int getmTimeHoo = member.getmTimeHoo();
							if (getmTimeHoo < 60) {
								hour = 0;
								minute = 0;
								second = getmTimeHoo;
							} else if (getmTimeHoo > 59 && getmTimeHoo < 3600) {
								hour = 0;
								minute = getmTimeHoo / 60;
								second = getmTimeHoo % 60;
							} else if (getmTimeHoo > 3599) {
								hour = getmTimeHoo / 3600;
								minute = ((getmTimeHoo - (3600 * hour)) / 60);
								second = getmTimeHoo - (3600 * hour) - (60 * minute);
							}
							usingTimePLb[iSeatNo].setText("이용시간 : " + hour + "시간" + minute + "분" + second + "초");
							totalPricePLb[iSeatNo].setText("총 요금 : " + String.valueOf(member.getmTotalFare()));
						}
					} else {
						getClientDataOutStream.writeUTF("아이디 없음");
						this.client.close();
					}
				} else {
					getClientDataOutStream.writeUTF("아이디 없음");
					this.client.close();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendI(int i) {
		num = i;
	}
	
	public static void main(String[] args) {
		new MainCounter();
	}

	class StartConnecting extends Thread {

		@Override
		public void run() {
			while (true) {
				connectingClient();
				Thread countingMember = new CountingMember();
				countingMember.start();
				Thread keepReadAndWriting = rwm.new KeepReadAndWriting();
				keepReadAndWriting.start();
				
				if(selected == true){
					Thread informMember = new InformMember(num);
					informMember.start();
				}
				try {
					Thread.sleep(1000);

				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		}
	}

	class CountingMember extends Thread {
		@Override
		public void run() {

			try {
				usingMember = 0;

				for (int i = 0; i < 30; i++) {
					if (userIDPLb[i].getText() != "" && userIDPLb[i].getText() != null
							&& userIDPLb[i].getText().length() > 0)
						usingMember++;
				}

				inUseMemberTf.setText("           " + usingMember);

				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	class InformMember extends Thread {
		int i;
		
		public InformMember(int i){
			this.i = i;
		}
		
		@Override
		public void run() {

			try {
				while (selected == true) {
					member = new Members();
					member = rwm.bringInfoFromID(userIDPLb[i].getText());

					userNumLb.setText(userNumPLb[i].getText());
					userNameLb.setText(member.getmName());
					userIdLb.setText(member.getmID());
					startTimeLb.setText(String.valueOf(member.getStartTime()));
					finishTimeLb.setText(String.valueOf(member.getFinishTime()));
					fareTypeLb.setText(member.getmSunOrHoo() == true ? "선불" : "후불");
					if (member.getmSunOrHoo() == true) {
						usingTimeLb.setText("남은시간 : " + hour + "시간" + minute + "분" + second + "초");
					} else {
						usingTimeLb.setText("이용시간 : " + hour + "시간" + minute + "분" + second + "초");
					}
					serviceLb.setText(String.valueOf(member.getService()));
					usingPriceLb.setText(String.valueOf(member.getmUsingFare()));
					productPriceLb.setText(String.valueOf(member.getmEatingFare()));
					totalPriceLb.setText(String.valueOf(member.getmTotalFare()));
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}