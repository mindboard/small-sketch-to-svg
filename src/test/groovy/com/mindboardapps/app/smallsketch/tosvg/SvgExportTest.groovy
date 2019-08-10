package com.mindboardapps.app.smallsketch.tosvg

import spock.lang.Specification
import java.util.zip.GZIPInputStream
import com.mindboardapps.app.smallsketch.tosvg.style.*

class SvgExportTest extends Specification {
    def "export-as-svg-test"(){
        when:
        def styleJsonFile = new File('./examples/style.json')
        def styleObject = new StyleObject( styleJsonFile )

        def treeSsfFile = new File('./examples/tree.ssf')
        def treeSvgFile = new File('tree.svg')

        def lines = new GZIPInputStream(new FileInputStream(treeSsfFile)).readLines()
        treeSvgFile.text = new SsfToSvg(styleObject).createSvg(lines)

        then:
        treeSvgFile.exists()
    }
}
