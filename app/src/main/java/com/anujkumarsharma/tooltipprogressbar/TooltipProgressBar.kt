package com.anujkumarsharma.tooltipprogressbar


import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import kotlin.math.atan
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.RotateDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.anuj55149.testtooltipprogressbar.R


class TooltipProgressBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var leftText: String = ""
        set(value) {
            field = value
            populateUI()
        }
    var rightText: String = ""
        set(value) {
            field = value
            populateUI()
        }
    var topToolTipText: String = ""
        set(value) {
            field = value
            populateUI()
        }
    var bottomToolTipText: String = ""
        set(value) {
            field = value
            populateUI()
        }
    var topTooltipColor: Int = ContextCompat.getColor(context,
        R.color.red
    )
        set(value) {
            field = value
            populateUI()
        }
    var bottomTooltipColor: Int = ContextCompat.getColor(context,
        R.color.orange_dark
    )
        set(value) {
            field = value
            populateUI()
        }
    var progressFillColor: Int = ContextCompat.getColor(context,
        R.color.black_21
    )
        set(value) {
            field = value
            populateUI()
        }
    var progressTotalSteps: Int = 100
        set(value) {
            field = value
            populateUI()
        }
    var progressFillSteps: Int = 0
        set(value) {
            field = value
            populateUI()
        }
    var bottomTooltipSteps: Int = 0
        set(value) {
            field = value
            populateUI()
        }

    private var leftTextView: TextView? = null
    private var rightTextView: TextView? = null
    private var topTooltipTextView: TextView? = null
    private var bottomTooltipTextView: TextView? = null
    private var topTooltipArrow: View? = null
    private var bottomTooltipArrow: View? = null
    private var progressBar: ProgressBar? = null


    init {
        View.inflate(getContext(),
            R.layout.layout_tooltip_progress_bar, this)
        attrs?.let {
            parseAttributes(context, it)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        inflateUI()
        populateUI()
    }

    private fun parseAttributes(context: Context, attributes: AttributeSet) {
        val attrs =
            context.theme.obtainStyledAttributes(attributes,
                R.styleable.TooltipProgressBar, 0, 0)
        try {
            this.leftText = attrs.getString(R.styleable.TooltipProgressBar_optionLeftText)?:""
            this.rightText = attrs.getString(R.styleable.TooltipProgressBar_optionRightText)?:""
            this.topToolTipText = attrs.getString(R.styleable.TooltipProgressBar_optionTopTooltipText)?:""
            this.bottomToolTipText = attrs.getString(R.styleable.TooltipProgressBar_optionBottomTooltipText)?:""
            this.topTooltipColor = attrs.getInt(
                R.styleable.TooltipProgressBar_optionTopTooltipColor,
                ContextCompat.getColor(context, R.color.red))
            this.bottomTooltipColor = attrs.getInt(
                R.styleable.TooltipProgressBar_optionBottomTooltipColor,
                ContextCompat.getColor(context,
                    R.color.orange_dark
                ))
            this.progressFillColor = attrs.getInt(
                R.styleable.TooltipProgressBar_optionProgressFillColor,
                ContextCompat.getColor(context,
                    R.color.black_21
                ))
            this.progressTotalSteps = attrs.getInt(R.styleable.TooltipProgressBar_optionProgressTotalSteps, 100)
            this.progressFillSteps = attrs.getInt(R.styleable.TooltipProgressBar_optionProgressFillSteps, 0)
            this.bottomTooltipSteps = attrs.getInt(R.styleable.TooltipProgressBar_optionBottomTooltipSteps, 0)
        } finally {
            attrs.recycle()
        }
    }

    private fun inflateUI() {
        leftTextView = findViewById(R.id.option_left_text)
        rightTextView = findViewById(R.id.option_right_text)
        topTooltipTextView = findViewById(R.id.top_tooltip_head)
        bottomTooltipTextView = findViewById(R.id.bottom_tooltip_head)
        topTooltipArrow = findViewById(R.id.top_tooltip_arrow)
        bottomTooltipArrow = findViewById(R.id.bottom_tooltip_arrow)
        progressBar = findViewById(R.id.option_progress_bar)
    }

    private fun populateUI() {

        leftTextView?.let {
            if (leftText.isNotEmpty()) {
                it.visibility = View.VISIBLE
                it.text = leftText
            } else {
                it.visibility = View.GONE
            }
        }

        rightTextView?.let {
            if (rightText.isNotEmpty()) {
                it.visibility = View.VISIBLE
                it.text = rightText
            } else {
                it.visibility = View.GONE
            }
        }

        progressBar?.let {
            it.max = progressTotalSteps
            it.progress = progressFillSteps
        }


        topTooltipArrow?.let {
            if(topToolTipText.isNotEmpty()) {
                setBackgroundColor(topTooltipTextView, it, topTooltipColor, topToolTipText)
                val temp = progressFillSteps.minus(progressTotalSteps.div(2))
                setTooltipPosition(topTooltipTextView, it, temp, topToolTipText)
            } else {
                it.visibility = View.GONE
                topTooltipTextView?.visibility = View.GONE
            }
        }

        bottomTooltipArrow?.let {
            if(bottomToolTipText.isNotEmpty()) {
                setBackgroundColor(bottomTooltipTextView, it, bottomTooltipColor, bottomToolTipText)
                val temp = bottomTooltipSteps.minus(progressTotalSteps.div(2))
                setTooltipPosition(bottomTooltipTextView, it, temp, bottomToolTipText)
            } else {
                it.visibility = View.GONE
                bottomTooltipTextView?.visibility = View.GONE
            }
        }
    }

    private fun setBackgroundColor(textView: TextView?, triangleView: View, color: Int, text: String) {
        triangleView.visibility = View.VISIBLE
        textView?.visibility = View.VISIBLE
        textView?.text = text
        textView?.setTextColor(color)

        val background: Drawable? = textView?.background
        val gradientDrawable = background as GradientDrawable
        gradientDrawable.setStroke((context?.resources?.displayMetrics?.density)?.toInt()?: 0, color)

        val layerDrawable = triangleView.background as LayerDrawable
        val rotateDrawable = layerDrawable
            .findDrawableByLayerId(R.id.arrow_item) as RotateDrawable
        val triangleGradientDrawable = rotateDrawable.drawable as GradientDrawable
        triangleGradientDrawable.setStroke((context?.resources?.displayMetrics?.density)?.toInt()?: 0, color)
    }

    private fun setTooltipPosition(textView: TextView?, triangleView: View, moveSteps: Int, text: String) {
        var bias = -0.5f
        if(progressTotalSteps > 0) {
            bias = moveSteps.toFloat().div(progressTotalSteps)
        }
        val progressWidth = progressBar?.layoutParams?.width
        val position = progressWidth?.times(bias)
        if (position != null) {
            triangleView.translationX = position
            textView?.translationX = position.times(getHeaderOffsetWRTArrow(text))
        }
    }

    private fun getHeaderOffsetWRTArrow(s: String): Float {
        val x = s.length.toFloat()
        return 1.minus(atan(x.minus(10).times(0.1))).div(2).plus(0.3).toFloat()
    }

}