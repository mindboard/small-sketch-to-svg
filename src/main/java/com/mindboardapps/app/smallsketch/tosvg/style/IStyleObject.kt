package com.mindboardapps.app.smallsketch.tosvg.style

import com.mindboardapps.app.smallsketch.tosvg.model.Color

interface IStyleObject {
    var border: Boolean
    var borderColor: Color
    var strokeWidth: Float
    var strokeColor: Color
    var backgroundColor: Color
    var canvasWidth: Int?

     fun enabledCanvasWidth(): Boolean
}
