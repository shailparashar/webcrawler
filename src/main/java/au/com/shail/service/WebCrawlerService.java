package au.com.shail.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import au.com.shail.command.WebCrawler;
import au.com.shail.model.Node;
import au.com.shail.model.WebCrawlerError;

@RestController
public class WebCrawlerService {

	private final Logger log = org.slf4j.LoggerFactory.getLogger(WebCrawlerService.class);

	@Autowired
	WebCrawler webCrawler;

	/**
	 * @param url
	 * @param maxDepth
	 * @return
	 */
	@RequestMapping(path = "webcrawler", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> crawlWebUrl(@RequestParam(value = "url", required = true) final String url,
			@RequestParam(value = "maxdepth", required = false) final String maxDepth) {

		try {
			log.info("Request received for URL : {} , Max depth : {}", url, maxDepth);
			Node response = webCrawler.crawl(url, maxDepth);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			log.error(e.getMessage());
			WebCrawlerError error = new WebCrawlerError(HttpStatus.INTERNAL_SERVER_ERROR, "Please contact API support");
			return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
