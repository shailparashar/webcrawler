package au.com.shail.command;

import org.springframework.stereotype.Component;

import au.com.shail.model.Node;

@Component
public interface WebCrawler {
	
	/**
	 * @param url
	 * @param maxDepth
	 * @return
	 */
	public Node crawl(String url, String maxDepth);

}
