package com.example.homework2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE

class MainActivity : AppCompatActivity() {

    companion object {
        private const val CURRENT_NUMBER_KEY = "number_key"
    }

    private var number = 0
    private var optionDelete : MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState != null)
            number = savedInstanceState.getInt(CURRENT_NUMBER_KEY)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        optionDelete = menu.findItem(R.id.action_delete)
        if(number == 0)
            optionDelete?.isEnabled = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_add -> {
                addDoc()
                return true
            }
            R.id.action_delete -> {
                removeDoc()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_NUMBER_KEY, number)
    }

    private fun addDoc(){
        number++
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, MyFragment.newInstance(number))
            .addToBackStack("$number")
            .commit()
        optionDelete?.isEnabled = true
    }

    private fun removeDoc(){
        supportFragmentManager.popBackStack("$number", POP_BACK_STACK_INCLUSIVE)
        decrease()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        decrease()
    }

    private fun decrease(){
        number--
        if(number == 0)
            optionDelete?.isEnabled = false
    }
}
