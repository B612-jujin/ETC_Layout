package kr.ac.kopo.etc_layout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class GridDir05_5 extends AppCompatActivity {

    EditText edit1, edit2;
    Button[] btnNums = new Button[10];
    Button btn_Plus, btn_Minus, btn_Multi, btn_Divide;
    TextView textResult;
    int[] btnNumIds = {R.id.btn_0,R.id.btn_1,R.id.btn_2,R.id.btn_3,R.id.btn_4,R.id.btn_5,R.id.btn_6,R.id.btn_7,R.id.btn_8,R.id.btn_9};

    String Num1 = "", Num2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.grid_dir05_5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        for(int i=0;i < btnNums.length ; i++){
            btnNums[i] = findViewById(btnNumIds[i]);
        }

        btn_Plus = findViewById(R.id.btn_pluse);
        btn_Minus = findViewById(R.id.btn_minus);
        btn_Multi = findViewById(R.id.btn_multiply);
        btn_Divide = findViewById(R.id.btn_divide);

        textResult = findViewById(R.id.text_result);

        btn_Plus.setOnClickListener(btn_resultListener);
        btn_Minus.setOnClickListener(btn_resultListener);
        btn_Multi.setOnClickListener(btn_resultListener);
        btn_Divide.setOnClickListener(btn_resultListener);


        for(int i = 0;i < btnNums.length;i++){
            final int index;
            index = i;
            btnNums[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (edit1.isFocused()){
                        Num1 = edit1.getText().toString() + btnNums[index].getText().toString();
                        edit1.setText(Num1);
                    } else if (edit2.isFocused()) {
                        Num2 = edit2.getText().toString() + btnNums[index].getText().toString();
                        edit2.setText(Num2);
                    } else{
                        Toast.makeText(getApplicationContext(),"먼저 에디트텍스트를 선택해 주세요.",Toast.LENGTH_LONG).show();
                    }
                }
            });




        }

    }

    View.OnClickListener btn_resultListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            if(!edit1.getText().toString().isEmpty() &&!edit2.getText().toString().isEmpty()) {
            Button evenBtn = (Button) v;
            double num1 = Integer.parseInt(edit1.getText().toString());
            double num2 = Integer.parseInt(edit2.getText().toString());
            double result = 0;
                if (evenBtn == btn_Plus) {
                    result = num1 + num2;
                } else if (evenBtn == btn_Minus) {
                    result = num1 - num2;
                } else if (evenBtn == btn_Multi) {
                    result = num1 * num2;
                } else if (evenBtn == btn_Divide) {
                    if (num1 != 0 && num2 != 0) {
                        result = num1 / num2;
                    } else {
                        Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                    }
                }

                textResult.setText("계산 결과: " + result);

            }else{
                Toast.makeText(getApplicationContext(), "먼저 값을 입력해주세요", Toast.LENGTH_SHORT).show();
            }
        }

    };
}