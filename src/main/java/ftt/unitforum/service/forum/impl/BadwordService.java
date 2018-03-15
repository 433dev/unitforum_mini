package ftt.unitforum.service.forum.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Token;
import org.ahocorasick.trie.Trie;
import org.ahocorasick.trie.Trie.TrieBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import ftt.unitforum.service.forum.IBadwordService;

@Service
public class BadwordService implements IBadwordService {
	
	//private TrieBuilder trieBuilder;
	private Trie badword;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostConstruct 
	public void init(){
		TrieBuilder trieBuilder = Trie.builder().caseInsensitive();
		
		Resource resource = new ClassPathResource("badword/badword.txt");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"));
			String keyword;
	        while ((keyword = br.readLine()) != null) {
	        	trieBuilder.addKeyword(keyword);
	        }
	        badword = trieBuilder.build();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
	
	@Override
	public String getFirstBadword(String text) {
		Emit firstMatch = badword.firstMatch(text);
		if (firstMatch != null)
			return firstMatch.getKeyword();
		
		return null;
	}
	
	@Override
	public String replaceBadword(String text, String mask) {
		Collection<Token> tokens = badword.tokenize(text);
		if (tokens.isEmpty())
			return text;
		
		StringBuffer repBuffer = new StringBuffer();
		for (Token token : tokens) {
	        if (token.isMatch())
	        	repBuffer.append(mask);
	        else
	        	repBuffer.append(token.getFragment());
		}
		
		return repBuffer.toString();
	}
	
}
