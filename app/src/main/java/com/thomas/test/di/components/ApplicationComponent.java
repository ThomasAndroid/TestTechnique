package com.thomas.test.di.components;


import android.content.Context;
import com.thomas.test.MyApplication;
import com.thomas.test.di.modules.ContextModule;
import com.thomas.test.di.scope.ApplicationScope;
import dagger.Component;

@Component(modules = ContextModule.class)
@ApplicationScope
public interface ApplicationComponent {

    Context getContext();

    void inject(MyApplication application);
}
