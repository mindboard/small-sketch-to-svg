
# small-sketch-to-svg

This is a tool in order to convert SSF to SVG.
SSF is small-sketch-format.

* Small Sketch : https://play.google.com/store/apps/details?id=com.mindboardapps.app.smallsketch


## Usage

1. Build small-sketch-to-svg.zip  
    `./gradlew distZip` and build/distributions/small-sketch-to-svg.zip is generated.

2. Install small-sketch-to-svg command  
    Unzip small-sketch-to-svg.zip in somewhere you want

3. Convert ssf to svg  
    `cat foo.ssf | /path/to/small-sketch-to-svg/bin/small-sketch-to-svg > foo.svg`

4. Convert ssf to svg with style
    `cat foo.ssf | /path/to/small-sketch-to-svg/bin/small-sketch-to-svg -s style.json > foo.svg`

5. Convert ssf to png with style
    `cat foo.ssf | /path/to/small-sketch-to-svg/bin/small-sketch-to-svg -f png -s style.json > foo.png`

There is an example file example1.ssf in the ./resources dir.
And a style.json example in the ./examples dir.

![Example1](https://github.com/mindboard/small-sketch-to-svg/blob/master/resources/example1.svg)


## License

See the LICENSE file for license rights and limitations (MIT).
