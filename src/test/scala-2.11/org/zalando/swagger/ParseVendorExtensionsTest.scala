package org.zalando.swagger

import java.io.File

import com.fasterxml.jackson.databind.JsonMappingException
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class ParseVendorExtensionsTest extends FlatSpec with Matchers {

  val ok = new File("src/test/resources/extensions.ok.yaml")
  val nok = new File("src/test/resources/extensions.nok.yaml")

  "The json swagger parser" should "read valid vendor extensions" in {
    val swagger = YamlParser.parse(ok)
    swagger.info.vendorExtensions contains ("x-info-extension")
    swagger.paths("/").vendorExtensions contains ("x-path-extension")
    swagger.paths("/").get.vendorExtensions contains ("x-operation-extension")
    swagger.paths("/").get.parameters(0).vendorExtensions contains ("x-parameter-extension")
    swagger.paths("/").get.responses("200").vendorExtensions contains ("x-response-extension")
    swagger.tags(0).vendorExtensions contains ("x-tag-extension")
    swagger.securityDefinitions("security").vendorExtensions contains ("x-security-extension")
  }

  "The json swagger parser" should "reject invalid vendor extensions" in {
    intercept[JsonMappingException] {
      val swagger = YamlParser.parse(nok)
    }
  }
}
