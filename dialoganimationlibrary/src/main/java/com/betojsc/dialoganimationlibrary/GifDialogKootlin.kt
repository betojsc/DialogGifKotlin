package com.betojsc.dialoganimationlibrary

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.view.View.GONE
import android.view.Window
import androidx.core.content.ContextCompat.getColor
import kotlinx.android.synthetic.main.gifdialogkotlin.*
import pl.droidsonroids.gif.GifImageView
import java.util.*

object GifDialogKotlin {
    class Builder(private var activity: Activity) {
        private var title: Int = 0
        private var message: Int = 0
        private var positiveBtnText: Int = 0
        private var negativeBtnText: Int = 0
        private var pBtnColor: Int = 0
        private var nBtnColor:Int = 0
        private var titleColor: Int  = 0
        private var messageColor: Int  = 0
        private lateinit var font: Typeface
        private lateinit var pListener: GifDialogListener
        private lateinit var nListener:GifDialogListener
        private var cancel: Boolean = false
        private var gifImageResource: Int = 0

        fun setTitle(title: Int):Builder{
            this.title = title
            return this
        }

        fun setFont(font: Typeface): Builder {
            this.font = font
            return this
        }

        fun setMessage(message: Int): Builder {
            this.message = message
            return this
        }

        fun setPositiveBtnText(positiveBtnText: Int): Builder {
            this.positiveBtnText = positiveBtnText
            return this
        }

        fun setColorTitle(titleColor: Int): Builder {
            this.titleColor = titleColor
            return this
        }

        fun setMessageColor(messageColor: Int): Builder {
            this.messageColor = messageColor
            return this
        }

        fun setPositiveBtnBackground(pBtnColor: Int): Builder {
            this.pBtnColor = pBtnColor
            return this
        }


        fun setNegativeBtnText(negativeBtnText: Int): Builder {
            this.negativeBtnText = negativeBtnText
            return this
        }

        fun setNegativeBtnBackground(nBtnColor: Int): Builder {
            this.nBtnColor = nBtnColor
            return this
        }

        //set Positive listener
        fun onPositiveClicked(pListener: GifDialogListener): Builder {
            this.pListener = pListener
            return this
        }

        //set Negative listener
        fun onNegativeClicked(nListener: GifDialogListener): Builder {
            this.nListener = nListener
            return this
        }

        fun isCancellable(cancel: Boolean): Builder {
            this.cancel = cancel
            return this
        }

        fun setGifResource(gifImageResource: Int): Builder {
            this.gifImageResource = gifImageResource
            return this
        }

        fun build() {
            val dialog = Dialog(activity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            Objects.requireNonNull<Window>(dialog.window)
                    .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(cancel)
            dialog.setContentView(R.layout.gifdialogkotlin)

            //getting resources
            val gifImageView: GifImageView = dialog.findViewById(R.id.gifImageView)
            gifImageView.setImageResource(gifImageResource)

            dialog.txt_title.text = activity.getString(title)
            dialog.txt_title.typeface = font
            if (titleColor != 0){
                val tColor: Int = titleColor
                dialog.txt_title.setTextColor(getColor(activity,tColor))
            }
            dialog.txt_message.text = activity.getString(message)
            dialog.txt_message.typeface = font
            if (messageColor != 0){
                val tColor: Int = messageColor
                dialog.txt_message.setTextColor(getColor(activity,tColor))
            }
            if (positiveBtnText != 0) {
                dialog.positiveBtn.text = activity.getString(positiveBtnText)
                dialog.positiveBtn.typeface = font
                if (pBtnColor != 0) {
                    val bgShape = dialog.positiveBtn.background as GradientDrawable
                    bgShape.setColor(getColor(activity,pBtnColor))
                }
                dialog.positiveBtn.setOnClickListener {
                    pListener.OnClick()
                    dialog.dismiss()
                }
            } else run {
                dialog.positiveBtn.visibility = GONE
            }
            if (negativeBtnText != 0) {
                dialog.negativeBtn.text = activity.getString(negativeBtnText)
                dialog.negativeBtn.typeface = font
                if (nBtnColor != 0) {
                    val bgShape = dialog.negativeBtn.background as GradientDrawable
                    bgShape.setColor(getColor(activity,nBtnColor))
                }
                dialog.negativeBtn.setOnClickListener {
                    nListener.OnClick()
                    dialog.dismiss()
                }
            } else run { dialog.negativeBtn.visibility = GONE }

            dialog.show()
        }
    }
}