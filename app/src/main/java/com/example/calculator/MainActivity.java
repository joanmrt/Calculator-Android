package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private String input = "";
    private String operator = "";
    private double firstValue = 0;
    private String memoryInput = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize the TextView display
        textView = findViewById(R.id.textView);

        // Set up number and operation buttons
        setupButtons();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Method to initialize and set up buttons
    private void setupButtons() {
        int[] numberButtonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.button00
        };

        // Setting number buttons
        for (int id : numberButtonIds) {
            Button numberButton = findViewById(id);
            numberButton.setOnClickListener(view -> {
                Button btn = (Button) view;
                input += btn.getText().toString();
                textView.setText(input);
            });
        }

        // Set operation buttons
        findViewById(R.id.buttonPlus).setOnClickListener(view -> handleOperation("+"));
        findViewById(R.id.buttonMinus).setOnClickListener(view -> handleOperation("-"));
        findViewById(R.id.buttonMultiply).setOnClickListener(view -> handleOperation("*"));
        findViewById(R.id.buttonDivide).setOnClickListener(view -> handleOperation("/"));
        findViewById(R.id.buttonEquals).setOnClickListener(view -> calculate());
        findViewById(R.id.buttonClear).setOnClickListener(view -> reset());
        findViewById(R.id.buttonMemoryAdd).setOnClickListener(view -> addMemory());
        findViewById(R.id.buttonMemoryLoad).setOnClickListener(view -> loadMemory());
    }

    // Method to handle operation buttons
    private void handleOperation(String operation) {
        if (!input.isEmpty()) {
            firstValue = Double.parseDouble(input);
            operator = operation;
            input = "";
            textView.setText(operator);
        }
    }

    // Calculate result
    private void calculate() {
        double secondValue = Double.parseDouble(input);
        double result = 0;

        switch (operator) {
            case "+":
                result = firstValue + secondValue;
                break;
            case "-":
                result = firstValue - secondValue;
                break;
            case "*":
                result = firstValue * secondValue;
                break;
            case "/":
                result = firstValue / secondValue;
                break;
        }

        textView.setText(String.valueOf(result));
        input = String.valueOf(result);
    }

    private void addMemory(){
        if (!input.isEmpty()) {
            memoryInput = input;
            input = "";
            textView.setText("0");
        }
    }

    private void loadMemory(){
        input = memoryInput;
        textView.setText(memoryInput);
    }

    // Reset text
    private void reset() {
        input = "";
        operator = "";
        firstValue = 0;
        textView.setText("0");
    }
}