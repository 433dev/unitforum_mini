package ftt.unitforum.types;

public class UnitforumRequest {
	private int ssn;
	private int articleIdx;
	private int masterIdx;
	private String langCode;
	private int worldIdx;
	private String class1Code;
	private String class2Code;
	private int accountIdx;
	private String nickname;
	private String charGrade;
	private String content;
	private int sort;
	private DEF.ARTICLE_ORDER articleOrder;
	private int page;
	private int unitStat;

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public int getAidx() {
		return articleIdx;
	}

	public void setAidx(int articleIdx) {
		this.articleIdx = articleIdx;
	}

	public int getMidx() {
		return masterIdx;
	}

	public void setMidx(int masterIdx) {
		this.masterIdx = masterIdx;
	}

	public String getLang() {
		return langCode;
	}

	public void setLang(String langCode) {
		this.langCode = langCode;
	}

	public int getWidx() {
		return worldIdx;
	}

	public void setWidx(int worldIdx) {
		this.worldIdx = worldIdx;
	}

	public String getC1code() {
		return class1Code;
	}

	public void setC1code(String class1Code) {
		this.class1Code = class1Code;
	}

	public String getC2code() {
		return class2Code;
	}

	public void setC2code(String class2Code) {
		this.class2Code = class2Code;
	}

	public int getGusn() {
		return accountIdx;
	}

	public void setGusn(int accountIdx) {
		this.accountIdx = accountIdx;
	}

	public String getNick() {
		return nickname;
	}

	public void setNick(String nickname) {
		this.nickname = nickname.trim();
	}

	public String getLevel() {
		return charGrade;
	}

	public void setLevel(String charGrade) {
		this.charGrade = charGrade.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content.trim();
	}
	
	public int getSort() {
		return sort;
	}
	
	public DEF.ARTICLE_ORDER getArticleOrder() {
		return this.articleOrder;
	}

	public void setSort(int sort) {
		this.sort = sort;
		this.articleOrder = DEF.ARTICLE_ORDER.getDefine(sort);
	}

	public int getPage() {
		if (page <= 0)
			return 1;
		
		return page;
	}

	public void setPage(int page) {
		if (page <= 0)
			page = 1;
		
		this.page = page;
	}

	public int getUnitStat() {
		return unitStat;
	}

	public void setUnitStat(int unitStat) {
		this.unitStat = unitStat;
	}
}
