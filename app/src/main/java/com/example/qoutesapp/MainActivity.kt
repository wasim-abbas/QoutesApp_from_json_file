package com.example.qoutesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var mainViewModel: MainViewModel
    private val tv_text: TextView get() = findViewById(R.id.tv_qoute)
    private val author: TextView get() = findViewById(R.id.tv_author)
    private lateinit var next: Button
    private lateinit var previous: Button
    private lateinit var floatingBtn: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel =
            ViewModelProvider(this, ViewModelFactory(application)).get(MainViewModel::class.java)
        setQoutes(mainViewModel.getQuotes())

        next = findViewById(R.id.btnNext)
        previous = findViewById(R.id.btnPrevious)
        floatingBtn= findViewById(R.id.floatingActionButton)

        next.setOnClickListener(this)
        previous.setOnClickListener(this)
        floatingBtn.setOnClickListener(this)
    }

    fun setQoutes(quotes: Quotes) {
        tv_text.text = quotes.text
        author.text = quotes.author
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnNext -> setQoutes(mainViewModel.nextQuotes())
            R.id.btnPrevious -> setQoutes(mainViewModel.previousQuotes())
            R.id.floatingActionButton ->{
                val intent= Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuotes().text)
                startActivity(intent)
            }
        }
    }
}