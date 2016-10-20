package cn.edu.gdmec.s07105733.mycalculator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button calculatorButton;
    private EditText weightEditText;
    private RadioButton manCheckBox;
    private  RadioButton womanCheckBox;
    private TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculatorButton = (Button) findViewById(R.id.calculator);
        weightEditText = (EditText) findViewById(R.id.weight);
        manCheckBox = (RadioButton) findViewById(R.id.man);
        womanCheckBox = (RadioButton) findViewById(R.id.woman);
        resultTextView = (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }
    private void registerEvent() {
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!weightEditText.getText().toString().trim().equals("")) {
                    if (manCheckBox.isChecked()||womanCheckBox.isChecked()){
                        Double weight = Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("----结果是----- \n");
                        if (manCheckBox.isChecked()) {
                            sb.append("男性标准身高");
                            double result = evaluateHeight(weight, "男");
                            sb.append((int) result + "(cm)");
                        }
                        if (womanCheckBox.isChecked()) {
                            sb.append("女性标准身高");
                            double result = evaluateHeight(weight, "女");
                            sb.append((int) result + "(cm)");
                        }
                        resultTextView.setText(sb.toString());
                    } else {
                        showMessage("请选择性别");
                    }
                }else
                {
                    showMessage("请输入体重");
                }
            }
        });
    }
    private double evaluateHeight(double weight, String sex) {
        double height;
        if (sex == "男") {
            height = 170 - (62 - weight) / 0.6;
        } else {
            height = 158 - (52 - weight) / 0.5;
        }
        return height;
    }
    private void showMessage(String message)
    {
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"确定",new DialogInterface.OnClickListener () {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,1,Menu.NONE,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

