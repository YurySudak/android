package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    int day;
    int month;
    int year;
    List<String> notesList = new ArrayList<>();
    CalendarView calendarView;
    Button buttonYury;
    Button buttonNatasha;
    Button buttonInput;

    public void toInput(View view) {
        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataService dataService = new DataService(this);


        buttonYury = findViewById(R.id.buttonYura);
        buttonNatasha = findViewById(R.id.buttonNatasha);
        buttonInput = findViewById(R.id.buttonInput);


        buttonYury.setActivated(false);

        Calendar calendar = Calendar.getInstance();
        MainActivity.this.month = calendar.get(Calendar.MONTH) + 1;
        MainActivity.this.year = calendar.get(Calendar.YEAR);

        dataService.getData(notesList, () -> recyclerView.getAdapter().notifyDataSetChanged(),
                day, month, year);

        calendarView = (CalendarView) findViewById(R.id.simpleCalendarView);
        calendarView.setOnDateChangeListener((view, y, m, d) -> {
            day = d;
            month = m + 1;
            year = y;
            dataService.getData(notesList, () -> recyclerView.getAdapter().notifyDataSetChanged(),
                    day, month, year);
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(notesList);
//       recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }




    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            Collections.swap(notesList, fromPosition, toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
