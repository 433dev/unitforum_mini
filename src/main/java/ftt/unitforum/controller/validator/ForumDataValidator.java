package ftt.unitforum.controller.validator;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ftt.unitforum.types.UnitforumRequest;

@Service
public class ForumDataValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return (UnitforumRequest.class.isAssignableFrom(clazz));
	}

	@Override
	public void validate(Object target, Errors errors) {
		// checking default parameters		
		UnitforumRequest req = (UnitforumRequest)target;
		
		if(req.getSsn() <= 0) // ssn
			errors.rejectValue("ssn", "data.invalid");
		if(req.getMidx() <= 0) // midx : uniforum_master_idx
			errors.rejectValue("midx", "data.invalid");
		
		ValidationUtils.rejectIfEmpty(errors, "lang", "data.invalid");
		
        if(req.getWidx() <= 0) // widx : world_idx
	        errors.rejectValue("widx", "data.invalid");
        if(req.getC1code() == null || req.getC1code().trim().length() == 0) // c1code : class1_idx
	        errors.rejectValue("c1code", "data.invalid");
        if(req.getC2code() == null || req.getC2code().trim().length() == 0) // c2code : class2_idx
	        errors.rejectValue("c2code", "data.invalid");
        if(req.getNick() == null || req.getNick().trim().length() == 0 || req.getNick().length() > 50) // nick : nickname
	        errors.rejectValue("nick", "data.invalid");
        if(req.getLevel() == null || req.getLevel().trim().length() == 0 || req.getLevel().length() > 50) // level : char_grade
	        errors.rejectValue("level", "data.invalid");
		if(req.getGusn() <= 0) // gusn : account_idx
			errors.rejectValue("gusn", "data.invalid");
	}

	public void validateListRequest(Object target, Errors errors) {
		validate(target, errors);
	}

	public void validateWriteRequest(Object target, Errors errors) {
		validate(target, errors);
		
		if(errors.hasErrors())
			return;
		
		UnitforumRequest req = (UnitforumRequest)target;
		if(req.getContent() == null || req.getContent().length() == 0 || req.getContent().length() > 300) // content : content
	        errors.rejectValue("content", "data.invalid");
	}
	
	public void validateDeleteRequest(Object target, Errors errors) {

		UnitforumRequest req = (UnitforumRequest)target;
		
		if(req.getAidx() <= 0) // aidx : article_idx
			errors.rejectValue("aidx", "data.invalid");
	}

	public void validateLikeRequest(Object target, Errors errors) {

		UnitforumRequest req = (UnitforumRequest)target;
		
		if(req.getAidx() <= 0) // aidx : article_idx
			errors.rejectValue("aidx", "data.invalid");
	}
}
