package com.seleniumFramework.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to declare constants
 * @author Abhijeet Maske
 * @version 1.0 June 22,2023	
 */

public class Config {

	//Desktop Grid details
	public static final String GRID_HUB_IP = "localhost";
	public static final String GRID_HUB_PORT = "4321";
	public static final String GRID_URL = "http://"+ GRID_HUB_IP + ":" + GRID_HUB_PORT + "/wd/hub";
	
	//Browser Stack
	public static final String BROWSER_STACK_USER_NAME = "";
	public static final String BROWSER_STACK_ACCESS_KEY = "";
	public static final String BROWSER_STACK_URL = "https://"+ BROWSER_STACK_USER_NAME +":"+ BROWSER_STACK_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	//Saucelab Details
	public static final String SAUCELABS_USER_NAME = "";
	public static final String SAUCELABS_ACCESS_KEY = "";
	public static final String SAUCELABS_APPIUM_URL = "http://"+ SAUCELABS_USER_NAME + ":" +SAUCELABS_ACCESS_KEY + "@ondamnd.saucelabs.com:80/wd/hub";
	public static final String SAUCELABS_SELENIUM_URL = "http://"+ SAUCELABS_USER_NAME + ":" +SAUCELABS_ACCESS_KEY + "@ondamnd.saucelabs.com/wd/hub";
	
	//TestObject details - US DATA Center
	
	public static final String TEST_OBJECT_URL = "http://appium.testobject.com/wd/hub";
	
	//perfecto
	public static final String PERFECTO_USER_NAME = "";
	public static final String PERFECTO_SECURITY_TOKEN = "";
	public static final String PERFECTO_HOST = "";
	public static final String PERFECTO_URL = "";
	
	//pCloudy
	public static final String PCLOUDY_USER_NAME = "";
	public static final String PCLOUDY_ACCESS_KEY = "";
	public static final String PCLOUDY_URL = "http://"+ SAUCELABS_USER_NAME + ":" +SAUCELABS_ACCESS_KEY + "@ondamnd.saucelabs.com/wd/hub";
	
	//Kobiton
	public static final String KOBITON_SERVER_URL = "";
	
	//bitbar details
	public static final String TESTDROID_API_KEY = "";
	public static final String BITBAR_URL = "";
	
	//SeeTest
	public static final String SEETEST_ACCESS_KEY = "";
	public static final String SEETEST_URL = "";
	
	//HPE mobile Center
	public static final String HPE_MC_URL = "";
	public static final String HPE_MC_USERNAME = "";
	public static final String HPE_MC_PASSWORD = "";
	
	//Applitools API Key
	public static final String APPLITOOLS_API_KEY = "";
	
	//voice automation
	public static final String VOICE_RSS_API_KEY = "";
	public static final String VOICERSS_BASEURL = "http://api.voicerss.org/?";
	
	//Allure Screenshot capture
	public static final String SCREENSHOT_CAPTURE = "Everystep";  //EveryStep or Laststep
 	
	//RemoteDriver config	
	public static final int MAX_WAIT_TIME = 180;
	public static final int POLLING_TIME = 500;
	public static final int XSMALL_PAUSE = 3;
	public static final int SMALL_PAUSE= 5;
	public static final int MEDIUM_PAUSE = 20;
	public static final int LARGE_PAUSE = 60;
	public static final int XLARGE_PAUSE = 120;
	
	// File paths
	
	public static final String SELENIUM_GRID_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resource\\grid\\SeleniumGrid.bat";
	public static final String TEST_DATA_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resource\\testdata\\testdata.xlsx";
	public static final String LOCALIZATION_FOLDER_PATH = System.getProperty("user.dir") + "\\src\\main\\resource\\localization";
	public static final String GSPEC_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resource\\specs";
	public static final String PROD_TEST_FILE_PATH = "";
	
	//App config
	public static final String ANDROID_APP_PATH = "";
	public static final String IOS_REAL_DEVICE_APP_PATH = "";
	public static final String IOS_SIMULATOR_APP_PATH = "";
	
	//WebApp
	public static final String APP_URL = "";
	public static final String PROD_APP_URL = "";
	//canada store
	public static final String APP_URL_CANADASTORE = "";
	
	public static final String QA_URL = "test20.synergyapps.in";
	public static final String UAT_URL = "uat20.synergyapps.in";
	
	public static final String APPLITOOLS_APP_URL = "";
	public static final String GALEN_APP_URL = "";
	public static final String API_TEST_URL = "";
	
	//DB Config
	public static final String DB_HOST =" ";
	public static final String DB_PORT =" ";
	public static final String DB_SID =" ";
	public static final String DB_USER_ID =" ";
	public static final String DB_PASSWORD =" ";
	
	//Global Variable
	public static  String emailaddr = "";
	public static  double subtotal = 0.0;
	public static List<String> Products = new ArrayList<String>();
		
	
}
