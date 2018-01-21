package ru.ekzeget.ui.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import ru.ekzeget.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        view_nz_list.visibility = View.VISIBLE
        view_vz_list.visibility = View.GONE
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_nz -> {
                view_nz_list.visibility = View.VISIBLE
                view_vz_list.visibility = View.GONE
            }
            R.id.navigation_vz -> {
                view_nz_list.visibility = View.GONE
                view_vz_list.visibility = View.VISIBLE
            }
        }
        return@OnNavigationItemSelectedListener true
    }
}