package com.example.opet.exercicio_28_03_18;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends Activity {

    private EditText projectName;
    private TextView projectStart;
    private TextView projectEnd;
    private CheckBox projectEnded;
    private TextView projectStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        projectName = findViewById(R.id.txtProjectName);
        projectStart = findViewById(R.id.dateprojectStart);
        projectEnd = findViewById(R.id.dateprojectEnd);
        projectEnded = findViewById(R.id.checkboxProjectEnded);
        projectStatus = findViewById(R.id.txtProjectStatus);

        final Calendar startDate = Calendar.getInstance();
        final Calendar endDate = Calendar.getInstance();
        final Calendar today = Calendar.getInstance();

        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        final DatePickerDialog.OnDateSetListener startDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                startDate.set(Calendar.YEAR, year);
                startDate.set(Calendar.MONTH, monthOfYear);
                startDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                projectStart.setText(format.format(startDate.getTime()));
            }
        };

        final DatePickerDialog.OnDateSetListener endDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                endDate.set(Calendar.YEAR, year);
                endDate.set(Calendar.MONTH, monthOfYear);
                endDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                projectEnd.setText(format.format(endDate.getTime()));
            }
        };

        projectStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, startDateListener,
                        startDate.get(Calendar.YEAR),
                        startDate.get(Calendar.MONTH),
                        startDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        projectEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, endDateListener,
                        endDate.get(Calendar.YEAR),
                        endDate.get(Calendar.MONTH),
                        endDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        projectEnded.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                updateProjectStatus(today, startDate, endDate, projectEnded.isChecked());
            }
        });

        projectEnded.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if(today.before(startDate)) {
                        Toast.makeText(MainActivity.this, "Você não pode finalizar um projeto antes dele iniciar!", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }

                return false;
            }
        });
    }

    public void updateProjectStatus(Calendar today, Calendar startDate, Calendar endDate, Boolean isEnded) {
        if(startDate.before(today) && endDate.after(today)) {
            if(isEnded) {
                projectStatus.setText("FINALIZADO DENTRO DO PRAZO");
            } else {
                projectStatus.setText("DENTRO DO PRAZO");
            }
        } else if(startDate.after(today)) {
            projectStatus.setText("AINDA NÃO FOI INICIADO");
        } else if(endDate.before(today)) {
            if(isEnded) {
                projectStatus.setText("FINALIZADO APÓS O PRAZO");
            } else {
                projectStatus.setText("ATRASADO");
            }
        }
    }
}
