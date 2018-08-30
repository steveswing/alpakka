import sbt._
import Keys._

object Dependencies {

  val ScalaVersions = Seq("2.12.6", "2.11.12")

  val AkkaVersion = sys.env.get("AKKA_SERIES") match {
    case Some("2.4") => sys.error("Akka 2.4 is not supported anymore")
    case _ => "2.5.16"
  }

  val AwsSdkVersion = "1.11.398"
  val AkkaHttpVersion = "10.1.4"

  val Common = Seq(
    // These libraries are added to all modules via the `Common` AutoPlugin
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % Test,
      "com.typesafe.akka" %% "akka-slf4j" % AkkaVersion % Test,
      "ch.qos.logback" % "logback-classic" % "1.2.3" % Test, // Eclipse Public License 1.0
      "org.scalatest" %% "scalatest" % "3.0.5" % Test, // ApacheV2
      "com.novocode" % "junit-interface" % "0.11" % Test, // BSD-style
      "junit" % "junit" % "4.12" % Test // Eclipse Public License 1.0
    )
  )

  val Amqp = Seq(
    libraryDependencies ++= Seq(
      "com.rabbitmq" % "amqp-client" % "5.3.0" // APLv2
    )
  )

  private val MockitoCoreVersion = "2.21.0"
  val AwsLambda = Seq(
    libraryDependencies ++= Seq(
      "com.amazonaws" % "aws-java-sdk-lambda" % AwsSdkVersion, // ApacheV2
      "org.mockito" % "mockito-core" % MockitoCoreVersion % Test // MIT
    )
  )

  val AzureStorageQueue = Seq(
    libraryDependencies ++= Seq(
      "com.microsoft.azure" % "azure-storage" % "8.0.0" // ApacheV2
    )
  )

  val Cassandra = Seq(
    libraryDependencies ++= Seq(
      "com.datastax.cassandra" % "cassandra-driver-core" % "3.6.0" // ApacheV2
    )
  )

  val Csv = Seq(
    libraryDependencies ++= Seq()
  )

  private val H2Version = "1.4.197"
  private val ActiveMqVersion = "5.15.5"
  val `Doc-examples` = Seq(
    libraryDependencies ++= Seq(
      // https://mina.apache.org/ftpserver-project/downloads.html
      "org.apache.ftpserver" % "ftpserver-core" % "1.1.1", // ApacheV2
      "com.google.jimfs" % "jimfs" % "1.1", // ApacheV2
      "com.typesafe.akka" %% "akka-slf4j" % AkkaVersion,
      "com.typesafe.akka" %% "akka-http-xml" % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
      // https://github.com/akka/alpakka-kafka/releases
      "com.typesafe.akka" %% "akka-stream-kafka" % "0.22",
      // https://github.com/manub/scalatest-embedded-kafka/tags
      "net.manub" %% "scalatest-embedded-kafka" % "2.0.0", // MIT
      // https://github.com/javaee/javax.jms
      "javax.jms" % "jms" % "1.1", // CDDL Version 1.1
      // http://activemq.apache.org/download.html
      "org.apache.activemq" % "activemq-all" % ActiveMqVersion exclude ("log4j", "log4j") exclude ("org.slf4j", "slf4j-log4j12"), // ApacheV2
      "com.h2database" % "h2" % H2Version, // Eclipse Public License 1.0
      "org.elasticsearch.client" % "elasticsearch-rest-client" % "6.4.0", // ApacheV2
      "org.codelibs" % "elasticsearch-cluster-runner" % "6.4.0.0", // ApacheV2
      "io.netty" % "netty-all" % "4.1.29.Final", // ApacheV2
      "org.slf4j" % "log4j-over-slf4j" % slf4jVersion,
      "org.slf4j" % "slf4j-jcl" % slf4jVersion,
      "ch.qos.logback" % "logback-classic" % "1.2.3" // Eclipse Public License 1.0
    )
  )

  val DynamoDB = Seq(
    libraryDependencies ++= Seq(
      "com.amazonaws" % "aws-java-sdk-dynamodb" % AwsSdkVersion, // ApacheV2
      "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion
    )
  )

  val Elasticsearch = Seq(
    libraryDependencies ++= Seq(
      "org.elasticsearch.client" % "rest" % "5.5.3", // ApacheV2
      "io.spray" %% "spray-json" % "1.3.4", // ApacheV2
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.6", // ApacheV2
      "org.codelibs" % "elasticsearch-cluster-runner" % "6.4.0.0" % Test // ApacheV2
    )
  )

  val File = Seq(
    libraryDependencies ++= Seq(
      "com.google.jimfs" % "jimfs" % "1.1" % Test // ApacheV2
    )
  )

  val Firestore = Seq(
    libraryDependencies ++= Seq(
      "com.google.cloud" % "google-cloud-firestore" % "0.59.0-beta"
    )
  )
  val Ftp = Seq(
    libraryDependencies ++= Seq(
      "commons-net" % "commons-net" % "3.6", // ApacheV2
      "com.hierynomus" % "sshj" % "0.26.0", // ApacheV2
      "org.apache.ftpserver" % "ftpserver-core" % "1.1.1" % Test, // ApacheV2
      "org.apache.sshd" % "sshd-core" % "2.0.0" % Test, // ApacheV2
      "net.i2p.crypto" % "eddsa" % "0.3.0" % Test, // CC0 1.0 Universal
      "com.google.jimfs" % "jimfs" % "1.1" % Test // ApacheV2
    )
  )

  val slf4jVersion = "1.7.25"
  val Geode = {
    val geodeVersion = "1.6.0"
    Seq(
      libraryDependencies ++=
        Seq("geode-core", "geode-cq")
          .map("org.apache.geode" % _ % geodeVersion exclude ("org.slf4j", "slf4j-log4j12")) ++
        Seq(
          "com.chuusai" %% "shapeless" % "2.3.3",
          "org.slf4j" % "log4j-over-slf4j" % slf4jVersion % Test // MIT like: http://www.slf4j.org/license.html
        )
    )
  }

  private val WiremockVersion = "2.18.0"
  val GooglePubSub = Seq(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
      "org.mockito" % "mockito-core" % MockitoCoreVersion % Test, // MIT
      "com.github.tomakehurst" % "wiremock" % WiremockVersion % Test // ApacheV2
    )
  )

  val GoogleFcm = Seq(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
      "com.pauldijou" %% "jwt-core" % "0.17.0", //ApacheV2
      "org.mockito" % "mockito-core" % MockitoCoreVersion % Test //ApacheV2
    )
  )

  val HadoopVersion = "3.1.1"
  val HBase = {
    val hbaseVersion = "2.1.0"
    Seq(
      libraryDependencies ++= Seq(
        "org.apache.hbase" % "hbase-client" % hbaseVersion exclude ("log4j", "log4j") exclude ("org.slf4j", "slf4j-log4j12"), // ApacheV2,
        "org.apache.hbase" % "hbase-common" % hbaseVersion exclude ("log4j", "log4j") exclude ("org.slf4j", "slf4j-log4j12"), // ApacheV2,
        "org.apache.hadoop" % "hadoop-common" % HadoopVersion exclude ("log4j", "log4j") exclude ("org.slf4j", "slf4j-log4j12"), // ApacheV2,
        "org.apache.hadoop" % "hadoop-mapreduce-client-core" % HadoopVersion exclude ("log4j", "log4j") exclude ("org.slf4j", "slf4j-log4j12"), // ApacheV2,
        "org.slf4j" % "log4j-over-slf4j" % slf4jVersion % Test // MIT like: http://www.slf4j.org/license.html
      )
    )
  }

  val Hdfs = {
    val catsVersion = "1.2.0"
    Seq(
      libraryDependencies ++= Seq(
        "org.apache.hadoop" % "hadoop-client" % HadoopVersion, // ApacheV2
        "org.typelevel" %% "cats-core" % catsVersion, // MIT,
        //Test
        "org.apache.hadoop" % "hadoop-hdfs" % HadoopVersion % Test classifier "tests", // ApacheV2
        "org.apache.hadoop" % "hadoop-common" % HadoopVersion % Test classifier "tests", // ApacheV2
        "org.apache.hadoop" % "hadoop-minicluster" % HadoopVersion % Test // ApacheV2
      )
    )
  }

  val IronMq = Seq(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
      "de.heikoseeberger" %% "akka-http-circe" % "1.21.0" // ApacheV2
    )
  )

  val Jms = Seq(
    libraryDependencies ++= Seq(
      "javax.jms" % "jms" % "1.1" % Provided, // CDDL + GPLv2
      "org.apache.activemq" % "activemq-broker" % ActiveMqVersion % Test, // ApacheV2
      "org.apache.activemq" % "activemq-client" % ActiveMqVersion % Test // ApacheV2
    ),
    resolvers += ("jboss" at "https://repository.jboss.org/nexus/content/groups/public")
  )

  val JsonStreaming = Seq(
    libraryDependencies ++= Seq(
      "com.github.jsurfer" % "jsurfer" % "1.4.3", // MIT,
      "com.github.jsurfer" % "jsurfer-jackson" % "1.4.3" // MIT
    )
  )

  val Kinesis = Seq(
    libraryDependencies ++= Seq(
      "com.amazonaws" % "aws-java-sdk-kinesis" % AwsSdkVersion, // ApacheV2
      "org.mockito" % "mockito-core" % MockitoCoreVersion % Test // MIT
    )
  )

  val Kudu = Seq(
    libraryDependencies ++= Seq(
      "org.apache.kudu" % "kudu-client-tools" % "1.7.1", // ApacheV2
      "org.apache.kudu" % "kudu-client" % "1.7.1" % Test // ApacheV2
    )
  )

  val MongoDb = Seq(
    libraryDependencies ++= Seq(
      "org.mongodb.scala" %% "mongo-scala-driver" % "2.4.1" // ApacheV2
    )
  )

  val Mqtt = Seq(
    libraryDependencies ++= Seq(
      "org.eclipse.paho" % "org.eclipse.paho.client.mqttv3" % "1.2.0" // Eclipse Public License 1.0
    )
  )

  val OrientDB = Seq(
    libraryDependencies ++= Seq(
      "com.orientechnologies" % "orientdb-graphdb" % "3.0.6", // ApacheV2
      "com.orientechnologies" % "orientdb-object" % "3.0.6" // ApacheV2
    )
  )

  val Reference = Seq(
    // connector specific library dependencies and resolver settings
    libraryDependencies ++= Seq(
      )
  )

  val S3 = Seq(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-xml" % AkkaHttpVersion,
      "com.amazonaws" % "aws-java-sdk-core" % AwsSdkVersion, // ApacheV2
      // in-memory filesystem for file related tests
      "com.google.jimfs" % "jimfs" % "1.1" % Test, // ApacheV2
      "com.github.tomakehurst" % "wiremock" % WiremockVersion % Test // ApacheV2
    )
  )

  val SpringWeb = {
    val SpringVersion = "5.0.8.RELEASE"
    val SpringBootVersion = "2.0.4.RELEASE"
    Seq(
      libraryDependencies ++= Seq(
        "org.springframework" % "spring-core" % SpringVersion,
        "org.springframework" % "spring-context" % SpringVersion,
        "org.springframework" % "spring-webflux" % SpringVersion,
        "org.springframework" % "spring-webmvc" % SpringVersion,
        "org.springframework.boot" % "spring-boot-autoconfigure" % SpringBootVersion, // TODO should this be provided?

        // for examples
        "org.springframework.boot" % "spring-boot-starter-web" % SpringBootVersion % Test
      )
    )
  }

  val SlickVersion = "3.2.3"
  val Slick = Seq(
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % SlickVersion, // BSD 2-clause "Simplified" License
      "com.typesafe.slick" %% "slick-hikaricp" % SlickVersion, // BSD 2-clause "Simplified" License
      "com.h2database" % "h2" % H2Version % Test // Eclipse Public License 1.0
    )
  )

  val Sns = Seq(
    libraryDependencies ++= Seq(
      "com.amazonaws" % "aws-java-sdk-sns" % AwsSdkVersion, // ApacheV2
      "org.mockito" % "mockito-core" % MockitoCoreVersion % Test // MIT
    )
  )

  val Solr = {
    val solrjVersion = "7.4.0"
    val slf4jVersion = "1.7.25"
    Seq(
      libraryDependencies ++= Seq(
        "org.apache.solr" % "solr-solrj" % solrjVersion, // ApacheV2
        //Test
        "org.apache.solr" % "solr-test-framework" % solrjVersion % Test, // ApacheV2
        "org.slf4j" % "slf4j-log4j12" % slf4jVersion % Test // MIT like: http://www.slf4j.org/license.html
      ),
      resolvers += ("restlet" at "https://maven.restlet.com")
    )
  }

  val Sqs = Seq(
    libraryDependencies ++= Seq(
      "com.amazonaws" % "aws-java-sdk-sqs" % AwsSdkVersion, // ApacheV2
      "org.elasticmq" %% "elasticmq-rest-sqs" % "0.14.5" % Test excludeAll (
        // elasticmq-rest-sqs depends on Akka 2.5, exclude it, so we can choose Akka version
        ExclusionRule(organization = "com.typesafe.akka") //
      ), // ApacheV2
      // pull up akka-http version to the latest version for elasticmq-rest-sqs
      "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion % Test, // ApacheV2
      "org.mockito" % "mockito-core" % MockitoCoreVersion % Test // MIT
    )
  )

  val Sse = Seq(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-testkit" % AkkaHttpVersion % Test
    )
  )

  val UnixDomainSocket = Seq(
    libraryDependencies ++= Seq(
      "com.github.jnr" % "jnr-unixsocket" % "0.19" // BSD/ApacheV2/CPL/MIT as per https://github.com/akka/alpakka/issues/620#issuecomment-348727265
    )
  )

  val Xml = Seq(
    libraryDependencies ++= Seq(
      "com.fasterxml" % "aalto-xml" % "1.1.0", // ApacheV2,
      "org.scala-lang.modules" %% "scala-java8-compat" % "0.9.0" // BSD-style
    )
  )

}
