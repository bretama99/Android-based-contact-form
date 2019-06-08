
package br.register1;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText etname,etsurname,etmarks,etid;
    Button etadd,etupdate,etdelete;
    Button etView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
        etname = (EditText) findViewById(R.id.name);
        etsurname = (EditText) findViewById(R.id.surname);
        etmarks = (EditText) findViewById(R.id.marks);
        etid = (EditText) findViewById(R.id.id);
        etadd = (Button) findViewById(R.id.button1);
        etView=(Button) findViewById(R.id.button2);
        etupdate = (Button) findViewById(R.id.button3);
        etdelete = (Button) findViewById(R.id.button4);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }
    public void DeleteData(){
        etdelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer isdeleted = mydb.Delete(etid.getText().toString());
                        if (isdeleted > 0){
                            Toast.makeText(MainActivity.this,"data deleted",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(MainActivity.this,"data is not deleted",Toast.LENGTH_LONG).show();
                    }

                        }

                }
        );
    }
    public void UpdateData(){
        etupdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isupdate = mydb.Update(etid.getText().toString(),etname.getText().toString(),etsurname.getText().toString(),etmarks.getText().toString());
                        if (isupdate==true){
                            Toast.makeText(MainActivity.this,"data updateed",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(MainActivity.this,"data is not updated",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
    public void AddData(){
        etadd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isinserted = mydb.insertData(etname.getText().toString(),etsurname.getText().toString(),etmarks.getText().toString());
                        if (isinserted==true){
                            Toast.makeText(MainActivity.this,"data inserted",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(MainActivity.this,"data is not inserted",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
    public void viewAll(){
        etView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = mydb.getAllData();if (res.getCount()==0){
                            showmessage("Error","Nothing is found");
                            return;
                        }

                            StringBuffer buffer = new StringBuffer();
                            while (res.moveToNext()){
                                buffer.append("ቁፅሪ:"+ res.getString(0)+"\n");
                                buffer.append("ስም:"+ res.getString(1)+"\n");
                                buffer.append("ስም_ኣቦ:"+ res.getString(2)+"\n");
                                buffer.append("ስልኪ_ቑፅሪ:"+ res.getString(3)+"\n");

                        }
                        showmessage("ፍሬንድ",buffer.toString());
                    }
                }
        );
    }
    public void showmessage(String Title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(message);
        builder.show();
    }
}
