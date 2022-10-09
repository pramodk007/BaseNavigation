package com.example.basenavigation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

private typealias ActivityViewBindingInflater<VB> = (inflater: LayoutInflater) -> VB

abstract class BaseNavigationComponent<VB : ViewDataBinding>(
    private val bindingInflater: ActivityViewBindingInflater<VB>
) : AppCompatActivity() {

    protected lateinit var binding: VB
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    //get navhost id
    abstract val getnavHostID: Int
    //get fragment ID Set
    abstract val getFragmentSet:Set<Int>
    //get bottom navigation view
    abstract val getBottomNavigationView:BottomNavigationView
    //get toolBar
    abstract val getToolBar:Toolbar

    abstract fun handleAction(navController: NavController)

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)
        setAsActionBar(getToolBar)
        setUpNavigation(getBottomNavigationView)
        handleAction(navController)
    }

    private fun setUpNavigation(getBottomNavigationView: BottomNavigationView){
        val navHostFragment = supportFragmentManager.findFragmentById(getnavHostID) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            getFragmentSet
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        getBottomNavigationView.setupWithNavController(navController)
    }
    private fun setAsActionBar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        //toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun showBottomNav() {
        getBottomNavigationView.visibility = View.VISIBLE
    }
    fun hideBottomNav() {
        getBottomNavigationView.visibility = View.GONE
    }
    fun showBackArrow(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }
    fun showToolbar(){
        supportActionBar?.show()
    }
    fun hideToolbar(){
        supportActionBar?.hide()
    }

}


