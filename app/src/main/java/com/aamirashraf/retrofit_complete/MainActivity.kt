package com.aamirashraf.retrofit_complete

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.aamirashraf.retrofit_complete.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoadapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        //for the retrofit don't forget to include the internet permission
        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible=true
            //now the most important concept
            val response=try {
                RetrofitInstance.api.getTodos()
            }catch (e:IOException){
                Log.d("hello",e.message.toString())
                binding.progressBar.isVisible=false
                return@launchWhenCreated
            }
            catch (e:HttpException){
                Log.d("hello",e.message())
                binding.progressBar.isVisible=false
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body()!=null){
                //now we really got the todos
                //important stuff for todoadapter also
                todoadapter.todos=response.body()!!

            }
            else{
                Log.d("hello","no response")
            }
            binding.progressBar.isVisible=false
        }

    }
    private fun setupRecyclerView() = binding.rvTodos.apply {
       todoadapter=TodoAdapter()
        adapter=todoadapter
        layoutManager=LinearLayoutManager(this@MainActivity)
    }
}