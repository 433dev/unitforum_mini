package ftt.unitforum.types;

import java.util.Date;

public class UnitforumLikeHistory {
	private int historyIdx;
	private int articleIdx;
	private int accountIdx;
	private Date createDt;
	
	public int getHistoryIdx() {
		return historyIdx;
	}
	
	public void setHistoryIdx(int historyIdx) {
		this.historyIdx = historyIdx;
	}
	
	public int getArticleIdx() {
		return articleIdx;
	}
	
	public void setArticleIdx(int articleIdx) {
		this.articleIdx = articleIdx;
	}
	
	public int getAccountIdx() {
		return accountIdx;
	}
	
	public void setAccountIdx(int accountIdx) {
		this.accountIdx = accountIdx;
	}
	
	public Date getCreateDt() {
		return createDt;
	}
	
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
}
