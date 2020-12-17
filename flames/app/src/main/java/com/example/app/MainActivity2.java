package com.example.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    Button b1;
    AlertDialog.Builder builder;
    String str1,str2,str3;
        public static int getCnt(String s1, String s2){
            String c;
            int cnt = 0;
            for(int i=0;i<s1.length();i++){
                c = s1.charAt(i)+"";
                if(s2.contains(c)){
                    s2.replaceFirst(c,"");
                    cnt++;
                }
            }
            return ((s1.length()+s2.length()) - (2*cnt));
        }
        public static String abb(char c){
            switch(c){
                case 'F' : return "Friends";
                case 'L' : return "Lovers";
                case 'A' : return "Affectionate";
                case 'M' : return "Married";
                case 'E' : return "Enemies";
                case 'S' : return "Siblings";
            }
            return "";
        }
        public static boolean isalphabet(String s1)
        {
            for(int i=0;i<s1.length();i++)
            {
                if(!Character.isLetter(s1.charAt(i)))
                {

                    return true;
                }

            }
            return false;

        }
        public static String finds(String name1,String name2) {
            name1=name1.replaceAll(" ","");
            name2=name2.replaceAll(" ","");
            if(name1.length()==0 || name2.length()==0)
            {
                return "Do not Leave Names as empty";
            }
            if (isalphabet(name1) || isalphabet(name2)) {

                return "Please enter valid Name characters";
            }
            int a[] = {1, 1, 1, 1, 1, 1};
            int k = 0;
            String str = "FLAMES";
            int cnt = getCnt(name1, name2);
            if (cnt == 0) {

                return "Please Enter Different Names";
            }
            for (int i = 1; i < 6; i++) {
                int j = 0;
                while (true) {
                    if (a[k] == 1)
                        j++;
                    if (j == cnt) {
                        a[k] = 0;
                        break;
                    }
                    k = (k + 1) % 6;
                }
            }

            for (int i = 0; i < 6; i++)
                if (a[i] == 1) {

                    return "You are : " + abb(str.charAt(i));
                }
            return "";
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final EditText txt1=findViewById(R.id.edt1);
        final EditText txt2=findViewById(R.id.edt2);
        b1=findViewById(R.id.clickon);
        builder = new AlertDialog.Builder(this);
        txt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(TextUtils.isEmpty(txt1.getText().toString()))
                {
                    str1="";
                }
                else {
                    str1=txt1.getText().toString();
                }

            }



            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        txt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(TextUtils.isEmpty(txt2.getText().toString()))
                {
                    str2="";
                }
                else {
                    str2=txt2.getText().toString();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(),finds(str1, str2),Toast.LENGTH_SHORT).show();
                builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                //Setting message manually and performing action on button click
                str1=txt1.getText().toString();
                str2=txt2.getText().toString();
                str3=finds(str1,str2);
                builder.setMessage(str3+"\n\nDo you want to play again?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                txt2.setText("");
                                txt1.setText("");
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"Here we go ^_^",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                finish();
                                Toast.makeText(getApplicationContext(),"Bye :)",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                if(str3.equals("Do not Leave Names as empty") || str3.equals("Please enter valid Name characters")) {
                    alert.setTitle("Oops!");
                } else
                    alert.setTitle("Hey!");
                alert.show();
            }
        });

    }
}
