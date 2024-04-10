package es.ieslavereda.calculadora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView display;
    private Button sumButton;
    private Button clear;
    private Button resolve;
    private Button restButton;
    private Button multButton;
    private Button divButton;
    private Button pointButton;
    private Button deleteButton;
    private CheckBox disabler;
    private RadioGroup options;
    private Double operator;
    private Operacion operation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        sumButton = findViewById(R.id.sumButton);
        clear = findViewById(R.id.clear);
        resolve = findViewById(R.id.resolve);
        deleteButton = findViewById(R.id.deleteButton);
        restButton = findViewById(R.id.restButton);
        multButton = findViewById(R.id.multButton);
        divButton = findViewById(R.id.divButton);
        pointButton = findViewById(R.id.pointButton);
        disabler = findViewById(R.id.showOpt);
        options = findViewById(R.id.disOptions);

        if (savedInstanceState != null){
            operation = (Operacion) savedInstanceState.getSerializable("operation");
            operator = (Double) savedInstanceState.getSerializable("operator");
            if((boolean) savedInstanceState.getSerializable("checked")){
                   options.setVisibility(View.VISIBLE);
            }else{
                options.setVisibility(View.GONE);
            }
        }
        else{
            options.setVisibility(View.GONE);
        }

        pointButton.setOnClickListener( view->{
            display.setText(display.getText().toString()+".");
        });

        sumButton.setOnClickListener(view->{
            operator = Double.parseDouble(display.getText().toString());
            display.setText("0");
            operation = Operacion.SUMA;
        });

        restButton.setOnClickListener( view->{
            operator = Double.parseDouble(display.getText().toString());
            display.setText("0");
            operation = Operacion.RESTA;
        });

        divButton.setOnClickListener( view->{
            operator = Double.parseDouble(display.getText().toString());
            display.setText("0");
            operation = Operacion.DIVISION;
        });

        multButton.setOnClickListener( view->{
            operator = Double.parseDouble(display.getText().toString());
            display.setText("0");
            operation = Operacion.MULTIPLICACION;
        });

        clear.setOnClickListener( view->{
            display.setText("0");
            operator = 0.0;
        });
        deleteButton.setOnClickListener( view->{
            display.setText(display.getText().toString().substring(0,display.getText().length()-1));
        });

        resolve.setOnClickListener( view ->{
            double resultado;
            if(operation == Operacion.SUMA) {
                resultado = operator + Double.parseDouble(display.getText().toString());
            } else if (operation == Operacion.RESTA) {
                resultado = operator - Double.parseDouble(display.getText().toString());
            } else if (operation == Operacion.DIVISION) {
                resultado = operator / Double.parseDouble(display.getText().toString());
            } else if (operation == Operacion.MULTIPLICACION) {
                resultado = operator * Double.parseDouble(display.getText().toString());
            }else{
                resultado = 0;
            }
            operator = Double.parseDouble(display.getText().toString());
            display.setText(String.valueOf(resultado));
        });

        disabler.setOnClickListener(view -> {
            if(disabler.isChecked()){
                options.setVisibility(View.VISIBLE);
            }
            else{
                options.setVisibility(View.GONE);
            }
        });
        options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.hideDiv) {
                    sumButton.setVisibility(View.VISIBLE);
                    divButton.setVisibility(View.INVISIBLE);
                    multButton.setVisibility(View.VISIBLE);
                    restButton.setVisibility(View.VISIBLE);
                } else if (i == R.id.hideMult) {
                    sumButton.setVisibility(View.VISIBLE);
                    divButton.setVisibility(View.VISIBLE);
                    multButton.setVisibility(View.INVISIBLE);
                    restButton.setVisibility(View.VISIBLE);
                } else if (i == R.id.hideSum) {
                    sumButton.setVisibility(View.INVISIBLE);
                    divButton.setVisibility(View.VISIBLE);
                    multButton.setVisibility(View.VISIBLE);
                    restButton.setVisibility(View.VISIBLE);
                } else if (i == R.id.hideRest) {
                    sumButton.setVisibility(View.VISIBLE);
                    divButton.setVisibility(View.VISIBLE);
                    multButton.setVisibility(View.VISIBLE);
                    restButton.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
    @Override
    public void onClick( View view) {
        if(display.getText().toString().equals("0")){
            display.setText(((Button)view).getTag().toString());
        }
        else{
            display.setText(display.getText().toString()+((Button)view).getText());
        }
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("operator", operator);
        outState.putSerializable("operation", operation);
        outState.putSerializable("checked",disabler.isChecked());
    }

}