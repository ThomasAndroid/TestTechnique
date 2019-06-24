package com.thomas.test.ui

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
import javax.inject.Inject

class MainActivity : AppCompatActivity() ,RecyclerViewAdapter.ClickListener{

    override fun onClickItem(item: Item?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Inject
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

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
    }
}
