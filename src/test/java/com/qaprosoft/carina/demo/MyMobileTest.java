package com.qaprosoft.carina.demo;


import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.demo.mobile.gui.pages.android.WebViewPage;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.CarinaDescriptionPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.LoginPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.WebViewPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.WelcomePageBase;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyMobileTest implements IAbstractTest, IMobileUtils {

    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestLabel(name = "feature", value = {"mobile", "regression"})
    public void firstTaskTest() {
        String username = "Test_user";
        String password = RandomStringUtils.randomAlphabetic(10);
        //Step 1
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        Assert.assertTrue(welcomePage.isPageOpened(), "Welcome page isn't opened");
        LoginPageBase loginPage = welcomePage.clickNextBtn();
        //Step 2
        Assert.assertTrue(loginPage.isNamePresent(), "Name field isn't present");
        Assert.assertTrue(loginPage.isPasswordPresent(), "Password field isn't present");
        Assert.assertTrue(loginPage.isSexPresent("Male"), "Male checkbox isn't present");
        Assert.assertTrue(loginPage.isSexPresent("Female"), "Female checkbox isn't present");
        Assert.assertFalse(loginPage.isSexChecked("Male"), "Female checkbox is checked");
        Assert.assertFalse(loginPage.isSexChecked("Female"), "Female checkbox isn't checked");
        //Step 3
        loginPage.typeName(username);
        loginPage.typePassword(password);
        Assert.assertTrue(loginPage.isItemByTextPresent(username),"The field Name is not checked");
        Assert.assertTrue(loginPage.isItemByTextPresent(password),"The field Password is not checked");
        //Step 4
        loginPage.selectMaleSex();
        Assert.assertTrue(loginPage.isSexChecked("Male"),"Male cheskbox is not typed");
        //Step 5
        loginPage.checkPrivacyPolicyCheckbox();
        Assert.assertTrue(loginPage.isPrivacyPolicyCheckboxChecked(),"Privacy Policy switch not selected");
        //Step 6
        CarinaDescriptionPageBase carinaDescriptionPage = loginPage.clickLoginBtn();
        WebViewPageBase webViewPage = initPage(getDriver(), WebViewPage.class);
        Assert.assertTrue(webViewPage.isPageOpened(), "Web View Page page isn't opened");

    }

}
