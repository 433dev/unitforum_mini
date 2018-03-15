package ftt.unitforum.types;

import java.util.Date;

public class UnitforumArticle {
	private int articleIdx;
	private int masterIdx;
	private int worldIdx;
	private String class1Code;
	private String class2Code;
	private int accountIdx;
	private String nickname;
	private String charGrade;
	private String content;
	private int likeCnt;
	private Date createDt;
	private boolean alreadyLike; // 해당 페이지를 호출한 유저가 이미 좋아야를 클릭했는지 여부

	public int getArticleIdx() {
		return articleIdx;
	}

	public void setArticleIdx(int articleIdx) {
		this.articleIdx = articleIdx;
	}

	public int getMasterIdx() {
		return masterIdx;
	}

	public void setMasterIdx(int masterIdx) {
		this.masterIdx = masterIdx;
	}

	public int getWorldIdx() {
		return worldIdx;
	}

	public void setWorldIdx(int worldIdx) {
		this.worldIdx = worldIdx;
	}

	public String getClass1Code() {
		return class1Code;
	}

	public void setClass1Code(String class1Code) {
		this.class1Code = class1Code;
	}

	public String getClass2Code() {
		return class2Code;
	}

	public void setClass2Code(String class2Code) {
		this.class2Code = class2Code;
	}

	public int getAccountIdx() {
		return accountIdx;
	}

	public void setAccountIdx(int accountIdx) {
		this.accountIdx = accountIdx;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCharGrade() {
		return charGrade;
	}

	public void setCharGrade(String charGrade) {
		this.charGrade = charGrade;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	
	public boolean getAlreadyLike() {
		return alreadyLike;
	}

	public void setAlreadyLike(boolean alreadyLike) {
		this.alreadyLike = alreadyLike;
	}
	
	public void setAlreadyLike(int gusn) {
		if (gusn > 0)
			this.alreadyLike = true;
		else
			this.alreadyLike = false;
	}

	public String toString(){
		return  articleIdx + "," + masterIdx + "," + worldIdx + "," + class1Code + "," + class2Code + ","
				+ accountIdx + "," + nickname + "," + charGrade + "," + content + "," + likeCnt + "," + createDt;
	}
}
