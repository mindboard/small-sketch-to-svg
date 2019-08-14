package com.mindboardapps.app.smallsketch.tosvg.style

import com.mindboardapps.app.smallsketch.tosvg.model.Color

interface IStyleObject {
    var border: Boolean
    var borderColor: Color

    var hasPadding: Boolean

    var fillBackground: Boolean
    var backgroundColor: Color

    var strokeWidth: Float
    var strokeColor: Color
    var canvasWidth: Int?

     fun enabledCanvasWidth(): Boolean
}
