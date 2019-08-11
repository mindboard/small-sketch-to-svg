package com.mindboardapps.app.smallsketch.tosvg

import java.io.*
import java.util.zip.GZIPInputStream
import org.apache.commons.cli.*;
import org.json.*
import com.mindboardapps.app.smallsketch.tosvg.style.*

fun main(args : Array<String>) {
    val options = Options()
    options.addOption("s", "style", true, "Specify style json filepath")
    options.addOption("f", "format", true, "Specify output file format")

    val cl = DefaultParser().parse(options, args)

    if ( cl.hasOption("-s") ){
        // -----------------------------------
        // A) the case with style json
        // -----------------------------------
        val styleJsonFilePath = cl.getOptionValue("s")
        val styleJsonFile = File(styleJsonFilePath)

        val styleObject = StyleObject( styleJsonFile )
        val lines = GZIPInputStream(System.`in`).bufferedReader(Charsets.UTF_8).use {
            it.readLines()
        }

        if( !cl.hasOption("-f") ){
            print( SsfToSvg(styleObject).createSvg(lines) )
        }
        else {
            val outputFormat = cl.getOptionValue("f")
            if( outputFormat=="svg" ){
                print( SsfToSvg(styleObject).createSvg(lines) )
            }
            if( outputFormat=="png" ){
                val outputStream = BufferedOutputStream(System.out)
                SsfToPng(styleObject).createPng(lines, outputStream)
                outputStream.close()
            }
        }
    }
}
