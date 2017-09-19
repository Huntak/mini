package model;
	
public class MembersManager{
	
	//Members me = new Members();
	
	
	private int mTimeSun; 	//선불
	private int mTimeHoo;		//후불
	private int mUsingFare;	//추가요금
	private int mEatingFare;	//상품판매
	private int mTotalFare;		//합계
	private boolean mSunOrHoo; // true  = 선불,  false = 후불 
	private int button; //콤보박스
	public MembersManager() {
	// 계산(선불후불 판단 후 계산할 금액 + 상품판매금액, 시간 단위로 금액)
		
		if(mSunOrHoo){
			new TimeSun();
		}else {
			new TimeHoo();
		}
	}

	public class TimeSun implements Runnable{
	@Override
	public void run() {
		mTimeSun = button * 3;
		
		while(mTimeSun >= 0){
			try {
				mTimeSun += 1; 
				Thread.sleep(1000);
				new TimeHoo();
			} catch (InterruptedException e) {					
				e.printStackTrace();
			}
		}
		new TimeHoo();
	}
	}
	public class TimeHoo implements Runnable {
	@Override
	public void run() {
		while(mTimeHoo <= 0){
			try {
				 mUsingFare += 100;
				Thread.sleep(10000);
			} catch (InterruptedException e) {					
				e.printStackTrace();
			}
		}
	}
	}
	public class  mTotal{
		
		public mTotal(){
			if(mSunOrHoo){
			//	mTotalFare = me.getmEatingFare();
			}else{
		//		mTotalFare = me.getmUsingFare() + me.getmEatingFare();
			}
		}
	}
}