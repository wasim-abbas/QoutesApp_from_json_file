package com.example.qoutesapp

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context) : ViewModel() {
    private var quotesList: Array<Quotes> = emptyArray()
    private var index = 0

    init {
        quotesList = loadQuotesList()
    }

    private fun loadQuotesList(): Array<Quotes> {
        val inputStream = context.assets.open("quotes.json")
        val size :Int = inputStream.available() // find size of input stream
        val buffer = ByteArray(size)// define byte array and put size
        inputStream.read(buffer) // read byte array
        inputStream.close()
        val json =   String(buffer, Charsets.UTF_8) //convert bytearray to string by passing array & types
        val gson = Gson()
        return gson.fromJson(json,Array<Quotes>::class.java)
    }

    fun getQuotes() = quotesList[index]
    fun nextQuotes() = quotesList[++index]
    fun previousQuotes() = quotesList[--index]


}