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
    public static final String TRN_ID = "trn_id";
    public static final String NAME = "name";
    public static final String DATE = "date";
    public static final String SUP_ID = "sup_id";
    public static final String GREENTEA_QUA = "greentea_qua";
    public static final String WATER_QUA = "water_qua";
    public static final String TOTAL_TEA_QUA = "total_tea_qua";
    public static final String ADDITIONAL_QUA = "additional_qua";
    public static final String CASH_QUA = "cash_qua";
    public static final String WELFARE_QUA = "welfare_qua";
    public static final String TRANSPORT_QUA = "transport_qua";
    public static final String MANURE_QUA = "manure_qua";
    public static final String MT_QUA = "mt_qua";
    public static final String KOK_QUA = "kok_qua";
    public static final String OTHER_QUA = "other_qua";

    public static final String SUPPLIER_NAME = "supplier_name";
    public static final String SUPPLIER_ID = "supplier_id";


    private SQLiteDatabase sqLiteDatabase;

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + DATE + " TEXT NOT NULL," + TRN_ID + " TEXT NOT NULL,"  + NAME + " TEXT NOT NULL,"  + SUP_ID + " TEXT NOT NULL," + GREENTEA_QUA + " TEXT NOT NULL," + WATER_QUA + " TEXT NOT NULL," + TOTAL_TEA_QUA + " TEXT NOT NULL," +
            ADDITIONAL_QUA + " TEXT NOT NULL," +
            WELFARE_QUA + " TEXT NOT NULL, " +
            CASH_QUA + " TEXT NOT NULL, " +
            TRANSPORT_QUA + " TEXT NOT NULL, " + MANURE_QUA + " TEXT NOT NULL, " +
            MT_QUA + " TEXT NOT NULL, " + KOK_QUA + " TEXT NOT NULL, " +
            OTHER_QUA + " TEXT NOT NULL);";

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
        contentValues.put(DatabaseHelperClass.DATE, employeeModalClass.getSupp_date());
        contentValues.put(DatabaseHelperClass.TRN_ID, employeeModalClass.getTrn_id());
        contentValues.put(DatabaseHelperClass.NAME, employeeModalClass.getSupplier_name());
        contentValues.put(DatabaseHelperClass.SUP_ID, employeeModalClass.getSupplier_id());
        contentValues.put(DatabaseHelperClass.GREENTEA_QUA, employeeModalClass.getGreentea_qua());
        contentValues.put(DatabaseHelperClass.WATER_QUA, employeeModalClass.getWater_qua());
        contentValues.put(DatabaseHelperClass.TOTAL_TEA_QUA, employeeModalClass.getTotal_tea_qua());
        contentValues.put(DatabaseHelperClass.ADDITIONAL_QUA, employeeModalClass.getAdditional_qua());
        contentValues.put(DatabaseHelperClass.CASH_QUA, employeeModalClass.getCash_qua());
        contentValues.put(DatabaseHelperClass.WELFARE_QUA, employeeModalClass.getWelfare_qua());
        contentValues.put(DatabaseHelperClass.TRANSPORT_QUA, employeeModalClass.getTransport_qua());
        contentValues.put(DatabaseHelperClass.MT_QUA, employeeModalClass.getMt_qua());
        contentValues.put(DatabaseHelperClass.MANURE_QUA, employeeModalClass.getManure_qua());
        contentValues.put(DatabaseHelperClass.KOK_QUA, employeeModalClass.getKok_qua());
        contentValues.put(DatabaseHelperClass.OTHER_QUA, employeeModalClass.getOther_qua());

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
        String sql = "select * from " + TABLE_NAME +" order by TRN_ID desc";
        sqLiteDatabase = this.getReadableDatabase();
        List<EmployeeModalClass> storeEmployee = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {

                int id = Integer.parseInt(cursor.getString(0));
                String DATE = cursor.getString(1);
                String TRN_ID = cursor.getString(2);
                String NAME = cursor.getString(3);
                String SUP_ID = cursor.getString(4);
                String GREENTEA_QUA = cursor.getString(5);
                String WATER_QUA = cursor.getString(6);
                String TOTAL_TEA_QUA = cursor.getString(7);
                String ADDITIONAL_QUA = cursor.getString(8);
                String CASH_QUA = cursor.getString(10);
                String WELFARE_QUA = cursor.getString(9);
                String TRANSPORT_QUA = cursor.getString(11);
                String MT_QUA = cursor.getString(13);
                String MANURE_QUA = cursor.getString(12);
                String KOK_QUA = cursor.getString(14);
                String OTHER_QUA = cursor.getString(15);

                EmployeeModalClass employeeModalClass = new EmployeeModalClass();
                employeeModalClass.setSupp_date(DATE);
                employeeModalClass.setTrn_id(TRN_ID);
                employeeModalClass.setSupplier_name(NAME);
                employeeModalClass.setSupplier_id(SUP_ID);
                employeeModalClass.setGreentea_qua(GREENTEA_QUA);
                employeeModalClass.setWater_qua(WATER_QUA);
                employeeModalClass.setTotal_tea_qua(TOTAL_TEA_QUA);
                employeeModalClass.setAdditional_qua(ADDITIONAL_QUA);
                employeeModalClass.setCash_qua(CASH_QUA);
                employeeModalClass.setWelfare_qua(WELFARE_QUA);
                employeeModalClass.setTransport_qua(TRANSPORT_QUA);
                employeeModalClass.setManure_qua(MANURE_QUA);
                employeeModalClass.setMt_qua(MT_QUA);
                employeeModalClass.setKok_qua(KOK_QUA);
                employeeModalClass.setOther_qua(OTHER_QUA);

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

    public void updateRecord(String fieldName, String fieldValue, String quantity_gt, String quantity_water, String quantity_total_tea,
                             String quantity_ae,
                             String quantity_ca_deduction,
                             String quantity_wf_deduction,
                             String quantity_tp_deduction,
                             String quantity_mr_deduction,
                             String quantity_mt_deduction,
                             String quantity_kok_deduction,
                             String quantity_ot_deduction) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GREENTEA_QUA, quantity_gt);
        contentValues.put(WATER_QUA, quantity_water);
        contentValues.put(TOTAL_TEA_QUA, quantity_total_tea);
        contentValues.put(ADDITIONAL_QUA, quantity_ae);
        contentValues.put(CASH_QUA, quantity_ca_deduction);
        contentValues.put(WELFARE_QUA, quantity_wf_deduction);
        contentValues.put(TRANSPORT_QUA, quantity_tp_deduction);
        contentValues.put(MANURE_QUA, quantity_mr_deduction);
        contentValues.put(MT_QUA, quantity_mt_deduction);
        contentValues.put(KOK_QUA, quantity_kok_deduction);
        contentValues.put(OTHER_QUA, quantity_ot_deduction);

        sqLiteDatabase.update(TABLE_NAME, contentValues, TRN_ID + " = ?", new String[]{fieldValue});
        sqLiteDatabase.close();
    }

    public void deleteSupplier(String name) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, TRN_ID + " = ? ", new String[]{String.valueOf(name)});
    }
}
