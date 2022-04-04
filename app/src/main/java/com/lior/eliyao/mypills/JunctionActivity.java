package com.lior.eliyao.mypills;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.LinearLayout;
        import android.widget.PopupMenu;
        import android.widget.TextView;

public class JunctionActivity extends AppCompatActivity {

    LinearLayout LLmain;
    TextView dn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junction);

        LLmain=findViewById(R.id.LLmain);
        dn=findViewById(R.id.dn);
        LLmain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu pm=new PopupMenu(JunctionActivity.this, dn);
                pm.getMenuInflater().inflate(R.menu.for_popup,pm.getMenu());
                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int menuItemID=menuItem.getItemId();
                        if (menuItemID==R.id.night)
                            LLmain.setBackgroundColor(Color.BLACK);
                        if (menuItemID==R.id.day)
                            LLmain.setBackgroundColor(Color.WHITE);
                        return true;
                    }
                });

                pm.show();
                return true;
            }
        });
    }

    public void goInsert(View view) {
        Intent go=new Intent(getApplicationContext(), InsertPillActivity.class);
        startActivity(go);
    }

    public void goView(View view) {
        Intent go=new Intent(getApplicationContext(), ViewDataActivity.class);
        startActivity(go);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.back)
            finish();
        if (id==R.id.exit)
            finishAffinity();
        return true;
    }
}