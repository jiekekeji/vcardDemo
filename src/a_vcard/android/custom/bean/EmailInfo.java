package a_vcard.android.custom.bean;

public class EmailInfo {

	// ��������
	public int type;
	// ����
	public String email;

	public EmailInfo(int type, String email) {
		this.type = type;
		this.email = email;
	}

	public EmailInfo() {

	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
