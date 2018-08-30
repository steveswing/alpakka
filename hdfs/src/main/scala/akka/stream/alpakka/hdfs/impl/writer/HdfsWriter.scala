/*
 * Copyright (C) 2016-2018 Lightbend Inc. <http://www.lightbend.com>
 */

package akka.stream.alpakka.hdfs.impl.writer

import akka.stream.alpakka.hdfs.FilePathGenerator
import akka.stream.alpakka.hdfs.impl.writer.HdfsWriter._
import org.apache.hadoop.fs.{FileSystem, Path}

/**
 * Internal API
 */
@akka.annotation.InternalApi
private[hdfs] trait HdfsWriter[W, I] {

  protected lazy val output: W = create(fs, temp)

  protected lazy val temp: Path = tempFromTarget(pathGenerator, target)

  def moveToTarget(): Boolean = {
    if (!fs.exists(target.getParent))
      fs.mkdirs(target.getParent)
    fs.rename(temp, target)
  }

  def sync(): Unit

  def targetPath: String = target.toString

  def write(input: I, separator: Option[Array[Byte]]): Long

  def rotate(rotationCount: Long): HdfsWriter[W, I]

  protected def target: Path

  protected def fs: FileSystem

  protected def pathGenerator: FilePathGenerator

  protected def create(fs: FileSystem, file: Path): W

}

private[writer] object HdfsWriter {

  def createTargetPath(generator: FilePathGenerator, c: Long): Path =
    generator(c, System.currentTimeMillis / 1000)

  def tempFromTarget(generator: FilePathGenerator, target: Path): Path =
    new Path(generator.tempDirectory, target.getName)

  def getOrCreatePath(maybePath: Option[Path], default: => Path): Path =
    maybePath.getOrElse(default)

}
