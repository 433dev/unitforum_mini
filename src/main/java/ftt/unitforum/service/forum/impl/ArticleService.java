package ftt.unitforum.service.forum.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftt.unitforum.dao.unitforum.IUnitforumArticleDao;
import ftt.unitforum.dao.unitforum.IUnitforumMasterDao;
import ftt.unitforum.exception.ForumException;
import ftt.unitforum.service.forum.IArticleService;
import ftt.unitforum.service.forum.IBadwordService;
import ftt.unitforum.types.UnitforumArticle;
import ftt.unitforum.types.UnitforumMaster;

@Service
public class ArticleService implements IArticleService {
	
	@Autowired
	private IUnitforumMasterDao masterDao;
	@Autowired
	private IUnitforumArticleDao articleDao;
	
	@Autowired
	private IBadwordService badwordService;
	
	private UnitforumMaster masterInfo;

	public UnitforumMaster getMasterInfo(int ssn, int masterIdx) {
		//if (masterInfo == null)
		masterInfo = masterDao.getMasterInfo(ssn, masterIdx);
		
		return masterInfo;
	}
	
	public boolean isShowable(int ssn, int masterIdx) {
		UnitforumMaster masterInfo = getMasterInfo(ssn, masterIdx);

		if (masterInfo != null && "Y".equals(masterInfo.getShowYn()))
			return true;
		
		return false;
	}
		
	public List<UnitforumArticle> getArticleList(int ssn, int masterIdx, String langCode, int worldIdx, String class1Code, String class2Code, int accountIdx, int sortOrder, int page, int rowCount) {
		int limitStart = 0;
		if (page <= 0) page = 1;
		if (page > 1) limitStart = (page-1) * rowCount;
		
		if (isShowable(ssn, masterIdx))
			return articleDao.getArticleList(masterIdx, langCode, worldIdx, class1Code, class2Code, accountIdx, sortOrder, limitStart, rowCount + 5);
		
		return null;
	}

	public boolean writeArticle(int ssn, int masterIdx, String langCode, int worldIdx, String class1Code, String class2Code, int accountIdx, String nickname, String charGrade, String content) throws Exception {
		int like_cnt = 0;
		Date createDate = new Date();
		
		/*
		String firstBadword = badwordService.getFirstBadword(content);
		if (firstBadword != null)
			throw new ForumException().setErrorCode("data.badword").setAddMessage(firstBadword).setDebugMessage(firstBadword);
		*/
		
		content = badwordService.replaceBadword(content, "***");
		if (isShowable(ssn, masterIdx) && articleDao.insertArticle(masterIdx, langCode, worldIdx, class1Code, class2Code, accountIdx, nickname, charGrade, content, like_cnt, createDate))
			return true;
		
		return false;
	}
	
	public boolean deleteArticle(int ssn, int masterIdx, int articleIdx, int accountIdx){

		if (isShowable(ssn, masterIdx) && articleDao.deleteArticle(masterIdx, articleIdx, accountIdx))
			return true;
		
		return false;
	}
	
	public int likeArticle(int ssn, int masterIdx, int articleIdx, int accountIdx) throws Exception {
		
		if (!isShowable(ssn, masterIdx))
			throw new ForumException().setErrorCode("article.permission.deny");
			
		UnitforumArticle article = articleDao.getArticle(masterIdx, articleIdx);
		if (article == null)
			throw new ForumException().setErrorCode("article.not_exist");
		
		if (article.getAccountIdx() == accountIdx) // 자신의 글이면
			throw new ForumException().setErrorCode("article.permission.self");
	
		if (articleDao.getLikeHistory(articleIdx, accountIdx) != null) // like 기록이 있으면
			throw new ForumException().setErrorCode("article.like.duplicate");
		
		if (!articleDao.insertLikeHistory(articleIdx, accountIdx, new Date()))
			throw new ForumException().setErrorCode("system.error.db");
		
		if (!articleDao.increaseLikeCount(masterIdx, articleIdx))
			throw new ForumException().setErrorCode("system.error.db");
	
		article = articleDao.getArticle(masterIdx, articleIdx); // get new count
		return article.getLikeCnt();
	}
	
	public int unlikeArticle(int ssn, int masterIdx, int articleIdx, int accountIdx) throws Exception {

		if (!isShowable(ssn, masterIdx))
			throw new ForumException().setErrorCode("article.permission.deny");
		
		if (articleDao.getLikeHistory(articleIdx, accountIdx) == null)
			throw new ForumException().setErrorCode("article.like.not_exist");
		
		if (!articleDao.deleteLikeHistory(articleIdx, accountIdx))
			throw new ForumException().setErrorCode("system.error.db");
		
		if (!articleDao.decreaseLikeCount(masterIdx, articleIdx))
			throw new ForumException().setErrorCode("system.error.db");
		
		UnitforumArticle article = articleDao.getArticle(masterIdx, articleIdx); // get new count
		return article.getLikeCnt();
	}
}
