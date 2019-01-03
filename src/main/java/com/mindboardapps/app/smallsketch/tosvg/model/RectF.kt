package com.mindboardapps.app.smallsketch.tosvg.model

class RectF(){
    var left = 0f
    var top = 0f
    var right = 0f
    var bottom = 0f

    constructor(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float):this(){

        this.left = left
        this.top = top
        this.right = right
        this.bottom = bottom
    }

    constructor(bounds: RectF?):this(){
        left = bounds?.left ?: 0f
        top = bounds?.top ?: 0f
        right = bounds?.right ?: 0f
        bottom = bounds?.bottom ?: 0f
    }
}
