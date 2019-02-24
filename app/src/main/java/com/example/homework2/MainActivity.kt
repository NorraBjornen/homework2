package com.example.homework2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE

class MainActivity : AppCompatActivity() {

    companion object {
        private const val CURRENT_NUMBER_KEY = "number_key"
    }

    private var optionDelete : MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.addOnBackStackChangedListener{
            optionDelete?.isEnabled = supportFragmentManager.backStackEntryCount != 0
            if(backStackIsNotEmpty())
                supportFragmentManager.beginTransaction().show(getLastFragment()).commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        optionDelete = menu.findItem(R.id.action_delete)
        optionDelete?.isEnabled = supportFragmentManager.backStackEntryCount != 0
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_add -> {
                if(backStackIsNotEmpty())
                    supportFragmentManager.beginTransaction().hide(getLastFragment()).commit()

                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, MyFragment.newInstance(supportFragmentManager.backStackEntryCount+1))
                    .addToBackStack((supportFragmentManager.backStackEntryCount+1).toString())
                    .commit()
                return true
            }
            R.id.action_delete -> {
                supportFragmentManager.popBackStack(supportFragmentManager.backStackEntryCount.toString(), POP_BACK_STACK_INCLUSIVE)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_NUMBER_KEY, supportFragmentManager.backStackEntryCount)
    }

    private fun getLastFragment() : Fragment {
        return supportFragmentManager.fragments[supportFragmentManager.backStackEntryCount-1]
    }

    private fun backStackIsNotEmpty() : Boolean {
        return supportFragmentManager.backStackEntryCount != 0
    }
}
