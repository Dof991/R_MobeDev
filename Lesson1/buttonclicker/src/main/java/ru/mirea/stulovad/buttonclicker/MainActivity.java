package ru.mirea.stulovad.buttonclicker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView textViewStudent;
    private Button btnWhoAmI;
    private CheckBox chbWhoAmI;
    private CheckBox chbItsNotMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        textViewStudent = findViewById(R.id.tvOut);
        btnWhoAmI = findViewById(R.id.btnWhoAmI);
        chbWhoAmI = findViewById(R.id.chbWhoAmI);
        chbItsNotMe = findViewById(R.id.chbItsNotMe);

        View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewStudent.setText("Мой номер по списку №23");
                chbWhoAmI.setChecked(true);
                chbItsNotMe.setChecked(false);
            }
        };
        btnWhoAmI.setOnClickListener(oclBtnWhoAmI);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public void onItsNotMeClick(View view)
    {
        textViewStudent.setText("Это был не я");
        chbWhoAmI.setChecked(false);
        chbItsNotMe.setChecked(true);
    }
}