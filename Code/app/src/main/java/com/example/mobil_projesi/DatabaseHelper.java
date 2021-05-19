package com.example.mobil_projesi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

        public class DatabaseHelper extends SQLiteOpenHelper {
            public static final String DB_NAME = "MobilUygulamaDatabase4";
            public static final String TABLE_NAME = "Users";
            public static final String COLUMN_ID = "id";
            public static final String COLUMN_USER_NAME = "username";
            public static final String COLUMN_FIRST_NAME = "firstname";
            public static final String COLUMN_LAST_NAME = "LastName";
            public static final String COLUMN_PASSWORD = "Password";
            public static final String COLUMN_EMAIL = "Email";
            public static final String COLUMN_ADDRESS = "Address";
            public static final String COLUMN_USER_ROLE = "UserRole";
            public static final String COLUMN_USER_WALLET ="Wallet";
            public Long newId;
            /*-----------------------------------------------------UserROLES TABLE----------------------------------------------*/
            public static final String USER_ROLE_TABLE = "UserRoles";
            public static final String COLUMN_USERID = "UserId";
            public static final String COLUMN_ROLEID = "RoleId";
            /*-----------------------------------------------------Buyer Request Balance----------------------------------------*/
            public static final String BUYER_REQUEST_TABLE = "BuyerRequestBalance";
            /*-----------------------------------------------------StatueId----------------------------------------------------*/
            public static final String BUYER_REQUEST_StatueId = "BuyerRequestBalanceStatue";
            Long newBuyerReqId;
            /*---------------------------------------------------------------------------------------------------------------*/
            public static final String SELLER_REQUEST_TABLE = "SellerRequest";
            /*---------------------------------------------------------------------------------------------------------------*/

            public static final int DB_VERSION = 1;

            public DatabaseHelper(Context context){
                super(context, DB_NAME , null, DB_VERSION);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                String query = "CREATE TABLE " + TABLE_NAME + " ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_USER_NAME+" VARCHAR, " +COLUMN_FIRST_NAME+" VARCHAR, " +COLUMN_LAST_NAME+ " VARCHAR, " +COLUMN_PASSWORD+ " VARCHAR,"
                        +COLUMN_EMAIL+" VARCHAR, "+COLUMN_ADDRESS+" VARCHAR, "+COLUMN_USER_ROLE+" VARCHAR, "+COLUMN_USER_WALLET+" FLOAT )";
                db.execSQL(query);
                String query2 = "create table Role (ID INTEGER PRIMARY KEY AUTOINCREMENT, UserType VARCHAR)";
                db.execSQL(query2);
                String query3 = "create table "+USER_ROLE_TABLE+" (RoleId INTEGER , UserId INTEGER, FOREIGN KEY(UserId) REFERENCES Users(id),FOREIGN KEY(RoleId) REFERENCES Role(ID))";
                db.execSQL(query3);
                String query4 = "create table "+BUYER_REQUEST_StatueId+" (Id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR)";
                db.execSQL(query4);
                String query5 = "create table "+BUYER_REQUEST_TABLE+" (Id INTEGER PRIMARY KEY AUTOINCREMENT ,UserId INTEGER , MsgFromAdmin VARCHAR, StatueId INTEGER, RequestedBalance FLOAT," +
                        " FOREIGN KEY(UserId) REFERENCES Users(id), FOREIGN KEY(StatueId) REFERENCES BuyerRequestBalance(Id))";
                db.execSQL(query5);


            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

                String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
                String query2 = "DROP TABLE IF EXISTS " + USER_ROLE_TABLE;
                String query3 = "DROP TABLE IF EXISTS " + BUYER_REQUEST_TABLE;
                String query4 = "DROP TABLE IF EXISTS " + BUYER_REQUEST_StatueId;
                db.execSQL(query);
                db.execSQL(query2);
                db.execSQL(query3);
                db.execSQL(query4);
                onCreate(db);
            }


            public boolean addUser(String name,  String firstName , String lastName , String password, String email, String address, String role, float wallet){
                SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_FIRST_NAME , firstName );
        values.put(COLUMN_LAST_NAME, lastName );
        values.put(COLUMN_PASSWORD, password );
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_USER_ROLE, role);
        values.put(COLUMN_USER_WALLET, wallet);
        newId = sqLiteDatabase.insert(TABLE_NAME , null,values);
        if(newId  == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean AddUserRole(int RoleId){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("RoleId" , RoleId);
        values.put("UserId" , newId);
        return db.insert(USER_ROLE_TABLE, null, values) != -1;
    }
    public boolean checkUsername  (String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " +TABLE_NAME+" where " +COLUMN_USER_NAME+" = ? ", new String[] {username});

        if(cursor.getCount() >0){
            return true;
        }
        else{
            return false;
        }
    }
    public String getUserType (String userName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor CR = db.rawQuery("select UserRole from "+TABLE_NAME+" where "+COLUMN_USER_NAME+" = ?", new String[] {userName});
        String s = "Not Found";
        if(CR.moveToFirst()){
            s = CR.getString(CR.getColumnIndex(COLUMN_USER_ROLE));
        }

        return s;

    }
    public boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " +TABLE_NAME+" where "+COLUMN_USER_NAME+" = ? and "+COLUMN_PASSWORD+" = ? ", new String[] {username,password});

        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean AddBuyerRequest(float Balance , int userId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("RequestedBalance", Balance);
        values.put("StatueId" , 3);
        values.put("UserId", userId);
        values.put("MsgFromAdmin","null");
        newBuyerReqId = db.insert(BUYER_REQUEST_TABLE , null,values);
        if(newBuyerReqId  == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public int getUserId(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor CR = db.rawQuery("select id from "+TABLE_NAME+" where "+COLUMN_USER_NAME+" = ?", new String[] {username});
        int s = 0;
        if(CR.moveToFirst()){
            s = CR.getInt(CR.getColumnIndex(COLUMN_ID));
        }
        return s;
    }
   // public float
}
