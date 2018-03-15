package ftt.unitforum.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ftt.unitforum.annotation.CheckFingerprint;
import ftt.unitforum.annotation.CheckLanguage;
import ftt.unitforum.config.AppConfig;
import ftt.unitforum.controller.validator.ForumDataValidator;
import ftt.unitforum.exception.ForumException;
import ftt.unitforum.service.forum.IArticleService;
import ftt.unitforum.types.UnitforumArticle;
import ftt.unitforum.types.UnitforumMaster;
import ftt.unitforum.types.UnitforumRequest;

@Controller
@RequestMapping("/forum")
@CheckFingerprint
@CheckLanguage
public class ForumController extends BaseController {

	@Autowired
    private AppConfig appConfig;
		
	@Autowired
	protected IArticleService articleSvc;
	
	@Autowired
    private ForumDataValidator validator;
	
	@RequestMapping
	private String index() {
		return "index";
	}

	@RequestMapping("/list")
	private String actionList(Model model, @ModelAttribute("forumReq") UnitforumRequest forumReq, BindingResult result, HttpServletRequest request) throws Exception {
		
		validator.validate(forumReq, result);

		if(result.hasErrors())
        	throw new ForumException().setErrorCode("data.invalid").setAddMessage(result.getFieldError().getField());
		
		int articleRowCount = appConfig.getArticleRowCount();
		UnitforumMaster masterInfo = articleSvc.getMasterInfo(forumReq.getSsn(), forumReq.getMidx());
        List<UnitforumArticle> articleList = articleSvc.getArticleList(forumReq.getSsn(), forumReq.getMidx(), forumReq.getLang(), forumReq.getWidx(), 
        																forumReq.getC1code(), forumReq.getC2code(), forumReq.getGusn(), 
        																forumReq.getSort(), forumReq.getPage(), articleRowCount);
        
        if (articleList.size() > articleRowCount) {
        	articleList = articleList.subList(0, articleRowCount);
        	model.addAttribute("hasMore", true);
        }
        else {
        	model.addAttribute("hasMore", false);
        }
        
        model.addAttribute("forumReq", forumReq);
        model.addAttribute("masterInfo", masterInfo);
        model.addAttribute("articleList", articleList);
        model.addAttribute("articleRowCount", articleRowCount);

		return "forum/forum_list";
	}
}
