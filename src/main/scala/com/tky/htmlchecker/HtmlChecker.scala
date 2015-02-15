package com.tky.htmlchecker

object HtmlChecker extends HtmlTagParser {

  def check(document: String): CheckResult = {
    val stack = new scala.collection.mutable.Stack[HtmlTag]
    val result = parse(document).foldLeft(None: Option[CheckResult]) { (result, tag) => result match {
      case Some(r) => Some(r)
      case None => {
        if (tag.isOpenTag) {
          stack.push(tag)
          None
        } else if (stack.isEmpty)  {
          Some(CheckResult.notFoundOpenTag)
        } else {
          val latest = stack.pop
          if (!latest.isPairTag(tag)) {
            Some(CheckResult.notParitTag)
          } else {
            None
          }
        }
      }
    }}
    if (stack.isEmpty) result.getOrElse(CheckResult.ok) else CheckResult.notFoundCloseTag
  }
}

trait HtmlTagParser {
  val TAG_PATTERN = """<[^>]+>""".r

  def parse(document: String): Iterator[HtmlTag] = {
    TAG_PATTERN.findAllIn(document).map { HtmlTag.apply }
  }
}
