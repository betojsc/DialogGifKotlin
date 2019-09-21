package com.betojsc.dialoggifkotlin

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.betojsc.dialoganimationlibrary.GifDialogKotlin
import com.betojsc.dialoganimationlibrary.GifDialogListener

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.btn_action)
        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val typeface: Typeface? = ResourcesCompat.getFont(this, R.font.courgette_regular)
        GifDialogKotlin.Builder(this)
            .setTitle(R.string.title)
            .setColorTitle(R.color.TitleColor)
            .setMessage(R.string.message)
            .setMessageColor(R.color.MessageColor)
            .setPositiveBtnBackground(R.color.positiveBtnBackground)
            .setPositiveBtnText(R.string.ok)
            .setNegativeBtnText(R.string.cancel)
            .setNegativeBtnBackground(R.color.NegativeBtnBackground)
            .setGifResource(R.drawable.gif1)
            .setFont(typeface!!)
            .isCancellable(true)
            .onPositiveClicked(object : GifDialogListener {
                override fun OnClick() {
                    Toast.makeText(
                        this@MainActivity, getString(R.string.ok), Toast.LENGTH_SHORT)
                        .show()
                }
            })
            .onNegativeClicked(object : GifDialogListener {
                override fun OnClick() {
                    Toast.makeText(
                        this@MainActivity, getString(R.string.cancel), Toast.LENGTH_SHORT)
                        .show()
                }
            })
            .build()
    }
}
