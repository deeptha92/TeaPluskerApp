package com.example.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.Modal.EmployeeModalClass;
import com.example.Modal.SupplierModalClass;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelperClass-->";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Final_Tea";
    private static final String TABLE_NAME = "SUPPLIER";
    private static final String TABLE_NAME_SUPPLIER = "SUPPLIER_NAMES";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SUP_ID = "sup_id";
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

    public static final String GREENTEA_UP = "greentea_up";
    public static final String ADDITIONAL_UP = "additional_up";
    public static final String CASH_UP = "cash_up";
    public static final String WELFARE_UP = "welfare_up";
    public static final String TRANSPORT_UP = "transport_up";
    public static final String MANURE_UP = "manure_up";
    public static final String MT_UP = "mt_up";
    public static final String KOK_UP = "kok_up";
    public static final String OTHER_UP = "other_up";


    public static final String SUPPLIER_NAME = "supplier_name";
    public static final String SUPPLIER_ID = "supplier_id";

    public static final String GREEN_TEA_UP_PRE = "green_up_pre";
    public static final String TRANSPORT_UP_PRE = "tr_up_pre";


    private SQLiteDatabase sqLiteDatabase;

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL,"  + SUP_ID + " TEXT NOT NULL," + GREENTEA_QUA + " TEXT NOT NULL," +
            GREENTEA_UP + " TEXT NOT NULL," + GREENTEA_PR + " TEXT NOT NULL, " + ADDITIONAL_QUA + " TEXT NOT NULL," +
            ADDITIONAL_UP + " TEXT NOT NULL," + ADDITIONAL_PR + " TEXT NOT NULL, " + WELFARE_QUA + " TEXT NOT NULL, " + WELFARE_UP + " TEXT NOT NULL, " + WELFARE_PR + " TEXT NOT NULL, " +
            CASH_UP + " TEXT NOT NULL," + CASH_QUA + " TEXT NOT NULL, " + CASH_PR + " TEXT NOT NULL, " +
            TRANSPORT_QUA + " TEXT NOT NULL, " + TRANSPORT_UP + " TEXT NOT NULL, " + TRANSPORT_PR + " TEXT NOT NULL, " + MANURE_QUA + " TEXT NOT NULL, " +
            MANURE_UP + " TEXT NOT NULL," + MANURE_PR + " TEXT NOT NULL, " + MT_QUA + " TEXT NOT NULL, " + MT_UP + " TEXT NOT NULL, " + MT_PR + " TEXT NOT NULL, " + KOK_QUA + " TEXT NOT NULL, " +
            KOK_UP + " TEXT NOT NULL," + KOK_PR + " TEXT NOT NULL, " + OTHER_QUA + " TEXT NOT NULL, " + OTHER_UP + " TEXT NOT NULL, " + OTHER_PR + " TEXT NOT NULL);";

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
        contentValues.put(DatabaseHelperClass.SUP_ID, employeeModalClass.getSupplier_id());
        contentValues.put(DatabaseHelperClass.GREENTEA_QUA, employeeModalClass.getGreentea_qua());
        contentValues.put(DatabaseHelperClass.ADDITIONAL_QUA, employeeModalClass.getAdditional_qua());
        contentValues.put(DatabaseHelperClass.CASH_QUA, employeeModalClass.getCash_qua());
        contentValues.put(DatabaseHelperClass.WELFARE_QUA, employeeModalClass.getWelfare_qua());
        contentValues.put(DatabaseHelperClass.TRANSPORT_QUA, employeeModalClass.getTransport_qua());
        contentValues.put(DatabaseHelperClass.MT_QUA, employeeModalClass.getMt_qua());
        contentValues.put(DatabaseHelperClass.MANURE_QUA, employeeModalClass.getManure_qua());
        contentValues.put(DatabaseHelperClass.KOK_QUA, employeeModalClass.getKok_qua());
        contentValues.put(DatabaseHelperClass.OTHER_QUA, employeeModalClass.getOther_qua());

        contentValues.put(DatabaseHelperClass.GREENTEA_UP, employeeModalClass.getGreentea_up());
        contentValues.put(DatabaseHelperClass.ADDITIONAL_UP, employeeModalClass.getAdditional_up());
        contentValues.put(DatabaseHelperClass.CASH_UP, employeeModalClass.getCash_up());
        contentValues.put(DatabaseHelperClass.WELFARE_UP, employeeModalClass.getWelfare_up());
        contentValues.put(DatabaseHelperClass.TRANSPORT_UP, employeeModalClass.getTransport_up());
        contentValues.put(DatabaseHelperClass.MT_UP, employeeModalClass.getMt_up());
        contentValues.put(DatabaseHelperClass.MANURE_UP, employeeModalClass.getManure_up());
        contentValues.put(DatabaseHelperClass.KOK_UP, employeeModalClass.getKok_up());
        contentValues.put(DatabaseHelperClass.OTHER_UP, employeeModalClass.getOther_up());

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
            Log.d(TAG, "addSuppliers: "+ "Added");
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
                String NAME = cursor.getString(1);
                String SUP_ID = cursor.getString(2);
                String GREENTEA_QUA = cursor.getString(3);
                String GREENTEA_UP = cursor.getString(4);
                String GREENTEA_PR = cursor.getString(5);
                String ADDITIONAL_QUA = cursor.getString(6);
                String ADDITIONAL_UP = cursor.getString(7);
                String ADDITIONAL_PR = cursor.getString(8);
                String CASH_QUA = cursor.getString(9);
                String CASH_UP = cursor.getString(10);
                String CASH_PR = cursor.getString(11);
                String WELFARE_QUA = cursor.getString(12);
                String WELFARE_UP = cursor.getString(13);
                String WELFARE_PR = cursor.getString(14);
                String TRANSPORT_QUA = cursor.getString(15);
                String TRANSPORT_UP = cursor.getString(16);
                String TRANSPORT_PR = cursor.getString(17);
                String MT_QUA = cursor.getString(18);
                String MT_UP = cursor.getString(19);
                String MT_PR = cursor.getString(20);
                String MANURE_QUA = cursor.getString(21);
                String MANURE_UP = cursor.getString(22);
                String MANURE_PR = cursor.getString(23);
                String KOK_QUA = cursor.getString(24);
                String KOK_UP = cursor.getString(25);
                String KOK_PR = cursor.getString(26);
                String OTHER_QUA = cursor.getString(27);
                String OTHER_UP = cursor.getString(28);
                String OTHER_PR = cursor.getString(29);

                EmployeeModalClass employeeModalClass = new EmployeeModalClass();
                employeeModalClass.setSupplier_name(NAME);
                employeeModalClass.setSupplier_id(SUP_ID);
                employeeModalClass.setGreentea_qua(GREENTEA_QUA);
                employeeModalClass.setGreentea_up(GREENTEA_UP);
                employeeModalClass.setGreentea_pr(GREENTEA_PR);
                employeeModalClass.setAdditional_qua(ADDITIONAL_QUA);
                employeeModalClass.setAdditional_up(ADDITIONAL_UP);
                employeeModalClass.setAdditional_pr(ADDITIONAL_PR);
                employeeModalClass.setCash_up(CASH_UP);
                employeeModalClass.setCash_pr(CASH_PR);
                employeeModalClass.setWelfare_up(WELFARE_UP);
                employeeModalClass.setWelfare_pr(WELFARE_PR);
                employeeModalClass.setTransport_qua(TRANSPORT_QUA);
                employeeModalClass.setTransport_up(TRANSPORT_UP);
                employeeModalClass.setTransport_pr(TRANSPORT_PR);
                employeeModalClass.setManure_qua(MANURE_QUA);
                employeeModalClass.setManure_up(MANURE_UP);
                employeeModalClass.setManure_pr(MANURE_PR);
                employeeModalClass.setMt_qua(MT_QUA);
                employeeModalClass.setMt_up(MT_UP);
                employeeModalClass.setMt_pr(MT_PR);
                employeeModalClass.setKok_qua(KOK_QUA);
                employeeModalClass.setKok_up(KOK_UP);
                employeeModalClass.setKok_pr(KOK_PR);
                employeeModalClass.setOther_qua(OTHER_QUA);
                employeeModalClass.setOther_up(OTHER_UP);
                employeeModalClass.setOther_pr(OTHER_PR);

                storeEmployee.add(employeeModalClass);

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

                SupplierModalClass supplierModalClass = new SupplierModalClass();
                supplierModalClass.setSupplier_id(SUPPLIER_ID);
                supplierModalClass.setSupplier_name(SUPPLIER_NAME);

                storeSupplier.add(supplierModalClass);

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
