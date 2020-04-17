package com.tilseier.escapethemonster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tilseier.escapethemonster.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        getApplicationComponent()?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
