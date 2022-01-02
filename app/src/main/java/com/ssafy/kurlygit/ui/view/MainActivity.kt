package com.ssafy.kurlygit.ui.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.kurlygit.ApplicationClass
import com.ssafy.kurlygit.ui.viewmodel.MainViewModel
import com.ssafy.kurlygit.databinding.ActivityMainBinding
import com.ssafy.kurlygit.ui.adapter.MainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter

    val viewModel: MainViewModel by viewModel()

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

            // 검색 버튼을 누를 시 새로운 검색으로 취급하여, 최근 단어를 ""로,  페이지를 1로 지정해줘야 합니다.
            ApplicationClass.nowPage = 1
            ApplicationClass.recentWord = ""

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
                    && itemTotalCount!=0 && !ApplicationClass.stopThisIsEnd
                ) {
                    viewModel.getRepositories(ApplicationClass.recentWord)
                }
            }
        })
    }

    private fun observeData() {
        /* repositories에서 repositoryModel의 변경을 submit 해줍니다.
        * rep에서 검색 결과가 없는 것을 알려주는 view와 검색 결과 수에 대한 변경을 observe 합니다.
        * */
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
