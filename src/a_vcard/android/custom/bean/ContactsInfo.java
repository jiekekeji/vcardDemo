package a_vcard.android.custom.bean;

import java.util.Arrays;
import java.util.List;

/**
 * ��ϵ����Ϣ
 * 
 * @author Administrator
 */
public class ContactsInfo {

	// ����
	public String name;
	// ͷ��
	public byte[] photoBytes;
	// ͷ��ͼƬ���� JPEG, BMP, etc.)
	public String photoType;
	// ��˾,����
	public String company;
	// ְλ
	public String position;
	// QQ
	public String qq;
	// MSN
	public String msn;
	// ��ע
	public List<String> notes;
	// ��ַ
	public List<String> websiteList;
	// ��ϵ���룬һ����ϵ�˿����ж����ϵ�绰����������ͥ�̻��������̻�����ͥ�ֻ��������ֻ�����ͥ���棬��������
	public List<PhoneInfo> phoneList;
	// Email һ����ϵ�˿����ж������ ����������ͥ���䣬�������䣬��ͥ���䣬�������䣬��ͥ���䣬��������
	public List<EmailInfo> emailList;
	// ��ַ��Ϣ��һ���˿����ж����ַ����������ͥ��ַ��������ַ
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
