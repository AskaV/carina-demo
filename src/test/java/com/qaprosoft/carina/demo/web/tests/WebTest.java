/*
 * Copyright 2013-2021 QAPROSOFT (http://qaprosoft.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qaprosoft.carina.demo.web.tests;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.core.foundation.utils.tag.Priority;
import com.qaprosoft.carina.core.foundation.utils.tag.TestPriority;
import com.qaprosoft.carina.demo.gui.components.HeaderMenu;
import com.qaprosoft.carina.demo.gui.components.NewsItem;
import com.qaprosoft.carina.demo.gui.pages.*;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;



public class WebTest implements IAbstractTest {

    private static final String LOGIN = "yelmarortu@vusra.com";
    private static final String PASS = "yelmarortu@vusra.com";


    @Test()
    @MethodOwner(owner = "Avoznyuk")
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = {"web", "regression"})
    public void testModelSpecs() {
        // #1 open site
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        
        // #2 login with LoginService
        HeaderMenu headerMenu = new HeaderMenu(getDriver());
        headerMenu.autorization(LOGIN,PASS);

        // #3 open News page from footer menu -> News page is opened
        NewsPage newsPage = homePage.getFooterMenu().openNewsPage();
        Assert.assertTrue(newsPage.isPageOpened(), "News page is not opened!");

        // #4 open first article -> The article page is opened
        ArticlePage articlePage = newsPage.openFirstArticleFromNewsPage();
        NewsItem newsItem = new NewsItem(getDriver());

        // Article name from News page and on the article page the same
        String comparedString = newsItem.getTitleFromNewsPage();
        String titleNews = "vivo Y75 5G's full specs leak, Dimensity 700 SoC and 50MP camera in tow";
        Assert.assertEquals(comparedString, titleNews, "Articles are not the same");

    }



    @Test()
    @MethodOwner(owner = "Avoznyuk")
    @TestLabel(name = "feature", value = {"web", "acceptance"})
    public void testNewsSearch() {
        // #1 open site
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");

        // #2 login with LoginService
        HeaderMenu headerMenu = new HeaderMenu(getDriver());
        headerMenu.autorization(LOGIN,PASS);

        // #3 open News page from footer menu -> News page is opened
        NewsPage newsPage = homePage.getFooterMenu().openNewsPage();
        Assert.assertTrue(newsPage.isPageOpened(), "News page is not opened!");

        // #4 type text 'iPhone' in search field
        final String searchQ = "iphone";
        List<NewsItem> news = newsPage.searchNews(searchQ);
        Assert.assertFalse(CollectionUtils.isEmpty(news), "News not found!");
        SoftAssert softAssert = new SoftAssert();
        for(NewsItem n : news) {
            System.out.println(n.readTitle());
            softAssert.assertTrue(StringUtils.containsIgnoreCase(n.readTitle(), searchQ),
                    "Invalid search results for " + n.readTitle());
        }
        softAssert.assertAll();
    }

}
