package a_vcard.android.custom.bean;

import java.util.Arrays;
import java.util.List;

/**
 * 联系人信息
 * 
 * @author Administrator
 */
public class ContactsInfo {

	// 姓名
	public String name;
	// 头像
	public byte[] photoBytes;
	// 头像图片类型 JPEG, BMP, etc.)
	public String photoType;
	// 公司,部门
	public String company;
	// 职位
	public String position;
	// QQ
	public String qq;
	// MSN
	public String msn;
	// 备注
	public List<String> notes;
	// 网址
	public List<String> websiteList;
	// 联系号码，一个联系人可能有多个联系电话。包括：家庭固话，工作固话，家庭手机，工作手机，家庭传真，工作传真
	public List<PhoneInfo> phoneList;
	// Email 一个联系人可能有多个邮箱 。包括：家庭邮箱，工作邮箱，家庭邮箱，工作邮箱，家庭邮箱，工作邮箱
	public List<EmailInfo> emailList;
	// 地址信息，一个人可能有多个地址。包括：家庭地址，工作地址
	public List<AddressInfo> addressList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getPhotoBytes() {
		return photoBytes;
	}

	public void setPhotoBytes(byte[] photoBytes) {
		this.photoBytes = photoBytes;
	}

	public String getPhotoType() {
		return photoType;
	}

	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public List<String> getNotes() {
		return notes;
	}

	public void setNotes(List<String> notes) {
		this.notes = notes;
	}

	public List<String> getWebsiteList() {
		return websiteList;
	}

	public void setWebsiteList(List<String> websiteList) {
		this.websiteList = websiteList;
	}

	public List<PhoneInfo> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<PhoneInfo> phoneList) {
		this.phoneList = phoneList;
	}

	public List<EmailInfo> getEmailList() {
		return emailList;
	}

	public void setEmailList(List<EmailInfo> emailList) {
		this.emailList = emailList;
	}

	public List<AddressInfo> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AddressInfo> addressList) {
		this.addressList = addressList;
	}

	@Override
	public String toString() {
		return "ContactsInfo [name=" + name + ", photoBytes="
				+ Arrays.toString(photoBytes) + ", photoType=" + photoType
				+ ", company=" + company + ", position=" + position + ", qq="
				+ qq + ", msn=" + msn + ", notes=" + notes + ", websiteList="
				+ websiteList + ", phoneList=" + phoneList + ", emailList="
				+ emailList + ", addressList=" + addressList + "]";
	}

}
