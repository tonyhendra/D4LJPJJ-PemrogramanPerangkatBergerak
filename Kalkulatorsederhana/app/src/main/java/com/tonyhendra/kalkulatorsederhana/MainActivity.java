package com.tonyhendra.kalkulatorsederhana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView tv_hasil;
    Button btn_tambah, btn_kurang, btn_kali, btn_bagi;
    EditText et_angka1, et_angka2;
    Double angka1,angka2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_hasil = (TextView)findViewById(R.id.tv_hasil);
        et_angka1 = (EditText)findViewById(R.id.et_angka1);
        et_angka2 = (EditText)findViewById(R.id.et_angka2);
        btn_tambah = (Button)findViewById(R.id.btn_tambah);
        btn_kurang = (Button)findViewById(R.id.btn_kurang);
        btn_kali = (Button)findViewById(R.id.btn_kali);
        btn_bagi = (Button)findViewById(R.id.btn_bagi);

        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(et_angka1.getText().toString().trim().equals("")){
                        et_angka1.setError("Required");
                    }else if(et_angka2.getText().toString().trim().equals("")){
                        et_angka2.setError("Required");
                    }else{
                        angka1 = Double.parseDouble(et_angka1.getText().toString());
                        angka2 = Double.parseDouble(et_angka2.getText().toString());
                        Double hasil = angka1 + angka2;
                        tv_hasil.setText("Hasil : "+
                                angka1+" + "+angka2+" = "+hasil.toString());
                    }
                }catch(Exception e){
                      e.printStackTrace();
                }
            }
        });
        btn_kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(et_angka1.getText().toString().trim().equals("")){
                        et_angka1.setError("Required");
                    }else if(et_angka2.getText().toString().trim().equals("")){
                        et_angka2.setError("Required");
                    }else{
                        angka1 = Double.parseDouble(et_angka1.getText().toString());
                        angka2 = Double.parseDouble(et_angka2.getText().toString());
                        Double hasil = angka1 - angka2;
                        tv_hasil.setText("Hasil : "+
                                angka1+" - "+angka2+" = "+hasil.toString());

                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        btn_kali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(et_angka1.getText().toString().trim().equals("")){
                        et_angka1.setError("Required");
                    }else if(et_angka2.getText().toString().trim().equals("")){
                        et_angka2.setError("Required");
                    }else{
                        angka1 = Double.parseDouble(et_angka1.getText().toString());
                        angka2 = Double.parseDouble(et_angka2.getText().toString());
                        Double hasil = angka1 * angka2;
                        tv_hasil.setText("Hasil : "+
                                angka1+" x "+angka2+" = "+hasil.toString());
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        btn_bagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(et_angka1.getText().toString().trim().equals("")){
                        et_angka1.setError("Required");
                    }else if(et_angka2.getText().toString().trim().equals("")){
                        et_angka2.setError("Required");
                    }else{
                        angka1 = Double.parseDouble(et_angka1.getText().toString());
                        angka2 = Double.parseDouble(et_angka2.getText().toString());
                        Double hasil = angka1 / angka2;
                        tv_hasil.setText("Hasil : "+
                                angka1+" / "+angka2+" = "+hasil.toString());
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}