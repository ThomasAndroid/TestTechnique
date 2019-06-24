package com.thomas.test.di.modules;

import com.thomas.test.adapter.RecyclerViewAdapter;
import com.thomas.test.di.scope.ActivityScope;
import com.thomas.test.ui.MainActivity;
import dagger.Module;
import dagger.Provides;


@Module(includes = {MainActivityModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getAdapter(RecyclerViewAdapter.ClickListener clickListener) {
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(MainActivity mainActivity) {
        return mainActivity;
    }
}
