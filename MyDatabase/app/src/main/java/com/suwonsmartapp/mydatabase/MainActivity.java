//Do it 안드로이드 롤리팝 Day31 01(youtube)
package com.suwonsmartapp.mydatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    EditText editText;
    EditText editText2;
    TextView textView;

    String databaseName;
    SQLiteDatabase database;

    CustomerDatabaseHelper databaseHelper;

    String tableName;

    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        textView = (TextView) findViewById(R.id.textView);

        listView = (ListView) findViewById(R.id.listView);
    }

    public void onButton1Clicked(View v) {
        databaseName = editText.getText().toString();


        try {
            //database = openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);

            databaseHelper = new CustomerDatabaseHelper(getApplicationContext(),databaseName, null, 2);
            database = databaseHelper.getWritableDatabase();


            println("데이터베이스를 열었습니다. : " + databaseName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onButton2Clicked(View v) {
        createTable(database);
    }

    public void createTable(SQLiteDatabase db) {
        tableName = editText2.getText().toString();

        try {
            if (db != null) {
                db.execSQL("CREATE TABLE if not exists " + tableName + "("
                        + "_id integer PRIMARY KEY autoincrement, "
                        + "name text, "
                        + "age integer, "
                        + "mobile  text"
                        + ")");
                println("데이터베이스를 만들었습니다. : " + tableName);
            } else {
                println("데이터베이스를 먼저 열어야 합니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeTable(SQLiteDatabase db) {
        try {
            if (db != null) {
                db.execSQL("CREATE TABLE if not exists " + "PRODUCT" + "("
                        + "_id integer PRIMARY KEY autoincrement, "
                        + "name text, "
                        + "price integer"
                        + ")");
                println("데이터베이스를 추가로 만들었습니다. : " + "PRODUCT");
            } else {
                println("데이터베이스를 먼저 열어야 합니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onButton3Clicked(View v) {

        try {

            if (tableName == null) {
                tableName = editText2.getText().toString();

            }

            if (database != null) {
                database.execSQL("INSERT INTO " + tableName + "(name, age, mobile) VALUES"
                        + "('소녀시대', 20, '010-1000-1000')");

                println("데이터를 추가했습니다. : ");
            } else {
                println("데이터베이스를 먼저 열어야 합니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onButton4Clicked(View v) {
        try {


            if (tableName == null) {
                tableName = editText2.getText().toString();

            }

            if (database != null) {
                Cursor cursor = database.rawQuery("SELECT _id, name, age, mobile FROM " + tableName, null);

                startManagingCursor(cursor);

                String[] columns = new String[]{"name", "age", "mobile"};
                int[] to = new int[]{R.id.textView2, R.id.textView3, R.id.textView4};

                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.customer_item, cursor, columns, to);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


                int count = cursor.getCount();
                println("결과 레코드의갯수 : " + count);

                for (int i = 0; i < count; i++) {
                    cursor.moveToNext();
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    int age = cursor.getInt(2);
                    String mobile = cursor.getString(3);

                    println("레코드 #" + id + " : " + name + ", " + age + ", " + mobile);
                }

                cursor.close();

                println("데이터를 조회했습니다.");

            } else {
                println("데이터베이스를 먼저 열어야 합니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void println(String data) {
        textView.append(data + "\n");

    }

    class CustomerDatabaseHelper extends SQLiteOpenHelper {


        CustomerDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);

            Toast.makeText(getApplicationContext(), "Helper의 onOpen() 호출됨.", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Toast.makeText(getApplicationContext(), "Helper의 onCreate() 호출됨.", Toast.LENGTH_LONG).show();

            createTable(db);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Toast.makeText(getApplicationContext(), "Helper의 onUpgrade() 호출됨. : " + oldVersion + " -> " + newVersion, Toast.LENGTH_LONG).show();

            changeTable(db);


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
