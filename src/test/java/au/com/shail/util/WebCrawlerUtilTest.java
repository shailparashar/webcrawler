package au.com.shail.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import au.com.shail.model.Node;


@RunWith(MockitoJUnitRunner.class)
public class WebCrawlerUtilTest {
	
	
	@InjectMocks
	WebCrawlerUtil webCrawlerUtil = new WebCrawlerUtil();
	
	@Before
	public void initializeMockito() { MockitoAnnotations.initMocks(this);}


	@Test
	public void getPageLinksTest() throws Exception {
		String url = "https://www.google.com/";
		int depth = 0;
		int maxDepth = 1;
		Node node = webCrawlerUtil.getPageLinks(url, depth, maxDepth,null);
		Assert.assertEquals("Google", node.getTitle());
		Assert.assertNull(node.getNodes());

	}
}