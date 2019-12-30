package com.sign.myutils;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.regex.Pattern;

public class FormatEditTextActivity extends AppCompatActivity {

    private static final String CPF_PATTERN = "[0-9]{3}\\.+[0-9]{3}\\.+[0-9]{3}-+[0-9]{2}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_format_edit_text);
        final EditText etCpf = findViewById(R.id.et_cpf);
        etCpf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                formatCpf(s.toString(), etCpf, start, count);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private static String formatCpf(String cpf, EditText editText, int start, int count) {
        if (Pattern.matches(CPF_PATTERN, cpf)) {
            return cpf;
        } else {
            String realCpf = cpf.replaceAll("-", "").replaceAll("\\.", "");
            StringBuilder sbCpf = new StringBuilder();
            if (realCpf.length() > 9) {
                sbCpf.append(realCpf, 0, 3)
                        .append(".")
                        .append(realCpf, 3, 6)
                        .append(".")
                        .append(realCpf, 6, 9)
                        .append("-")
                        .append(realCpf.substring(9));
            } else if (realCpf.length() > 6) {
                sbCpf.append(realCpf, 0, 3)
                        .append(".")
                        .append(realCpf, 3, 6)
                        .append(".")
                        .append(realCpf.substring(6));
            } else if (realCpf.length() > 3) {
                sbCpf.append(realCpf, 0, 3)
                        .append(".")
                        .append(realCpf.substring(3));
            } else {
                sbCpf.append(realCpf);
            }
            if (!TextUtils.equals(cpf, sbCpf.toString())) {
                editText.setText(sbCpf);
                String selectStr = cpf.substring(0, start + count).replaceAll("-", "").replaceAll("\\.", "");
                int selectIndex;
                if (selectStr.length() > 9) {
                    selectIndex = selectStr.length() + 3;
                } else if (selectStr.length() > 6) {
                    selectIndex = selectStr.length() + 2;
                } else if (selectStr.length() > 3) {
                    selectIndex = selectStr.length() + 1;
                } else {
                    selectIndex = selectStr.length();
                }
                if (selectIndex >= 0) {
                    editText.setSelection(selectIndex);
                }
            }
            return sbCpf.toString();
        }
    }
}
