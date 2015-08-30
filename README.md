# em-maven-resource-optimizer

Simple Maven plugin to gzip JS & CSS files at build time.

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
 * Publish to Maven central

## License
Licensed under the MIT license.