package com.example.dcornea.accountingpriceapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
/*
    *  Checks if questions are correct and displays the score
    *  @param view
    *  @return void
*/
    public void submit_button (View view){
        int score;
        RadioButton tmp_button;
        EditText tmp_view;
        String tmp_string;
        CheckBox tmp_checkbox0;
        CheckBox tmp_checkbox1;
        CheckBox tmp_checkbox2;
        score = 0;
        //first_question
        tmp_button = (RadioButton) findViewById(R.id.econ_goods);
        if (tmp_button.isChecked()){
            score++;
        }
        //second_question
        tmp_view = (EditText) findViewById(R.id.smith);
        tmp_string = tmp_view.getText().toString();
        if (tmp_string.equalsIgnoreCase("Smith")) {
            score++;
        }
        //third_question
        tmp_checkbox0 = (CheckBox) findViewById(R.id.father_marx);
        tmp_checkbox1 = (CheckBox) findViewById(R.id.father_engels);
        tmp_checkbox2 = (CheckBox) findViewById(R.id.father_lenin);
        if(tmp_checkbox0.isChecked() && tmp_checkbox1.isChecked() && !tmp_checkbox2.isChecked()){
            score++;
        }
        //fourth_question
        tmp_button = (RadioButton) findViewById(R.id.smith_hand);
        if (tmp_button.isChecked()){
            score++;
        }
        //fifth_question
        tmp_button = (RadioButton) findViewById(R.id.cycle_4560);
        if (tmp_button.isChecked()){
            score++;
        }
        //sixth_question
        tmp_button = (RadioButton) findViewById(R.id.soviet_com);
        if (tmp_button.isChecked()){
            score++;
        }
        //seventh_question
        tmp_view = (EditText) findViewById(R.id.seventh);
        tmp_string = tmp_view.getText().toString();
        if (tmp_string.equalsIgnoreCase("Austrian")) {
            score++;
        }
        //eighth_question
        tmp_button = (RadioButton) findViewById(R.id.com_soc_no);
        if (tmp_button.isChecked()){
            score++;
        }

        if (score > 0){
            Toast.makeText(getApplicationContext(), "Your score is ".concat(Integer.toString(score)), Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Mate, go sudy, your score is ".concat(Integer.toString(score)), Toast.LENGTH_LONG).show();
        }

    }


}
