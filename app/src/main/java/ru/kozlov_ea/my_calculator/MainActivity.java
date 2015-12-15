package ru.kozlov_ea.my_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bplus, bminus, bdiv, bmul;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0;
    Button bAC, bC, bAns;

    TextView Ans;
    String inStr = "";
    int count = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // определяем элементы
        bplus = (Button) findViewById(R.id.bplus);
        bminus = (Button) findViewById(R.id.bminus);
        bmul = (Button) findViewById(R.id.bmul);
        bdiv = (Button) findViewById(R.id.bdiv);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        b0 = (Button) findViewById(R.id.b0);

        bAC = (Button) findViewById(R.id.bAC);
        bC = (Button) findViewById(R.id.bC);
        bAns = (Button) findViewById(R.id.bAns);

        Ans = (TextView) findViewById(R.id.textView);

        // обрабатываем
        bplus.setOnClickListener(this);
        bminus.setOnClickListener(this);
        bmul.setOnClickListener(this);
        bdiv.setOnClickListener(this);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b0.setOnClickListener(this);

        bAC.setOnClickListener(this);
        bC.setOnClickListener(this);
        bAns.setOnClickListener(this);
    }

    //проверяем корретность строки на длину
    private boolean CheckStr(String cur) {
        if (cur.length() > 0 && cur.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }

    // если исходная строчка задана корректно, то разбираем её
    private String getCur() {
        String curS = "";
        if (CheckStr(inStr)) {
            int m = 0;
            m = count;
            for (int i = 0; i < inStr.length(); i++) {
                char ch = inStr.charAt(i);
                if ((ch == '.') || (ch >= '0' && ch <= '9')) {
                    m = i;
                } else {
                    break;
                }
            }
            if (m < count) {
                curS = inStr.substring(m, count);
            }
        }
        return curS;
    }


    // разбираем выражение в порядке приоритета
    // унарные операции; умножение/деление; сложение/вычитание

    private double First() {
        double curAns = 0;
        String q = "";

        if (inStr.charAt(count) == '-') {
            count++;
            q = getCur();
            q = '-' + q;

            if (CheckStr(q)) {
                curAns = Double.parseDouble(q);
            }
        }
        return curAns;
    }

    private Double Second() {
        double t = First();
        for (int i = 0; i < inStr.length(); i++) {
            char ch = inStr.charAt(i);
            switch (ch) {
                case '*':
                    t = t * First();
                    count++;
                    break;
                case '/':
                    t = t / First();
                    count++;
                    break;
                default:
                    return t;
            }
        }
        return t;
    }

    private Double Third() {
        double t = Second();
        for (int i = 0; i < inStr.length(); i++) {
            char ch = inStr.charAt(i);
            switch (ch) {
                case '+':
                    t += Second();
                    count++;
                    break;
                case '-':
                    t -= Second();
                    count++;
                    break;
                default:
                    return t;
            }
        }
        return t;
    }

    // нажатая пользователем кнопка определяет текущую операцию
    public void onClick(View v) {
        if (CheckStr(inStr)) {
            try {
                switch (v.getId()) {


                    case R.id.b1:
                        inStr += '1';
                        Ans.setText(inStr);
                        break;
                    case R.id.b2:
                        inStr += '2';
                        Ans.setText(inStr);
                        break;
                    case R.id.b3:
                        inStr += '3';
                        Ans.setText(inStr);
                        break;
                    case R.id.b4:
                        inStr += '4';
                        Ans.setText(inStr);
                        break;
                    case R.id.b5:
                        inStr += '5';
                        Ans.setText(inStr);
                        break;
                    case R.id.b6:
                        inStr += '6';
                        Ans.setText(inStr);
                        break;
                    case R.id.b7:
                        inStr += '7';
                        Ans.setText(inStr);
                        break;
                    case R.id.b8:
                        inStr += '8';
                        Ans.setText(inStr);
                        break;
                    case R.id.b9:
                        inStr += '9';
                        Ans.setText(inStr);
                        break;
                    case R.id.b0:
                        inStr += '0';
                        Ans.setText(inStr);
                        break;


                    case R.id.bplus:
                        int w1 = inStr.length() - 1;
                        char ch1 = inStr.charAt(w1);
                        if ((ch1 == '+') || (ch1 == '-') || (ch1 == '*') || (ch1 == '/')) {
                            inStr = inStr.substring(0, w1);
                        }
                        inStr += '+';
                        Ans.setText(inStr);
                        break;
                    case R.id.bminus:
                        int w2 = inStr.length() - 1;
                        char ch2 = inStr.charAt(w2);
                        if ((ch2 == '+') || (ch2 == '-') || (ch2 == '*') || (ch2 == '/')) {
                            inStr = inStr.substring(0, w2);
                        }
                        inStr += '-';
                        Ans.setText(inStr);
                        break;
                    case R.id.bmul:
                        int w3 = inStr.length() - 1;
                        char ch3 = inStr.charAt(w3);
                        if ((ch3 == '+') || (ch3 == '-') || (ch3 == '*') || (ch3 == '/')) {
                            inStr = inStr.substring(0, w3);
                        }
                        inStr += '*';
                        Ans.setText(inStr);
                        break;
                    case R.id.bdiv:
                        int w4 = inStr.length() - 1;
                        char ch4 = inStr.charAt(w4);
                        if ((ch4 == '+') || (ch4 == '-') || (ch4 == '*') || (ch4 == '/')) {
                            inStr = inStr.substring(0, w4);
                        }
                        inStr += '/';
                        Ans.setText(inStr);
                        break;


                    case R.id.bdot:
                        inStr += ".";
                        Ans.setText(inStr);
                        break;


                    case R.id.bAC:
                        inStr = "";
                        Ans.setText(inStr);
                        break;
                    case R.id.bC:
                        inStr = inStr.substring(0, inStr.length() - 1);
                        Ans.setText(inStr);
                        break;

                    case R.id.bAns:
                        String s = "";
                        count = 0;
                        double t;

                        try {
                            t = First();
                            s = Double.toString(t);
                        } catch (Exception e) {
                            s = "incorrect";
                        }

                        Ans.setText(s);
                        inStr = s;
                        break;
                }
            } catch (Exception e) {
                Ans.setText("incorrect input");
            }
        }
    }
}
