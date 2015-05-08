package a_vcard.android.custom.bean;
/**
 * 电话信息
 * @author Administrator
 *
 */
public class PhoneInfo {

	// 联系电话类型,家庭电话还是公司电话等等
	public int type;
	// 联系电话
	public String number;

	public PhoneInfo(int type, String number) {
		this.type = type;
		this.number = number;
	}

	public PhoneInfo() {
		
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
