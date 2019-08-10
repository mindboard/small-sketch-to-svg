
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

There is example file tree.ssf in the ./examples dir.
If you want customize svg style such as background color and stroke color etc, use ./examples/style.json.
In order to apply style.json file, use -s option:

```
cat examples/tree.ssf | /path/to/small-sketch-to-svg -s examples/style.json > tree.svg
```

![Example1](https://github.com/mindboard/small-sketch-to-svg/blob/master/resources/example1.svg)


## License
MIT
