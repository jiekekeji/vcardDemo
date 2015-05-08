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
 * ���������vcard
 * 
 * @author Administrator
 *
 */
public class VcardOperater {

	/**
	 * ��ContactsInfo����תΪvcard����
	 * 
	 * @param contactsInfo
	 * @return
	 * @throws VCardException
	 */
	public String parserContactsInfoToString(ContactsInfo contactsInfo)
			throws VCardException {

		String vcardString = null;
		ContactStruct contact = new ContactStruct();
		// ����
		contact.name = contactsInfo.name;
		// ͷ��ͼƬ������
		contact.photoBytes = contactsInfo.photoBytes;
		// ͷ��ͼƬ���� JPEG, BMP, etc.)
		contact.photoType = contactsInfo.photoType;
		// ��˾,����
		contact.company = contactsInfo.company;
		// ְλ
		contact.title = contactsInfo.position;
		// QQ
		contact.qq = contactsInfo.qq;
		// MSN
		contact.msn = contactsInfo.msn;
		// ��ע
		contact.notes = contactsInfo.notes;
		// ��ַ
		contact.websiteList = contactsInfo.websiteList;

		// ��ӵ绰
		List<PhoneInfo> phoneList = contactsInfo.getPhoneList();
		for (PhoneInfo phoneInfo : phoneList) {
			contact.addPhone(phoneInfo.getType(), phoneInfo.getNumber(), null,
					false);
		}

		// ��ӵ����ʼ�
		List<EmailInfo> emailList = contactsInfo.getEmailList();
		for (EmailInfo emailInfo : emailList) {
			contact.addContactmethod(Contacts.KIND_EMAIL, emailInfo.getType(),
					emailInfo.getEmail(), null, false);
		}

		// ��ӵ�ַ
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
	 * ��String���͵�vcardStringתΪContactsInfo�ļ���
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
			// ����
			contactsInfo.name = contactStruct.name;

			Map map = contactStruct.extensionMap;

			// ��ַ
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
			

			// ��ȡ��˾��Ϣ
			List<OrganizationData> organizationDataList = contactStruct.organizationList;
			for (OrganizationData organizationData : organizationDataList) {
				contactsInfo.company = organizationData.companyName;
				contactsInfo.position = organizationData.positionName;
			}

			// ��ϵ�˵绰��Ϣ
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

			// ��ϵ��������Ϣ
			List<ContactMethod> emailList = contactStruct.contactmethodList;
			contactsInfo.emailList = new ArrayList<EmailInfo>();
			// ���� Email ��Ϣ
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

			// ��ȡ��ַ��Ϣ
			List<ContactMethod> addressList = contactStruct.contactmethodList;
			contactsInfo.addressList = new ArrayList<AddressInfo>();
			// ���� Address ��Ϣ
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
			
			//��ע��Ϣ
			contactsInfo.notes=contactStruct.notes;

			contactInfoList.add(contactsInfo);
		}
		return contactInfoList;
	}

}
