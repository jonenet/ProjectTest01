package com.example.dragger.dialogapp.view.testActivity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import com.example.dragger.dialogapp.R;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp.view.testActivity
 * ProjectName: ProjectTest01
 * Date: 2019/10/9 17:59
 */
public class RadioGroupActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiogroup);
        LinearLayout rootView = findViewById(R.id.ll_content);

        RadioGroup radioGroup = new RadioGroup(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        layoutParams.setMargins(0, 100, 0, 0);
        radioGroup.setLayoutParams(layoutParams);
        radioGroup.setOrientation(RadioGroup.HORIZONTAL);
        radioGroup.setGravity(Gravity.CENTER_VERTICAL);
        radioGroup.setFocusable(true);

        for (int i = 0; i < 3; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText("position" + i);
            radioButton.setTextSize(24);
            radioButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            int padding = 3;
            radioButton.setPadding(padding, padding, padding, padding);
            radioButton.setGravity(Gravity.CENTER);
            radioButton.setFocusable(true);
            radioButton.setButtonDrawable(android.R.color.transparent);
            radioButton.setBackgroundResource(R.drawable.selector_tab_title);
            radioButton.setTag(i);
            radioButton.setClickable(true);
            radioButton.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    Log.d("TAG","v"+v.getTag());
                }
            });
//            radioButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
            Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
//            drawable.setBounds(new Rect(100,100,100,100));
            radioButton.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
            radioButton.setCompoundDrawablePadding(20);
            radioGroup.addView(radioButton);
            if (i == 3 - 1) {
                radioButton.setNextFocusRightId(radioButton.getId());
            } else if (i == 0) {
                radioButton.setNextFocusLeftId(radioButton.getId());
                radioButton.requestFocus();
            }
        }

        rootView.addView(radioGroup);

    }
}
