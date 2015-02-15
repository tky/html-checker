package com.tky.htmlchecker

trait CheckStatus
object Ok extends CheckStatus
object Error extends CheckStatus
object NotFoundOpenTag extends CheckStatus
object NotFoundCloseTag extends CheckStatus
object NotPairTag extends CheckStatus

case class CheckResult(status: CheckStatus)

object CheckResult {
  def ok: CheckResult = CheckResult(Ok)
  def error: CheckResult = CheckResult(Error)
  def notFoundOpenTag: CheckResult = CheckResult(NotFoundOpenTag)
  def notParitTag: CheckResult = CheckResult(NotPairTag)
  def notFoundCloseTag: CheckResult = CheckResult(NotFoundCloseTag)
}
