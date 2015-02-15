package com.tky.htmlchecker

import org.scalatest.{ FunSpec, Matchers }
import org.scalatest.matchers._

class HtmlTagParserSpec extends FunSpec with Matchers {

  object Parser extends HtmlTagParser

  describe("HtmlTagParser") {
    it("parse simple html") {
      val html = """<a html="#">test</a>"""
      Parser.parse(html).toList should be(List(HtmlTag("<a html=\"#\">"), HtmlTag("</a>")))
    }
  }
}
