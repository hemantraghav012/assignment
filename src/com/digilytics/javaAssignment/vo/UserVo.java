/**
 * 
 */
package com.digilytics.javaAssignment.vo;

import com.opencsv.bean.CsvBindByName;

/**
 * @author hemant
 *
 */

public class UserVo {

	@CsvBindByName
	private String emailId;
	@CsvBindByName
	private String userName;
	@CsvBindByName
	private String[] roleName;
	@CsvBindByName
	private String password;
	@CsvBindByName
	private String message;
	private Integer successCount;
	private Integer faildCount;

	/**
	 * 
	 */
	public UserVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param emailId
	 * @param userName
	 * @param password
	 * @param roleName
	 * @param message
	 * @param successCount
	 * @param faildCount
	 */
	public UserVo(String emailId, String userName, String password, String[] roleName, String message) {
		this.emailId = emailId;
		this.userName = userName;
		this.roleName = roleName;
		this.password = password;
		this.message = message;

	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String[] getRoleName() {
		return roleName;
	}

	public void setRoleName(String[] roleName) {
		this.roleName = roleName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}

	public Integer getFaildCount() {
		return faildCount;
	}

	public void setFaildCount(Integer faildCount) {
		this.faildCount = faildCount;
	}

}
