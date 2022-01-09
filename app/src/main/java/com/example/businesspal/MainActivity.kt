package com.example.businesspal

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.businesspal.adapter.MainAdapter
import com.example.businesspal.model.MainViewModel
import com.example.businesspal.model.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG: String = "Main Activity";

    lateinit var viewModel: MainViewModel

    private val adapter = MainAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, MovieViewModelFactory()).get(MainViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.movieList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setBusinessList(it)
        })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
        viewModel.getAllMovies(this@MainActivity)

        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                Log.d(TAG, "onCreate: Connected")
                viewModel.getAllMovies(this@MainActivity)
                //take action when network connection is gained
            }

            override fun onLost(network: Network) {
                viewModel.getAllMovies(this@MainActivity)
                Log.d(TAG, "onCreate: Lost")
                //take action when network connection is lost
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.reload) {
            Toast.makeText(this, "Reloading!", Toast.LENGTH_SHORT).show()
            viewModel.getAllMovies(this)
        } else if (item.itemId == R.id.add) {
            val intent = Intent(this, AddNewDataActivity::class.java)
            startActivity(intent)
        }

        return true
    }

}