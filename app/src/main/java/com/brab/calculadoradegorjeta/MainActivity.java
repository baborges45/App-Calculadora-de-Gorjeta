package com.brab.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editValor;
    private TextView textPorcentagem;
    private TextView textGorjeta;
    private TextView textTotal;
    private TextView textTotalDividido;
    private EditText eidtDividirConta;
    private SeekBar seekBarGorjeta;

    private double porcentagem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValor         = findViewById(R.id.editValor);
        eidtDividirConta  = findViewById(R.id.editDividirConta);
        textGorjeta       = findViewById(R.id.textGorjeta);
        textPorcentagem   = findViewById(R.id.textPorcentagem);
        textTotal         = findViewById(R.id.textTotal);
        textTotalDividido = findViewById(R.id.textTotalDividido);
        seekBarGorjeta    = findViewById(R.id.seekBarGorjeta);

        //Adicionar Listener SeekBar
        seekBarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                porcentagem = progress;
                textPorcentagem.setText( Math.round( porcentagem ) + " %");
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public  void calcular(){

        String valorRecuperado = editValor.getText().toString();
        String valorRecuperado2 = eidtDividirConta.getText().toString();
        if (valorRecuperado.isEmpty() || valorRecuperado2.isEmpty()){

            Toast.makeText(
                    getApplicationContext(),
                    "Digite o valor da conta!",
                    Toast.LENGTH_SHORT
            ).show();
        }else {

            //primeiro converte String para double
            double valorDigitado = Double.parseDouble(valorRecuperado);
            double quantDividirConta = Double.parseDouble(valorRecuperado2);
            //calcular a gorjeta total
            double gorjeta = valorDigitado * (porcentagem / 100);
            double total = gorjeta + valorDigitado;
            double total2 = total / quantDividirConta ;
            textGorjeta.setText("R$ " + gorjeta);
            textTotal.setText("R$ " + total);
            textTotalDividido.setText("R$ " + total2);
//        }if (valorRecuperado2 == null || valorRecuperado2.isEmpty()){
//            double valorDigitado = Double.parseDouble(valorRecuperado);
//            double quantDividirConta = Double.parseDouble(valorRecuperado);
//            //calcular a gorjeta total
//            double gorjeta = valorDigitado * (porcentagem / 100);
//            double total = gorjeta + valorDigitado;
//            double total2 = (gorjeta + valorDigitado) / quantDividirConta ;
//            textGorjeta.setText("R$ " + gorjeta);
//            textTotal.setText("R$ " + total);
//            textTotalDividido.setText("R$ " + total2);
        }
    }
}