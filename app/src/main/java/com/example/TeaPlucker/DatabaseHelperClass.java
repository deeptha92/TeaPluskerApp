package com.example.TeaPlucker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelperClass-->";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "supplier_database2";
    private static final String TABLE_NAME = "SUPPLIER";
    private static final String TABLE_NAME_SUPPLIER = "SUPPLIER_NAMES";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String GREENTEA_QUA = "greentea_qua";
    public static final String ADDITIONAL_QUA = "additional_qua";
    public static final String CASH_QUA = "cash_qua";
    public static final String WELFARE_QUA = "welfare_qua";
    public static final String TRANSPORT_QUA = "transport_qua";
    public static final String MANURE_QUA = "manure_qua";
    public static final String MT_QUA = "mt_qua";
    public static final String KOK_QUA = "kok_qua";
    public static final String OTHER_QUA = "other_qua";

    public static final String GREENTEA_PR = "greentea_pr";
    public static final String ADDITIONAL_PR = "additional_pr";
    public static final String CASH_PR = "cash_pr";
    public static final String WELFARE_PR = "welfare_pr";
    public static final String TRANSPORT_PR = "transport_pr";
    public static final String MANURE_PR = "manure_pr";
    public static final String MT_PR = "mt_pr";
    public static final String KOK_PR = "kok_pr";
    public static final String OTHER_PR = "other_pr";


    public static final String SUPPLIER_NAME = "supplier_name";
    public static final String SUPPLIER_ID = "supplier_id";


    private SQLiteDatabase sqLiteDatabase;

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL," + GREENTEA_QUA + " TEXT NOT NULL," +
            GREENTEA_PR + " TEXT NOT NULL, " + ADDITIONAL_QUA + " TEXT NOT NULL," +
            ADDITIONAL_PR + " TEXT NOT NULL, " + WELFARE_QUA + " TEXT NOT NULL, " + WELFARE_PR + " TEXT NOT NULL, " +
            CASH_QUA + " TEXT NOT NULL, " + CASH_PR + " TEXT NOT NULL, " +
            TRANSPORT_QUA + " TEXT NOT NULL, " + TRANSPORT_PR + " TEXT NOT NULL, " + MANURE_QUA + " TEXT NOT NULL, " +
            MANURE_PR + " TEXT NOT NULL, " + MT_QUA + " TEXT NOT NULL, " + MT_PR + " TEXT NOT NULL, " + KOK_QUA + " TEXT NOT NULL, " +
            KOK_PR + " TEXT NOT NULL, " + OTHER_QUA + " TEXT NOT NULL, " + OTHER_PR + " TEXT NOT NULL);";

    private static final String CREATE_TABLE_SUPPLIER = "CREATE TABLE " + TABLE_NAME_SUPPLIER + "(" + ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + SUPPLIER_ID + " TEXT NOT NULL," +
            SUPPLIER_NAME + " TEXT NOT NULL);";

    public DatabaseHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d("testcsv", "CREATE" + CREATE_TABLE);

        db.execSQL(CREATE_TABLE_SUPPLIER);
        Log.d("testcsv", "CREATE" + CREATE_TABLE_SUPPLIER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SUPPLIER);
        onCreate(db);
    }

    public void addEmployee(EmployeeModalClass employeeModalClass) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.NAME, employeeModalClass.getSupplier_name());
        contentValues.put(DatabaseHelperClass.GREENTEA_QUA, employeeModalClass.getGreentea_qua());
        contentValues.put(DatabaseHelperClass.ADDITIONAL_QUA, employeeModalClass.getAdditional_qua());
        contentValues.put(DatabaseHelperClass.CASH_QUA, employeeModalClass.getCash_qua());
        contentValues.put(DatabaseHelperClass.WELFARE_QUA, employeeModalClass.getWelfare_qua());
        contentValues.put(DatabaseHelperClass.TRANSPORT_QUA, employeeModalClass.getTransport_qua());
        contentValues.put(DatabaseHelperClass.MT_QUA, employeeModalClass.getMt_qua());
        contentValues.put(DatabaseHelperClass.MANURE_QUA, employeeModalClass.getManure_qua());
        contentValues.put(DatabaseHelperClass.KOK_QUA, employeeModalClass.getKok_qua());
        contentValues.put(DatabaseHelperClass.OTHER_QUA, employeeModalClass.getOther_qua());

        contentValues.put(DatabaseHelperClass.GREENTEA_PR, employeeModalClass.getGreentea_pr());
        contentValues.put(DatabaseHelperClass.ADDITIONAL_PR, employeeModalClass.getAdditional_pr());
        contentValues.put(DatabaseHelperClass.CASH_PR, employeeModalClass.getCash_pr());
        contentValues.put(DatabaseHelperClass.WELFARE_PR, employeeModalClass.getWelfare_pr());
        contentValues.put(DatabaseHelperClass.TRANSPORT_PR, employeeModalClass.getTransport_pr());
        contentValues.put(DatabaseHelperClass.MT_PR, employeeModalClass.getMt_pr());
        contentValues.put(DatabaseHelperClass.MANURE_PR, employeeModalClass.getManure_pr());
        contentValues.put(DatabaseHelperClass.KOK_PR, employeeModalClass.getKok_pr());
        contentValues.put(DatabaseHelperClass.OTHER_PR, employeeModalClass.getOther_pr());
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelperClass.TABLE_NAME, null, contentValues);


    }

    public void addSuppliers(SupplierModalClass supplierModalClass) {

        ContentValues contentValues = new ContentValues();
//        contentValues.put(DatabaseHelperClass.NAME, employeeModalClass.getName());
        contentValues.put(DatabaseHelperClass.SUPPLIER_ID, supplierModalClass.getSupplier_id());
        contentValues.put(DatabaseHelperClass.SUPPLIER_NAME, supplierModalClass.getSupplier_name());
//        String whereclause = SUPPLIER_NAME+ "=?";
//        String[] whereargs = new String[]{supplierModalClass.getSupplier_name()};
//
////        String sql = "SELECT * FROM "+TABLE_NAME_SUPPLIER+" WHERE "+SUPPLIER_NAME+ "=?";
//        Cursor cursor = sqLiteDatabase.query(TABLE_NAME_SUPPLIER, null, whereclause, whereargs, null,null, null);
//        Log.d(TAG, "addSuppliers: "+cursor.getCount());
//        String sql = "SELECT * FROM " + TABLE_NAME_SUPPLIER + " WHERE SUPPLIER_NAME=?";
//        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[] { SUPPLIER_NAME, supplierModalClass.getSupplier_name() });

        Cursor cursor;
        String checkQuery = "SELECT " + SUPPLIER_NAME + " FROM " + TABLE_NAME_SUPPLIER + " WHERE " + SUPPLIER_NAME + "= '"+supplierModalClass.getSupplier_name() + "'  OR "+ SUPPLIER_ID +"= '"+supplierModalClass.getSupplier_id() + "'";
//        cursor= sqLiteDatabase.rawQuery(checkQuery,null);
        cursor = getReadableDatabase().rawQuery(checkQuery,null);

        if (cursor.getCount() > 0) {
            AddSuplierActivity as = new AddSuplierActivity();
            as.noData();
        } else {
            sqLiteDatabase = getWritableDatabase();
            sqLiteDatabase.insert(DatabaseHelperClass.TABLE_NAME_SUPPLIER, null, contentValues);

        }



    }

    public List<EmployeeModalClass> getEmployeeList() {
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<EmployeeModalClass> storeEmployee = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String GREENTEA_QUA = cursor.getString(2);
                String NAME = cursor.getString(1);
                String GREENTEA_PR = cursor.getString(3);
                String ADDITIONAL_QUA = cursor.getString(4);
                String ADDITIONAL_PR = cursor.getString(5);
                String CASH_QUA = cursor.getString(6);
                String CASH_PR = cursor.getString(7);
                String WELFARE_QUA = cursor.getString(8);
                String WELFARE_PR = cursor.getString(9);
                String TRANSPORT_QUA = cursor.getString(10);
                String TRANSPORT_PR = cursor.getString(11);
                String MT_QUA = cursor.getString(12);
                String MT_PR = cursor.getString(13);
                String MANURE_QUA = cursor.getString(14);
                String MANURE_PR = cursor.getString(15);
                String KOK_QUA = cursor.getString(16);
                String KOK_PR = cursor.getString(17);
                String OTHER_QUA = cursor.getString(18);
                String OTHER_PR = cursor.getString(19);

                storeEmployee.add(new EmployeeModalClass(NAME,GREENTEA_QUA, GREENTEA_PR, ADDITIONAL_QUA, ADDITIONAL_PR,
                        CASH_QUA, CASH_PR, WELFARE_QUA, WELFARE_PR, TRANSPORT_QUA, TRANSPORT_PR, MT_QUA, MT_PR, MANURE_QUA, MANURE_PR,
                        KOK_QUA, KOK_PR, OTHER_QUA, OTHER_PR));

            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeEmployee;
    }

    public List<SupplierModalClass> getSupplierNames() {
        String sql = "select * from " + TABLE_NAME_SUPPLIER;
        sqLiteDatabase = this.getReadableDatabase();
        List<SupplierModalClass> storeSupplier = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String SUPPLIER_ID = cursor.getString(1);
                String SUPPLIER_NAME = cursor.getString(2);

                storeSupplier.add(new SupplierModalClass(SUPPLIER_ID, SUPPLIER_NAME));

            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeSupplier;
    }

    public void updateEmployee(EmployeeModalClass employeeModalClass) {

    }

//    public void exportDB () {
//        DatabaseHelperClass dbhelper = new DatabaseHelperClass(this);
//        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
//        if (!exportDir.exists())
//        {
//            exportDir.mkdirs();
//        }
//
//        File file = new File(exportDir, "csvname.csv");
//        try
//        {
//            file.createNewFile();
//            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
//            SQLiteDatabase db = dbhelper.getReadableDatabase();
//            Cursor curCSV = db.rawQuery("SELECT * FROM contacts",null);
//            csvWrite.writeNext(curCSV.getColumnNames());
//            while(curCSV.moveToNext())
//            {
//                //Which column you want to exprort
//                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
//                csvWrite.writeNext(arrStr);
//            }
//            csvWrite.close();
//            curCSV.close();
//        }
//        catch(Exception sqlEx)
//        {
//            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
//        }
//    }

    public void deleteSupplier(String name) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, NAME + " = ? ", new String[]{String.valueOf(name)});
    }
}
