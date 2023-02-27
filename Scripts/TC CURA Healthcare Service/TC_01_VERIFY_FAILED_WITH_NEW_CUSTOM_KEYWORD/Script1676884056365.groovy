import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import webUICustomKeywords.Common as Common

Boolean result
WebUI.openBrowser('')
WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')
WebUI.maximizeWindow()

WebUI.comment("Step 1: Navigate to login page")
Common.printStepMessage("Home page", "Verify", "Button MakeAppointment")

// Case 1: object not exist in Repository
result = Common.verifyObjectPresent(findTestObject('Object Repository/bookAppointment/Page_CURA Healthcare Service/a_Make AppointmentDisabledaadsadas'), 10)

// Case 2: object exist in Repository but with wrong xpath
result = Common.verifyObjectPresent(findTestObject('Object Repository/bookAppointment/Page_CURA Healthcare Service/btnMakeAppointmentDisabled'), 10)

// Case 3: verify object is disabled
Common.printStepMessage("Home page", "Verify", "Button MakeAppointment")
result = Common.verifyObjectEnabled(findTestObject('Object Repository/bookAppointment/Page_CURA Healthcare Service/btnMakeAppointment'))

