package funtion;

import java.io.*;
import java.util.*;
import model.*;

public class ReadAndWriteMembers {
	private HashMap<String, Members> hm = new HashMap<String, Members>();
	
	public ReadAndWriteMembers(){
		//firstSetInfo();
		hm = readMembersInfor();
	}
	
	public void firstSetInfo(){
		addMember(new Members("Seowanwon", "SeoPW", "서완원", 33, "010-1234-5678", 10, 0, 0, 0, 0, true));
		addMember(new Members("OHoontak", "OhPW", "오훈탁", 26, "010-1234-5679", 0, 0, 0, 0, 0, false));
		addMember(new Members("Leecheolho", "LeePW", "이철호", 30, "010-1234-5680", 0, 0, 0, 0, 0, false));
		addMember(new Members("Jobongyeoun", "JoPW", "조봉연", 29, "010-1234-5681", 0, 0, 0, 0, 0, false));
		addMember(new Members("Gobyeonwan", "GoPW", "고병완", 30, "010-1234-5682", 0, 0, 0, 0, 0, false));
		addMember(new Members("Kimyongchul", "KimPW", "김용철", 27, "010-1234-5683", 0, 0, 0, 0, 0, false));
	}
	
	public HashMap<String, Members> readMembersInfor(){
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream("memberInfor.dat"))));
			String str;
			while((str = br.readLine())!= null){
				String memberInfo = str;
				String[] memberInfoFromID = memberInfo.split(",");

				String mID = memberInfoFromID[0];
				String mPW = memberInfoFromID[1];
				String mName = memberInfoFromID[2];
				int mAge = Integer.parseInt(memberInfoFromID[3]);
				String mPhone = memberInfoFromID[4];
				int mTimeSun = Integer.parseInt(memberInfoFromID[5]);
				int mTimeHoo = Integer.parseInt(memberInfoFromID[6]);
				int mUsingFare = Integer.parseInt(memberInfoFromID[7]);
				int mEatingFare = Integer.parseInt(memberInfoFromID[8]);
				int mTotalFare = Integer.parseInt(memberInfoFromID[9]);
				boolean mSunOrHoo = Boolean.parseBoolean(memberInfoFromID[10]);
				Members member = new Members(mID, mPW, mName, mAge, mPhone, mTimeSun, mTimeHoo, mUsingFare, mEatingFare, mTotalFare, mSunOrHoo);
				hm.put(member.getmID(), member);
			}
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch(IOException e){
				e.printStackTrace();
			} finally{
				try { 
					br.close();				
				}catch (IOException e2){
					e2.printStackTrace();
				}
			}			 
		return hm;
	}
	
	public void writeMembersInfor(){
		PrintWriter pw = null;
		StringBuilder data = new StringBuilder();
		
		try {
			pw = new PrintWriter(new FileOutputStream("memberInfor.dat"));
			
			Iterator<Map.Entry<String, Members>> entryIter = hm.entrySet().iterator();
			while(entryIter.hasNext()){
				Map.Entry<String, Members> entry = entryIter.next();
				
				data.append(entry.getValue() + "\n");
			}			
			
			pw.write(data.toString());
			pw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			pw.close();
		}
	}
	
	public void addMember(Members member){
		hm.put(member.getmID(), member);
	}
	
	public void deleteMember(String mID){
		hm.remove(mID);
	}
	
	public Members bringInfoFromID(String id){	   
		return hm.get(id);
	}
	
	public void allMemberInfo(){
		Set<Map.Entry<String,Members>> entryList = hm.entrySet();
		Iterator<Map.Entry<String,Members>> entryIter = entryList.iterator();
		while(entryIter.hasNext()){
			Map.Entry<String,Members> entry = entryIter.next();
			String key = (String)entry.getKey();
		System.out.println(hm.get(key));	    
			
		}		
	}
	
	//  아이디와 패스워드 일치하는지 확인하여 일치하면 true,  틀리면 false 반환.
	public boolean inforIsRight(String id, String pw){
		boolean theValue = false;
		if( hm.get(id).getmPW().equals(pw)) theValue = true;		
		return theValue;
	}
	
	// 아이디가 존재 하는지 확인하여 있으면 true, 없으면 false반환
	public boolean udIsRight(String id){
		boolean theValue = false;
		if( hm.get(id)!=null) theValue = true;		
		return theValue;
	}
	
	
	public class KeepReadAndWriting extends Thread{
		@Override
		public void run(){
			try {
				while (true) {
					writeMembersInfor();
					Thread.sleep(10000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
