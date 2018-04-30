package au.com.shail.command;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import au.com.shail.model.Node;
import au.com.shail.util.WebCrawlerUtil;

@Component
public class BasicWebCrawler implements WebCrawler {
	
	private final Logger log = org.slf4j.LoggerFactory.getLogger(BasicWebCrawler.class);

	@Autowired
	WebCrawlerUtil util;

	@Value("#{new Integer('${webcrawl.max.depth}')}")
	private int maxDepth;

	@Override
	@Cacheable("web-crawler")
	public Node crawl(String url, String mxDepth) {
		int depth = (mxDepth != null && !mxDepth.isEmpty()) ? Integer.parseInt(mxDepth) : maxDepth;
		log.info("Max Web Crawling depth : {}", depth);
		return util.getPageLinks(url, 0, depth,null);
	}

}
