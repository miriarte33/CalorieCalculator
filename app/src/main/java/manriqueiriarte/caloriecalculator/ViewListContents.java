package manriqueiriarte.caloriecalculator;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ViewListContents extends AppCompatActivity {

    DataBaseHelper myDB;
    ArrayList<User> userList;
    ListView listView;
    User user;
    Button deleteButton;
    EditText enterNameEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        myDB = new DataBaseHelper(this);

        deleteButton = findViewById(R.id.deleteEntryButton);
        enterNameEditText = findViewById(R.id.enterNameEditText);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.deleteProduct(enterNameEditText.getText().toString());
                startActivity(new Intent(ViewListContents.this, profile.class));
            }
        });

        userList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        int numRows = data.getCount();
        if(numRows == 0){
            Toast.makeText(ViewListContents.this,"The Database is empty.",Toast.LENGTH_LONG).show();
        }else{
            int i=0;
            while(data.moveToNext()) {
                user = new User(data.getString(0),data.getInt(1),data.getInt(2), data.getDouble(3));
                userList.add(i, user);
                //System.out.println(data.getString(1)+" "+data.getString(2)+" "+data.getString(3));
                //System.out.println(userList.get(i).getFirstName());
                i++;
            }
            FourColumn_ListAdapter adapter =  new FourColumn_ListAdapter(this,R.layout.list_adapter_view, userList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(ViewListContents.this, CaloriesGraph.class);
                    intent.putExtra("DailyCaloricIntake", listView.getItemAtPosition(i).toString()); //get the listView item we clicked at's dailyCaloricIntake
                    startActivity(intent);
                }
            });
            listView.setAdapter(adapter);
        }
    }


}