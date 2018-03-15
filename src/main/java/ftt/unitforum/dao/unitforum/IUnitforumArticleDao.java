package ftt.unitforum.dao.unitforum;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import ftt.unitforum.types.UnitforumArticle;
import ftt.unitforum.types.UnitforumLikeHistory;

public interface IUnitforumArticleDao {
	public List<UnitforumArticle> getArticleList(@Param("master_idx") int master_idx, 
												@Param("lang_code") String lang_code, @Param("world_idx") int world_idx, 
												@Param("class1_code") String class1_code, @Param("class2_code") String class2_code, 
												@Param("account_idx") int account_idx, @Param("sort_order") int sort_order,
												@Param("limit_start") int limit_start, @Param("limit_count") int limit_count
												);
	
	public UnitforumArticle getArticle(@Param("master_idx") int master_idx,	@Param("article_idx") int article_idx);
	
	public boolean insertArticle(@Param("master_idx") int master_idx, 
								@Param("lang_code") String lang_code, @Param("world_idx") int world_idx, 
								@Param("class1_code") String class1_code, @Param("class2_code") String class2_code,
								@Param("account_idx") int account_idx, @Param("nickname") String nickname, 
								@Param("char_grade") String char_grade, @Param("content") String content,
								@Param("like_cnt") int like_cnt, @Param("create_dt") Date create_dt);
	
	public boolean deleteArticle(@Param("master_idx") int master_idx,	@Param("article_idx") int article_idx,
								@Param("account_idx") int account_idx);
	
	public UnitforumLikeHistory getLikeHistory(@Param("article_idx") int article_idx, @Param("account_idx") int account_idx);
	
	public boolean insertLikeHistory(@Param("article_idx") int article_idx, @Param("account_idx") int account_idx, 
									@Param("create_dt") Date create_dt);
	
	public boolean deleteLikeHistory(@Param("article_idx") int article_idx,	@Param("account_idx") int account_idx);
	
	public boolean increaseLikeCount(@Param("master_idx") int master_idx,	@Param("article_idx") int article_idx);
	
	public boolean decreaseLikeCount(@Param("master_idx") int master_idx,	@Param("article_idx") int article_idx);
}