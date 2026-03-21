package ru.mirea.stulovad.dialog;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnForSnack), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onClickShowDialog (View view) {
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "meow");
    }

    public void onOkClicked(String variantText) {
        Toast.makeText(getApplicationContext(), String.format("Вы выбрали кнопку \"%s\"!", variantText),
                Toast.LENGTH_LONG).show();
    }
    public void onCancelClicked(String variantText) {
        Toast.makeText(getApplicationContext(), String.format("Вы выбрали кнопку \"%s\"!", variantText),
                Toast.LENGTH_LONG).show();
    }
    public void onNeutralClicked(String variantText) {
        Toast.makeText(getApplicationContext(), String.format("Вы выбрали кнопку \"%s\"!", variantText),
                Toast.LENGTH_LONG).show();
    }

    public  void makeSnackbar(View view){
        Snackbar snackbar = Snackbar.make(view, "Это снэкбар", Snackbar.LENGTH_LONG);

        snackbar.setAction("Жмяк", new View.OnClickListener (){
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Кнопка снэкбара жмякнута",Toast.LENGTH_LONG);
                toast.show();
            }
        });
        snackbar.show();
    }

    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        }
    };

    public void onClickShowTimeDialog (View view) {
        // TimeDialogFragment dialogFragment = new TimeDialogFragment();
        // dialogFragment.show(getSupportFragmentManager(), "time");

        new TimePickerDialog(MainActivity.this, t,
                0,
                0, true)
                .show();
    }

    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        }
    };

    public void onClickShowDateDialog (View view) {
        // AlertDialogFragment dialogFragment = new AlertDialogFragment();
        // dialogFragment.show(getSupportFragmentManager(), "date");

        new DatePickerDialog(MainActivity.this, d,
                2026,
                3,
                6)
                .show();
    }


    public void onClickShowProgressDialog (View view) {
        // ProgressDialogFragment dialogFragment = new ProgressDialogFragment();
        // dialogFragment.show(getSupportFragmentManager(), "progress");

        ProgressDialog progress = new ProgressDialog(MainActivity.this);
        progress.setTitle("Загрузка");
        progress.setMessage("Качаем вирусы");
        progress.show();

    }



}