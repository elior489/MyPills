package com.lior.eliyao.mypills;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


        import androidx.appcompat.app.AppCompatActivity;

        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

        import java.util.Calendar;

public class InsertPillActivity extends AppCompatActivity {
    HelperDB hlp;
    SQLiteDatabase db;
    EditText etName, etCode, etBatch, etCompany, etExp, etPrice;
    String stName, stCode, stBatch, stCompany, stExp, stPrice;
    int pillYear;
    double pillPrice;
    Pill pill;
    Calendar c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_pill);

        etCompany=findViewById(R.id.etCompany);
        etName=findViewById(R.id.etName);
        etCode=findViewById(R.id.etCode);
        etBatch=findViewById(R.id.etBatch);
        etExp=findViewById(R.id.etExp);
        etPrice=findViewById(R.id.etPrice);

        hlp=new HelperDB(getApplicationContext());
        db=hlp.getWritableDatabase();
        db.close();
    }

    public void insertCarData(View view) {
        stCompany=etCompany.getText().toString();
        stBatch=etBatch.getText().toString();
        stExp=etExp.getText().toString();
        stPrice=etPrice.getText().toString();

        pillYear=Integer.parseInt(stExp);
        pillPrice=Double.parseDouble(stPrice);

        c=Calendar.getInstance();
        stCode=""+c.get(Calendar.YEAR)+c.get(Calendar.MONTH)+c.get(Calendar.DAY_OF_MONTH)+
                c.get(Calendar.HOUR_OF_DAY)+c.get(Calendar.MINUTE)+c.get(Calendar.MILLISECOND);

        pill=new Pill(stName,stCode,stBatch,stCompany,  pillYear,pillPrice);

        Toast.makeText(this, ""+pill.toString(), Toast.LENGTH_SHORT).show();

        hlp.addPill(pill);

        etCompany.setText("");
        etBatch.setText("");
        etExp.setText("");
        etPrice.setText("");

        Toast.makeText(this, "Ok, new pill added", Toast.LENGTH_LONG).show();
    }
}