package a_vcard.android.custom.bean;

/**
 * ��ַ��Ϣ
 * 
 * @author Administrator
 *
 */
public class AddressInfo {
	// ��ϵ�绰����,��ͥ�绰���ǹ�˾�绰�ȵ�
	public int type;
	// ��ϵ�绰
	public String address;
	
	public AddressInfo() {
		
	}
	
	public AddressInfo(int type,String address) {
		this.type=type;
		this.address=address;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
