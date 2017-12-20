package domain

object BowLingGameScore  extends  BuildGameFrames {
  def calculateScores(game: String): Int = {
    def score(frames: List[Frame]): Int =
      frames match {
        case List()  => 0
        case ExtraRolls(_, _) :: _ | Nil => 0
        case (n:  Normal)  :: remain => n.scores.sum + score(remain)
        case (sp: Spare)   :: remain => sp.scores.sum + remain.head.scores.head + score(remain)
        case (st@ Strike)  :: remain => scoreForStrike(st, remain) + score(remain)
      }

    def scoreForStrike(frameSt: Frame, frs: List[Frame]): Int = {
      val scr = if(frs.head.scores.head == 10) {
        frs.take(2).map { fr =>
          if (fr.scores.head < 10) fr.scores.head else fr.scores.sum
        }.sum
      }
      else frs.take(1).head.scores.sum
      frameSt.scores.sum + scr
    }
    score(parseGame(game: String))
  }
}
