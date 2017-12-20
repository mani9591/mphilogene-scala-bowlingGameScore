package domain

import scala.annotation.tailrec

case class ExceedTotalFramesException(message: String = "", cause: Throwable = None.orNull) extends Exception(message, cause)

trait BuildGameFrames {
  val (refVal, maxFrame, maxPins) = (48, 10, 10)

  def parseGame(game: String): List[Frame] = {
    @tailrec
    def loop(frames: List[Frame], stream: Stream[Char]): List[Frame] = stream match {
      case Stream.Empty  => frames
      case firstChar #:: tail =>
        val (res, remain) = createNextFrame(firstChar, tail, frames)
        loop(res, remain)
    }

    loop(List(), game.toStream)
  }

  private def createNextFrame(fch: Char, stream: Stream[Char],
                              res: List[Frame]): (List[Frame], Stream[Char])  = {
    if(res.length + 1 > maxFrame + 2) {
      throw new ExceedTotalFramesException(
        s"Error number of frames exceed 12 frames.")
    }
    fch match {
      case digit if '1' to '9' contains digit => firstDigit(digit.toInt - refVal, res, stream)

      case '-' => firstDigit(0, res, stream)

      case 'X' => (res ++ List(procStrike(res.length)), stream)

      case ' ' => (res, stream)

      case _ => throw new Exception(s"Invalid Character in stream $fch")
    }
  }
  private def procStrike(frameCnt: Int): Frame =
    if (frameCnt < maxFrame) Strike else ExtraRolls(maxPins, 0)

  private def firstDigit(scr1: Int, res: List[Frame], remain: Stream[Char]): (List[Frame], Stream[Char]) = {
    val scr2: Char = remain.head
    scr2 match {
      case '/' => procSpare(scr1, res, remain.drop(1))
      case '-' => (res ++ secondDigit(scr1, 0, res.length), remain.drop(1))
      /** second score 'scr2' must be a digit */
      case digit if '1' to '9' contains digit  =>
        (res ++ secondDigit(scr1, digit.toInt - refVal, res.length), remain.drop(1))

      case _  => throw new NumberFormatException(
        s"Invalid Character $scr2 found, expect a digit in Frame number=${res.length + 1}.")
    }
  }

  private def secondDigit(scr1: Int, scr2: Int, frCnt: Int): List[Frame] = {
    if(scr1 + scr2 > 10)
      throw new IllegalArgumentException(s"Invalid scores=${scr1 + scr2} for Frame number=${frCnt + 1}")

    if (frCnt < maxFrame) List(Normal(scr1, scr2))
    else List(ExtraRolls(scr1, scr2))
  }

  private def procSpare(scr1: Int, res: List[Frame], stream: Stream[Char]): (List[Frame], Stream[Char]) = {
    if (res.length.compare(maxFrame - 1) < 0) (res ++ List(Spare(scr1)), stream)
    else if(res.length.compare(maxFrame - 1) == 0)
      (res ++ List(Spare(scr1), ExtraRolls(stream.head.toInt - refVal, 0)), stream.drop(1))
    else
      (res ++ List(ExtraRolls(scr1, stream.head.toInt - refVal)), stream.drop(1))
  }
}
