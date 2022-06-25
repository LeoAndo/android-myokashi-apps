package com.leoleo2.myokashi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private val okashiListAdapter = OkashiListAdapter(onItemClick = {
        // TODO ブラウザを起動する処理を入れる.
        AppLaunchHelper(this).launchBrowserApp(it.url)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSearch = findViewById<Button>(R.id.buttonSearch)
        val textInputLayoutKeyword = findViewById<TextInputLayout>(R.id.textInputLayoutKeyword)
        val textInputEditTextKeyword =
            findViewById<TextInputEditText>(R.id.textInputEditTextKeyword)

        buttonSearch.isEnabled = false

        textInputEditTextKeyword.doOnTextChanged { text, start, before, count ->
            val isErrorEnabled = text.isNullOrEmpty()
            buttonSearch.isEnabled = !isErrorEnabled
            textInputLayoutKeyword.isErrorEnabled = isErrorEnabled
            if (isErrorEnabled) {
                textInputLayoutKeyword.error = "未入力です！"
            }
        }

        buttonSearch.setOnClickListener {
            val keyword = textInputEditTextKeyword.text.toString()
            viewModel.searchOkashi(keyword)
        }

        val progressIndicator = findViewById<CircularProgressIndicator>(R.id.progressIndicator)
        viewModel.uiState.observe(this) {
            progressIndicator.isVisible = it is UiState.Loading
            when (it) {
                is UiState.Error -> {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_LONG).show()
                }
                is UiState.Success -> {
                    okashiListAdapter.submitList(it.items)
                }
                else -> {
                    // 何も処理しない.
                }
            }
        }

        val okashiList = findViewById<RecyclerView>(R.id.okashiList)
        okashiList.adapter = okashiListAdapter
        okashiList.layoutManager = LinearLayoutManager(this)
    }
}