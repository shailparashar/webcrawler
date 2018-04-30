package au.com.shail.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import au.com.shail.model.Node;

@Component
public class WebCrawlerUtil {

	private final Logger log = org.slf4j.LoggerFactory.getLogger(WebCrawlerUtil.class);
	private Cleaner cleaner = new Cleaner(Whitelist.basic());

	/**
	 * recursive crawler to fetch child pages upto provided max depth
	 * 
	 * @param url
	 * @param depth
	 * @param maxDepth
	 * @param processedUrls
	 * @return
	 */
	public Node getPageLinks(String url, int depth, int maxDepth, final List<String> processedUrls) {

		Node node = null;
		final List<String> updatedProcessedUrls = Optional.ofNullable(processedUrls).orElse(new ArrayList<>());

		if (url != null && !url.isEmpty() && depth < maxDepth && !updatedProcessedUrls.contains(url)) {			
			try {
				updatedProcessedUrls.add(url);
				Document document = Jsoup.connect(url).get();
				String title = document.title();
				node = new Node();
				node.setTitle(title);
				node.setUrl(url);
				depth++;
				log.info("Depth {} : url {} : Title {}", depth, url, title);
				if (depth < maxDepth) {
					HashSet<Node> nodes = new HashSet<Node>();
					document = cleaner.clean(document);
					Elements linksOnPage = document.select("a[href]");
					for (Element page : linksOnPage) {
						Node childNode = getPageLinks(page.attr("abs:href"), depth, maxDepth, updatedProcessedUrls);
						if (childNode != null) {
							nodes.add(childNode);
						}
					}
					node.setNodes(nodes);
				}

			} catch (IOException e) {
				log.error("For URL : {} Error : {} ", url, e.getMessage());
			}
		}
		return node;
	}

}
