package com.tonyhendra.bukutelepon;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String[] daftar;
    ListView listView1;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, InsertBukuTelepon.class);
                startActivity(i);
            }
        });
        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }
     public void RefreshList(){
         SQLiteDatabase db = dbcenter.getReadableDatabase();
         cursor = db.rawQuery("Select * from bukutelepon", null);
         daftar = new String[cursor.getCount()];
         cursor.moveToFirst();
         for(int cc=0; cc<cursor.getCount(); cc++){
             cursor.moveToPosition(cc);
             daftar[cc] = cursor.getString(1).toString();
         }
         listView1 = (ListView)findViewById(R.id.listView1);
         listView1.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,daftar));
         listView1.setSelected(true);
         listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                 final String selection = daftar[arg2];
                 final CharSequence[] dialogitem = {"Lihat BukuTelepon", "Update BukuTelepon", "Hapus BukuTelepon"};
                 AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                 builder.setTitle("Pilihan");
                 builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int item) {
                         switch(item){
                             case 0 :
                                 Intent i = new Intent(getApplicationContext(), ViewBukuTelepon.class);
                                 i.putExtra("nama", selection);
                                 startActivity(i);
                                 break;
                             case 1 :
                                 Intent in = new Intent(getApplicationContext(), UpdateBukuTelepon.class);
                                 in.putExtra("nama", selection);
                                 startActivity(in);
                                 break;
                             case 2 :
                                 SQLiteDatabase db = dbcenter.getWritableDatabase();
                                 db.execSQL("delete from bukutelepon where nama = '"+selection+"'");
                                 RefreshList();
                                 break;
                         }
                     }
                 });
                 builder.create().show();
             }});
         ((ArrayAdapter)listView1.getAdapter()).notifyDataSetInvalidated();
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
