package mobileCustomKeywords



import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import io.appium.java_client.AppiumDriver
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory


/**
 * General description: List out some common keywords used for mobile application</br>
 * 
 * Keyword list:</br>
 * &nbsp;&nbsp;&nbsp;{@link #verifyObjectPresent verifyObjectPresent}</br>
 * &nbsp;&nbsp;&nbsp;{@link #verifyObjectEnabled verifyObjectEnabled}</br>
 * &nbsp;&nbsp;&nbsp;{@link #verifyObjectChecked verifyObjectChecked}</br>
 * &nbsp;&nbsp;&nbsp;{@link #inputDataToTestobject name inputDataToTestObject}</br>
 * &nbsp;&nbsp;&nbsp;{@link #printStepMessage printStepMessage}</br>
 * &nbsp;&nbsp;&nbsp;{@link #clickToRadioButton clickToRadioButton}</br>
 * &nbsp;&nbsp;&nbsp;{@link #clickToCheckBox clickToCheckBox}</br>
 * &nbsp;&nbsp;&nbsp;{@link #clickToButton clickToButton}</br>
 * &nbsp;&nbsp;&nbsp;{@link #getTextFromTestObject getTextFromTestObject}</br>
 * &nbsp;&nbsp;&nbsp;{@link #getTextFromInput getTextFromInput}</br>
 * &nbsp;&nbsp;&nbsp;{@link #selectListItemByIndex selectListItemByIndex}</br>
 * &nbsp;&nbsp;&nbsp;{@link #selectListItemByLabel selectListItemByLabel}</br>
 * &nbsp;&nbsp;&nbsp;{@link #selectListViewItem selectListViewItem}</br>
 * &nbsp;&nbsp;&nbsp;{@link #selectMenuItem selectMenuItem}</br>
 * &nbsp;&nbsp;&nbsp;{@link #swipeFromTopToBottom swipeFromTopToBottom}</br>
 * &nbsp;&nbsp;&nbsp;{@link #swipeFromBottomToTop swipeFromBottomToTop}</br>
 * &nbsp;&nbsp;&nbsp;{@link #swipeFromRightToLeft swipeFromRightToLeft}</br>
 * &nbsp;&nbsp;&nbsp;{@link #swipeFromLeftToRight swipeFromLeftToRight}</br>
 * &nbsp;&nbsp;&nbsp;{@link #closeTestApp closeTestApp}</br>
 * &nbsp;&nbsp;&nbsp;{@link #getTitleDialog}</br>
 * &nbsp;&nbsp;&nbsp;{@link #verifyTitleDialog}</br>
 * 
 * @since 22/02/2023 
 * 
 * @author ABA Automation team
 */
public class Common {
	/**
	 * Custom keyword name: verifyObjectPresent(TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for verify object existence on a mobile application
	 *
	 * @param objPath	: The object name in the Object Repository
	 * @param iTimeOut	: The maximum time for waiting object is displayed on mobile application
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
			if(Mobile.verifyElementExist(objPath, iTimeOut, FailureHandling.CONTINUE_ON_FAILURE) == true) {
				//System.out.println("The object is existence.")
				return true
			} else {
				KeywordUtil.markFailed("Object '" + objPath.objectId.split('/').last() + "' is not existence.")
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
	 * General description: Custom keyword is used for verify object enabled on a mobile application
	 *
	 * @param objPath	: The object name in the Object Repository
	 * @param iTimeOut	: The maximum time for waiting object is displayed on mobile application
	 *
	 * @return
	 *			true if the element is present and clickable; otherwise, false
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static Boolean verifyObjectEnabled(TestObject objPath, int iTimeOut) {
		try{
			//Verify that the object is enabled or not
			if(Mobile.verifyElementAttributeValue(objPath, "enabled", "true", iTimeOut)) {
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
	 * General description: Custom keyword is used for verify if the given object is not checked on a mobile application
	 *
	 * @param objPath	: The object name in the Object Repository
	 * @param iTimeOut	: The maximum time for waiting object is displayed on mobile application
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
			if(Mobile.verifyElementNotChecked(objPath, iTimeOut, FailureHandling.CONTINUE_ON_FAILURE) == true) {
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
	 * General description: Custom keyword is used for inputting a text string to object on a mobile application
	 *
	 * @param objPath	: The object name in the Object Repository
	 * @param sText		: The text string is input in object
	 * @param iTimeOut	: The maximum time for waiting object is displayed on mobile application
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
				if(verifyObjectEnabled(objPath, iTimeOut) == true) {
					Mobile.sendKeys(objPath, sText)
					if(getTextFromObject(objPath, iTimeOut).equals(sText)) {
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
	 * General description: Custom keyword is used for print step message to console
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
	 * General description: Custom keyword is used for clicked a radio button on a mobile application
	 *
	 * @param objPath	: The object name in the Object Repository
	 * @param iTimeOut	: The maximum time for waiting object is displayed on mobile application
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
				if(verifyObjectEnabled(objPath, iTimeOut) == true) {
					//Verify that the object is checked or not
					if(verifyObjectNotChecked(objPath, iTimeOut) == true) {
						Mobile.tap(objPath, iTimeOut)
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
	 * General description: Custom keyword is used for clicked a check box on a mobile application
	 *
	 * @param objPath	: The object name in the Object Repository
	 * @param iTimeOut	: The maximum time for waiting object is displayed on mobile application
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
				if(verifyObjectEnabled(objPath, iTimeOut) == true) {
					//Verify that the object is checked or not
					if(verifyObjectNotChecked(objPath, iTimeOut) == true) {
						Mobile.tap(objPath, iTimeOut)
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
	 * General description: Custom keyword is used for clicked a button on a mobile application
	 *
	 * @param objPath	: The represent a web element in the Object Repository
	 * @param iTimeOut	: The maximum time for waiting object is displayed on mobile application
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
				if(verifyObjectEnabled(objPath, iTimeOut) == true) {
					Mobile.tap(objPath, iTimeOut)
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
	 * General description: Custom keyword is used for getting a text string from object(button, radio button, label,...) on a mobile application
	 *
	 * @param objPath	: The object name in the Object Repository
	 * @param iTimeOut	: The maximum time for waiting object is displayed on mobile application
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
				if(verifyObjectEnabled(objPath, iTimeOut) == true) {
					return Mobile.getText(objPath, iTimeOut)
				}
			}
		} catch(Exception ex) {

		}
	}

	/**
	 * Custom keyword name: getTextFromObjectByInput(TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for getting a text string from object(with html tag name input) on a mobile application
	 *
	 * @param objPath		: The object name in the Object Repository
	 * @param iTimeOut		: The maximum time for waiting object is displayed on mobile application
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
				if(verifyObjectEnabled(objPath, iTimeOut) == true) {
					return Mobile.getAttribute(objPath, "value", iTimeOut)
				}
			}
		} catch(Exception ex) {

		}
	}


	/**
	 * Custom keyword name: selectListItemByIndex(TestObject objPath, int itemIndex, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for select item on list view control that have a index matching the "index" argument on a mobile application
	 *
	 * @param objPath		: The object name in the Object Repository
	 * @param itemIndex		: The index index range of the options to select from object. Index starts from 0.
	 * @param iTimeOut		: The maximum time for waiting object is displayed on mobile application
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void selectListItemByIndex(TestObject objPath, int sItemIndex, int iTimeOut) {
		try{
			//Verify that the object exists or not
			if(verifyObjectPresent(objPath, iTimeOut) == true) {
				if (verifyObjectEnabled(objPath, iTimeOut) == true) {
					if(sItemIndex >= 0) {
						Mobile.selectListItemByIndex(objPath, sItemIndex, iTimeOut)
					} else {
						KeywordUtil.markFailed("The item index typed is lower than 0")
					}
				}
			}
		} catch(Exception ex) {

		}
	}

	/**
	 * Custom keyword name: selectListItemByLabel(TestObject objPath, String itemValue, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for select item on list view control that have a value matching the "value" argument on a mobile application
	 *
	 * @param objPath		: The object name in the Object Repository
	 * @param itemValue		: The value of the options to select from object
	 * @param iTimeOut		: The maximum time for waiting object is displayed on mobile application
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void selectListItemByLabel(TestObject objPath, String sItemValue, int iTimeOut) {
		try{
			//Verify that the object exists or not
			if(verifyObjectPresent(objPath, iTimeOut) == true) {
				if (verifyObjectEnabled(objPath, iTimeOut) == true) {
					if(!sItemValue.equals("")) {
						Mobile.selectListItemByLabel(objPath, sItemValue, iTimeOut)
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
	 * General description: Custom keyword is used to click an item from listview on a mobile application
	 *
	 * @param objPath		: The object name in the Object Repository
	 * @param iTimeOut		: The maximum time for waiting object is displayed on mobile application
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
				if(verifyObjectEnabled(objPath, iTimeout) == true) {
					Mobile.tap(objPath, iTimeout)
				}
			}
		}catch(Exception ex) {

		}
	}

	/**
	 * Custom keyword name: selectMenuItem(TestObject objPath, int iTimeout)</br></br>
	 *
	 * General description: Custom keyword is used to click an item from Menu on a mobile application
	 *
	 * @param objPath		: The object name in the Object Repository
	 * @param iTimeOut		: The maximum time for waiting object is displayed on mobile application
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
				if(verifyObjectEnabled(objPath, iTimeout) == true) {
					Mobile.tap(objPath, iTimeout)
				}
			}
		}catch(Exception ex) {

		}
	}

	/**
	 * Custom keyword name: swipeFromTopToBottom(int iFrom, int iTo)</br></br>
	 *
	 * General description: Custom keyword is used to swipe Vertical from top to bottom on a mobile application
	 *
	 * @param iFrom		: Position ready to swipe 
	 * @param iTo		: Position to end swipe
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void swipeFromTopToBottom(int iFrom, int iTo) {
		try {
			Mobile.swipe(0, iTo, 0, iFrom)
		}catch(Exception ex) {

		}
	}

	/**
	 * Custom keyword name: swipeFromBottomToTop(int iFrom, int iTo)</br></br>
	 *
	 * General description: Custom keyword is used to swipe Vertical from bottom to top on a mobile application
	 *
	 * @param iFrom		: Position ready to swipe 
	 * @param iTo		: Position to end swipe
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void swipeFromBottomToTop(int iFrom, int iTo) {
		try {
			Mobile.swipe(0, iFrom, 0, iTo)
		}catch(Exception ex) {

		}
	}

	/**
	 * Custom keyword name: swipeFromRightToLeft(int iFrom, int iTo)</br></br>
	 *
	 * General description: Custom keyword is used to swipe Horizontal from right to left on a mobile application
	 *
	 * @param iFrom		: Position ready to swipe
	 * @param iTo		: Position to end swipe
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void swipeFromRightToLeft(int iFrom, int iTo) {
		try {
			Mobile.swipe(iFrom, 0, iTo, 0)
		}catch(Exception ex) {

		}
	}

	/**
	 * Custom keyword name: swipeFromLeftToRight(int iFrom, int iTo)</br></br>
	 *
	 * General description: Custom keyword is used to swipe Horizontal from left to right on a mobile application
	 *
	 * @param iFrom		: Position ready to swipe
	 * @param iTo		: Position to end swipe
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void swipeFromLeftToRight(int iFrom, int iTo) {
		try {
			Mobile.swipe(iTo, 0, iFrom, 0)
		}catch(Exception ex) {

		}
	}

	/**
	 * Custom keyword name: closeTestApp(String appId)</br></br>
	 *
	 * General description: Custom keyword is used to close application
	 *
	 * @param appId		: The application ID
	 *
	 * @return
	 * 			none
	 *
	 * @since 05/02/2023
	 *
	 * @author ABA Automation team
	 */
	@Keyword
	def static void closeTestApp(String sAppId) {
		AppiumDriver<?> driver = MobileDriverFactory.getDriver()
		driver.terminateApp(sAppId)
	}

	/**
	 * Custom keyword name: getTitleFromDialog (TestObject objPath, int iTimeOut)</br></br>
	 *
	 * General description: Custom keyword is used for getting a title string text from a dialog</br></br>
	 *
	 * @param objPath	: The object name in the Object Repository
	 * @param iTimeOut	: The maximum time for waiting object is displayed on on mobile application
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
				if(verifyObjectEnabled(objPath, iTimeOut) == true) {
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
	 * @param iTimeOut	: The maximum time for waiting object is displayed on on mobile application
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
				if(verifyObjectEnabled(objPath, iTimeOut) == true) {
					// Getting the dialog title text
					String sActualTitle = getTitleFromDialog(objPath, 5)
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
}