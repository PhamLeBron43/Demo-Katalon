import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable
import webUICustomKeywords.Common

import org.openqa.selenium.Keys as Keys

WebUI.comment("Test case 01: Verify login succesfully")

WebUI.comment("Step 1: Navigate to login page")
WebUI.openBrowser('')
WebUI.navigateToUrl('https://opensource-demo.orangehrmlive.com/web/index.php/auth/login')

WebUI.comment("Step 2: Input user name and password then press 'Enter'")
// Input username to text box
Common.printStepMessage("Orange HRM Login ", "Input data", "Text box Username")
Common.inputDataToTestObject(findTestObject("Object Repository/Orange HRM/input_Username_username"), "Admin", 5)

// Input password to text box
Common.printStepMessage("Orange HRM Login ", "Input data", "Text box Password")
Common.inputDataToTestObject(findTestObject("Object Repository/Orange HRM/input_Password_password"), "admin123", 5)

// Click login button
Common.printStepMessage("Orange HRM Login ", "Click", "Login button")
Common.clickToButton(findTestObject("Object Repository/Orange HRM/button_Login"), 10)

WebUI.closeBrowser()