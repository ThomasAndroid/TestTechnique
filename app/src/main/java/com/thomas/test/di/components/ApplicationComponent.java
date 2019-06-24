package com.thomas.test.di.components;


import android.content.Context;
import com.thomas.test.MyApplication;
import com.thomas.test.di.modules.ContextModule;
import com.thomas.test.di.modules.RetrofitModule;
import com.thomas.test.di.modules.ViewModelFactoryModule;
import com.thomas.test.di.scope.ApplicationScope;
import com.thomas.test.retrofit.APIInterface;
import com.thomas.test.viewmodel.ListItemsViewModel;
import dagger.Component;

@Component(modules = {RetrofitModule.class, ContextModule.class, ViewModelFactoryModule.class})
@ApplicationScope
public interface ApplicationComponent {

    Context getContext();

    APIInterface getAPIInterface();

    ListItemsViewModel.Factory provideListItemsViewModelFactory();

    void inject(MyApplication application);
}
