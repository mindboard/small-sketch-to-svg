package com.mindboardapps.app.smallsketch.tosvg.svg

import com.mindboardapps.app.smallsketch.tosvg.model.Color

object SvgRes {
    fun createRgbColor(color: Color): String{
        return "rgb(${color.r}, ${color.g}, ${color.b})"
    }
}
