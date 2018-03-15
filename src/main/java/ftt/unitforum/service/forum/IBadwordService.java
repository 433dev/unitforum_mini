package ftt.unitforum.service.forum;

public interface IBadwordService {
	public String getFirstBadword(String text);
	public String replaceBadword(String text, String mask);
}
