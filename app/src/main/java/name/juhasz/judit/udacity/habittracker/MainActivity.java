package name.juhasz.judit.udacity.habittracker;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import name.juhasz.judit.udacity.habittracker.data.HabitContract.HabitEntry;
import name.juhasz.judit.udacity.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private TextView mDatabaseInfoDisplayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseInfoDisplayTextView = (TextView) findViewById(R.id.tv_database_info_display);

        HabitDbHelper databaseHelper = new HabitDbHelper(this);

        addTestHabitsToDatabase(databaseHelper);
        displayDatabaseContent(databaseHelper);
    }

    private void addTestHabitsToDatabase(HabitDbHelper databaseHelper) {
        databaseHelper.insertHabit("Cycling", 2017, "July", 1);
        databaseHelper.insertHabit("Cycling", 2017, "July", 2);
        databaseHelper.insertHabit("Cycling", 2017, "July", 3);
    }

    private void displayDatabaseContent(HabitDbHelper databaseHelper) {
        Cursor cursor = databaseHelper.readAllHabits();

        int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
        int yearColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DATE_YEAR);
        int monthColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DATE_MONTH);
        int dayColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DATE_DAY);

        String habitDatabaseContent = "";
        cursor.moveToFirst();
        do {
            int id = cursor.getInt(idColumnIndex);
            String name = cursor.getString(nameColumnIndex);
            int year = cursor.getInt(yearColumnIndex);
            String month = cursor.getString(monthColumnIndex);
            int day = cursor.getInt(dayColumnIndex);
            habitDatabaseContent = habitDatabaseContent + id + " " + name + " " +
                    year + " " + month + " " + day + "\n";
        } while (cursor.moveToNext());

        mDatabaseInfoDisplayTextView.setText(habitDatabaseContent);
    }
}
