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

    val PEN0 = Color( 76,  96, 130) // Blue
    val PEN1 = Color(232,  83,  33) // mandarin orange
    val PEN2 = Color(240, 149,  31) // orange
    val PEN3 = Color(  0, 159, 129) // emerarudo (green)
    val PEN4 = Color(177, 150, 147) // SAKURANEZUMI
    val PEN5 = Color(  8,   8,   8) // Black

    val DEFAULT_STROKE_COLOR_LIST: List<Color> = listOf(
        StyleObjectRes.PEN0,
        StyleObjectRes.PEN1,
        StyleObjectRes.PEN2,
        StyleObjectRes.PEN3,
        StyleObjectRes.PEN4,
        StyleObjectRes.PEN5)
}
