package com.javaer.plusxposed.view.base.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toolbar;

import xposed.main.MainHook;

/**
 * Created by javaer on 2018/7/1.
 */

public class BaseScrollDialog extends LinearLayout {

    protected Context mContext;
    public SharedPreferences prefs;
    public SharedPreferences.Editor editor;

    public ScrollView scrollView;
    public AlertDialog dialog;
    protected LinearLayout tool_line,content, root;
    public Toolbar toolbar;

    public BaseScrollDialog(Context context, String title) {
        super(context);
        this.mContext = context;
        init(title);
    }

    private void init(String title){

        root = new LinearLayout(mContext);
        root.setOrientation(VERTICAL);
        root.setFocusable(true);
        root.setFocusableInTouchMode(true);
        LayoutParams root_layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        root.setLayoutParams(root_layoutParams);

        LayoutParams contentParam = new LayoutParams
                (LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        scrollView = new ScrollView(mContext);
        scrollView.setLayoutParams(contentParam);

        content = new LinearLayout(mContext);
        content.setFocusable(true);
        content.setFocusableInTouchMode(true);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setLayoutParams(contentParam);

        tool_line = new LinearLayout(mContext);
        LayoutParams tool_line_params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        tool_line.setLayoutParams(tool_line_params);
        tool_line.setGravity(Gravity.CENTER);
        tool_line.setOrientation(HORIZONTAL);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar = new Toolbar(mContext);

            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.setBackgroundColor(Color.parseColor("#58575c"));
            toolbar.setTitle(title);
            toolbar.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, dpi2px(50, mContext)));

            tool_line.addView(toolbar);
        }

        root.addView(tool_line);
    }

    public void addRootTextView(String str, int id){
        TextView tv = new TextView(mContext);
        tv.setText(str);
        tv.setId(id);
        tv.setTextSize(dpi2px(6,mContext));

        LayoutParams tv_params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        tv_params.setMargins(dpi2px(18,mContext),dpi2px(5,mContext),dpi2px(18,mContext),dpi2px(5,mContext));
        tv.setLayoutParams(tv_params);

        root.addView(tv);
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

    public void addRootTextView(String str, int color, int id){
        TextView tv = new TextView(mContext);
        tv.setText(str);
        tv.setId(id);
        tv.setTextColor(color);
        tv.setTextSize(dpi2px(6,mContext));

        LayoutParams tv_params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        tv_params.setMargins(dpi2px(18,mContext),dpi2px(5,mContext),dpi2px(18,mContext),dpi2px(5,mContext));
        tv.setLayoutParams(tv_params);

        root.addView(tv);
    }

    public void addLinearLayout(LinearLayout linearLayout){
        root.addView(linearLayout);
    }

    public void addTextView(String str, int color, int id){
        TextView tv = new TextView(mContext);
        tv.setText(str);
        tv.setId(id);
        tv.setTextColor(color);
        tv.setTextSize(dpi2px(6,mContext));

        LayoutParams tv_params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        tv_params.setMargins(dpi2px(18,mContext),dpi2px(5,mContext),dpi2px(18,mContext),dpi2px(5,mContext));
        tv.setLayoutParams(tv_params);

        content.addView(tv);
    }

    public void addRootTextView(String str, int color, int id, OnClickListener onClickListener){
        TextView tv = new TextView(mContext);
        tv.setText(str);
        tv.setId(id);
        tv.setOnClickListener(onClickListener);
        tv.setTextColor(color);
        tv.setTextSize(dpi2px(6,mContext));

        LayoutParams tv_params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        tv_params.setMargins(dpi2px(18,mContext),dpi2px(5,mContext),dpi2px(18,mContext),dpi2px(5,mContext));
        tv.setLayoutParams(tv_params);

        root.addView(tv);
    }

    public void addTextView(String str, int color, int id, OnClickListener onClickListener){
        TextView tv = new TextView(mContext);
        tv.setText(str);
        tv.setId(id);
        tv.setOnClickListener(onClickListener);
        tv.setTextColor(color);
        tv.setTextSize(dpi2px(6,mContext));

        LayoutParams tv_params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        tv_params.setMargins(dpi2px(18,mContext),dpi2px(5,mContext),dpi2px(18,mContext),dpi2px(5,mContext));
        tv.setLayoutParams(tv_params);

        content.addView(tv);
    }

    //隐藏文本框
    public void addTextViewGone(String str, int id){
        TextView tv = new TextView(mContext);
        tv.setText(str);
        tv.setId(id);
        tv.setVisibility(GONE);
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
        wxidParams.setMargins(dpi2px(16,mContext),dpi2px(3,mContext), dpi2px(16,mContext), 0);
        editText.setLayoutParams(wxidParams);

        content.addView(editText);
    }

    //编辑框
    public void addEdit(String hint, int id){
        EditText editText = new EditText(mContext);
        editText.setHint(hint);
        editText.setId(id);
        editText.setTextSize(dpi2px(6, mContext));

        LayoutParams wxidParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        wxidParams.setMargins(dpi2px(16,mContext),dpi2px(3,mContext), dpi2px(16,mContext), 0);
        editText.setLayoutParams(wxidParams);

        content.addView(editText);
    }

    //添加默认不可见的编辑框
    public void addEditGone(final String key, String dependKey, int id, final String defaultText, String hint){
        EditText editText = new EditText(mContext);
        editText.setId(id);
        editText.setVisibility(prefs.getBoolean(dependKey, false) ? VISIBLE : GONE);
        editText.setTextSize(dpi2px(6, mContext));
        editText.setText(prefs.getString(key, defaultText));
        editText.setHint(hint);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && !s.toString().equals("")){
                    editor.putString(key, s.toString());
                    editor.apply();
                }else {
                    editor.putString(key, defaultText);
                    editor.apply();
                }
            }
        });

        LinearLayout.LayoutParams wxidParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        wxidParams.setMargins(dpi2px(14,mContext),0, dpi2px(14,mContext), 0);
        editText.setLayoutParams(wxidParams);

        content.addView(editText);
    }

    /**
     * 添加开关
     * @param text
     * @param key
     */
    public void addSwitch(String text, final String key){
        final Switch swh = new Switch(mContext);
        swh.setText(text);
        swh.setChecked(prefs.getBoolean(key, false));
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

    /**
     * 添加隐藏开关
     * @param text
     * @param key
     */
    public void addSwitchGone(int id , String text, final String key, String dependKey, boolean default_value,final OnCheckChange onCheckChange){
        final Switch swh = new Switch(mContext);
        swh.setId(id);
        swh.setText(text);
        swh.setVisibility(prefs.getBoolean(dependKey, default_value) ? VISIBLE : GONE);
        swh.setChecked(prefs.getBoolean(key, default_value));
        swh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean(key,swh.isChecked());
                editor.apply();
                if (onCheckChange != null){
                    onCheckChange.onCheckChange(buttonView, isChecked);
                }
            }
        });

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(dpi2px(18,mContext),dpi2px(4,mContext),dpi2px(18,mContext),dpi2px(1, mContext));
        swh.setLayoutParams(params);

        content.addView(swh);
    }

    /**
     * 添加开关,控制UI
     * @param text
     * @param key
     */
    public void addSwitch(String text, final String key, boolean default_value, final OnCheckChange onCheckChange){
        final Switch swh = new Switch(mContext);
        swh.setText(text);
        swh.setChecked(prefs.getBoolean(key, default_value));
        swh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean(key, swh.isChecked());
                editor.apply();
                onCheckChange.onCheckChange(buttonView, isChecked);
            }
        });

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(dpi2px(18,mContext),dpi2px(4,mContext),dpi2px(18,mContext),dpi2px(1, mContext));
        swh.setLayoutParams(params);

        content.addView(swh);
    }

    /**
     * 添加可设置默认值开关Switch
     * @param text
     * @param key
     * @param default_boolean
     */
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
                public void onClick(View v) {
                    click.onClick();
                }
            });
        }
        LayoutParams goParams = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        goParams.setMargins(dpi2px(18,mContext),dpi2px(4,mContext), dpi2px(18,mContext), 0);
        btn.setLayoutParams(goParams);

        content.addView(btn);
    }

    public void addBtnGone(int id, String text, final Click click){
        Button btn = new Button(mContext);
        btn.setText(text);
        btn.setVisibility(GONE);
        btn.setId(id);
        if (click != null){
            btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.onClick();
                }
            });
        }
        LayoutParams goParams = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        goParams.setMargins(dpi2px(18,mContext),dpi2px(4,mContext), dpi2px(18,mContext), 0);
        btn.setLayoutParams(goParams);

        content.addView(btn);
    }

    @SuppressLint("NewApi")
    public void show(boolean cancelOut, boolean cancelable, final Click positive, String sure, final Click negative, final String nagativeString, OnDismiss onDismiss){
        scrollView.addView(content);
        root.addView(scrollView);
        this.addView(root);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(this);
        if (positive != null){
            builder.setPositiveButton(sure, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    positive.onClick();
                }
            });
        }
        if (negative != null){
            builder.setNegativeButton(nagativeString, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    negative.onClick();
                }
            });
        }
        if (onDismiss != null){
            builder.setOnDismissListener(dialog -> onDismiss.onDismiss());
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

    public interface Click{
        void onClick();
    }

    public interface OnDismiss{
        void onDismiss();
    }

    public interface OnCheckedChange{
        void onChecked(AlertDialog baseDialog);

        void onUnChecked(AlertDialog baseDialog);
    }

    public interface OnCheckChange{
        void onCheckChange(CompoundButton buttonView, boolean isChecked);
    }
}
