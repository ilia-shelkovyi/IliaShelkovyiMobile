package scenarios;

import org.testng.annotations.Test;
import setup.BaseTest;

import static utils.PropertyReader.get;
import static utils.TextFromScreenshot.getToastMessage;

public class nativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "register a new account and then sign in. Make sure that you are on the BudgetActivity page")
    public void nativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
    	//Account registration
        getPo().getWelement("registerBtn").click();
        getPo().getWelement("registrationEmail").sendKeys(get("email"));
        getPo().getWelement("registrationUsername").sendKeys(get("username"));
        getPo().getWelement("registrationPassword").sendKeys(get("password"));
        getPo().getWelement("registrationConfirmPassword").sendKeys(get("password"));
        getPo().getWelement("registerNewAccountBtn").click();

        //Sign in
        getPo().getWelement("loginEmail").sendKeys(get("email"));
        getPo().getWelement("loginPwd").sendKeys(get("password"));
        getPo().getWelement("signInBtn").click();

        assert getPo().getWelement("frameTitle").getText().equals(get("title")) : "This is not expected title";
        System.out.println("Android native test done");
    }

    @Test(groups = {"native"}, description = "Make sure that you are getting error without registration")
    public void nativeTestWithoutRegistration() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        //Sign in
        getPo().getWelement("loginEmail").sendKeys(get("email"));
        getPo().getWelement("loginPwd").sendKeys(get("password"));
        getPo().getWelement("signInBtn").click();

        assert getToastMessage(getDriver()).contains(get("error")) : "No expected error";
        System.out.println("Android native test on error done");
    }

}
