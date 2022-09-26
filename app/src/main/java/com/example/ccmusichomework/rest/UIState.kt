package com.example.ccmusichomework.rest


import com.example.ccmusichomework.model.Song
import java.lang.Exception

sealed class UIState{
    object LOADING : UIState()
    data class SUCCESS(val songs: List<Song>) : UIState()
    data class FAILURE (val error : Exception) : UIState()
}
