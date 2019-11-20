package com.javaer.plusxposed.view.base.dialog;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toolbar;

/**
 * Created by javaer on 2018/7/1.
 */

public class BaseListDialog extends LinearLayout {

    protected Context mContext;
    public SharedPreferences prefs;
    public SharedPreferences.Editor editor;

    public AlertDialog dialog;
    protected LinearLayout content;
    protected Toolbar toolbar;

    public BaseListDialog(Context context, String title) {
        super(context);
        this.mContext = context;
        init(title);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void init(String title){

        LayoutParams contentParam = new LayoutParams
                (LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        content = new LinearLayout(mContext);
        content.setFocusable(true);
        content.setFocusableInTouchMode(true);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setLayoutParams(contentParam);

        LinearLayout tool_line = new LinearLayout(mContext);
        LayoutParams tool_line_params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        tool_line.setLayoutParams(tool_line_params);
        tool_line.setGravity(Gravity.CENTER);
        tool_line.setOrientation(HORIZONTAL);

        toolbar = new Toolbar(mContext);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setBackgroundColor(Color.parseColor("#58575c"));//#343339
        toolbar.setTitle(title);
        toolbar.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, dpi2px(50, mContext)));

        tool_line.addView(toolbar);
        content.addView(tool_line);
    }

    //文本框
    public void addTextView(String str, int id){
        TextView tv = new TextView(mContext);
        tv.setText(str);
        tv.setId(id);
        tv.setTextSize(dpi2px(6,mContext));

        LayoutParams tv_params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        tv_params.setMargins(dpi2px(18,mContext),dpi2px(5,mContext),dpi2px(18,mContext),dpi2px(5,mContext));
        tv.setLayoutParams(tv_params);

        content.addView(tv);
    }

    //添加带key的编辑框,编辑框的key对应的value会自动存到SharedPreferences的xml文本里
    public void addEdit(final String key, String hint, final String defaultText){
        final EditText editText = new EditText(mContext);
        editText.setHint(hint);
        editText.setTextSize(dpi2px(6,mContext));
        editText.setText(prefs.getString(key, defaultText));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editText.getText().toString().equals("") && editText.getText() != null){
                    editor.putString(key,editText.getText().toString());
                    editor.apply();
                }else {
                    editor.putString(key, defaultText);
                    editor.apply();
                }
            }
        });

        LayoutParams wxidParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        wxidParams.setMargins(dpi2px(18,mContext),dpi2px(3,mContext), dpi2px(18,mContext), 0);
        editText.setLayoutParams(wxidParams);

        content.addView(editText);
    }

    //
    public void addEdit(String hint, int id){
        EditText editText = new EditText(mContext);
        editText.setHint(hint);
        editText.setId(id);
        editText.setTextSize(dpi2px(6, mContext));

        LayoutParams wxidParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        wxidParams.setMargins(dpi2px(18,mContext),dpi2px(3,mContext), dpi2px(18,mContext), 0);
        editText.setLayoutParams(wxidParams);

        content.addView(editText);
    }

    //带默认文字
    public void addEdit(int id, String hint, String text){
        EditText editText = new EditText(mContext);
        editText.setHint(hint);
        editText.setId(id);
        editText.setText(text);
        editText.setTextSize(dpi2px(6, mContext));

        LayoutParams wxidParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        wxidParams.setMargins(dpi2px(18,mContext),dpi2px(3,mContext), dpi2px(18,mContext), 0);
        editText.setLayoutParams(wxidParams);

        content.addView(editText);
    }

    public void addSwitch(String text, final String key, boolean default_boolean){
        final Switch swh = new Switch(mContext);
        swh.setText(text);
        swh.setChecked(prefs.getBoolean(key, default_boolean));
        swh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean(key,swh.isChecked());
                editor.apply();
            }
        });

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(dpi2px(18,mContext),dpi2px(4,mContext),dpi2px(18,mContext),dpi2px(1, mContext));
        swh.setLayoutParams(params);

        content.addView(swh);
    }

    public void addBtn(int id, String text, final Click click){
        Button btn = new Button(mContext);
        btn.setText(text);
        btn.setId(id);
        if (click != null){
            btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.onClick();
                }
            });
        }
        LayoutParams goParams = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        goParams.setMargins(dpi2px(18,mContext),dpi2px(4,mContext), dpi2px(18,mContext), 0);
        btn.setLayoutParams(goParams);

        content.addView(btn);
    }

    /**
     * 添加水平排列的单选框
     * @param key
     * @param defaultCheckedId
     * @param strings
     */
    public void addHorizontalRadios(final String key, int defaultCheckedId, String... strings){
        RadioGroup rg = new RadioGroup(mContext);
        rg.setOrientation(HORIZONTAL);
        rg.setGravity(Gravity.CENTER);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(0, dpi2px(3, mContext), 0, dpi2px(3, mContext));
        rg.setLayoutParams(params);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
        for (int i = 0; i < strings.length; i++){
            RadioButton rb = new RadioButton(mContext);
            rb.setId(i);
            rb.setText(strings[i]);

            rg.addView(rb);
        }

        content.addView(rg);
    }

    public void show(boolean cancelOut, boolean cancelable, final Click positive, String sure, final Click negative){
        this.addView(content);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(this);
        if (positive != null){
            builder.setPositiveButton(sure, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    positive.onClick();
                }
            });
        }
        if (negative != null){
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    negative.onClick();
                }
            });
        }
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(cancelOut);
        dialog.setCancelable(cancelable);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.show();
    }

    public static int dpi2px(float value, Context context){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

    public int dpi2px(float value){
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

    public interface OnCheckedChangeListener{
        void onCheckedChange(RadioGroup radioGroup, int checkedId);
    }

    public interface Click{
        void onClick();
    }
}
