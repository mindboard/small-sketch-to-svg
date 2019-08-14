package com.mindboardapps.app.smallsketch.tosvg

import java.io.*

interface ISsfToImg {
    fun createImage(lines: List<String>, outputStream: OutputStream)
}
