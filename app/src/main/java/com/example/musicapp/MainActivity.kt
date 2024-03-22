package com.example.musicapp

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.musicapp.adapter.SongAdapter
import com.example.musicapp.databinding.ActivityMainBinding
import com.example.musicapp.vm.MainViewModel
import com.facebook.drawee.backends.pipeline.Fresco

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel
    private val mAdapter by lazy { SongAdapter() }
    private var type = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init(){
        Fresco.initialize(this)

        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        activityMainBinding.etContent.apply {
            inputType = InputType.TYPE_CLASS_TEXT
            imeOptions = EditorInfo.IME_ACTION_DONE
            setOnEditorActionListener { v, actionId, event ->
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    val input = activityMainBinding.etContent.text.toString()
                    if(input.isEmpty()){
                        Toast.makeText(this@MainActivity, "Please input search content", Toast.LENGTH_SHORT).show()
                    }else{
                        requestData(input)
                    }
                    true
                }
                false
            }
        }

        activityMainBinding.rbOff.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked){
                type = 0
                val input = activityMainBinding.etContent.text.toString()
                if(input.isNotEmpty()){
                    requestData(input)
                }
            }
        }

        activityMainBinding.rbSort.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                type = 1
                val input = activityMainBinding.etContent.text.toString()
                if(input.isNotEmpty()){
                    requestData(input)
                }
            }
        }

        activityMainBinding.rv.adapter = mAdapter
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mViewModel.response.observe(this) {
            activityMainBinding.progressBar.visibility = View.GONE
            mAdapter.setNewInstance(it.results.toMutableList())
            activityMainBinding.rv.postDelayed({
                                               activityMainBinding.rv.scrollToPosition(0)
            }, 100)
        }
    }

    private fun requestData(input: String){
        activityMainBinding.progressBar.visibility = View.VISIBLE
        mViewModel.getSong(input, type)
    }
}