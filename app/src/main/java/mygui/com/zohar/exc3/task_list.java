package mygui.com.zohar.exc3;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class task_list extends AppCompatActivity {


    ListView listView; // in order to access the list view in the xml page
    ArrayList<String> itemList=new ArrayList<>();
    MyCustomAdapter adapter;
    private EditText textInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        DataClass.flag=0;
        listView=(ListView)findViewById(R.id.L1);
        DataClass.listPref=getSharedPreferences(DataClass.DATA,MODE_PRIVATE);//access to database
        DataClass.init(this);//add the milk and eggs
        showItems();



    }


    public  void showItems()//shows the item from DB  in the list
    {
        int counter=DataClass.listPref.getInt("counter",2);//if there r two items
        int first=DataClass.listPref.getInt("first",0);//and this is the first time we open the app w'll show the milk and eggs in the item
        if(first==0)
        {
            Log.d("TKT", "add milk and eggs");
            for (int i = 0; i < counter; i++) {
                Log.d("TKT", counter + "");
                itemList.add(DataClass.listPref.getString(String.valueOf(i), "k"));
                // adapter = /*new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemList);*/
                adapter = new MyCustomAdapter(itemList, this);
                listView.setAdapter(adapter);

            }
        }
        else
        {


            if(DataClass.listPref.getString("0",null)!=null)
            {
                for(int i=2; i<counter; i++) {
                    itemList.add(DataClass.listPref.getString(String.valueOf(i),"k"));
                }
            }
            adapter =new MyCustomAdapter(itemList, this); /*new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemList);*/
            listView.setAdapter(adapter);

        }

    }


    public void hide()
    {
        InputMethodManager inputMethodManager=(InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);// hide keyboard
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
    }



    public void addTask(View view)
    {
        DataClass.listEditor=DataClass.listPref.edit();
        int counter=DataClass.listPref.getInt("counter",2);
      //  Log.d("TKT","counter addItem: "+counter);
        String temp="";
        EditText editText=(EditText)findViewById(R.id.E1);
        temp=editText.getText().toString();// to get the input from  the user
        itemList.add(temp);
        adapter =new MyCustomAdapter(itemList, this);
        listView.setAdapter(adapter);
        hide();
        DataClass.listEditor.putString(Integer.toString(counter),temp);
        counter++;
        //Log.d("TKT","counter: "+counter);
        DataClass.listEditor.putInt("counter", counter); //to save the current counter in DB
        DataClass.listEditor.commit();//save changes in DB
        editText.setText("");// to clear the edittext after item was added



    }




   // public void addTask(View view){
     //   Intent intent=new Intent(this,create_task.class);//to start another activity
     //   startActivity(intent);

   // }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_list, menu);
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
