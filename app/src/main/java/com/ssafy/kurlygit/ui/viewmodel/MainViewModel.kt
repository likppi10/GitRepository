package com.ssafy.kurlygit.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.kurlygit.ApplicationClass
import com.ssafy.kurlygit.data.repository.MainRepository
import com.ssafy.kurlygit.ui.view.data.model.RepositoriesModel
import com.ssafy.kurlygit.ui.view.data.model.RepositoryModel
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {

    private val _repositories = MutableLiveData<MutableList<RepositoryModel>>()
    val repositories: LiveData<MutableList<RepositoryModel>>
        get() = _repositories
    private val list: MutableList<RepositoryModel> = mutableListOf()

    private var _rep = MutableLiveData<RepositoriesModel>()
    val rep: LiveData<RepositoriesModel>
        get() = _rep

    @SuppressLint("SetTextI18n")
    fun getRepositories(searchWord: String) = viewModelScope.launch {
        runCatching { mainRepository.getRepositories(searchWord)}
            .onSuccess {

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
                if(it.items.size==0){
                    if(list.size!=0 && searchWord == ApplicationClass.recentWord){
                        // 예상할 수 없는 예외를 차단하기 위해 레파지토리 명을 ""로 지정해준 로딩 아이템만 지우도록 조건을 줍니다.
                        if(list[list.lastIndex].name==""){
                            list.removeAt(list.lastIndex)
                        }
                        ApplicationClass.stopThisIsEnd = true
                    }else{
                        list.clear()
                        ApplicationClass.stopThisIsEnd = true
                        _rep.postValue(it)
                    }
                }else{
                    if(list.size==0){
                        list.addAll(it.items)
                        _rep.postValue(it)
                    }else if(searchWord != ApplicationClass.recentWord){
                        list.clear()
                        list.addAll(it.items)
                        _rep.postValue(it)
                    }else{
                        // 예상할 수 없는 예외를 차단하기 위해 레파지토리 명을 ""로 지정해준 로딩 아이템만 지우도록 조건을 줍니다.
                        if(list[list.lastIndex].name==""){
                            list.removeAt(list.lastIndex)
                        }
                        list.addAll(it.items)
                    }
                    list.add(RepositoryModel(0,"",true,"",0,0))
                }

                _repositories.value = list
                /* 리스트 적용을 마친 후, 최근 검색어를 현재 edtSearchWord에 있는 단어로 바꿔주고 페이지 수를 1 늘립니다. 또, 검색 결과 수를 출력합니다. */
                ApplicationClass.recentWord = searchWord
                ApplicationClass.nowPage++

            }
            .onFailure {
                it.printStackTrace()
            }
    }
}