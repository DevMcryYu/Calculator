package com.devmcryyu.calculate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView txt_output;
    private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0;
    private Button btn_equal, btn_add, btn_minus, btn_multiply, btn_divide;
    private Button btn_clear, btn_delete, btn_point;
    private String str;
    private boolean point_enable = true;                                                              //小数点是否可用
    private boolean point_atFirst = true;                                                             //小数点是否在首位
    private boolean zeroAtFirst = false;                                                              //零是否在首位
    private boolean isOperated = false;                                                               //是否运算过

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_add = findViewById(R.id.btn_add);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);
        btn_point = findViewById(R.id.btn_point);
        btn_clear = findViewById(R.id.btn_clear);
        btn_delete = findViewById(R.id.btn_delete);
        btn_equal = findViewById(R.id.btn_equal);
        txt_output = findViewById(R.id.output);
        /**
         * 注册点击事件监听器
         */
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_point.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        str = (String) txt_output.getText();
        switch (view.getId()) {
            case R.id.btn_0: {
                if (isOperated) {
                    txt_output.setText("");
                    str = "";
                    point_enable = true;
                    point_atFirst = true;
                    zeroAtFirst = false;
                    isOperated = false;
                }
                if (str.equals("")) {
                    txt_output.setText(str + ((Button) view).getText());
                    point_atFirst = false;
                    zeroAtFirst = true;
                }
                if (str.lastIndexOf(" ") == str.length() - 1) {
                    txt_output.setText(str + ((Button) view).getText());
                    point_atFirst = false;
                    zeroAtFirst = true;
                }
                if (zeroAtFirst) {
                    break;
                } else {
                    txt_output.setText(str + ((Button) view).getText());
                    point_atFirst = false;
                }

            }
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
                if (isOperated) {
                    txt_output.setText("");
                    str = "";
                }
                if (zeroAtFirst) {                                                                    //零在首位自动替换
                    txt_output.setText(str.substring(0, str.length() - 1) + ((Button) view).getText());
                    point_atFirst = false;
                    zeroAtFirst = false;
                    break;
                }
                txt_output.setText(str + ((Button) view).getText());
                point_atFirst = false;                                                              //此时小数点不在首位
                zeroAtFirst = false;                                                                //此时零不在首位
                break;
            case R.id.btn_point: {
                if (isOperated) {
                    txt_output.setText("");
                    str = "";
                    point_enable = true;
                    point_atFirst = true;
                    zeroAtFirst = false;
                    isOperated = false;
                }
                if (point_enable) {
                    if (point_atFirst)
                        txt_output.setText(str + "0" + ((Button) view).getText());                  //小数点在首位，前加0
                    else
                        txt_output.setText(str + ((Button) view).getText());                        //加小数点
                    point_enable = false;
                    point_atFirst = false;
                    zeroAtFirst = false;
                }
            }
            break;
            case R.id.btn_add:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide: {
                if ((str.charAt(str.length() - 1) == ' ')) {                                        //替换运算符
                    txt_output.setText(str.substring(0, str.length() - 3) + " " + ((Button) view).getText() + " ");
                    break;
                }
                txt_output.setText(str + " " + ((Button) view).getText() + " ");                    //运算符前后加" "以便于后续计算
                point_enable = true;                                                                //新的数值，初始化 下同
                point_atFirst = true;
                zeroAtFirst = false;
            }
            break;
            case R.id.btn_clear: {                                                                  //清屏，所有均初始化
                txt_output.setText("");
                point_enable = true;
                point_atFirst = true;
                zeroAtFirst = false;
                isOperated = false;
            }
            break;
            case R.id.btn_delete: {
                if (str != null & !str.equals("")) {
                    if ((str.charAt(str.length() - 1) == ' ')) {                                    //最后一位为空格时，删除3个字符长度
                        txt_output.setText(str.substring(0, str.length() - 3));                     //删除运算符
                        point_enable = true;                                                                //新的数值，初始化 下同
                        point_atFirst = true;
                        zeroAtFirst = false;
                        break;
                    }
                    if (str.charAt(str.length() - 1) == '.') {
                        point_enable = true;
                        point_atFirst = false;
                        zeroAtFirst = false;
                    }
                    txt_output.setText(str.substring(0, str.length() - 1));
                }
                if (str.equals("")) {
                    point_enable = true;
                    point_atFirst = true;
                    zeroAtFirst = false;
                    isOperated = false;
                }
            }
            break;
            case R.id.btn_equal: {
                if (str.equals(""))
                    break;
                txt_output.setText(String.valueOf(operate(str)));
                isOperated = true;
            }
            break;
        }

    }

    public double operate(String str) {
        int index = str.indexOf("+");
        if (index != -1)
            return operate(str.substring(0, index - 1)) + operate(str.substring(index + 2));
        index = str.lastIndexOf("-");
        if (index != -1)
            return operate(str.substring(0, index - 1)) - operate(str.substring(index + 2));
        index = str.indexOf("×");
        if (index != -1)
            return operate(str.substring(0, index - 1)) * operate(str.substring(index + 2));
        index = str.lastIndexOf("÷");
        if (index != -1)
            return operate(str.substring(0, index - 1)) / operate(str.substring(index + 2));
        return Double.parseDouble(str);
    }
}

