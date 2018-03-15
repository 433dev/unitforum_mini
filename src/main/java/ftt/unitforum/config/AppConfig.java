package ftt.unitforum.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application")
public class AppConfig {
	private Map<String, DataSourceConfig> datasource = new HashMap<String, DataSourceConfig>();
	private String environment;
	private String localeParam;
	private int articleRowCount;
	private String staticResource;
	private String messageResource;

	@PostConstruct
	private void init() {
		System.setProperty("unitforum.domain.static", staticResource);
	}

	public Map<String, DataSourceConfig> getDatasource() {
		return datasource;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getLocaleParam() {
		return localeParam;
	}

	public void setLocaleParam(String localeParam) {
		this.localeParam = localeParam;
	}

	public int getArticleRowCount() {
		return articleRowCount;
	}

	public void setArticleRowCount(int articleRowCount) {
		this.articleRowCount = articleRowCount;
	}

	public String getStaticResource() {
		return staticResource;
	}

	public void setStaticResource(String staticResource) {
		this.staticResource = staticResource;
	}

	public String getMessageResource() {
		return messageResource;
	}

	public void setMessageResource(String messageResource) {
		this.messageResource = messageResource;
	}
}
