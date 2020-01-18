package com.mindboardapps.app.smallsketch.tosvg

class CanvasRectF(
    val left: Float, 
    val top: Float, 
    val right: Float, 
    val bottom: Float){


    val width: Float
        get(){
            return (right-left)
        }

    val height: Float
        get() {
            return (bottom-top)
        }
}
