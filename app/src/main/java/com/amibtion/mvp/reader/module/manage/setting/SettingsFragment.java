package com.amibtion.mvp.reader.module.manage.setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.amibtion.mvp.reader.R;
import com.amibtion.mvp.reader.widget.XFilePickerPreference;
import com.orhanobut.logger.Logger;

/**
 * Created by nieyuxin on 2017/3/21.
 */

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String NO_IMAGE_KEY = "setting_no_image";
    public static final String SAVE_PATH_KEY = "setting_save_path";

    public static final String DEFAULT_SAVE_PATH = "/storage/emulated/0/Reader";

    private XFilePickerPreference  mFilePickerPreference;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_settings);
        _initPreferences();
    }

    private void _initPreferences(){
        mFilePickerPreference = (XFilePickerPreference) findPreference(SAVE_PATH_KEY);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        mFilePickerPreference.setSummary(sharedPreferences.getString(SAVE_PATH_KEY,DEFAULT_SAVE_PATH));
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(NO_IMAGE_KEY)){
            Logger.w(sharedPreferences.getBoolean(NO_IMAGE_KEY,false) +  "");
        } else if(key.equals(SAVE_PATH_KEY)){
            String path = sharedPreferences.getString(SAVE_PATH_KEY,"/sdcard");
            mFilePickerPreference.setSummary(path);
        }
    }
}
