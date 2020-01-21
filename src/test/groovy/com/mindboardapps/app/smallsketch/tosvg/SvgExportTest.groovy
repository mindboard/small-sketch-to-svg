package com.mindboardapps.app.smallsketch.tosvg

import spock.lang.Specification
import spock.lang.Ignore
import java.util.zip.GZIPInputStream
import com.mindboardapps.app.smallsketch.tosvg.style.*

class SvgExportTest extends Specification {
    private static void toSvg(
        File styleJsonFile,
        File ssfFile,
        File svgFile){

        def lines = new GZIPInputStream(new FileInputStream(ssfFile)).readLines()
        svgFile.text = new SsfToSvg( new StyleObject( styleJsonFile ) ).createSvg(CmdHelper.INSTANCE.toStrokeObjectList(lines))
    }

    //@Ignore
    def "export-as-svg-test-2"(){
        when:
        def styleJsonFile = new File('./examples/style.json')
        def svgFile = new File('nuc.svg')

        toSvg(
            styleJsonFile, 
            new File('./examples/nuc.ssf'),
            svgFile)

        then:
        svgFile.exists()
    }

    //@Ignore
    def "export-as-svg-test-1"(){
        when:
        def styleJsonFile = new File('./examples/style.json')
        def svgFile = new File('tree.svg')

        toSvg(
            styleJsonFile, 
            new File('./examples/tree.ssf'),
            svgFile)

        then:
        svgFile.exists()
    }
}
