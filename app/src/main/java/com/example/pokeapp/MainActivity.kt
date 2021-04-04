package com.example.pokeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokeapp.ui.main.Comunicator
import com.example.pokeapp.ui.main.PokemonDetailsFragment
import com.example.pokeapp.ui.main.PokemonListFragment
import javax.inject.Inject

/***
 * Main and only Activity where there is an injection to provide the view model factory to use in fragments
 */

class MainActivity : AppCompatActivity(), Comunicator {
    @Inject
    lateinit var mainComponent: MainComponent

    val fragmentA = PokemonListFragment.newInstance()
    lateinit var fragmentB:PokemonDetailsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (applicationContext as PokemonAPP).appComponent.mainComponent().create()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragmentA)
                .commitNow()
        }
    }

    override fun passData(pokemonName: String) {
        val bundle = Bundle ()
        bundle.putString("Pokemon Name", pokemonName)

        fragmentB = PokemonDetailsFragment.newInstance()
        fragmentB.arguments = bundle
        
        supportFragmentManager.beginTransaction().replace(R.id.container, fragmentB, "FRAGMENT B").commitNow()
    }

    override fun goBack() {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragmentA, "FRAGMENT A").commitNow()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.findFragmentByTag("FRAGMENT B")?.isVisible == true)
            goBack()
        else
            super.onBackPressed()
    }
}
