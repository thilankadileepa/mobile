package com.dushantha.dto;

public class PhoneContactInfoDTO implements Comparable<PhoneContactInfoDTO> {

	private int phoneContactID;
	private String contactName;
	private String contactNumber;

	public int getPhoneContactID() {
		return phoneContactID;
	}

	public void setPhoneContactID(int phoneContactID) {
		this.phoneContactID = phoneContactID;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public int compareTo(PhoneContactInfoDTO another) {
		// TODO Auto-generated method stub
		return this.getContactName().compareTo(another.getContactName());
	}

}
