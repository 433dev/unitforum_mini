package ftt.unitforum.controller;

import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftt.unitforum.annotation.CheckFingerprint;
import ftt.unitforum.annotation.CheckLanguage;
import ftt.unitforum.config.AppConfig;
import ftt.unitforum.controller.validator.ForumDataValidator;
import ftt.unitforum.exception.ForumException;
import ftt.unitforum.service.forum.IArticleService;
import ftt.unitforum.types.JsonResult;
import ftt.unitforum.types.UnitforumArticle;
import ftt.unitforum.types.UnitforumRequest;

@RestController
@RequestMapping("/forum/ajax/article")
@CheckFingerprint
@CheckLanguage
public class AjaxArticleController {

	@Autowired
    private AppConfig appConfig;
	
	@Autowired
	protected IArticleService articleSvc;
	
	@Autowired
    private ForumDataValidator validator;
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getList(@ModelAttribute("forumReq") UnitforumRequest forumReq, BindingResult result) throws Exception {

		validator.validateListRequest(forumReq, result);
        if(result.hasErrors())
        	throw new ForumException().setErrorCode("data.invalid").setAddMessage(result.getFieldError().getCode());

        int articleRowCount = appConfig.getArticleRowCount();
		// UnitforumMaster masterInfo = articleSvc.getMasterInfo(forumReq.getSsn(), forumReq.getMidx());
        List<UnitforumArticle> articleList = articleSvc.getArticleList(forumReq.getSsn(), forumReq.getMidx(), forumReq.getLang(), forumReq.getWidx(), 
        																forumReq.getC1code(), forumReq.getC2code(), forumReq.getGusn(), 
        																forumReq.getSort(), forumReq.getPage(), articleRowCount);
        
        if (articleList != null) {
        	for (int i=0; i < articleList.size(); i++) {
        		articleList.get(i).setCharGrade( StringEscapeUtils.escapeHtml4(articleList.get(i).getCharGrade()) );
        		articleList.get(i).setNickname( StringEscapeUtils.escapeHtml4(articleList.get(i).getNickname()) );
        		articleList.get(i).setContent( StringEscapeUtils.escapeHtml4(articleList.get(i).getContent()) );
        	}
        }
        
		JsonResult jsonResult = JsonResult.success();
		jsonResult.setData(articleList);

		return jsonResult;
	}
	
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public Object doWrite(@ModelAttribute("forumReq") UnitforumRequest forumReq, BindingResult result) throws Exception {

		validator.validateWriteRequest(forumReq, result);
        if(result.hasErrors())
        	throw new ForumException().setErrorCode("data.invalid").setAddMessage(result.getFieldError().getCode());
        
		if (articleSvc.writeArticle(forumReq.getSsn(), forumReq.getMidx(), forumReq.getLang(), forumReq.getWidx(), 
									forumReq.getC1code(), forumReq.getC2code(), forumReq.getGusn(),
									forumReq.getNick(), forumReq.getLevel(), forumReq.getContent()) ) {
			return JsonResult.success();
		}

		return JsonResult.fail();
	}
	
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public Object doDelete(@ModelAttribute("forumReq") UnitforumRequest forumReq, BindingResult result) throws Exception {

		validator.validateDeleteRequest(forumReq, result);
        if(result.hasErrors()) {
            // 검증에 실패한 경우
        	System.out.println("vaildation error!" + result.getErrorCount() + " , " + result.getFieldError().getCode());
        	throw new ForumException().setErrorCode("data.invalid").setAddMessage(result.getFieldError().getCode());
        }
        
		if ( articleSvc.deleteArticle(forumReq.getSsn(), forumReq.getMidx(), forumReq.getAidx(), forumReq.getGusn()) ) 
			return JsonResult.success();

		return JsonResult.fail();
	}
	
	@RequestMapping(value = "/like", method = { RequestMethod.GET, RequestMethod.POST })
	public Object doLike(@ModelAttribute("forumReq") UnitforumRequest forumReq, BindingResult result) throws Exception {

		validator.validateLikeRequest(forumReq, result);
        if(result.hasErrors())
        	throw new ForumException().setErrorCode("data.invalid").setAddMessage(result.getFieldError().getCode());
        
        int curLikeCnt = articleSvc.likeArticle(forumReq.getSsn(), forumReq.getMidx(), forumReq.getAidx(), forumReq.getGusn());
		JsonResult jsonResult = JsonResult.success();
		jsonResult.setData(curLikeCnt);

		return jsonResult;
	}
	
	@RequestMapping(value = "/unlike", method = { RequestMethod.GET, RequestMethod.POST })
	public Object doUnlike(@ModelAttribute("forumReq") UnitforumRequest forumReq, BindingResult result) throws Exception {

		validator.validateLikeRequest(forumReq, result);
        if(result.hasErrors()) {
            // 검증에 실패한 경우
        	System.out.println("vaildation error!" + result.getErrorCount() + " , " + result.getFieldError().getCode());
        	throw new ForumException().setErrorCode("data.invalid").setAddMessage(result.getFieldError().getCode());
        }
        
        int curLikeCnt = articleSvc.unlikeArticle(forumReq.getSsn(), forumReq.getMidx(), forumReq.getAidx(), forumReq.getGusn());
        JsonResult jsonResult = JsonResult.success();
		jsonResult.setData(curLikeCnt);

		return jsonResult;
	}
}
