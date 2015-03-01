package com.test.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.web.view.html.HtmlTag;

public class HtmlTagTest {

	@Test
	public void sanityTest() {
		try {
			{
				HtmlTag html = HtmlTag.html();
				html.addChild(HtmlTag.head());
				html.addChild(HtmlTag.body());
				System.out.println(html);
				String expect = "<html><head></head><body></body></html>";
				assertEquals(html.toString(), expect);
			}
			{
				HtmlTag div = HtmlTag.div();
				div.setClass("col-md-8");
				div.addClass("black");
				div.setId("main");
				HtmlTag subDiv = HtmlTag.div();
				div.addChild(subDiv);
				System.out.println(div);
				String expect = "<div id='main' class='col-md-8 black'><div></div></div>";
				assertEquals(div.toString(), expect);
			}
			{
				HtmlTag input = HtmlTag.inputText("username");
				input.setId("name");
				System.out.println(input);
				String expect = "<input type='text' id='name' name='username'></input>";
				assertEquals(input.toString(), expect);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
