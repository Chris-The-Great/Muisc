package com.example.ccmusichomework.model

/**
 * Creating data class of the vaules that will be retreived from the json
 */

data class Song (
    val artistName: String = "Invalid Name",
    val collectionName: String = "Invalid Name",
    val trackName: String = "Invalid Name",
    val trackPrice: Double = 99999.99,
    val artistViewUrl: String = "Invalid URL",
    val collectionViewUrl: String = "Invalid URL",
    val trackViewUrl: String = "Invalid URL",
    val previewUrl: String = "Invalid URL",
    val artworkUrl30: String = "Invalid URL",
    val artworkUrl60: String = "Invalid URL",
    val artworkUrl100: String = "Invalid URL",
)


/**
 * Setting up a list to store the data
 */
fun List<SongData?>?
        .mapToSongList(): List<Song> =
    this?.map {
        Song(
            artistName = it?.artistName ?: "Invalid Name",
            collectionName = it?.collectionName ?: "Invalid Name",
            trackName = it?.trackName ?: "Invalid Name",
            trackPrice = it?.trackPrice ?: 99999.99,
            artistViewUrl = it?.artistViewUrl ?: "Invalid URL",
            collectionViewUrl = it?.collectionViewUrl ?: "Invalid URL",
            trackViewUrl = it?.trackViewUrl ?: "Invalid URL",
            previewUrl = it?.previewUrl ?: "Invalid URL",
            artworkUrl30 = it?.artworkUrl30 ?: "Invalid URL",
            artworkUrl60 = it?.artworkUrl60 ?: "Invalid URL",
            artworkUrl100 = it?.artworkUrl100 ?: "Invalid URL"
        )
    } ?: emptyList()


fun SongData.mapToSong(): Song =
    Song(
        artistName = this.artistName ?: "Invalid Name",
        collectionName = this.collectionName ?: "Invalid Name",
        trackName = this.trackName ?: "Invalid Name",
        trackPrice = this.trackPrice ?: 99999.99,
        artistViewUrl = this.artistViewUrl ?: "Invalid URL",
        collectionViewUrl = this.collectionViewUrl ?: "Invalid URL",
        trackViewUrl = this.trackViewUrl ?: "Invalid URL",
        previewUrl = this.previewUrl ?: "Invalid URL",
        artworkUrl30 = this.artworkUrl30 ?: "Invalid URL",
        artworkUrl60 = this.artworkUrl60 ?: "Invalid URL",
        artworkUrl100 = this.artworkUrl100 ?: "Invalid URL"
    )