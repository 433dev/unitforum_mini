package ftt.unitforum.service.forum;

import java.util.List;

import ftt.unitforum.types.UnitforumArticle;
import ftt.unitforum.types.UnitforumMaster;

public interface IArticleService {
	public UnitforumMaster getMasterInfo(int ssn, int masterIdx);
	public List<UnitforumArticle> getArticleList(int ssn, int masterIdx, String langCode, int worldIdx, String class1Code, String class2Code, int gusn, int sortOrder, int page, int rowCount);
	public boolean writeArticle(int ssn, int masterIdx, String langCode, int worldIdx, String class1Code, String class2Code, int accountIdx, String nickname, String charGrade, String content) throws Exception;
	public boolean deleteArticle(int ssn, int masterIdx, int articleIdx, int gusn);
	public int likeArticle(int ssn, int masterIdx, int articleIdx, int gusn) throws Exception;
	public int unlikeArticle(int ssn, int masterIdx, int articleIdx, int gusn) throws Exception;
}