package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    TextView firstScreen, secondScreen;
    private MaterialButton btn1, btn2, btn3, btn4 ,btn5 ,btn6, btn7, btn8, btn9, btn0;
    private MaterialButton equal, plus, minus, multiply, divide, clear, clearAll;
    private String input, history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstScreen = findViewById(R.id.firstScreen);
        secondScreen = findViewById(R.id.secondScreen);

        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btn0 = findViewById(R.id.btn_0);

        equal = findViewById(R.id.btn_equal);
        plus = findViewById(R.id.btn_plus);
        minus = findViewById(R.id.btn_minus);
        multiply = findViewById(R.id.btn_multiply);
        divide = findViewById(R.id.btn_divide);
        clear = findViewById(R.id.btn_clear);
        clearAll = findViewById(R.id.btn_clearAll);

    }

    public void onDigitButton(View view) {

        MaterialButton button = (MaterialButton) view;
        String getDataButton = button.getText().toString();

        switch (getDataButton) {
            case "C":
                if(input.length()>0){
                    input = input.substring(0, input.length()-1);
                } else {
                    input="";
                }
                secondScreen.setText(input);
                break;
            case "AC" :
                input = "";
                history = "";
                firstScreen.setText("");
                secondScreen.setText("");
                break;

            case "x" :
                input+="*";
                calculate();
                break;

            case "=" :
                history = input+"=";
                calculate();
                break;

            default:
                if (input==null){
                    input = "";
                }
                if ( getDataButton.equals("+") || getDataButton.equals("-")
                    || getDataButton.equals("/")){
                    calculate();
                }
                input += getDataButton;
        }
        secondScreen.setText(input);
    }

    private void calculate() {
        if(input.split("\\+").length == 2){
            String numbers[] = input.split("\\+");
            try {
                double plus = Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[1]);
                input = plus + "";
            } catch (Exception e){
                Log.e("Error input", input);
            }
        }else if(input.split("\\*").length == 2){
            String numbers[] = input.split("\\*");
            try {
                double multi = Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]);
                input = multi + "";
            } catch (Exception e){
                Log.e("Error input", input);
            }
        }else if(input.split("\\/").length == 2){
            String numbers[] = input.split("\\/");
            try {
                double div = Double.parseDouble(numbers[0]) / Double.parseDouble(numbers[1]);
                input = div + "";
            } catch (Exception e){
                Log.e("Error input", input);
            }
        } else if(input.split("\\-").length>1){
            String numbers[] = input.split("\\-");

            if(numbers[0]=="" && numbers.length==2){
                numbers[0] = 0 + "";
            }
            try {
                double minus = 0;
                if (numbers.length == 2){
                    minus = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                }
                else if (numbers.length == 3){
                    if (numbers[0].length()>0){
                        minus =  Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[2]);
                    } else {
                        minus = - Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[2]);
                    }
                }
                else if (numbers.length == 4){
                    minus = - Double.parseDouble(numbers[1]) + Double.parseDouble(numbers[3]);
                }
                input = minus + "";
            } catch (Exception e){
                Log.e("Error input", input);
            }
        }

        if(input.endsWith(".0")){
            input = input.replace(".0","");
        }
        firstScreen.setText(history);
    }
}