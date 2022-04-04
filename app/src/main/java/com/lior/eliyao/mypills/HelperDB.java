package com.lior.eliyao.mypills;

        import android.annotation.SuppressLint;
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import androidx.annotation.Nullable;

        import java.util.ArrayList;
        import java.util.Arrays;

class HelperDB extends SQLiteOpenHelper {

    public static final String DB_FILE="all_pills.db";

    public static final String TABLE_PILLS="Pills";
    public static final String NAME="Name";
    public static final String CODE="Code";
    public static final String BATCH="Batch";
    public static final String COMPANY="Company";
    public static final String EXPIRE="Exp";
    public static final String PRICE="Price";

    public HelperDB(Context context) {
        super(context, DB_FILE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String st="CREATE TABLE IF NOT EXISTS "+TABLE_PILLS+" ( ";
        st+=NAME+" TEXT, "+CODE+" TEXT, "+COMPANY+" TEXT, "+BATCH+" TEXT, "+EXPIRE+" INTEGER, "+PRICE+" REAL);";
        db.execSQL(st);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addPill(Pill pill)  {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(NAME,pill.getCode());
        cv.put(CODE,pill.getCode());
        cv.put(COMPANY,pill.getCompany());
        cv.put(BATCH,pill.getBatch());
        cv.put(EXPIRE,pill.getExp());
        cv.put(PRICE,pill.getPrice());
        db.insert(HelperDB.TABLE_PILLS, null, cv);
        db.close();
    }

    @SuppressLint("Range")
    public ArrayList<Pill> allData() {
        ArrayList<Pill> arrayList=new ArrayList<Pill>();
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.query(TABLE_PILLS,
                null, null, null,
                null, null, null);
        int n=c.getCount();
        c.moveToFirst();
        for (int i = 0; i < n; i++) {
            Pill pill=new Pill("","","","",0,0.0);
            pill.setName(c.getString(c.getColumnIndex(NAME)));
            pill.setCode(c.getString(c.getColumnIndex(CODE)));
            pill.setCompany(c.getString(c.getColumnIndex(COMPANY)));
            pill.setBatch(c.getString(c.getColumnIndex(BATCH)));
            pill.setExp(c.getInt(c.getColumnIndex(EXPIRE)));
            pill.setPrice(c.getDouble(c.getColumnIndex(PRICE)));
            arrayList.add(pill);
            c.moveToNext();
        }
        db.close();
        return arrayList;

    }

    @SuppressLint("Range")
    public ArrayList<Pill> byCompanyData(String st) {
        ArrayList<Pill> arrayList=new ArrayList<Pill>();
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.query(TABLE_PILLS,
                null, COMPANY+"=?", new String[]{st},
                null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Pill car=new Pill("","","","",0,0.0);
            car.setCode(c.getString(c.getColumnIndex(CODE)));
            car.setCompany(c.getString(c.getColumnIndex(COMPANY)));
            car.setBatch(c.getString(c.getColumnIndex(BATCH)));
            car.setExp(c.getInt(c.getColumnIndex(EXPIRE)));
            car.setPrice(c.getDouble(c.getColumnIndex(PRICE)));
            arrayList.add(car);
            c.moveToNext();
        }
        db.close();
        return arrayList;
    }

    @SuppressLint("Range")
    public ArrayList<Pill> byOldest(int before) {
        ArrayList<Pill> arrayList=new ArrayList<Pill>();
        SQLiteDatabase db=getWritableDatabase();

        Cursor c=db.query(TABLE_PILLS,
                null, EXPIRE+"<?",
                new String[]{String.valueOf(before)},
                null, null, null);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            Pill car=new Pill("","","","",0,0.0);
            car.setCode(c.getString(c.getColumnIndex(CODE)));
            car.setCompany(c.getString(c.getColumnIndex(COMPANY)));
            car.setBatch(c.getString(c.getColumnIndex(BATCH)));
            car.setExp(c.getInt(c.getColumnIndex(EXPIRE)));
            car.setPrice(c.getDouble(c.getColumnIndex(PRICE)));
            arrayList.add(car);
            c.moveToNext();
        }
        db.close();
        return arrayList;
    }

    @SuppressLint("Range")
    public ArrayList<Pill> byFin(String f) {
        ArrayList<Pill> arrayList=new ArrayList<Pill>();
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.query(TABLE_PILLS,
                null, COMPANY+"=?", new String[]{f},
                null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Pill car=new Pill("","","","",0,0.0);
            car.setCode(c.getString(c.getColumnIndex(CODE)));
            car.setCompany(c.getString(c.getColumnIndex(COMPANY)));
            car.setBatch(c.getString(c.getColumnIndex(BATCH)));
            car.setExp(c.getInt(c.getColumnIndex(EXPIRE)));
            car.setPrice(c.getDouble(c.getColumnIndex(PRICE)));
            arrayList.add(car);
            c.moveToNext();
        }
        db.close();
        return arrayList;
    }

    public double calcSum() {
        SQLiteDatabase db=getWritableDatabase();

        String forSum="SUM ("+PRICE+") AS "+PRICE;
        String[] columns=new String[]{forSum};
        Cursor c=db.query(TABLE_PILLS,
                columns, null,null,
                null, null, null);
        c.moveToFirst();
        @SuppressLint("Range") double sum=c.getDouble(c.getColumnIndex(PRICE));
        db.close();

        return sum;
    }

    public ArrayList<String> byCompany() {
        ArrayList<String> arrayList=new ArrayList<String>();
        SQLiteDatabase db=getWritableDatabase();

        String stCount="COUNT ("+COMPANY+") AS COUNT";
        String[] columns=new String[]{COMPANY,stCount};

        Cursor c=db.query(TABLE_PILLS,
                columns, null , null,
                COMPANY, null, null);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            arrayList.add(c.getString(0)+"="+c.getString(1));
            c.moveToNext();
        }

        db.close();
        return arrayList;
    }

    public ArrayList<String> byBatch() {
        ArrayList<String> arrayList=new ArrayList<String>();
        SQLiteDatabase db=getWritableDatabase();
        String stCount="COUNT ("+BATCH+") AS COUNT";
        String[] columns=new String[]{COMPANY,BATCH,stCount};
        Cursor c=db.query(TABLE_PILLS,
                columns, null , null,
                BATCH, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            arrayList.add(c.getString(0)+" "+c.getString(1)+"="+c.getString(2));
            c.moveToNext();
        }
        db.close();
        return arrayList;
    }

    public void updateModel(String oldModel, String newModel) {
        ContentValues cv=new ContentValues();
        cv.put(BATCH, newModel);
        String inField=BATCH+"=?";
        String[] oldData=new String[]{oldModel};
        SQLiteDatabase db=getWritableDatabase();
        db.update(TABLE_PILLS, cv, inField, oldData);
        db.close();
    }
}
