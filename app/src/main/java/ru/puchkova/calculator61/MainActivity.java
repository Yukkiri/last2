package ru.puchkova.calculator61;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {
    private TextView computation;
    private TextView calc;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button dot;
    private Button ac;
    private Button opposite;
    private Button percent;
    private Button division;
    private Button multiply;
    private Button subtraction;
    private Button addition;
    private Button equal;

    private ImageButton background;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //final String imagePath = getIntent().getStringExtra("background");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        /*if (imagePath != null){
            Drawable d = Drawable.createFromPath(imagePath);
            ImageView background_image = findViewById(R.id.background_image);
            background_image.setImageDrawable(d);
            //ConstraintLayout main = findViewById(R.id.main);
            //main.setBackground(d);
        }*/

        init();
    }

    private void init(){
        computation = findViewById(R.id.computation);
        calc = findViewById(R.id.calc);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        dot = findViewById(R.id.dot);
        ac = findViewById(R.id.ac);
        opposite = findViewById(R.id.opposite);
        percent = findViewById(R.id.percent);
        division = findViewById(R.id.division);
        multiply = findViewById(R.id.multiply);
        subtraction = findViewById(R.id.subtraction);
        addition = findViewById(R.id.addition);
        equal = findViewById(R.id.equal);


        Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/calc.ttf");
        computation.setTypeface(typeFace);
        calc.setTypeface(typeFace);

        button0.setOnClickListener(onClickListener);
        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        button4.setOnClickListener(onClickListener);
        button5.setOnClickListener(onClickListener);
        button6.setOnClickListener(onClickListener);
        button7.setOnClickListener(onClickListener);
        button8.setOnClickListener(onClickListener);
        button9.setOnClickListener(onClickListener);
        dot.setOnClickListener(onClickListener);
        ac.setOnClickListener(onClickListener);
        percent.setOnClickListener(onClickListener);
        opposite.setOnClickListener(onClickListener);
        division.setOnClickListener(onClickListener);
        multiply.setOnClickListener(onClickListener);
        addition.setOnClickListener(onClickListener);
        subtraction.setOnClickListener(onClickListener);
        equal.setOnClickListener(onClickListener);

        background = findViewById(R.id.background);
        background.setOnClickListener(backOnClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String calcString;
            String solution;
            String sNumber;
            double calcDouble;
            char operation = '!';
            double number1;
            double number2;
            double number;

            Pattern pattern = Pattern.compile("([+-/\\*])");
            Matcher matcher;

            switch(v.getId()){
                case R.id.ac:
                    computation.setText("");
                    calc.setText("");
                    break;
                case R.id.opposite:
                    calcString = computation.getText().toString();
                    Pattern minus = Pattern.compile("-");
                    Matcher min = minus.matcher(calcString);
                    if(calcString.equals("ERROR")){
                        calcString = "0";
                    }
                    if(min.find()){
                        calcString = calcString.substring(1);
                    } else {
                        calcString = "-" + calcString;
                    }
                    computation.setText(calcString);
                    break;
                case R.id.percent:
                    calcString = computation.getText().toString();
                    if(calcString.equals("ERROR") || calcString.equals("")){
                        calcString = "0";
                    }
                    calcDouble = Double.parseDouble(calcString)/100;
                    computation.setText(valueOf(calcDouble));
                    break;
                case R.id.button0:
                    calcString = computation.getText().toString();
                    if(calcString.equals("ERROR")){
                        calcString = "0";
                        computation.setText(calcString);
                    } else {
                        calcString = calcString + "0";
                        computation.setText(calcString);
                    }
                    break;
                case R.id.button1:
                    sNumber = "1";
                    numbers(sNumber);
                    break;
                case R.id.button2:
                    sNumber = "2";
                    numbers(sNumber);
                    break;
                case R.id.button3:
                    sNumber = "3";
                    numbers(sNumber);
                    break;
                case R.id.button4:
                    sNumber = "4";
                    numbers(sNumber);
                    break;
                case R.id.button5:
                    sNumber = "5";
                    numbers(sNumber);
                    break;
                case R.id.button6:
                    sNumber = "6";
                    numbers(sNumber);
                    break;
                case R.id.button7:
                    sNumber = "7";
                    numbers(sNumber);
                    break;
                case R.id.button8:
                    sNumber = "8";
                    numbers(sNumber);
                    break;
                case R.id.button9:
                    sNumber = "9";
                    numbers(sNumber);
                    break;
                case R.id.equal:
                    calcString = computation.getText().toString();
                    solution = calc.getText().toString();
                    if (solution.equals("") && calcString.equals("")){
                        break;
                    }
                    matcher = pattern.matcher(solution);
                    if (matcher.find()){
                        int length = solution.length();
                        if (calcString.equals("")){
                            solution = solution.substring(0, length-1);
                            computation.setText(solution);
                            calc.setText("");
                            break;
                        }
                        number1 = Double.parseDouble(solution.substring(0, length-1));
                        number2 = Double.parseDouble(calcString);
                        if (number2 == 0){
                            calc.setText("");
                            computation.setText(R.string.error);
                            break;
                        }
                        operation = solution.charAt(length-1);
                        number = solutionResult(number1, number2, operation);
                        solution = valueOf(number);
                        calc.setText("");
                        computation.setText(solution);
                    } else {
                        computation.setText(calcString);
                        calc.setText("");
                    }
                    break;
                case R.id.division:
                    sNumber = "/";
                    operations(sNumber);
                    break;
                case R.id.multiply:
                    sNumber = "*";
                    operations(sNumber);
                    break;
                case R.id.addition:
                    sNumber = "+";
                    operations(sNumber);
                    break;
                case R.id.subtraction:
                    sNumber = "-";
                    operations(sNumber);
                    break;
                case R.id.dot:
                    calcString = computation.getText().toString();
                    Pattern dot = Pattern.compile("\\.");
                    Matcher mDot = dot.matcher(calcString);
                    if (mDot.find()){
                        break;
                    }
                    if (calcString.equals("") || calcString.equals(R.string.error)){
                        calcString = "0" + ".";
                    } else {
                        calcString = calcString + ".";
                    }
                    computation.setText(calcString);
                    break;
            }
        }
    };

    private double solutionResult(double number1, double number2, char operation){
        double solution;
        switch (operation){
            case '/':
                solution = number1/number2;
                break;
            case '*':
                solution = number1*number2;
                break;
            case '-':
                solution = number1-number2;
                break;
            case '+':
                solution = number1+number2;
                break;
            default:
                solution = 0;
        }
        return solution;
    }

    private void numbers(String sNumber){
        String calcString;
        calcString = computation.getText().toString();
        if(calcString.equals("ERROR")){
            calcString = sNumber;
            computation.setText(calcString);
        } else {
            calcString = calcString + sNumber;
            computation.setText(calcString);
        }
    }

    private void operations(String sNumber){
        String calcString;
        String solution;
        char operation = '!';
        double number1;
        double number2;
        double number;

        Pattern pattern = Pattern.compile("([+-/\\*])");
        Matcher matcher;

        calcString = computation.getText().toString();
        int calcLength = calcString.length();
        solution = calc.getText().toString();

        if (solution.equals("") && calcString.equals("")){

        } else{
            matcher = pattern.matcher(solution);
            if (matcher.find()) {
                int length = solution.length();
                if (calcString.equals("")) {
                    solution = solution.substring(0, length - 1);
                    calc.setText(solution + sNumber);
                } else {
                    if (calcLength > 14){
                        calcString = calcString.substring(0,length-1);
                    }
                    number1 = Double.parseDouble(solution.substring(0, length - 1));
                    number2 = Double.parseDouble(calcString);
                    if (number2 == 0) {
                        calc.setText("");
                        computation.setText(R.string.error);
                    } else {
                        operation = solution.charAt(length - 1);
                        number = solutionResult(number1, number2, operation);
                        solution = number + sNumber;
                        calc.setText(solution);
                        computation.setText("");
                    }
                }
            } else {
                computation.setText("");
                calc.setText(calcString + sNumber);

            }
        }
    }

    View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            //startActivity(intent);
            /*finish();
            Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickIntent.setType("image/*");*/
            startActivityForResult(intent, 111);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
            final String imagePath = getIntent().getStringExtra("background");
            Drawable d = Drawable.createFromPath(imagePath);
            // v это тоже не работает
            //ImageView background_image = findViewById(R.id.background_image);
            //background_image.setImageDrawable(d);
            ConstraintLayout main = findViewById(R.id.main);
            main.setBackground(d);
            /*Uri selectedMediaUri = data.getData();
            if (selectedMediaUri.toString().contains("image")){
                File file = new File(selectedMediaUri.getSchemeSpecificPart());
                final String docId = file.getPath();
                final String[] split = docId.split(":");
                final String filePath = split[1];
                Drawable d = Drawable.createFromPath(filePath);
                ConstraintLayout main = findViewById(R.id.main);
                main.setBackground(d);
            }*/
        }
    }
}

// /storage/emulated/0/Download/00869e054931aaf926a42874490c26a5.jpg