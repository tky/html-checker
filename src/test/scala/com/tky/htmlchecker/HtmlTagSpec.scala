package com.tky.htmlchecker

import org.scalatest.{ FunSpec, Matchers }
import org.scalatest.matchers._

class HtmlTagSpec extends FunSpec with Matchers {

  describe("HtmlTag") {
    it("#apply") {
      HtmlTag("<a>") should be(HtmlTag("<a>", "a"))
      HtmlTag("<div>") should be(HtmlTag("<div>", "div"))
      HtmlTag("< div >") should be(HtmlTag("< div >", "div"))
      HtmlTag("</div>") should be(HtmlTag("</div>", "div"))
      HtmlTag("< /div>") should be(HtmlTag("< /div>", "div"))
      HtmlTag("< / div>") should be(HtmlTag("< / div>", "div"))
      HtmlTag("< / div >") should be(HtmlTag("< / div >", "div"))
    }

    it("#isCloseTag") {
      HtmlTag("</a>").isCloseTag  should be(true)
      HtmlTag("</div>").isCloseTag  should be(true)
      HtmlTag("< /div>").isCloseTag  should be(true)
      HtmlTag("< / div>").isCloseTag  should be(true)
      HtmlTag("< / div >").isCloseTag  should be(true)
      HtmlTag("<a>").isCloseTag  should be(false)
      HtmlTag("< div >").isCloseTag  should be(false)
    }

    it("#isOpenTag") {
      HtmlTag("<div>").isOpenTag  should be(true)
      HtmlTag("</div>").isOpenTag  should be(false)
    }

    it("#isPairTag should return true") {
      val open = HtmlTag("<div>")
      val close = HtmlTag("</div>")
      open.isPairTag(close) should be(true)
      close.isPairTag(open) should be(true)
    }

    it("#isPairTag should return false un paire tag") {
      val open = HtmlTag("<div>")
      val close = HtmlTag("</p>")
      open.isPairTag(close) should be(false)
    }

    it("#isPairTag should return false both tags are open tag") {
      val t1 = HtmlTag("<div>")
      val t2 = HtmlTag("<div>")
      t1.isPairTag(t2) should be(false)
    }

    it("#isPairTag should return false both tags are close tag") {
      val t1 = HtmlTag("</div>")
      val t2 = HtmlTag("</div>")
      t1.isPairTag(t2) should be(false)
    }
  }
}
