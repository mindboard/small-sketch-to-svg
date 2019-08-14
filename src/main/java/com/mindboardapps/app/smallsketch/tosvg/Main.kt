package com.mindboardapps.app.smallsketch.tosvg

import java.io.*
import java.util.zip.GZIPInputStream
import org.apache.commons.cli.*;
import org.json.*
import com.mindboardapps.app.smallsketch.tosvg.style.*

fun main(args : Array<String>) {
    System.setProperty("java.awt.headless", "true")
    val options = Options()
    options.addOption("s", "style", true, "Specify style json filepath")
    options.addOption("f", "format", true, "Specify output file format")

    val cl = DefaultParser().parse(options, args)

    val outputFormat = if( !cl.hasOption("-f") ){ "png" } else { cl.getOptionValue("f")!! }.toLowerCase()
    val styleObject  = if( !cl.hasOption("-s") ){ DefaultStyleObject() } else { StyleObject(File( cl.getOptionValue("s")!!)) }

    val lines = GZIPInputStream(System.`in`).bufferedReader(Charsets.UTF_8).use { it.readLines() }

    if( outputFormat=="svg" ){
        print( SsfToSvg(styleObject).createSvg(lines) )
    }
    if( outputFormat=="pdf" ){
        val outputStream = BufferedOutputStream(System.out)
        SsfToPdf(styleObject).createImage(lines, outputStream)
        outputStream.close()
    }
    if( outputFormat=="png" ){
        val outputStream = BufferedOutputStream(System.out)
        SsfToPng(styleObject).createImage(lines, outputStream)
        outputStream.close()
    }
    if( listOf("jpg","jpeg").contains(outputFormat) ){
        val outputStream = BufferedOutputStream(System.out)
        SsfToJpeg(styleObject).createImage(lines, outputStream)
        outputStream.close()
    }
}
