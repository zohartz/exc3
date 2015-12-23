package mygui.com.zohar.exc3;


        import android.content.Context;
        import android.content.SharedPreferences;
        import android.util.Log;
        import android.widget.TextView;



public class DataClass  {

    public static final String DATA="dataFile";
    public static SharedPreferences listPref;
    public static SharedPreferences.Editor listEditor;
    public static int counter=-1;
    public static int flag=0;

    public static void init(Context context) {


        listEditor=listPref.edit();//we need to edit the database
        // listEditor.commit();
        //Log.d("TKT", "counter: " + counter);

        // if(listPref.getInt("first",0)==0)
        {
            if (listPref.getString("2", null) == null)//returns what inside of eggs otherwise will return null
            {
                Log.d("TKT", "counter: " + counter);
                listEditor.putString("0", context.getString(R.string.findjob));
                listEditor.putString("1", context.getString(R.string.fixroom));
                counter = 2;
                Log.d("TKT", "counter: " + counter);
                listEditor.putInt("counter", 2);
                listEditor.commit();
            }
        }

    }



}
