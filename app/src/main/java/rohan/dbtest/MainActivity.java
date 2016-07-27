package rohan.dbtest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText usn,name;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name= (EditText) findViewById(R.id.name);
        usn= (EditText) findViewById(R.id.usn);
        textView = (TextView) findViewById(R.id.text);

        db = openOrCreateDatabase("TestDB",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS students(NAME VARCHAR,USN VARCHAR);");

    }

    public void insert(View view) {

        String query = "INSERT INTO students VALUES('"+name.getText().toString()+"','"+usn.getText().toString()+"')";
        db.execSQL(query);

    }

    public void delete(View view) {
        db.delete("students","USN='"+usn.getText().toString()+"'",null);
    }

    public void get(View view) {
        String result = "";
        Cursor c = db.rawQuery("SELECT * FROM students",null);

        c.moveToFirst();



        do {
            result += c.getString(0)+":"+c.getString(1)+"\n";
        }while (c.moveToNext());

        textView.setText(result);
    }
}
