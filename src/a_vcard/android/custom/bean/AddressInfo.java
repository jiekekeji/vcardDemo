package a_vcard.android.custom.bean;

/**
 * 地址信息
 * 
 * @author Administrator
 *
 */
public class AddressInfo {
	// 联系电话类型,家庭电话还是公司电话等等
	public int type;
	// 联系电话
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
