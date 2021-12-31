package com.ssafy.kurlygit

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.kurlygit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter
    private var stopThisIsEnd = false
    private var repositoryList: MutableList<Repository> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(repositoryList)
        binding.recyclerView.adapter = adapter

        binding.btnSerach.setOnClickListener {
            val searchWord = binding.edtSearchWord.text.toString()
            RepositoryService().getRepositories(searchWord, RepositoryCallback())
            stopThisIsEnd = false

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
                if (!binding.recyclerView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount && itemTotalCount!=0 && !stopThisIsEnd) {
                    val searchWord = binding.edtSearchWord.text.toString()
                    RepositoryService().getRepositories(searchWord, RepositoryCallback())
                }
            }
        })

    }

    inner class RepositoryCallback : BaseCallback<Repositories> {

        @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
        override fun onSuccess(code: Int, responseData: Repositories) {

            /*
            * 1. 결과가 없을 때
            * 1-1. 어댑터에는 이미 불러온 리스트가 있고, 더 불러올게 없을 경우
            * 1-2. 원래 없었던 경우 (처음 검색했을 때, 한 번 검색한 상태에서 또 검색했을 때) 모두의 경우
            * >>>>>>> 1의 경우 한번이라도 이 곳에 오게되면, addOnScrollListener는 컨트롤 할 수 없더라도,
            *           불필요한 레트로핏 통신으로 가는 것은 막아줘야합니다. (stopThisIsEnd를 true로 바꿔줍니다.)
            *
            * 2. 결과가 있다.
            * 2-1. //첫번째로// 검색할 때 > 검색
            * 2-2. 한번 검색을 하고, 다른 것을 검색할 때 > 기존의 것을 지우고, //새로// 검색
            * 2-3. 첫번째 검색 후 [[[추가로]]] 볼 때
            */

            if(responseData.items.size==0){
                if(adapter.itemCount!=0 && binding.edtSearchWord.text.toString() == ApplicationClass.recentWord){
                    adapter.deleteLoading()
                    adapter.notifyDataSetChanged()
                    stopThisIsEnd = true
                }else{
                    adapter.clearList()
                    binding.tvNoRes.visibility= VISIBLE
                    stopThisIsEnd = true
                }
            }else{
                binding.tvNoRes.visibility= GONE

                if(adapter.itemCount==0){
                    adapter.setList(responseData.items)
                    adapter.notifyDataSetChanged()
                }else if(binding.edtSearchWord.text.toString() != ApplicationClass.recentWord){
                    adapter.clearList()
                    adapter.setList(responseData.items)
                    adapter.notifyDataSetChanged()
                }else{
                    adapter.deleteLoading()
                    adapter.setList(responseData.items)
                    /*
                    위의 두 경우는 새로 0부터 30까지 로딩하기 때문에 notifyDataSetChanged를 해도 부담이 없습니다.
                    이 경우에도 adapter.notifyDataSetChanged()를 사용해도 작동은 하지만,
                    추가 로딩 중인 지금이 몇번째 페이지인지 알 수 없고,
                    그렇다면 전체를 notify 하는 notifyDataSetChanged의 기능이 비효율적일 수 밖에 없습니다.
                    그렇기 때문에 구간을 선택해서 추가된 정보를 notify 하는 notifyItemRangeInserted를 쓰는 것이 효율적입니다.
                     */
                    adapter.notifyItemRangeInserted((ApplicationClass.nowPage - 1) * 30, 30)
                }
            }

            /* 리스트 적용을 마친 후, 최근 검색어를 현재 edtSearchWord에 있는 단어로 바꿔주고, 검색 결과 수를 출력합니다. */
            ApplicationClass.recentWord = binding.edtSearchWord.text.toString()
            binding.tvResultCnt.text = "${responseData.total_count.toString()} 개"

        }

        override fun onError(t: Throwable) {
            Log.d("Test", "실패 "+t.message ?: "Repository 정보 받아오는 중 통신오류")
        }

        override fun onFailure(code: Int) {
            Log.d("Test", "실패 onResponse: Error Code $code")
        }
    }
}