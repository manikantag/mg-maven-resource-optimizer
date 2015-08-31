# em-maven-resource-optimizer

Simple Maven plugin to gzip JS & CSS files at build time. No other dependencies, other than Maven itself.

## Usage: 
	<plugin>
		<groupId>com.ekaminds</groupId>
		<artifactId>em-maven-resource-optimizer</artifactId>
		<version>0.0.1</version>
		<executions>
			<execution>
				<id>js-gzip</id>
				<phase>process-classes</phase>
				<goals>
					<goal>gzip</goal>
				</goals>
				<configuration>
					<resourcedir>${project.basedir}/src/main/webapp/js-min</resourcedir>
				</configuration>
			</execution>
		</executions>
	</plugin>

## TODO
 * Much better implementation. *I know this can be written much better. I've written just to serve my purpose of gzip wro4j minified JS & CSS files at build time.*
 * I m using this in production with Java 8 from last 6 months without any issue. I've not tested on Java 6/7 though.
 * Publish to Maven central
 

## License
Licensed under the MIT license.
