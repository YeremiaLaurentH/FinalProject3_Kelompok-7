package com.example.finalproject3_kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView angkaTv,hasilTv;
    MaterialButton buttonC,buttonKurungBuka,buttonKurungTutup;
    MaterialButton buttonBagi,buttonKali,buttonKurang,buttonTambah,buttonSamaDengan;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        angkaTv = findViewById(R.id.angka_tv);
        hasilTv = findViewById(R.id.hasil_tv);

        assignId(buttonC,R.id.button_C);
        assignId(buttonKurungBuka,R.id.button_kurungbuka);
        assignId(buttonKurungTutup,R.id.button_kurungTutup);
        assignId(buttonBagi,R.id.button_bagi);
        assignId(buttonKali,R.id.button_kali);
        assignId(buttonKurang,R.id.button_kurang);
        assignId(buttonTambah,R.id.button_tambah);
        assignId(buttonSamaDengan,R.id.button_samadengan);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonAC,R.id.button_ac);
        assignId(buttonDot,R.id.button_dot);

    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = angkaTv.getText().toString();

        if (buttonText.equals("AC")){
            angkaTv.setText("");
            hasilTv.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            angkaTv.setText(hasilTv.getText());
            return;
        }
        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        angkaTv.setText(dataToCalculate);

        String finalHasil = getHasil(dataToCalculate);

        if (!finalHasil.equals("Err")){
            hasilTv.setText(finalHasil);
        }

    }

    String getHasil(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalHasil = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalHasil.endsWith(".0")){
                finalHasil = finalHasil.replace(".0","");
            }
            return finalHasil;
        }catch (Exception e){
            return "Err";
        }
    }
}