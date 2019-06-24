package com.thomas.test;

import android.app.Application;
import com.thomas.test.di.components.ApplicationComponent;
import com.thomas.test.di.components.DaggerApplicationComponent;
import com.thomas.test.di.modules.ContextModule;
import com.thomas.test.ui.MainActivity;

public class MyApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this)).build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }

    public static MyApplication get(MainActivity mainActivity){
        return (MyApplication)mainActivity.getApplication();
    }
}
