package com.library.validatations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.library.businessModels.IUserBasicInfo;
import com.library.businessModels.IUserExtendedInfo;
import com.library.xmlParser.XmlParser;

//Template pattern implemented in this class and in its child class. I have implemented setValidationRules() and setErrorStringRules() that are being used by the child class many times.
// Abstract functions are also added in this class and that are used by child class, where it changes the flow of action as required.
// Hence, template patter.
public abstract class ValidateUserFormsAbstract {
	// Roots in the .xml file are declared below.
	private static final String passwordLengthKeyRoot = "passwordlength";
	private static final String passwordUpperKeyRoot = "passwordUpperCase";
	private static final String passwordLowerKeyRoot = "passwordLowerCase";
	private static final String passwordSymbolsKeyRoot = "passwordSymbols";
	private static final String emailRegexKeyRoot = "emailRegex";
	private static final String phoneCheckKeyRoot = "phoneCheck";
	private static final String adminIDCheckKeyRoot = "adminId";
	private static final String adminPasswordCheckKeyRoot = "adminPwd";
	private static final String emailErrorKeyRoot = "emailErrorString";
	private static final String passwordErrorKeyRoot = "passwordErrorString";
	private static final String emptyErrorKeyRoot = "emptyErrorString";
	private static final String phoneErrorKeyRoot = "phoneErrorString";
	private static final String cpasswordErrorKeyRoot = "cpasswordErrorString";
	private static final String saltKeyRoot = "salt";
	protected String passwordErrorStatement;
	protected String emailErrorStatement = "";
	protected String blankErrorStatement;
	protected String phoneErrorStatement;
	protected String cpasswordErrorStatement;
	public static String isAdminPwd;
	public static String isAdmin;
	public static String saltValue;
	protected List<Object> passwordListAttributes = new ArrayList<Object>();
	protected List<Object> emailPhoneListAttributes = new ArrayList<Object>();
	protected List<String> errorStringToDisplay = new ArrayList<String>();
	private static final String filePathToValidations = "AuthenticationRules.xml";
	private static final String filePathToErrorStatements = "ValidationStatements.xml";

	public abstract ArrayList<Map.Entry<String, String>> signUpUserData(IUserBasicInfo userBasicInfo,
			IUserExtendedInfo userExtendedInfo) throws Exception;

	public abstract ArrayList<Map.Entry<String, String>> signInUserData(IUserBasicInfo userBasicInfo) throws Exception;

	public void setValidationRules() throws Exception {
		List<Map.Entry<String, String>> list = XmlParser.parse(filePathToValidations);
		for (int i = 0; i < list.size(); i++) {
			String getKeyFromList = list.get(i).getKey();
			String getValueFromList = list.get(i).getValue();
			switch (getKeyFromList) {
			case passwordLengthKeyRoot:
				PasswordLengthValidation pwdLength = new PasswordLengthValidation();
				pwdLength.setPasswordLength(getValueFromList);
				passwordListAttributes.add(pwdLength);
				break;
			case passwordUpperKeyRoot:
				PasswordUppercaseValidation pwdUpper = new PasswordUppercaseValidation();
				pwdUpper.setPasswordUpper(getValueFromList);
				passwordListAttributes.add(pwdUpper);
				break;
			case passwordLowerKeyRoot:
				PasswordLowercaseValidation pwdLower = new PasswordLowercaseValidation();
				pwdLower.setPasswordLowerCase(getValueFromList);
				passwordListAttributes.add(pwdLower);
				break;
			case passwordSymbolsKeyRoot:
				PasswordSymbolValidation pwdSymbols = new PasswordSymbolValidation();
				pwdSymbols.setPasswordSymbols(getValueFromList);
				passwordListAttributes.add(pwdSymbols);
				break;
			case emailRegexKeyRoot:
				EmailStrength emailStrength = new EmailStrength();
				emailStrength.setEmailStrength(getValueFromList);
				emailPhoneListAttributes.add(emailStrength);
				break;
			case phoneCheckKeyRoot:
				PhoneStrength phoneStrength = new PhoneStrength();
				phoneStrength.setPhoneStrength(getValueFromList);
				emailPhoneListAttributes.add(phoneStrength);
				break;
			case adminIDCheckKeyRoot:
				isAdmin = getValueFromList;
				break;
			case adminPasswordCheckKeyRoot:
				isAdminPwd = getValueFromList;
				break;
			case saltKeyRoot:
				saltValue = getValueFromList;
				break;
			default:
				break;
			}
		}
	}

	public void setErrorStringToHTML() throws Exception {
		this.emailErrorStatement = "";
		this.blankErrorStatement = "";
		this.passwordErrorStatement = "";
		this.phoneErrorStatement = "";
		this.cpasswordErrorStatement = "";
		List<Map.Entry<String, String>> list = XmlParser.parse(filePathToErrorStatements);
		for (int i = 0; i < list.size(); i++) {
			switch (list.get(i).getKey()) {
			case emailErrorKeyRoot:
				this.emailErrorStatement = list.get(i).getValue();
				break;
			case emptyErrorKeyRoot:
				this.blankErrorStatement = list.get(i).getValue();
				break;
			case passwordErrorKeyRoot:
				this.passwordErrorStatement = list.get(i).getValue();
				break;
			case phoneErrorKeyRoot:
				this.phoneErrorStatement = list.get(i).getValue();
				break;
			case cpasswordErrorKeyRoot:
				this.cpasswordErrorStatement = list.get(i).getValue();
				break;
			default:
				break;
			}
		}
	}
}
