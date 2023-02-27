package webUICustomKeywords



import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword

import net.sf.jasperreports.web.util.WebUtil



/**
 * General description: List out some common keywords used for web application
 * 
 * Keyword list:
 *                  verifyObjectPresent					: Custom keyword is used for verify if the given object is existence on a web page
 * 					verifyObjectEnabled					: Custom keyword is used for verify if the given object is enabled on a web page
 * 					verifyObjectEnabled					: Custom keyword is used for verify if the given object is checked on a web page
 *                  inputDataToTestObject				: Custom keyword is used for inputting a text string to object on a web page
 *                  printStepMessage					: Custom keyword is used for print step message to console
 *					clickToRadioButton					: Custom keyword is used for clicked a radio button on a web page
 *					clickToCheckBox						: Custom keyword is used for clicked a check box on a web page
 *					clickToButton						: Custom keyword is used for clicked a button on a web page
 *					getTextFromTestObject				: Custom keyword is used for getting a text string from object(button, radio button, label,...) on a web page
 *					getTextFromInput					: Custom keyword is used for getting a text string from object(with html tag name input) on a web page
 *					selectComboBoxItemByIndex			: Custom keyword is used for select all items that have a index matching the "index" argument on a web page
 *					selectComboBoxItemByValue			: Custom keyword is used for select all options that have a value matching the "value" argument on a web page
 *					selectListViewItem					: Custom keyword is used for selected a item from list view on a web page
 *					selectMenuItem						: Custom keyword is used for selected a item from menu on a web page
 * 
 * @since 08/02/2023 
 * 
 * @author ABA Automation team
 */
public class Common {
	/**
	 * Custom keyword name: verifyObjectPresent(TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for verify object existence on a web page</br></br>
	 *
	 * @param objPath	: The object in the Object Repository
	 * @param iTimeOut	: The time for waiting object is displayed on web page
	 *
	 * @return
	 *			true if element presents; otherwise, false
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static Boolean verifyObjectPresent(TestObject objPath, int iTimeOut) {
		try{
			//Verify that the object exists or not
			if(WebUI.verifyElementPresent(objPath, iTimeOut, FailureHandling.CONTINUE_ON_FAILURE) == true) {
				//System.out.println("The object is existence.")
				return true
			} else {
				KeywordUtil.logInfo("Object '" + objPath.objectId.split('/').last() + "' is not existence.")
				return false
			}
		} catch(Exception ex) {
			KeywordUtil.markFailed("Object is not existence in Object Repository.")
			return false
		}
	}

	/**
	 * Custom keyword name: verifyObjectEnabled(TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for verify object enabled on a web page</br></br>
	 *
	 * @param objPath	: The object in the Object Repository
	 * @param iTimeOut	: The time for waiting object is displayed on web page
	 *
	 * @return
	 *			true if the element is present and clickable; otherwise, false
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static Boolean verifyObjectEnabled(TestObject objPath) {
		try{
			//Verify that the object is enabled or not
			if(WebUI.verifyElementClickable(objPath, FailureHandling.CONTINUE_ON_FAILURE) == true) {
				//System.out.println("The object is enabled.")
				return true
			} else {
				KeywordUtil.markFailed("Object '" + objPath.objectId.split('/').last() + "'is disabled.")
				return false
			}
		} catch(Exception ex) {
			KeywordUtil.markFailed("Object is not enabled in Object Repository.")
			return false
		}
	}

	/**
	 * Custom keyword name: verifyObjectEnabled(TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for verify if the given object is checked on a web page</br></br>
	 *
	 * @param objPath	: The object in the Object Repository
	 * @param iTimeOut	: The time for waiting object is displayed on web page
	 *
	 * @return
	 *			true if the element is present and clickable; otherwise, false
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */	
	@Keyword
	def static Boolean verifyObjectChecked(TestObject objPath, int iTimeOut) {
		try{
			//Verify that the object is enabled or not
			if(WebUI.verifyElementNotChecked(objPath, iTimeOut, FailureHandling.CONTINUE_ON_FAILURE) == true) {
				return true
			} else {
				KeywordUtil.logInfo("Object '" + objPath.objectId.split('/').last() + "' is already checked.")
				return false
			}
		} catch(Exception ex) {
			return false
		}
	}


	/**
	 * Custom keyword name: inputDataToTestObject(String sObjPath, String sText, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for inputting a text string to object on a web page</br></br>
	 *
	 * @param objPath	: The object in the Object Repository
	 * @param sText		: The text string is input in object
	 * @param iTimeOut	: The time for waiting object is displayed on web page
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void inputDataToTestObject(TestObject objPath, String sText, int iTimeOut) {
		try{
			if(verifyObjectPresent(objPath, iTimeOut) == true) {
				if(verifyObjectEnabled(objPath) == true) {
					WebUI.setText(objPath, sText)
					if(getTextFromObjectByInput(objPath, iTimeOut).equals(sText)) {
						KeywordUtil.markPassed("The object is inputed data successful.")
					} else {
						KeywordUtil.markFailed("The object is input data not match with typed data.")
					}
				}
			}
		} catch(Exception ex) {

		}
	}

	/**
	 * Custom keyword name: printStepMessage(String sPageNameOrScreen, String sAction, String sObjName)</br></br>
	 *
	 * General description: Custom keyword is used for print step message to console</br></br>
	 *
	 * @param sPageNameOrScreen	: The name of page or screen
	 * @param sAction      		: The name of action
	 * @param objName    		: The name of object
	 *
	 * @return
	 * 			None
	 *
	 * @since 17/02/2023
	 *
	 * @author ABA Automation team
	 */

	@Keyword
	def static void printStepMessage(String sPageNameOrScreen, String sAction, String sObjName) {
		KeywordUtil.logInfo(sPageNameOrScreen + " - " + sAction + " - " + sObjName)
	}

	/**
	 * Custom keyword name: clickToRadioButton(String sObjPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for clicked a radio button on a web page</br></br>
	 *
	 * @param objPath	: The object in the Object Repository
	 * @param iTimeOut	: The time for waiting object is displayed on web page
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void clickToRadioButton(TestObject objPath, int iTimeOut) {
		try{
			//Verify that the object exists or not and verify that the object is enabled or not
			if(verifyObjectPresent(objPath, iTimeOut) == true) {
				if(verifyObjectEnabled(objPath) == true) {
					//Verify that the object is checked or not
					if(verifyObjectChecked(objPath, iTimeOut) == true) {
						WebUI.click(objPath)
						//System.out.println("The object is clicked successful.")
					}
				}
			}
		} catch(Exception ex) {

		}
	}

	/**
	 * Custom keyword name: clickToCheckBox(TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for clicked a check box on a web page</br></br>
	 *
	 * @param objPath	: The object in the Object Repository
	 * @param iTimeOut	: The time for waiting object is displayed on web page
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void clickToCheckBox(TestObject objPath, int iTimeOut) {
		try{
			//Verify that the object exists or not and verify that the object is enabled or not
			if(verifyObjectPresent(objPath, iTimeOut) == true) {
				if(verifyObjectEnabled(objPath) == true) {
					//Verify that the object is checked or not
					if(verifyObjectChecked(objPath, iTimeOut) == true) {
						WebUI.click(objPath)
						//System.out.println("The object is clicked successful.")
					}
				}
			}
		} catch(Exception ex) {

		}
	}

	/**
	 * Custom keyword name: clickToButton(TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for clicked a button on a web page</br></br>
	 *
	 * @param objPath	: The represent a web element in the Object Repository
	 * @param iTimeOut	: The time for waiting object is displayed on web page
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void clickToButton(TestObject objPath, int iTimeOut) {
		try{
			if(verifyObjectPresent(objPath, iTimeOut) == true) {
				if(verifyObjectEnabled(objPath) == true) {
					WebUI.click(objPath)
					//KeywordUtil.markPassed("The object is clicked successful.")
				}
			}
		} catch(Exception ex) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
		}
	}

	/**
	 * Custom keyword name: getTextFromObject(TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for getting a text string from object(button, radio button, label,...) on a web page</br></br>
	 *
	 * @param objPath	: The object in the Object Repository
	 * @param iTimeOut	: The time for waiting object is displayed on web page
	 *
	 * @return
	 * 			innerText of the test object
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */	
	@Keyword
	def static String getTextFromObject(TestObject objPath, int iTimeOut) {
		try{
			if(verifyObjectPresent(objPath, iTimeOut) == true) {
				if(verifyObjectEnabled(objPath) == true) {
					return WebUI.getText(objPath)
				}
			}
		} catch(Exception ex) {

		}
	}

	/**
	 * Custom keyword name: getTextFromObjectByInput(TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for getting a text string from object(with html tag name input) on a web page</br></br>
	 *
	 * @param objPath		: The object in the Object Repository
	 * @param iTimeOut		: The time for waiting object is displayed on web page
	 *
	 * @return
	 * 			innerText of the test object
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static String getTextFromObjectByInput(TestObject objPath, int iTimeOut) {
		try{
			if(verifyObjectPresent(objPath, iTimeOut) == true) {
				if(verifyObjectEnabled(objPath) == true) {
					return WebUI.getAttribute(objPath, "value")
				}
			}
		} catch(Exception ex) {

		}
	}


	/**
	 * Custom keyword name: selectComboBoxItemByIndex(TestObject objPath, int itemIndex, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for select all items that have a index matching the "index" argument on a web page</br></br>
	 *
	 * @param objPath		: The object in the Object Repository
	 * @param itemIndex		: The index index range of the options to select from object. Index starts from 0.
	 * @param iTimeOut		: The time for waiting object is displayed on web page
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void selectComboBoxItemByIndex(TestObject objPath, int sItemIndex, int iTimeOut) {
		try{
			//Verify that the object exists or not
			if(verifyObjectPresent(objPath, iTimeOut) == true) {
				if (verifyObjectEnabled(objPath) == true) {
					if(sItemIndex >= 0) {
						WebUI.selectOptionByIndex(objPath, sItemIndex)
					} else {
						KeywordUtil.markFailed("The item index typed is lower than 0")
					}
				}
			}
		} catch(Exception ex) {

		}
	}

	/**
	 * Custom keyword name: selectComboBoxItemByValue(TestObject objPath, String itemValue, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for select all items that have a value matching the "value" argument on a web page</br></br>
	 *
	 * @param sObjPath		: The element logical name path in the Object Repository
	 * @param itemValue		: The value of the options to select from object
	 * @param iTimeOut		: The time for waiting object is displayed on web page
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void selectComboBoxItemByValue(TestObject objPath, String sItemValue, int iTimeOut) {
		try{
			//Verify that the object exists or not
			if(verifyObjectPresent(objPath, iTimeOut) == true) {
				if (verifyObjectEnabled(objPath) == true) {
					if(!sItemValue.equals("")) {
						WebUI.selectOptionByValue(objPath, sItemValue, false)
					} else {
						KeywordUtil.markFailed("The item value typed is null")
					}
				}
			}
		} catch(Exception ex) {

		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Custom keyword name: selectListViewItem(TestObject objPath, String itemValue, int iTimeOut)
	//
	//
	// General description: Custom keyword is used to click an item from listview on the website
	//
	// Parameter list:
	//                  objectPath  : The test object path in the Object Repository
	//                  itemName    : The name of item from object
	//                  iTimeOut    : The time for waiting object is displayed on web page
	// Return values:
	//                  1   : System is get text form the object successful
	//                  0   : The object is not existence
	//                  -4  : Throw message if there is any other errors
	//                  -1  : The object is disabled
	//
	// Last updated: 05/02/2023     // Author: ABA Automation team
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Keyword
	def static int selectListViewItem(String sObjPath, String propName, String propValue, String sText, int iTimeOut) {
		try{
			TestObject objPath = findTestObject(sObjPath,[(propName):propValue])
			//Verify that the object exists or not
			if(WebUI.verifyElementPresent(objPath, iTimeOut, FailureHandling.CONTINUE_ON_FAILURE) == true) {
				if (WebUI.getAttribute(objPath, "enabled") == true) {
					//Verify the test object is enable and click
					if(WebUI.verifyElementClickable(objPath){
						WebUI.click(objPath)
						//System.out.println("The object is clicked successful")
						return 1
					}
				} else {
					//System.out.println("The object is disabled")
					return -1
				}
			} else {
				//System.out.println("The object is not existence.")
				return 0
			}
		} catch(Exception ex) {
			//System.out.println("Throw message if there is any other errors")
			return -4
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Custom keyword name: selectMenuItem(TestObject objPath, String itemValue, int iTimeOut)
	//
	//
	// General description: Custom keyword is used to click an item from Menu on the website
	//
	// Parameter list:
	//                  objectPath  : The test object path in the Object Repository
	//                  itemName    : The name of item from object
	//                  iTimeOut    : The time for waiting object is displayed on web page
	// Return values:
	//                  1   : System is get text form the object successful
	//                  0   : The object is not existence
	//                  -4  : Throw message if there is any other errors
	//                  -1  : The object is disabled
	//
	// Last updated: 05/02/2023     // Author: ABA Automation team
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Keyword
	def static int selectMenuItem(String sObjPath, String propName, String propValue, String sText, int iTimeOut) {
		try{
			TestObject objPath = findTestObject(sObjPath,[(propName):propValue])
			//Verify that the object exists or not
			if(WebUI.verifyElementPresent(objPath, iTimeOut, FailureHandling.CONTINUE_ON_FAILURE) == true) {
				if (WebUI.getAttribute(objPath, "enabled") == true) {
					//Verify the test object is enable and click
					if(WebUI.verifyElementClickable(objPath){
						WebUI.click(objPath)
						//System.out.println("The object is clicked successful")
						return 1
					}
				} else {
					//System.out.println("The object is disabled")
					return -1
				}
			} else {
				//System.out.println("The object is not existence.")
				return 0
			}
		} catch(Exception ex) {
			//System.out.println("Throw message if there is any other errors")
			return -4
		}
	}


	/**
	 * Custom keyword name: getTitleFromDialog (TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for getting a title string text from a dialog</br></br>
	 *
	 * @param objPath	: The object in the Object Repository
	 * @param iTimeOut	: The time for waiting object is displayed on web page
	 *
	 * @return
	 * 			The text in the dialog title
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */

	@Keyword
	def static String getTitleFromDialog (TestObject objPath, int iTimeOut) {
		try{
			// Verify object is existing or not
			if(verifyObjectPresent(objPath, iTimeOut) == true) {
				// Verify object is enabled or not
				if(verifyObjectEnabled(objPath) == true) {
					// Get dialog title
					KeywordUtil.markPassed("Title dialog is getted successfully")
					return getTextFromObject(objPath, 5)
				}
			}
		} catch(Exception ex) {
			KeywordUtil.markFailed("Cannot get the dialog text title")
			return false
		}
	}

	/**
	 * Custom keyword name: verifyTitleFromDialog (TestObject objPath, String sExpectedTitle, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for getting a title string text from a dialog</br></br>
	 *
	 * @param objPath	: The object in the Object Repository
	 * @param sExpectedTitle	: The expected string text of dialog title
	 * @param iTimeOut	: The time for waiting object is displayed on web page
	 *
	 * @return
	 * 			The text in the dialog title is the same or not as expected
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */

	@Keyword
	def static Boolean verifyTitleFromDialog (TestObject objPath, String sExpectedTitle, int iTimeOut) {
		try{
			// Getting the dialog title text
			String sActualTitle = getTitleFromDialog(objPath, 5)
			// Compare between the actual text from window title and expected text from input
			if (sExpectedTitle.equals(sActualTitle)) {
				KeywordUtil.markPassed("Verify successfully with the actual text title is: " +sActualTitle)
				return true
			} else {
				// The actual and expected text is different
				KeywordUtil.markFailed("The window text title is incorrect as expected")
				return false
			}
		} catch(Exception ex) {
			KeywordUtil.markFailed("Cannot verify the window text title")
			return false
		}
	}
}