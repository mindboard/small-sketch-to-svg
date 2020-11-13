package com.mindboardapps.app.smallsketch.tosvg.style

import com.mindboardapps.app.smallsketch.tosvg.model.Color

interface IStyleObject {
    var border: Boolean
    var borderColor: Color
    var borderWidth: Float

    var padding: Boolean

    var fillBackground: Boolean
    var backgroundColor: Color

    var strokeWidth: Float
    var strokeColor: Color?
    var strokeColorList: List<Color>?
    var canvasWidth: Int?

     fun enabledCanvasWidth(): Boolean
}
