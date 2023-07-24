package com.example.nhom14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btndangnhap;
    EditText edttk,edtmk;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btndangnhap=(Button) findViewById(R.id.btndangnhap);
        edttk=(EditText) findViewById(R.id.edttk);
        edtmk=(EditText) findViewById(R.id.edtmk);
        checkBox=(CheckBox) findViewById(R.id.checkBox);
        anhienmk();
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username="nhom14";
                String password="1";
                if(edttk.getText().toString().equals(username)&&edtmk.getText().toString().equals(password)){
                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Sai Tài Khoản hoặc Mật Khẩu",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void anhienmk(){
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b){
                    edtmk.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }else{
                    edtmk.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }
}