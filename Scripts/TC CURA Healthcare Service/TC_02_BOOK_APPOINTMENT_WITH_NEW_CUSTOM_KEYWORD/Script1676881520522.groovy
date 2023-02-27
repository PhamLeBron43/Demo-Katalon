import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import webUICustomKeywords.Common as Common


WebUI.comment("TC_02_BOOK_A_APPOINTMENT")
//WebUI.openBrowser('')
//WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')

WebUI.comment("Step 1: Navigate to login page")
// Click to button: Make Appointment
Common.printStepMessage("Home page", "Click", "Button MakeAppointment")
Common.clickToButton(findTestObject('Object Repository/bookAppointment/Page_CURA Healthcare Service/btnMakeAppointment'), 10)

WebUI.comment("Step 2: Set login and password and press login button")
// Input data to text box: UserName
Common.printStepMessage("Login page", "Input Data", "Textbox UserName")
Common.inputDataToTestObject(findTestObject('Object Repository/bookAppointment/Page_CURA Healthcare Service/txtUsername'), "John Doe", 10)

// Get Text from text box: txtUserName
Common.printStepMessage("Login page", "Get Text", "Textbox UserName")
println(Common.getTextFromObjectByInput(findTestObject('Object Repository/bookAppointment/Page_CURA Healthcare Service/txtUsername'), 10))

// Input data to text box: txtPassword
Common.printStepMessage("Login page", "Input Data", "Textbox Password")
Common.inputDataToTestObject(findTestObject('Object Repository/bookAppointment/Page_CURA Healthcare Service/txtPassword'), "ThisIsNotAPassword", 10)

// Get Text from text box: txtPassword
Common.printStepMessage("Login page", "Get Text", "Textbox Password")
println(Common.getTextFromObjectByInput(findTestObject('Object Repository/bookAppointment/Page_CURA Healthcare Service/txtPassword'), 10))

// Click to button: Login
Common.printStepMessage("Login page", "Click", "Button Login")
Common.clickToButton(findTestObject('Object Repository/bookAppointment/Page_CURA Healthcare Service/btnLogin'), 10)

WebUI.comment("Step 3: Book a appointment")
// Select item from comboBox: Facility (by index)
Common.printStepMessage("Make Appointment page", "Select", "Combobox Facility")
Common.selectComboBoxItemByIndex(findTestObject('Object Repository/bookAppointment/Page_CURA Healthcare Service/cbFacility'), 1, 10)

// Select item from comboBox: Facility (by value)
Common.printStepMessage("Make Appointment page", "Select", "Combobox Facility")
Common.selectComboBoxItemByValue(findTestObject('Object Repository/bookAppointment/Page_CURA Healthcare Service/cbFacility'), "Seoul CURA Healthcare Center", 10)

// Click to check box: Apply for hospital readmission
Common.printStepMessage("Make Appointment page", "Click", "Checkbox Apply")
Common.clickToCheckBox(findTestObject('Object Repository/bookAppointment/Page_CURA Healthcare Service/ckApply'), 10)

// Click to radio button: None programs
Common.printStepMessage("Make Appointment page", "Click", "Radio None")
Common.clickToRadioButton(findTestObject('Object Repository/bookAppointment/Page_CURA Healthcare Service/rdNonePrograms'), 10)

// Input data to text box: Date
Common.printStepMessage("Make Appointment page", "Input Data", "Textbox Date")
Common.inputDataToTestObject(findTestObject('Object Repository/bookAppointment/Page_CURA Healthcare Service/txtDate'), "20/02/2023", 10)

// Click to button: Book Appointment
Common.printStepMessage("Page 3", "Click", "Button BookAppointment")
Common.clickToButton(findTestObject('Object Repository/bookAppointment/Page_CURA Healthcare Service/btnBookAppointment'), 10)

WebUI.closeBrowser()

