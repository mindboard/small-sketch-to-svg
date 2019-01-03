package com.mindboardapps.app.smallsketch.tosvg.style

import org.json.*
import com.mindboardapps.app.smallsketch.tosvg.model.Color

object StyleObjectRes {
    fun createColor( colorJsonObject: JSONObject?, defaultColor: Color ): Color {
        if( colorJsonObject==null ){
            return defaultColor
        }
        return Color(
            colorJsonObject.getInt("R"),
            colorJsonObject.getInt("G"),
            colorJsonObject.getInt("B"))
    }

    val BLACK = Color(0, 0, 0)
    val WHITE = Color(255, 255, 255)
}
