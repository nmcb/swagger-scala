package org.zalando.swagger

import java.io.File

import org.junit.runner.RunWith
import org.scalatest.{Matchers, FlatSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ParseExamplesTest extends FlatSpec with Matchers {

  val fixtures = new File("src/test/resources/examples").listFiles

  fixtures.filter(_.getName.endsWith(".json")).foreach { file =>
    "The json swagger parser" should s"read the ${file.getName} specification" in {
      JsonParser.parse(file)
    }
  }

  fixtures.filter(_.getName.endsWith(".yaml")).foreach { file =>
    "The yaml swagger parser" should s"read the ${file.getName} specification" in {
      YamlParser.parse(file)
    }
  }
}
