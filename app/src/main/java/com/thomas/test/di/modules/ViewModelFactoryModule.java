package com.thomas.test.di.modules;


import com.thomas.test.di.scope.ApplicationScope;
import com.thomas.test.retrofit.APIInterface;
import com.thomas.test.viewmodel.ListItemsViewModel;
import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelFactoryModule {

    @Provides
    @ApplicationScope
    ListItemsViewModel.Factory provideListItemsViewModelFactory(APIInterface apiInterface){
        return new ListItemsViewModel.Factory(apiInterface);
    }
}
