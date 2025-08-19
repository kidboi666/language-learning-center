package com.example.flowdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DemoViewModel : ViewModel() {

    /**
     * MutableStateFlow는 현재 상태와 새로운 상태를 업데이트하는 관찰 가능한 상태 홀더 flow입니다.
     * StateFlow는 collector가 활성 상태일 때만 값을 방출합니다.
     */
    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()

    /**
     * MutableSharedFlow는 모든 collector에게 값을 방출하는 핫 flow입니다.
     * 핫 flow는 collector가 없어도 활성 상태를 유지합니다.
     */
    private val _sharedFlow = MutableSharedFlow<Int>(
        // 수집 이전에 버퍼에 저장할 최대값
        replay = 10,
        /**
         * 버퍼가 가득 찼을 때 발생하는 동작을 지정합니다. 여기서는 가장 오래된 값을 삭제합니다.
         * [BufferOverflow.DROP_LATEST]: 가장 최근에 전달된 값을 삭제. 새로운 값이 처리되어도 버퍼는 변경되지 않음.
         * [BufferOverflow.DROP_OLDEST]: 가장 오래된 값을 삭제. 버퍼를 Last-In, First-Out 스택처럼 다룸.
         * [BufferOverflow.SUSPEND]: 버퍼가 가득 차면 플로를 중지
         */
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val sharedFlow = _sharedFlow.asSharedFlow()
    val subCount = _sharedFlow.subscriptionCount

    fun increaseValue() {
        _stateFlow.value++
    }

    fun startSharedFlow() {
        viewModelScope.launch {
            for (i in 1..5) {
                _sharedFlow.emit(i)
                delay(2000)
            }
        }
    }
}
