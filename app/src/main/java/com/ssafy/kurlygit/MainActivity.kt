package com.ssafy.kurlygit

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.kurlygit.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//        val itemList = listOf(1,2,3)
//        val adapter = MainAdapter(itemList)
//
//        binding.recyclerView.adapter = adapter
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.btnSerach.setOnClickListener {
            val searchWord = binding.edtSearchWord.text.toString()
            Log.d("Test", "searchWord :  $searchWord")
            RepositoryService().getRepositories(searchWord, RepositoryCallback())
        }



    }

    inner class RepositoryCallback : BaseCallback<Repositories> {
        override fun onSuccess(code: Int, responseData: Repositories) {

            Toast.makeText(this@MainActivity, ""+responseData,Toast.LENGTH_SHORT).show()
            Log.d("Test", "성공 $responseData")


        }

        override fun onError(t: Throwable) {
            Log.d("Test", "실패 "+t.message ?: "Repository 정보 받아오는 중 통신오류")
        }

        override fun onFailure(code: Int) {
            Log.d("Test", "실패 onResponse: Error Code $code")
        }
    }
}