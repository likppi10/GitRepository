package com.ssafy.kurlygit

interface BaseCallback<T> {
    fun onError(t: Throwable)

    fun onSuccess(code: Int, responseData: T)

    fun onFailure(code: Int)
}