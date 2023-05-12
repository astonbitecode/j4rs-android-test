package com.example.j4rstest;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.j4rstest.api.dto.TestDto;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View v = getWindow().getDecorView();
        TextView title = v.findViewById(R.id.mytextview);
        RustFunctionCalls rfc = new RustFunctionCalls();
        String s = rfc.strings("Hi");
        System.out.println("--------------" + s);

        Integer i = rfc.addInRust(111, 222);
        System.out.println("--------------" + i);

        Long l = rfc.addInRust(11L, 22L);
        System.out.println("--------------" + l);

        TestDto dto = new TestDto(33);
        TestDto dtoRet = rfc.doCallWithCustomObject(dto);
        title.setText("The result is " + dtoRet.getNumber());
        System.out.println("--------------" + dtoRet.getNumber());
    }
}