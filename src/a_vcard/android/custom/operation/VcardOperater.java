package a_vcard.android.custom.operation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import a_vcard.android.custom.bean.AddressInfo;
import a_vcard.android.custom.bean.ContactsInfo;
import a_vcard.android.custom.bean.EmailInfo;
import a_vcard.android.custom.bean.PhoneInfo;
import a_vcard.android.provider.Contacts;
import a_vcard.android.syncml.pim.VDataBuilder;
import a_vcard.android.syncml.pim.VNode;
import a_vcard.android.syncml.pim.vcard.ContactStruct;
import a_vcard.android.syncml.pim.vcard.ContactStruct.ContactMethod;
import a_vcard.android.syncml.pim.vcard.ContactStruct.OrganizationData;
import a_vcard.android.syncml.pim.vcard.ContactStruct.PhoneData;
import a_vcard.android.syncml.pim.vcard.VCardComposer;
import a_vcard.android.syncml.pim.vcard.VCardException;
import a_vcard.android.syncml.pim.vcard.VCardParser;

/**
 * 生成与解析vcard
 * 
 * @author Administrator
 *
 */
public class VcardOperater {

	/**
	 * 将ContactsInfo对象转为vcard对象
	 * 
	 * @param contactsInfo
	 * @return
	 * @throws VCardException
	 */
	public String parserContactsInfoToString(ContactsInfo contactsInfo)
			throws VCardException {

		String vcardString = null;
		ContactStruct contact = new ContactStruct();
		// 姓名
		contact.name = contactsInfo.name;
		// 头像图片二进制
		contact.photoBytes = contactsInfo.photoBytes;
		// 头像图片类型 JPEG, BMP, etc.)
		contact.photoType = contactsInfo.photoType;
		// 公司,部门
		contact.company = contactsInfo.company;
		// 职位
		contact.title = contactsInfo.position;
		// QQ
		contact.qq = contactsInfo.qq;
		// MSN
		contact.msn = contactsInfo.msn;
		// 备注
		contact.notes = contactsInfo.notes;
		// 网址
		contact.websiteList = contactsInfo.websiteList;

		// 添加电话
		List<PhoneInfo> phoneList = contactsInfo.getPhoneList();
		for (PhoneInfo phoneInfo : phoneList) {
			contact.addPhone(phoneInfo.getType(), phoneInfo.getNumber(), null,
					false);
		}

		// 添加电子邮件
		List<EmailInfo> emailList = contactsInfo.getEmailList();
		for (EmailInfo emailInfo : emailList) {
			contact.addContactmethod(Contacts.KIND_EMAIL, emailInfo.getType(),
					emailInfo.getEmail(), null, false);
		}

		// 添加地址
		List<AddressInfo> addressList = contactsInfo.getAddressList();
		for (AddressInfo addressInfo : addressList) {
			contact.addContactmethod(Contacts.KIND_ADR, addressInfo.getType(),
					addressInfo.getAddress(), null, true);
		}

		VCardComposer composer = new VCardComposer();
		vcardString = composer.createVCard(contact,
				VCardComposer.VERSION_VCARD30_INT);
		return vcardString;
	}

	/**
	 * 将String类型的vcardString转为ContactsInfo的集合
	 * 
	 * @param vcardString
	 * @return List<ContactsInfo>
	 * @throws VCardException
	 * @throws IOException
	 */
	public List<ContactsInfo> parserStringToContactsInfo(String vcardString)
			throws VCardException, IOException {

		List<ContactsInfo> contactInfoList = new ArrayList<ContactsInfo>();
		VCardParser parse = new VCardParser();
		VDataBuilder builder = new VDataBuilder();

		parse.parse(vcardString, "UTF-8", builder);
		List<VNode> pimContacts = builder.vNodeList;

		for (VNode contact : pimContacts) {
			ContactStruct contactStruct = ContactStruct
					.constructContactFromVNode(contact, 1);

			ContactsInfo contactsInfo = new ContactsInfo();
			// 姓名
			contactsInfo.name = contactStruct.name;

			Map map = contactStruct.extensionMap;

			// 网址
			List<String> webs = (List<String>) map.get("URL");
			contactsInfo.websiteList = webs;
			
			//qq
			List<String> qqs=(List<String>) map.get("X-QQ");
			if (qqs!=null&&qqs.size()>0) {
				contactsInfo.qq=qqs.get(0);
			}
			
			//MSN
			List<String> msns=(List<String>) map.get("X-MSN");
			if (msns!=null&&msns.size()>0) {
				contactsInfo.msn=msns.get(0);
			}
			

			// 获取公司信息
			List<OrganizationData> organizationDataList = contactStruct.organizationList;
			for (OrganizationData organizationData : organizationDataList) {
				contactsInfo.company = organizationData.companyName;
				contactsInfo.position = organizationData.positionName;
			}

			// 联系人电话信息
			List<PhoneData> phoneDataList = contactStruct.phoneList;
			contactsInfo.phoneList = new ArrayList<PhoneInfo>();
			if (null != phoneDataList) {
				for (PhoneData phoneData : phoneDataList) {
					PhoneInfo phoneInfo = new PhoneInfo();
					phoneInfo.number = phoneData.data;
					phoneInfo.type = phoneData.type;
					contactsInfo.phoneList.add(phoneInfo);
				}
			}

			// 联系人邮箱信息
			List<ContactMethod> emailList = contactStruct.contactmethodList;
			contactsInfo.emailList = new ArrayList<EmailInfo>();
			// 存在 Email 信息
			if (null != emailList) {
				for (ContactMethod contactMethod : emailList) {
					if (Contacts.KIND_EMAIL == contactMethod.kind) {
						EmailInfo emailInfo = new EmailInfo();
						emailInfo.email = contactMethod.data;
						emailInfo.type = contactMethod.type;
						contactsInfo.emailList.add(emailInfo);
					}
				}
			}

			// 获取地址信息
			List<ContactMethod> addressList = contactStruct.contactmethodList;
			contactsInfo.addressList = new ArrayList<AddressInfo>();
			// 存在 Address 信息
			if (null != addressList) {
				for (ContactMethod contactMethod : emailList) {
					if (Contacts.KIND_ADR == contactMethod.kind) {
						AddressInfo addressInfo = new AddressInfo();
						addressInfo.address = contactMethod.data;
						addressInfo.type = contactMethod.type;
						contactsInfo.addressList.add(addressInfo);
					}
				}
			}
			
			//备注信息
			contactsInfo.notes=contactStruct.notes;

			contactInfoList.add(contactsInfo);
		}
		return contactInfoList;
	}

}
