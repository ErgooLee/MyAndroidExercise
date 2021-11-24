package com.example.test.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.test.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_test_dialog.*

class TestDialog : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_dialog)

        showFloat.setOnClickListener {
            FloatDialog(this).show()
        }

        showDialogCode.setOnClickListener {
            FullDialogCode(this).show()
        }

        showDialogTheme.setOnClickListener {
            FullDialogTheme(this, R.style.dialog).show()
        }

        showDialogFull.setOnClickListener {
            FullDialogCodeNoStatus(this).show()
        }

        showAlertDialog.setOnClickListener {
            AlertDialog.Builder(this).setPositiveButton("ok") { _, _ ->
                Toast.makeText(this, "ok click", Toast.LENGTH_SHORT).show()
            }.show()
        }

    }

    override fun onPause() {
        super.onPause()
        Log.d("chao", "dialog pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("chao", "dialog onStop")
    }

    override fun onStart() {
        super.onStart()
        Log.d("chao", "dialog onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("chao", "dialog onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("chao", "dialog onResume")
    }
}
