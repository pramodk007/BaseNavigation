package com.example.basenavigation

import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import com.example.basenavigation.base.BaseNavigationComponent
import com.example.basenavigation.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseNavigationComponent<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override val getnavHostID: Int
        get() = R.id.navHost

    override val getFragmentSet: Set<Int>
        get() = setOf(
            R.id.movieListFragment,
            R.id.movieDetailsFragment
        )

    override val getToolBar: Toolbar
        get() = binding.materialToolbar

    override val getBottomNavigationView: BottomNavigationView
        get() = binding.bottomNavigationView

    override fun handleAction(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.movieListFragment ->{
                    showBottomNav()
                }
                R.id.movieDetailsFragment -> {
                    hideBottomNav()
                    showBackArrow()
                }
            }
        }
    }

}