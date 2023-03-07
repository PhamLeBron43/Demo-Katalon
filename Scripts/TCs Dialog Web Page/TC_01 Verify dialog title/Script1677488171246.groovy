import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.awt.List

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable
import webUICustomKeywords.Common

import org.openqa.selenium.Keys as Keys

WebUI.comment("Test case 01: Verify login succesfully")

WebUI.comment("Step 1: Navigate to page")

WebUI.openBrowser('')

WebUI.navigateToUrl('https://www.globalsqa.com/demo-site/dialog-boxes/#Form')


WebUI.comment("Step 2: Click to 'Message Box'")

Common.clickToButton(findTestObject('Object Repository/Page_Dialog_Message/button_Message Box'), 5)


//WebUI.comment("Step 3: Get dialog title")
//Common.getTitleFromDialog(findTestObject('null'), 10)
//
//WebUI.comment("Step 4: Verify dialog title")
//Common.verifyTitleFromDialog(findTestObject('null'), "Download complete", 10)

//WebUI.comment("Step 5: Verify dialog message")
//Common.verifyMessageFromDialog(findTestObject('Object Repository/Page_Dialog_Title/title_dialog'), 
//	findTestObject('Object Repository/Page_Dialog_Message/message_dialog'), 
//	"Download complete", "Your files have downloaded successfully into the My Downloads folder.", 10)


WebUI.comment("Step 5.1: Verify dialog message")
List<TestObject> list = new ArrayList<TestObject>()
list.add(findTestObject('Object Repository/Page_Dialog_Title/title_dialog'))
list.add(findTestObject('Object Repository/Page_Dialog_Message/message_dialog'))
Common.verifyMessageFromDialog_2(list, "Download complete", "Your files have downloaded successfully into the My Downloads folder.", 10)

WebUI.closeBrowser()
