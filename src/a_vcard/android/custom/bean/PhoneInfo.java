package a_vcard.android.custom.bean;
/**
 * �绰��Ϣ
 * @author Administrator
 *
 */
public class PhoneInfo {

	// ��ϵ�绰����,��ͥ�绰���ǹ�˾�绰�ȵ�
	public int type;
	// ��ϵ�绰
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
