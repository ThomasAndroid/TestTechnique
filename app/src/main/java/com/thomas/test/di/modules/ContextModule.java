package com.thomas.test.di.modules;

import android.content.Context;
import com.thomas.test.di.scope.ApplicationScope;
import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    Context getContext(){
        return context;
    }
}
