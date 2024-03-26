package es.ieslavereda.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView display;
    private Button plusButton;
    private Button clear;
    private Button resolve;
    private Button restButton;
    private Button multButton;
    private Button divButton;
    private Double operador;
    private Operacion operacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
        plusButton = findViewById(R.id.plusButton);
        clear = findViewById(R.id.clear);
        resolve = findViewById(R.id.resolve);
        restButton = findViewById(R.id.restButton);
        multButton = findViewById(R.id.multButton);
        divButton = findViewById(R.id.divButton);

        plusButton.setOnClickListener( view->{
            operador = Double.parseDouble(display.getText().toString());
            display.setText("0");
            operacion = Operacion.SUMA;
        });

        restButton.setOnClickListener( view->{
            operador = Double.parseDouble(display.getText().toString());
            display.setText("0");
            operacion = Operacion.RESTA;
        });

        divButton.setOnClickListener( view->{
            operador = Double.parseDouble(display.getText().toString());
            display.setText("0");
            operacion = Operacion.DIVISION;
        });

        multButton.setOnClickListener( view->{
            operador = Double.parseDouble(display.getText().toString());
            display.setText("0");
            operacion = Operacion.MULTIPLICACION;
        });

        clear.setOnClickListener( view->{
            display.setText("0");
        });

        resolve.setOnClickListener( view ->{
            double resultado;
            if(operacion == Operacion.SUMA) {
                resultado = operador + Double.parseDouble(display.getText().toString());
            } else if (operacion == Operacion.RESTA) {
                resultado = operador - Double.parseDouble(display.getText().toString());
            } else if (operacion == Operacion.DIVISION) {
                resultado = operador / Double.parseDouble(display.getText().toString());
            } else if (operacion == Operacion.MULTIPLICACION) {
                resultado = operador * Double.parseDouble(display.getText().toString());
            }else{
                resultado = 0;
            }
            display.setText(String.valueOf(resultado));
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

}