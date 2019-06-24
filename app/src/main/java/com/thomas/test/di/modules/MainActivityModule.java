package com.thomas.test.di.modules;


import com.thomas.test.di.scope.ActivityScope;
import com.thomas.test.ui.MainActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    private MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @ActivityScope
    public MainActivity providesMainActivity() {
        return mainActivity;
    }

}