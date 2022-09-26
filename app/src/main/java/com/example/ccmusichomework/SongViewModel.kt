package com.example.ccmusichomework

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ccmusichomework.model.Song
import com.example.ccmusichomework.model.mapToSongList
import com.example.ccmusichomework.rest.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SongViewModel(): ViewModel() {
    private val _songs : MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)

    val songs :LiveData<UIState> get()= _songs

    private val serviceAPI = MusicApi.serviceApi
    private val serviceAPIC = ClassicMusicApi.serviceApi
    private val serviceAPIR = RockMusicApi.serviceApi

    var songPlaying : Song? = null




    var userListSong : List<Song> = emptyList()


    /**
     * Sets up the fuction that will go though the process of loading the API
     * using flow and the UIstates we made
     */
    fun getSongs() {
        viewModelScope.launch() {
            val flowHolder : Flow<UIState> = flow {
                emit(UIState.LOADING)
                try{
                   val response = serviceAPI.getMusic()
                    if(response.isSuccessful)
                    {
                        response.body()?.let {
                            Log.d("Network", "This worked buddy")
                            emit(UIState.SUCCESS(it.songData.mapToSongList()))
                        }?: throw NullResponseFromServer("Songs are null")
                    } else {
                        throw FailureResponseFromServer(response.errorBody()?.string())
                    }
                } catch (e : Exception){
                    emit(UIState.FAILURE(e))
                    Log.e("Network", "Caught Error: ${e.localizedMessage}", e)
                }
            }
            flowHolder.collect {
                withContext(Dispatchers.Main) {

                }
                _songs.postValue(it)
            }
                    }
                }
    fun getClassicSongs() {
        viewModelScope.launch() {
            val flowHolder : Flow<UIState> = flow {
                emit(UIState.LOADING)
                try{
                    val response = serviceAPIC.getMusic()
                    if(response.isSuccessful)
                    {
                        response.body()?.let {
                            Log.d("Network", "This worked buddy")
                            emit(UIState.SUCCESS(it.songData.mapToSongList()))
                        }?: throw NullResponseFromServer("Songs are null")
                    } else {
                        throw FailureResponseFromServer(response.errorBody()?.string())
                    }
                } catch (e : Exception){
                    emit(UIState.FAILURE(e))
                    Log.e("Network", "Caught Error: ${e.localizedMessage}", e)
                }
            }
            flowHolder.collect {
                withContext(Dispatchers.Main) {

                }
                _songs.postValue(it)
            }
        }
    }
    fun getRockSongs() {
        viewModelScope.launch() {
            val flowHolder : Flow<UIState> = flow {
                emit(UIState.LOADING)
                try{
                    val response = serviceAPIR.getMusic()
                    if(response.isSuccessful)
                    {
                        response.body()?.let {
                            Log.d("Network", "This worked buddy")
                            emit(UIState.SUCCESS(it.songData.mapToSongList()))
                        }?: throw NullResponseFromServer("Songs are null")
                    } else {
                        throw FailureResponseFromServer(response.errorBody()?.string())
                    }
                } catch (e : Exception){
                    emit(UIState.FAILURE(e))
                    Log.e("Network", "Caught Error: ${e.localizedMessage}", e)
                }
            }
            flowHolder.collect {
                withContext(Dispatchers.Main) {

                }
                _songs.postValue(it)
            }
        }
    }




            }