package com.example.vcarddemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import a_vcard.android.custom.bean.AddressInfo;
import a_vcard.android.custom.bean.ContactsInfo;
import a_vcard.android.custom.bean.EmailInfo;
import a_vcard.android.custom.bean.PhoneInfo;
import a_vcard.android.custom.operation.VcardOperater;
import a_vcard.android.syncml.pim.vcard.VCardException;
import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;

public class MainActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ContactsInfo contactsInfo = new ContactsInfo();
		contactsInfo.setName("С��");
		contactsInfo.setCompany("��˾");
		contactsInfo.setPosition("ְλ");
		contactsInfo.qq = "8110220120";
		contactsInfo.msn = "52510684613";
   
		// �����绰
		String phoneNumber1 = "18898611864";
		PhoneInfo phoneInfo1 = new PhoneInfo(Phone.TYPE_WORK, phoneNumber1);
		// �ֻ���
		String phoneNumber2 = "13426604038";
		PhoneInfo phoneInfo2 = new PhoneInfo(Phone.TYPE_MOBILE, phoneNumber2);
		// ��������
		String phoneNumber3 = "13426604038";
		PhoneInfo phoneInfo3 = new PhoneInfo(Phone.TYPE_FAX_WORK, phoneNumber2);

		List<PhoneInfo> phoneList = new ArrayList<PhoneInfo>();
		phoneList.add(phoneInfo1);
		phoneList.add(phoneInfo2);
		phoneList.add(phoneInfo3);
		contactsInfo.setPhoneList(phoneList);

		// ����1
		String emailNumber1 = "jack@163.com";
		EmailInfo emailInfo1 = new EmailInfo(Email.TYPE_HOME, emailNumber1);
		String emailNumber2 = "goolge@gmail.com";
		EmailInfo emailInfo2 = new EmailInfo(Email.TYPE_WORK, emailNumber2);
		// ����2
		List<EmailInfo> emailList = new ArrayList<EmailInfo>();
		emailList.add(emailInfo1);
		emailList.add(emailInfo2);
		contactsInfo.setEmailList(emailList);

		// ��ַ1
		String address1 = "�����г�����";
		AddressInfo addressInfo1 = new AddressInfo(
				ContactsContract.CommonDataKinds.StructuredPostal.TYPE_HOME,
				address1);
		// ��ַ2
		String address2 = "������";
		AddressInfo addressInfo2 = new AddressInfo(
				ContactsContract.CommonDataKinds.StructuredPostal.TYPE_WORK,
				address2);

		List<AddressInfo> addressList = new ArrayList<AddressInfo>();
		addressList.add(addressInfo1);
		addressList.add(addressInfo2);
		contactsInfo.setAddressList(addressList);

		// ��ַ
		String web1 = "www.baidu.com";
		String web2 = "www.google.com";
		List<String> webList = new ArrayList<String>();
		webList.add(web1);
		webList.add(web2);
		contactsInfo.setWebsiteList(webList);

		// ��ע��Ϣ
		String note1 = "��ע1";
		String note2 = "��ע2";
		List<String> noteList = new ArrayList<String>();
		noteList.add(note1);
		noteList.add(note2);
		contactsInfo.setNotes(noteList);

		VcardOperater op = new VcardOperater();
		String vcardString = null;
		try {
			vcardString = op.parserContactsInfoToString(contactsInfo);
			Log.i("tag", vcardString);
		} catch (VCardException e) {
			e.printStackTrace();
		}
		
		try {
			List<ContactsInfo> infos=op.parserStringToContactsInfo(vcardString);
			Log.i("xxx", infos.get(0).getCompany());
		} catch (VCardException | IOException e) {
			e.printStackTrace();
		}

	}

}
