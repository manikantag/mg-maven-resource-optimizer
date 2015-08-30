package com.manikanta.optimizer.maven;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Maven plugin to gzip the JS &amp; CSS files.
 * <p>Usage: in pom.xml,
 * <pre>
 * <!-- GZIP the minified files -->
 *&lt;plugin&gt;
 *	&lt;groupId&gt;com.manikanta&lt;/groupId&gt;
 *	&lt;artifactId&gt;em-maven-resource-optimizer&lt;/artifactId&gt;
 *	&lt;version&gt;0.0.1&lt;/version&gt;
 *	&lt;executions&gt;
 *		&lt;execution&gt;
 *			&lt;id&gt;js-gzip&lt;/id&gt;
 *			&lt;phase&gt;process-classes&lt;/phase&gt;
 *			&lt;goals&gt;
 *				&lt;goal&gt;gzip&lt;/goal&gt;
 *			&lt;/goals&gt;
 *			&lt;configuration&gt;
 *				&lt;resourcedir&gt;${project.basedir}/src/main/webapp/js-min&lt;/resourcedir&gt;
 *			&lt;/configuration&gt;
 *		&lt;/execution&gt;
 *	&lt;/executions&gt;
 *&lt;/plugin&gt;
 *</pre>
 *
 * @author Manikanta G
 */
@Mojo(name = "gzip", defaultPhase = LifecyclePhase.PROCESS_CLASSES, threadSafe = true)
public class GZIPMojo extends AbstractMojo {

	/**
	 * Location of the resource directories.
	 * @required
	 */
	@Parameter(property = "resourcedir", required = true)
	private File resourcedir;
	
	private Log LOG;
	
	public void execute() throws MojoExecutionException {
		if(resourcedir == null) {
			LOG.error("resourcedir is null or empty");
			throw new MojoExecutionException("resourcedir is not used.");
		}
		
		if(! resourcedir.exists()) {
			throw new MojoExecutionException("resourcedir '" + resourcedir + "' doesn't exist");
		}
		
		if(! resourcedir.isDirectory()) {
			throw new MojoExecutionException("resourcedir '" + resourcedir + "' is not a directory");
		}
		
		FilenameFilter resourceFilenameFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".js") || name.endsWith(".css");
			}
		};
		
		gzipFiles(resourcedir, resourceFilenameFilter);
		
	}
	
	private void gzipFiles(File resourceDir, FilenameFilter resourceFilenameFilter) {
		File[] resourceFiles = resourceDir.listFiles(resourceFilenameFilter);
		
		if(resourceFiles.length == 0) {
			LOG.info(resourceDir + " doesn't have any resource files; skipping...");
			return;
		}
		
		for(File resourceFile : resourceFiles) {
			LOG.info("gzipping " + resourceFile);
			gzipFile(resourceFile);
		}
		
	}
	
	private void gzipFile(File sourceFile) {
		BufferedInputStream bis = null;
		GZIPOutputStream gzipOut = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(sourceFile));
			
			File gzippedFile = new File(sourceFile.getAbsolutePath() + ".gz");
			if(gzippedFile.exists()) {
				gzippedFile.delete();
			}
			gzippedFile.createNewFile();
			
			gzipOut = new GZIPOutputStream(new FileOutputStream(gzippedFile));
			
			for (int ch = bis.read(); ch != -1; ch = bis.read()) {
				gzipOut.write(ch);
			}
			
			gzipOut.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					LOG.error("Error while closing the input stream");
				}
			}
			if(gzipOut != null) {
				try {
					gzipOut.close();
				} catch (IOException e) {
					LOG.error("Error while closing the gzipped output stream");
				}
			}
		}
	}
	
	public void setLog(Log log) {
		this.LOG = log;
	}

}