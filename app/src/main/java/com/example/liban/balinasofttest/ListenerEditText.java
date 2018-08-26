package com.example.liban.balinasofttest;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by liban on 26.08.2018.
 */

public class ListenerEditText implements TextWatcher {

    private AutoCompleteTextView mAutoCompleteTextViewEmail;
    private String a;
    private Context mContext;

    public ListenerEditText(AutoCompleteTextView mAutoCompleteTextViewEmail, Context mContext) {
        this.mAutoCompleteTextViewEmail = mAutoCompleteTextViewEmail;
        this.mContext = mContext;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        a = charSequence.toString();
        init();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    private void init() {
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_dropdown_item_1line, createList());
        mAutoCompleteTextViewEmail.setAdapter(arrayAdapter);
//            adapter = new Adapter(this, createList());
//            mAutoCompleteTextViewEmail.setAdapter(adapter);

    }

    private List<String> createList() {

        String[] array = {a + "@mail.ru", a + "@mail.com", a + "@gmail.com", a + "@yandex.ru", a + "@yahoo.com",
                a + "@tut.by", a + "@front.ru", a + "@bk.ru"};

        return Arrays.asList(array);

    }
}
