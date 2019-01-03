package com.mindboardapps.app.smallsketch.tosvg

import java.io.*
import java.util.zip.GZIPInputStream
import org.apache.commons.cli.*;
import org.json.*
import com.mindboardapps.app.smallsketch.tosvg.style.*

fun main(args : Array<String>) {
    val options = Options()
    options.addOption("s", "style", true, "Specify style json file")
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
        print( SsfToSvg(styleObject).createSvg(lines) )
    }
    else {
        // -----------------------------------
        // B) the case without style json
        // -----------------------------------
        val styleObject = DefaultStyleObject()

        if( args.size<1 ){
            val lines = GZIPInputStream(System.`in`).bufferedReader(Charsets.UTF_8).use {
                it.readLines()
            }
    
            print( SsfToSvg(styleObject).createSvg(lines) )
        }
        else if( args.size>1 ){
            val ssfFile = File(args[0])
            val svgFile = File(args[1])

            if( ssfFile.exists() && ssfFile.name.endsWith("ssf") ){
                val lines = GZIPInputStream(FileInputStream(ssfFile)).bufferedReader(Charsets.UTF_8).use { it.readLines() }
    
                val svgText = SsfToSvg(styleObject).createSvg(lines)
                svgFile.bufferedWriter(Charsets.UTF_8).use { it.write(svgText) }
            }
        }
    }
}
