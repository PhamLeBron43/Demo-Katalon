package webUICustomKeywords

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * General description: List out some common keywords used for web application</br>
 * 
 * Keyword list:</br>
 * &nbsp;&nbsp;&nbsp;{@link #verifyObjectPresent verifyObjectPresent}</br>
 * &nbsp;&nbsp;&nbsp;{@link #verifyObjectEnabled verifyObjectEnabled}</br>
 * &nbsp;&nbsp;&nbsp;{@link #verifyObjectNotChecked verifyObjectNotChecked}</br>
 * &nbsp;&nbsp;&nbsp;{@link #inputDataToTestobject name inputDataToTestObject}</br>
 * &nbsp;&nbsp;&nbsp;{@link #printStepMessage printStepMessage}</br>
 * &nbsp;&nbsp;&nbsp;{@link #clickToRadioButton clickToRadioButton}</br>
 * &nbsp;&nbsp;&nbsp;{@link #clickToCheckBox clickToCheckBox}</br>
 * &nbsp;&nbsp;&nbsp;{@link #clickToButton clickToButton}</br>
 * &nbsp;&nbsp;&nbsp;{@link #getTextFromObject getTextFromObject}</br>
 * &nbsp;&nbsp;&nbsp;{@link #getTextFromObjectByInput getTextFromObjectByInput}</br>
 * &nbsp;&nbsp;&nbsp;{@link #selectComboBoxItemByIndex selectComboBoxItemByIndex}</br>
 * &nbsp;&nbsp;&nbsp;{@link #selectComboBoxItemByValue selectComboBoxItemByValue}</br>
 * &nbsp;&nbsp;&nbsp;{@link #selectListViewItem selectListViewItem}</br>
 * &nbsp;&nbsp;&nbsp;{@link #selectMenuItem selectMenuItem}</br>
 * &nbsp;&nbsp;&nbsp;{@link #getTitleDialog}</br>
 * &nbsp;&nbsp;&nbsp;{@link #verifyTitleDialog}</br>
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
	 * @param objPath	: The object name in the Object Repository
	 * @param iTimeOut	: The maximum time for waiting object is displayed on web page
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
	 * @param objPath	: The object name in the Object Repository
	 * @param iTimeOut	: The maximum time for waiting object is displayed on web page
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
			KeywordUtil.markFailed("Object is not existence in Object Repository.")
			return false
		}
	}

	/**
	 * Custom keyword name: verifyObjectNotChecked(TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for verify if the given object is checked on a web page</br></br>
	 *
	 * @param objPath	: The object name in the Object Repository
	 * @param iTimeOut	: The maximum time for waiting object is displayed on web page
	 *
	 * @return
	 *			true if the element is present and clickable; otherwise, false
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */	
	@Keyword
	def static Boolean verifyObjectNotChecked(TestObject objPath, int iTimeOut) {
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
	 * @param objPath	: The object name in the Object Repository
	 * @param sText		: The text string is input in object
	 * @param iTimeOut	: The maximum time for waiting object is displayed on web page
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
	 * @param objPath	: The object name in the Object Repository
	 * @param iTimeOut	: The maximum time for waiting object is displayed on web page
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
					if(verifyObjectNotChecked(objPath, iTimeOut) == true) {
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
	 * @param objPath	: The object name in the Object Repository
	 * @param iTimeOut	: The maximum time for waiting object is displayed on web page
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
					if(verifyObjectNotChecked(objPath, iTimeOut) == true) {
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
	 * @param iTimeOut	: The maximum time for waiting object is displayed on web page
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
			System.out.println(ex)
		}
	}

	/**
	 * Custom keyword name: getTextFromObject(TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for getting a text string from object(button, radio button, label,...) on a web page</br></br>
	 *
	 * @param objPath	: The object name in the Object Repository
	 * @param iTimeOut	: The maximum time for waiting object is displayed on web page
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
	 * @param objPath		: The object name in the Object Repository
	 * @param iTimeOut		: The maximum time for waiting object is displayed on web page
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
	 * @param objPath		: The object name in the Object Repository
	 * @param itemIndex		: The index index range of the options to select from object. Index starts from 0.
	 * @param iTimeOut		: The maximum time for waiting object is displayed on web page
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
	 * @param objPath		: The object name in the Object Repository
	 * @param itemValue		: The value of the options to select from object
	 * @param iTimeOut		: The maximum time for waiting object is displayed on web page
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

	/**
	 * Custom keyword name: selectListViewItem(TestObject objPath, int iTimeout)</br></br>
	 *
	 * General description: Custom keyword is used to click an item from listview on the website</br></br>
	 *
	 * @param objPath		: The object name in the Object Repository
	 * @param iTimeOut		: The maximum time for waiting object is displayed on web page
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void selectListViewItem(TestObject objPath, int iTimeout) {
		try {
			if(verifyObjectPresent(objPath, iTimeout) == true) {
				if(verifyObjectEnabled(objPath) == true) {
					WebUI.click(objPath)
				}
			}
		}catch(Exception ex) {
		}
	}

	/**
	 * Custom keyword name: selectMenuItem(TestObject objPath, int iTimeout)</br></br>
	 *
	 * General description: Custom keyword is used to click an item from Menu on the website</br></br>
	 *
	 * @param objPath		: The object name in the Object Repository
	 * @param iTimeOut		: The maximum time for waiting object is displayed on web page
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void selectMenuItem(TestObject objPath, int iTimeout) {
		try {
			if(verifyObjectPresent(objPath, iTimeout) == true) {
				if(verifyObjectEnabled(objPath) == true) {
					WebUI.click(objPath)
				}
			}
		}catch(Exception ex) {
		}
	}


	/**
	 * Custom keyword name: getTitleFromDialog (TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for getting a title string text from a dialog</br></br>
	 *
	 * @param objPath	: The object name in the Object Repository
	 * @param iTimeOut	: The maximum time for waiting object is displayed on web page
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
	 * @param objPath	: The dialog object name in the Object Repository
	 * @param sExpectedTitle	: The expected string text of dialog title
	 * @param iTimeOut	: The maximum time for waiting object is displayed on web page
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
			// Verify object is existing or not
			if(verifyObjectPresent(objPath, iTimeOut) == true) {
				// Verify object is enabled or not
				if(verifyObjectEnabled(objPath) == true) {
					// Getting the dialog title text
					String sActualTitle = getTitleFromDialog(objPath, iTimeOut)
					// Compare between the actual text from window title and expected text from input
					if (sExpectedTitle.equals(sActualTitle)) {
						KeywordUtil.markPassed("Verify successfully: The window text title is correct as expected with the actual text title is: " +sActualTitle)
						return true
					} else {
						// The actual and expected text is different
						KeywordUtil.markFailed("The window text title is incorrect as expected")
						return false
					}
				}
			}
		} catch(Exception ex) {
			KeywordUtil.markError("Cannot verify the window text title")
			return false
		}
	}

	/**
	 * Custom keyword name: verifyMessageInDialog (TestObject objPathTitle, TestObject objPathMessage, String sExpectedMessage, String sExpectedTitle, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for getting a title string text from a dialog</br></br>
	 *
	 * @param objPathTitle		: The dialog object name in the Object Repository
	 * @param objPathMessage	: The dialog object name in the Object Repository
	 * @param sExpectedTitle	: The expected string text of dialog title
	 * @param sExpectedMessage	: The expected string text of dialog message
	 * @param iTimeOut	: The maximum time for waiting object is displayed on web page
	 *
	 * @return
	 * 			The text in the dialog message is the same or not as expected
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 * 
	 * @Note: If The title of dialog or message is icon, then parameter: sExpectedTitle or sExpectedMessage are passed with value: null.
	 */
	@Keyword
	def static Boolean verifyMessageFromDialog (TestObject objPathDialogTitle, TestObject objPathDialogMessage, String sExpectedTitle, String sExpectedMessage, int iTimeOut) {
		try{
			// Verify dialog exists or not
			if(verifyObjectPresent(objPathDialogMessage, iTimeOut) == true) {
				// Verify title dialog
				if(verifyTitleFromDialog(objPathDialogTitle, sExpectedTitle, iTimeOut) == true) {
					// Verify message object present and get message text
					String sActualMessage = getTextFromObject(objPathDialogMessage, iTimeOut)
					// Compare value between the actual text message and expected text
					if (sExpectedMessage.equals(sActualMessage)) {
						//KeywordUtil.markPassed("Verify successfully: The window text message is correct as expected with the actual text message is: " +sActualMessage)
						return true
					} else {
						// The actual and expected text is different
						KeywordUtil.markFailed("The window text message is incorrect as expected")
						return false
					}
				}
			}
		} catch(Exception ex) {
			KeywordUtil.markError("Cannot verify the window text message")
			return false
		}
	}

	/**
	 * Custom keyword name: verifyMessageInDialog (List <TestObject> lisObjPath, String sExpectedTitle, String sExpectedMessage, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for getting a title string text from a dialog</br></br>
	 *
	 * @param lisObjPath		: The list of object name in the Object Repository
	 * @param sExpectedTitle	: The expected string text of dialog title
	 * @param sExpectedMessage	: The expected string text of dialog message
	 * @param iTimeOut			: The maximum time for waiting object is displayed on web page
	 *
	 * @return
	 * 			The text in the dialog message is the same or not as expected
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 *
	 * @Note: If The title of dialog or message is icon, then parameter: sExpectedTitle or sExpectedMessage are passed with value: null.
	 */
	@Keyword
	def static Boolean verifyMessageFromDialog_2 (List <TestObject> listObjPath, String sExpectedTitle, String sExpectedMessage, int iTimeOut) {
		try{
			// Verify dialog exists or not
			if(verifyObjectPresent(listObjPath[1], iTimeOut) == true) {
				// Verify title dialog
				if(verifyTitleFromDialog(listObjPath[0], sExpectedTitle, iTimeOut) == true) {
					// Verify message object present and get message text
					String sActualMessage = getTextFromObject(listObjPath[1], iTimeOut)
					// Compare value between the actual text message and expected text
					if (sExpectedMessage.equals(sActualMessage)) {
						//KeywordUtil.markPassed("Verify successfully: The window text message is correct as expected with the actual text message is: " +sActualMessage)
						return true
					} else {
						// The actual and expected text is different
						KeywordUtil.markFailed("The window text message is incorrect as expected")
						return false
					}
				}
			}
		} catch(Exception ex) {
			KeywordUtil.markError("Cannot verify the window text message")
			return false
		}
	}
}