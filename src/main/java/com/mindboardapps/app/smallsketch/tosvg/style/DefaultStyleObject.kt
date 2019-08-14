package com.mindboardapps.app.smallsketch.tosvg.style

import java.io.*
import org.json.*
import com.mindboardapps.app.smallsketch.tosvg.model.Color

class DefaultStyleObject : IStyleObject{
    override var border: Boolean = false
    override var borderColor: Color = StyleObjectRes.BLACK

    override var hasPadding: Boolean = false

    override var fillBackground: Boolean = false
    override var backgroundColor: Color = StyleObjectRes.WHITE

    override var strokeWidth: Float = 1.0f
    override var strokeColor: Color = StyleObjectRes.BLACK
    override var canvasWidth: Int? = null

    override fun enabledCanvasWidth(): Boolean = false
}
