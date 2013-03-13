<html>
	<head>
		<meta charset="UTF-8"/>
		<title>JFormat</title>
		<link rel="shortcut icon" href="favicon.ico"/>
		<meta name="Description" content="JFormat is a java general object formatting library"/>
		<meta name="Keywords" content="Hezi Daniel, Java, Logging, Formatting, Tracing, jar, open source, free source, github"/>
		<!-- start of syntax highlighting stuff -->
		<script type="text/javascript" src="sh/scripts/shCore.js"></script>
		<script type="text/javascript" src="sh/scripts/shBrushJava.js"></script>
		<link href="sh/styles/shCore.css" rel="stylesheet" type="text/css" />
		<link href="sh/styles/shThemeDefault.css" rel="stylesheet" type="text/css" />
		<script>
			SyntaxHighlighter.all();
		</script>
		<!-- end of syntax highlighting stuff -->
	</head>
	<body>
		<h1>Welcome to the JFormat web site</h1>

		<h2>What is JFormat?</h2>
		<p>Have you ever wanted to dump a Java object into an output stream for logging
		purposes? Did you want to do that for <b>Every</b> Java object without adding
		special methods to the class? Did you want the dump to be done in a streaming
		fashion so that even very big object will be written effectively? Did you
		want to be able to control the depth of the dump? The fields to dump?
		This is what JFormat is for...</p>

		<h2>News...</h2>
		<p>Collections printing is now supported! (17/3/13)</p>

		<h2>Can I see an example? Sure. Here it is...</h2>
		<p>
			<pre class="brush: java">
			public static void main(String[] args) {
				Person p=new Person(324,"sadfads");
				Car c1=new Car(3245243);
				Car c2=new Car(4564365);
				p.addCar(c1);
				p.addCar(c2);
				LoggingUtility.dumptoStream(p, System.out);
			}</pre>
		</p>

		<h2>Great. How do I use it? Here are the basic instructions...</h2>
		<p><ul>
		<li>get the jar file from <a href="../jformat.jar">here</a></li>
		<li>put it in your class path</li>
		<li>import the 'org.hd.jformat.LoggingUtility' class</li>
		<li>use the library according to the code snipplet above...</li>
		<li>use the javadocs from <a href="../javadoc">here</a></li>
		</ul>

		<p>
		Hezi Daniel, <?php 
			$copyYear = 2013;
			$curYear = date('Y');
			echo $copyYear . (($copyYear != $curYear) ? '-' . $curYear : '');
		?>
		<a href="mailto:hezi.daniel2000@gmail.com">hezi.daniel2000@gmail.com</a>
		</p>
	</body>
</html>
