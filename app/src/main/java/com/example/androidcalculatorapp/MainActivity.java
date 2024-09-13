package com.example.androidcalculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText result;
    private EditText newNum;
    private TextView displayOperation;

    private Double operand1 = null;
    private Double operand2 = null;
    private String pendingOperation = "=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        result = (EditText) findViewById(R.id.result);
        newNum = (EditText) findViewById(R.id.newNum);
        displayOperation = (TextView) findViewById(R.id.operation);

        Button num0 = (Button) findViewById(R.id.num0);
        Button num1 = (Button) findViewById(R.id.num1);
        Button num2 = (Button) findViewById(R.id.num2);
        Button num3 = (Button) findViewById(R.id.num3);
        Button num4 = (Button) findViewById(R.id.num4);
        Button num5 = (Button) findViewById(R.id.num5);
        Button num6 = (Button) findViewById(R.id.num6);
        Button num7 = (Button) findViewById(R.id.num7);
        Button num8 = (Button) findViewById(R.id.num8);
        Button num9 = (Button) findViewById(R.id.num9);
        Button dot = (Button) findViewById(R.id.decimal);

        Button equal = (Button) findViewById(R.id.equal);
        Button divide = (Button) findViewById(R.id.divide);
        Button multiply = (Button) findViewById(R.id.multiply);
        Button minus = (Button) findViewById(R.id.minus);
        Button add = (Button) findViewById(R.id.add);
        Button ac = (Button) findViewById(R.id.reset);
        Button brackets = (Button) findViewById(R.id.parenthesis);
        Button undo = (Button) findViewById(R.id.undo);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                newNum.append(b.getText().toString());
            }
        };
        num0.setOnClickListener(listener);
        num1.setOnClickListener(listener);
        num2.setOnClickListener(listener);
        num3.setOnClickListener(listener);
        num4.setOnClickListener(listener);
        num5.setOnClickListener(listener);
        num6.setOnClickListener(listener);
        num7.setOnClickListener(listener);
        num8.setOnClickListener(listener);
        num9.setOnClickListener(listener);
        dot.setOnClickListener(listener);

        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String op = b.getText().toString();
                String value = newNum.getText().toString();
                if (!value.isEmpty()) {
                    performOperation(value, op);
                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);
            }
        };
        equal.setOnClickListener(opListener);
        divide.setOnClickListener(opListener);
        multiply.setOnClickListener(opListener);
        minus.setOnClickListener(opListener);
        add.setOnClickListener(opListener);
    }

    private void performOperation(String value, String operation) {
        displayOperation.setText(operation);
        if (null == operand1) {
            operand1 = Double.valueOf(value);
        } else {
            operand2 = Double.valueOf(value);

            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }
            switch (pendingOperation) {
                case "=":
                    operand1 = operand2;
                    break;
                case "/":
                    if (operand2 == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= operand2;
                    }
                    break;
                case "x":
                    operand1 *= operand2;
                    break;
                case "-":
                    operand1 -= operand2;
                    break;
                case "+":
                    operand1 += operand2;
                    break;
            }
        }
        result.setText(String.valueOf(operand1));
        newNum.setText("");
    }



}