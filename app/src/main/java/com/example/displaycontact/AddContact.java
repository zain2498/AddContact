package com.example.displaycontact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {

    EditText frstName, lstName, inputNumber;
    Button add, cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        frstName = (EditText) findViewById(R.id.Edt_InputFirstName);
        lstName = (EditText) findViewById(R.id.Edt_InputLstName);
        inputNumber = (EditText) findViewById(R.id.Edt_InputNumber);

        add = (Button) findViewById(R.id.Btn_add);
        cancel = (Button) findViewById(R.id.Btn_cancel);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddContact.this);
                builder.setMessage("Are you sure You wanna Exit ");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AddContact.this.finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public void Add_Contact(View view) {
        //Create Intent Contact_add
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);


            intent.putExtra(ContactsContract.Intents.Insert.PHONE, inputNumber.getText())
                    .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                    .putExtra(ContactsContract.Intents.Insert.NAME, frstName.getText()+ " " + lstName.getText().toString());

        startActivity(intent);

        //initialize
        checkedData();

    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    //validation check


    void checkedData() {
        if (isEmpty(frstName)) {
            Toast.makeText(this, "you must enter the name to save contact", Toast.LENGTH_LONG).show();
        } else if (isEmpty(lstName)) {
            lstName.setError("Must enter the last name");
        } else if (isEmpty(inputNumber)) {
            inputNumber.setError("Must enter the valid number");
        } else {
            Toast.makeText(this, "Successfully saved", Toast.LENGTH_LONG).show();
        }
    }
}