package com.ssafy.kurlygit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.kurlygit.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter

    val viewModel:MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter()
        binding.recyclerView.adapter = adapter

        observeData()

        binding.btnSerach.setOnClickListener {
            val searchWord = binding.edtSearchWord.text.toString()

            // API와 통신하기 직전의 지점으로 다른 단어를 새로 검색한 경우 페이지를 1로 지정해줘야 합니다.
            if(ApplicationClass.recentWord!=searchWord){
                ApplicationClass.nowPage = 1
            }

            viewModel.getRepositories(searchWord)
            ApplicationClass.stopThisIsEnd = false
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount-1

                /* 스크롤이 끝에 도달했는지 확인합니다.
                   * 마지막으로 불러온 페이지의 데이터가 딱 30개가 되어도,
                   * 30개보다 덜 되도,
                   * 심지어 없어도
                   작동합니다.
                   >> 즉, 끝에만 가면 작동합니다.
                   * stopThisIsEnd로 이를 컨트롤 해주고자 설계했는데, 아래에 어떤 경우 컨트롤 했는지 언급했습니다.
                * */
                if (!binding.recyclerView.canScrollVertically(1)
                    && lastVisibleItemPosition == itemTotalCount
                    && itemTotalCount!=0 && !ApplicationClass.stopThisIsEnd) {

                    viewModel.getRepositories(ApplicationClass.recentWord)
                }
            }
        })
    }

    private fun observeData() {
        viewModel.repositories.observe(this) {
            adapter.submitList(it)
        }
        viewModel.rep.observe(this) {
            with(binding){
                repos = it
                nonRes = it.total_count==0
            }
        }

    }
}
