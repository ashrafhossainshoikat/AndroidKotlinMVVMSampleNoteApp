package com.ashraf.notemeappkotlin.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.widget.ViewPager2
import com.ashraf.notemeappkotlin.R

import com.ashraf.notemeappkotlin.ui.main.adapter.FragmentViewPagerAdapter
import com.ashraf.notemeappkotlin.ui.main.adapter.NoteRVAdapter
import com.ashraf.notemeappkotlin.ui.main.viewmodel.NoteViewModel
import com.ashraf.notemeappkotlin.utils.Constants
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator





class HomeActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager2
    lateinit var tabs:TabLayout

    lateinit var addFAB: FloatingActionButton
    private val tabIcons = intArrayOf(
        R.drawable.ic_open,
        R.drawable.ic_in_progress,
        R.drawable.ic_test,
        R.drawable.ic_done
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        addFAB = findViewById(R.id.fab)
        val adapter = FragmentViewPagerAdapter(supportFragmentManager,lifecycle)
        adapter.addFragment(NoteFragment.newInstance(Constants.STATUS_OPEN), Constants.STATUS_OPEN)
        adapter.addFragment(NoteFragment.newInstance(Constants.STATUS_IN_PROGRESS), Constants.STATUS_IN_PROGRESS)
        adapter.addFragment(NoteFragment.newInstance(Constants.STATUS_TEST), Constants.STATUS_TEST)
        adapter.addFragment(NoteFragment.newInstance(Constants.STATUS_DONE), Constants.STATUS_DONE)
        adapter.notifyDataSetChanged()
        viewPager=findViewById(R.id.viewPager)
        tabs=findViewById(R.id.tabs)
        viewPager.adapter =adapter
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
            tab.icon= getDrawable(tabIcons[position])
            viewPager.setCurrentItem(tab.position, true)
        }.attach()

        addFAB.setOnClickListener {
            //adding a click listner for fab button and opening a new intent to add a new note.
            val intent = Intent(this, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }


    }



}