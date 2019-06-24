package com.thomas.test.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.thomas.test.MyApplication
import com.thomas.test.R
import com.thomas.test.`object`.Item
import com.thomas.test.adapter.RecyclerViewAdapter
import com.thomas.test.di.components.DaggerMainActivityComponent
import com.thomas.test.di.modules.MainActivityModule
import com.thomas.test.viewmodel.ListItemsViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() ,RecyclerViewAdapter.ClickListener{

    override fun onClickItem(item: Item?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Inject
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    @Inject
    lateinit var viewModelFactory: ListItemsViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        val applicationComponent = MyApplication.get(this).applicationComponent
        val mainActivityComponent = DaggerMainActivityComponent.builder()
            .mainActivityModule(MainActivityModule(this))
            .applicationComponent(applicationComponent)
            .build()

        mainActivityComponent.injectMainActivity(this)
        recyclerView.adapter = recyclerViewAdapter


        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListItemsViewModel::class.java)
        viewModel.listItems.observe(this, Observer { items -> updateList(items) })

    }

    private fun updateList(list: List<Item>?) {
        recyclerViewAdapter.setListItems(list)
    }
}
