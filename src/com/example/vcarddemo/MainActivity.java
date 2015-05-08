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
		contactsInfo.setName("小丽");
		contactsInfo.setCompany("公司");
		contactsInfo.setPosition("职位");
		contactsInfo.qq = "8110220120";
		contactsInfo.msn = "52510684613";
   
		// 工作电话
		String phoneNumber1 = "18898611864";
		PhoneInfo phoneInfo1 = new PhoneInfo(Phone.TYPE_WORK, phoneNumber1);
		// 手机号
		String phoneNumber2 = "13426604038";
		PhoneInfo phoneInfo2 = new PhoneInfo(Phone.TYPE_MOBILE, phoneNumber2);
		// 工作传真
		String phoneNumber3 = "13426604038";
		PhoneInfo phoneInfo3 = new PhoneInfo(Phone.TYPE_FAX_WORK, phoneNumber2);

		List<PhoneInfo> phoneList = new ArrayList<PhoneInfo>();
		phoneList.add(phoneInfo1);
		phoneList.add(phoneInfo2);
		phoneList.add(phoneInfo3);
		contactsInfo.setPhoneList(phoneList);

		// 邮箱1
		String emailNumber1 = "jack@163.com";
		EmailInfo emailInfo1 = new EmailInfo(Email.TYPE_HOME, emailNumber1);
		String emailNumber2 = "goolge@gmail.com";
		EmailInfo emailInfo2 = new EmailInfo(Email.TYPE_WORK, emailNumber2);
		// 邮箱2
		List<EmailInfo> emailList = new ArrayList<EmailInfo>();
		emailList.add(emailInfo1);
		emailList.add(emailInfo2);
		contactsInfo.setEmailList(emailList);

		// 地址1
		String address1 = "北京市朝阳区";
		AddressInfo addressInfo1 = new AddressInfo(
				ContactsContract.CommonDataKinds.StructuredPostal.TYPE_HOME,
				address1);
		// 地址2
		String address2 = "深圳市";
		AddressInfo addressInfo2 = new AddressInfo(
				ContactsContract.CommonDataKinds.StructuredPostal.TYPE_WORK,
				address2);

		List<AddressInfo> addressList = new ArrayList<AddressInfo>();
		addressList.add(addressInfo1);
		addressList.add(addressInfo2);
		contactsInfo.setAddressList(addressList);

		// 网址
		String web1 = "www.baidu.com";
		String web2 = "www.google.com";
		List<String> webList = new ArrayList<String>();
		webList.add(web1);
		webList.add(web2);
		contactsInfo.setWebsiteList(webList);

		// 备注信息
		String note1 = "备注1";
		String note2 = "备注2";
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
