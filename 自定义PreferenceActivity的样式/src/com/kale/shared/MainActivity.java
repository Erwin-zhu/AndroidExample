package com.kale.shared;

import java.util.List;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends PreferenceActivity {

	SharedPreferences sp;
	SharedPreferences.Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置背景图，给activity设置后。所有fragment的背景都会改了，十分方便！
		getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgColor));
		//setContentView(R.layout.activity_main); 这里就不能设置布局了
    	
		sp = getSharedPreferences("kaleShared", MODE_PRIVATE);
		editor = sp.edit();
		editor.putString("KEY", "value");
		editor.commit();
		
		if (sp.contains("KEY")) {
			System.out.println("have a key");
		}

		;
    }
    /**
     * Populate the activity with the top-level headers.
     */
    @Override
    public void onBuildHeaders(List<Header> target) {
		//下面我们从源码的角度来自己搭建整个布局，所以我们设置布局。整个布局里面用一个list，id是默认的@android:id/list
		setContentView(R.layout.preference_hearders_frame);
        loadHeadersFromResource(R.xml.preference_headers, target);
    }
    
    public static class Prefs0Fragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.customer_preferences);
        }
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
        		Bundle savedInstanceState) {
        	View v = inflater.inflate(R.layout.preference_hearders_frame, container, false);
        	return v;
        }
    }
    
    /**
     * This fragment shows the preferences for the first header.
     */
    public static class Prefs1Fragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            

            // Make sure default values are applied.  In a real app, you would
            // want this in a shared function that is used to retrieve the
            // SharedPreferences wherever they are needed.
            PreferenceManager.setDefaultValues(getActivity(),R.xml.fx_setting, false);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.fragmented_preferences);
        }
    }

   /**
     * This fragment contains a second-level set of preference that you
     * can get to by tapping an item in the first preferences fragment.
     */
    /**
     * @author:Jack Tony
     * @tips  :在第一个fragment中点击一个PreferenceScreen中的fragment对象启动的fragment
     * @date  :2014-8-4
     */
    public static class Prefs1FragmentInner extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Can retrieve arguments from preference XML.
            Log.i("args", "Arguments: " + getArguments());

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.fx_setting);
        }
    }

    /**
     * This fragment shows the preferences for the second header.
     */
    public static class Prefs2Fragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Can retrieve arguments from headers XML.
            Log.i("args", "Arguments: " + getArguments());

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.display_prefs);
        }
    }
}
