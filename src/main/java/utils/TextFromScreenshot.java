package utils;

import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.AppiumDriver;

public class TextFromScreenshot {

    private static final String TESSDATA_PATH = "src/assets/tessdata/";

    public static String getToastMessage(AppiumDriver appiumDriver) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        captureScreenshot(TESSDATA_PATH, appiumDriver);
        String str = "";
        TessBaseAPI api = new TessBaseAPI();
        api.Init(TESSDATA_PATH, "eng");
        PIX image = pixRead(TESSDATA_PATH + "ToastMessage.png");
        api.SetImage(image);
        BytePointer outText = api.GetUTF8Text();
        str = outText.getString();
        System.out.println("OCR output:\n" + str);
        api.End();
        outText.deallocate();
        pixDestroy(image);
        return str;
    }
    	
    private static void captureScreenshot(String filePath, AppiumDriver appiumDriver) {
        File scrFile = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);	
        try {
            FileUtils.copyFile(scrFile,  new File(filePath + "ToastMessage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
