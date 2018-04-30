package au.com.shail.service;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.stub;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import au.com.shail.command.WebCrawler;
import au.com.shail.model.Node;


@RunWith(MockitoJUnitRunner.class)
public class WebCrawlerServiceTest {

	@InjectMocks
	WebCrawlerService webCrawlerService = new WebCrawlerService ();
	
	@Mock
	WebCrawler webCrawler;
	
	@Before
	public void initializeMockito() { MockitoAnnotations.initMocks(this);}

	@Test
	public void crawlWebUrlTest() {
		String url = "https://google.com";
		String maxDepth = "2";
		Node node = new Node();
		stub(webCrawler.crawl(Mockito.anyString(), Mockito.anyString())).toReturn(node);
		ResponseEntity<Object> re = webCrawlerService.crawlWebUrl(url, maxDepth);
		Assert.assertEquals(HttpStatus.OK, re.getStatusCode());

	}
	
	@Test
	public void crawlWebUrlExceptionTest() {
		String url = "https://google.com";
		String maxDepth = "2";
		doThrow(new RuntimeException("Test")).when(webCrawler).crawl(Mockito.anyString(), Mockito.anyString());
		ResponseEntity<Object> re = webCrawlerService.crawlWebUrl(url, maxDepth);
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode());

	}	
}