package com.thomas.test.di.components;


import com.thomas.test.di.modules.AdapterModule;
import com.thomas.test.di.scope.ActivityScope;
import com.thomas.test.ui.MainActivity;
import dagger.Component;

@Component(modules= AdapterModule.class, dependencies = ApplicationComponent.class)
@ActivityScope
public interface MainActivityComponent {

    void injectMainActivity(MainActivity mainActivity);

}
