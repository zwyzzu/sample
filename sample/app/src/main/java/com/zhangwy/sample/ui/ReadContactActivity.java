package com.zhangwy.sample.ui;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zhangwy.sample.R;
import com.zhangwy.sample.entity.ReadContactEntity;
import com.zhangwy.util.Logger;
import com.zhangwy.util.Util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ReadContactActivity extends BaseActivity implements View.OnClickListener {
    private RadioGroup radioGroup;
    private HashMap<String, ReadContactEntity> hashMap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_contact);
        this.radioGroup = this.findViewById(R.id.readContactRadioGroup);
        this.hashMap.clear();
        this.readUri(ContactsContract.class, this.hashMap);
        this.addButton(new ArrayList<>(this.hashMap.values()));
        this.findViewById(R.id.readContactRequest).setOnClickListener(this);
        Button button = this.findViewById(R.id.readContactRequestAll);
        button.setOnClickListener(this);
        button.setText(getString(R.string.request_all_num, this.hashMap.size()));
        this.radioGroup.check(0);
    }

    private void addButton(List<ReadContactEntity> array) {
        for (int i = 0; i < array.size(); i++) {
            ReadContactEntity entity = array.get(i);
            if (entity == null) {
                continue;
            }
            String uriString = entity.getUri().toString();
            Logger.d(String.format("add.button.isContact:%b，uri:%s", uriString.startsWith("content://com.android.contacts"), uriString));
            View view = LayoutInflater.from(this).inflate(R.layout.item_radiobutton, this.radioGroup, false);
            RadioButton button = view.findViewById(R.id.radioButton);
            this.radioGroup.addView(button);
            button.setId(i);
            button.setText(entity.getName());
        }
    }

    private void readUri(Class clazz, HashMap<String, ReadContactEntity> hashMap) {
        if (clazz == null) {
            return;
        }
        String clazzName = clazz.getName().replace("android.provider.", "");
        Field[] fields = clazz.getFields();
        if (!Util.isEmpty(fields)) {
            for (Field field : fields) {
                if (field == null) {
                    continue;
                }
                if (field.getType() != Uri.class) {
                    continue;
                }
                String fieldName = field.getName();
                try {
                    ReadContactEntity entity = ReadContactEntity.newBuilder().setName(clazzName + "$" + fieldName).setUri((Uri) field.get(null)).build();
                    hashMap.put(entity.getName(), entity);
                } catch (Exception e) {
                    Logger.e("field error:" + clazzName + "$" + fieldName);
                }
            }
        }
        Class<?>[] classes = clazz.getClasses();
        if (Util.isEmpty(classes)) {
            return;
        }
        for (Class<?> subClass : classes) {
            if (subClass == null) {
                continue;
            }
            readUri(subClass, hashMap);
        }
    }

    //调用并获取联系人信息
    private void readContacts(Uri uri, String name) {
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if (cursor != null) {
                Logger.d(String.format("success.name=%s，uri=%s，columns=%s", name, uri.toString(), Util.array2Strings(Arrays.asList(cursor.getColumnNames()), ',')));
//                while (cursor.moveToNext()) {
//                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                }
            }
        } catch (Exception e) {
            Logger.e(String.format("error.name=%s，uri=%s，exception=%s，msg=%s", name, uri.toString(), e.getClass().getSimpleName(), e.getMessage()));
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.readContactRequest: {
                RadioButton button = this.radioGroup.findViewById(this.radioGroup.getCheckedRadioButtonId());
                String text = button.getText().toString();
                ReadContactEntity entity = this.hashMap.get(text);
                this.readContacts(entity.getUri(), entity.getName());
                break;
            }
            case R.id.readContactRequestAll:
                Collection<ReadContactEntity> collection = this.hashMap.values();
                for (ReadContactEntity entity : collection) {
                    if (entity != null) {
                        this.readContacts(entity.getUri(), entity.getName());
                    }
                }
                break;
        }
    }
}
