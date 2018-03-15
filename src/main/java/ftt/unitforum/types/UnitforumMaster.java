package ftt.unitforum.types;

import java.lang.reflect.Field;
import java.util.Date;

public class UnitforumMaster {
	private int masterIdx;
	private int ssn;
	private Date createDt;
	private String desc;
	private String showYn;
	private int viewType;
	private String unitType;

	public int getMasterIdx() {
		return masterIdx;
	}

	public void setMasterIdx(int masterIdx) {
		this.masterIdx = masterIdx;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getShowYn() {
		return showYn;
	}

	public void setShowYn(String showYn) {
		this.showYn = showYn;
	}
	
	public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}
	
	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	
	public String toString(){
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		sb.append("\n[");
		sb.append(this.getClass().getName());
		sb.append("]\n");
		
		try {
			for (Field f : fields){
				sb.append(f.getName() + ":" + f.get(this).toString() + "\n");
			}
		}catch(Exception e){ }
		
		return sb.toString();
	}
}