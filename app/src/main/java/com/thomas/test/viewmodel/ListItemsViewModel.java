package com.thomas.test.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.thomas.test.object.Item;
import com.thomas.test.retrofit.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

public class ListItemsViewModel extends ViewModel {

    private APIInterface apiInterface;
    private MutableLiveData<List<Item>> listItems;

    public ListItemsViewModel(APIInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    @Singleton
    public static class Factory implements ViewModelProvider.Factory{

        private final APIInterface apiInterface;

        @Inject
        public Factory(APIInterface apiInterface) {
            this.apiInterface = apiInterface;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ListItemsViewModel(apiInterface);
        }
    }



    public LiveData<List<Item>> getListItems(){
        if(listItems == null){
            listItems = new MutableLiveData<>();
            loadItems();
        }
        return listItems;
    }

    private void loadItems() {

        apiInterface.getItems().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                listItems.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {

            }
        });
    }


}
