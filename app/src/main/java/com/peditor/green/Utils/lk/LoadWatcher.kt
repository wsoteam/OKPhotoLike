package com.peditor.green.Utils.lk

object LoadWatcher {
    private var watcherCallbacks: WatcherCallbacks? = null

    const val K_FLOW = 0
    const val V_FLOW = 1

    interface WatcherCallbacks{
        fun loaded(number : Int, url : String)
        fun choiceFlow(numberFlow : Int)
    }

    fun speakAboutLoaded(number : Int, url : String){
        watcherCallbacks?.loaded(number, url)
    }


    fun speakAboutFlow(flow : Int){
        watcherCallbacks?.choiceFlow(flow)
    }

    fun setCallbacks(watcherCallbacks: WatcherCallbacks){
        this.watcherCallbacks = watcherCallbacks
    }
}