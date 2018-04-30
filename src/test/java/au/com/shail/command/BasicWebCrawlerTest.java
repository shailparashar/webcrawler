package au.com.shail.command;

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
import org.springframework.test.util.ReflectionTestUtils;

import au.com.shail.model.Node;
import au.com.shail.util.WebCrawlerUtil;

@RunWith(MockitoJUnitRunner.class)
public class BasicWebCrawlerTest {


	@InjectMocks
	BasicWebCrawler basicWebCrawler = new BasicWebCrawler();
	
	@Mock
	WebCrawlerUtil util;
	
	@Before
	public void initializeMockito() { MockitoAnnotations.initMocks(this);}

	@Test
	public void crawlTest() throws Exception {
		ReflectionTestUtils.setField(basicWebCrawler, "maxDepth", 5);
		String url = "https://google.com";
		String maxDepth = "2";
		Node node = new Node();
		stub(util.getPageLinks(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyListOf(String.class))).toReturn(node);
		Assert.assertNotNull(basicWebCrawler.crawl(url, maxDepth));

	}
}