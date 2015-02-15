package com.tky.htmlchecker

case class HtmlTag(value: String, kind: String) {
  lazy val isCloseTag: Boolean = HtmlTag.CLOSE_TAG_PATTERN.findFirstIn(value).nonEmpty
  lazy val isOpenTag: Boolean = !isCloseTag

  def isPairTag(that: HtmlTag): Boolean = {
    kind == that.kind && (isCloseTag != that.isCloseTag)
  }
}

object HtmlTag {
  val TAG_KIND_PATTERN = """<[ ]*/?[ ]*([a-zA-Z]+).*>""".r
  val CLOSE_TAG_PATTERN = """<[ ]*/.*""".r

  def apply(value: String): HtmlTag = {
    (for {
     m <- TAG_KIND_PATTERN.findFirstMatchIn(value)
    } yield {
      HtmlTag(value, m.group(1))
    }).getOrElse(throw new IllegalArgumentException)
  }
}
