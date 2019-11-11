# em-maven-resource-optimizer

Simple Maven plugin to gzip JS & CSS files at build time. No other dependencies, other than Maven itself.

## Maven dependency

Maven package is published at Github: https://github.com/manikantag/mg-maven-resource-optimizer/packages

## Usage: 
	<plugin>
		<groupId>com.manikanta</groupId>
		<artifactId>mg-maven-resource-optimizer</artifactId>
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
 * Much better implementation. *I know this can be written much better. I've written just to serve my purpose of gzip wro4j minified JS & CSS files at build time in couple of hours while everyone else is sleeping :p*
 * I've used this in production with Java 8 without any issue
 

## License
Licensed under the MIT license.
