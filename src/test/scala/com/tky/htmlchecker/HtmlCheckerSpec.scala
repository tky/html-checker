package com.tky.htmlchecker

import org.scalatest.{ FunSpec, Matchers }
import org.scalatest.matchers._

class HtmlCheckerSpec extends FunSpec with Matchers {

  describe("HtmlChecker") {
    describe("#check") {
      it("valid simple html") {
        val actual = HtmlChecker.check("""<a href="#">test</a>""")
        actual.status should be(Ok)
      }

      it("valid complex html") {
        val actual = HtmlChecker.check("""test:<div><a href="#">test <strong>!</strong></a></div>""")
        actual.status should be(Ok)
      }

      it("not found open tag") {
        val actual = HtmlChecker.check("""test</div>""")
        actual.status should be(NotFoundOpenTag)
      }

      it("not pair tag") {
        val actual = HtmlChecker.check("""<div>test</p>""")
        actual.status should be(NotPairTag)
      }

      it("not found close tag") {
        val actual = HtmlChecker.check("""<div>test</div><p>""")
        actual.status should be(NotFoundCloseTag)
      }
    }
  }
}
