package model;

import java.io.Serializable;

public class Members implements Serializable{
	private String mID;
	private String mPW;
	private String mName;
	private int mAge;
	private String mPhone;
	private int mTimeSun;
	private int mTimeHoo;
	private int mUsingFare;
	private int mEatingFare;
	private int mTotalFare;
	private boolean mSunOrHoo; // true  = 선불,  false = 후불 
	private int startTime;
	private int finishTime;
	private int service;
	
	
	public Members(){}
	
	public Members(String mID, String mPW, String mName, int mAge, String mPhone, int mTimeSun, int mTimeHoo,
			int mUsingFare,int mEatingFare, int mTotalFare, boolean mSunOrHoo) {
			super();
			this.mID = mID;
			this.mPW = mPW;
			this.mName = mName;
			this.mAge = mAge;
			this.mPhone = mPhone;
			this.mTimeSun = mTimeSun;
			this.mTimeHoo = mTimeHoo;
			this.mUsingFare = mUsingFare;
			this.mEatingFare = mEatingFare;
			this.mTotalFare = mTotalFare;
			this.mSunOrHoo = mSunOrHoo;
	}

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getmPW() {
		return mPW;
	}

	public void setmPW(String mPW) {
		this.mPW = mPW;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public int getmAge() {
		return mAge;
	}

	public void setmAge(int mAge) {
		this.mAge = mAge;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public int getmTimeSun() {
		return mTimeSun;
	}

	public void setmTimeSun(int mTimeSun) {
		this.mTimeSun = mTimeSun;
	}
	
	public int getmTimeHoo() {
		return mTimeHoo;
	}

	public void setmTimeHoo(int mTimeHoo) {
		this.mTimeHoo = mTimeHoo;
	}

	public int getmUsingFare() {
		return mUsingFare;
	}

	public void setmUsingFare(int mUsingFare) {
		this.mUsingFare = mUsingFare;
	}

	public int getmEatingFare() {
		return mEatingFare;
	}

	public void setmEatingFare(int mEatingFare) {
		this.mEatingFare = mEatingFare;
	}

	public int getmTotalFare() {
		return mTotalFare;
	}

	public void setmTotalFare(int mTotalFare) {
		this.mTotalFare = mTotalFare;
	}

	public boolean getmSunOrHoo() {
		return mSunOrHoo;
	}
	

	public void setmSunOrHoo(boolean mSunOrHoo) {
		this.mSunOrHoo = mSunOrHoo;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return  mID + "," + mPW + "," + mName + "," + mAge + "," + mPhone + "," + mTimeSun + "," + mTimeHoo 
				+ "," + mUsingFare + "," + mEatingFare + "," + mTotalFare + "," + mSunOrHoo;
	}
	
	

}
/*서완원 사용자정보(처음 멤버 텍스트, (총요금,) [“ID, 이름, pass, 나이, 전화번호, 남은시간,
                             (요금, 상품판매금액, 선불후불)”]
                            	입출력, 직렬화, */
