import domain.BowLingGameScore.calculateScores
import org.scalatest.FlatSpec


class BowlingGameScoreSpec extends FlatSpec  {

  "CalculateScores1" should "calculate score for all misses bowling Game" in {
    val game = "-- -- -- -- -- -- -- -- -- --"
    assert(calculateScores(game) === 0)
  }

  "CalculateScores2" should "calculate score for all 1 and miss in all frames bowling Game" in {
    val game = "1- 1- 1- 1- 1- 1- 1- 1- 1- 1-"
    assert(calculateScores(game) === 10)
  }

  "CalculateScores3" should "calculate score for all 5 Spare in all frames bowling Game" in {
    val game = "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5"
    assert(calculateScores(game) === 150)
  }

  "CalculateScores4" should "calculate score for all Strike in all frames bowling Game" in {
    val game = "X X X X X X X X X X X X"
    assert(calculateScores(game) === 300)
  }

  "CalculateScores5" should "calculate score for all 9 and miss in all frames bowling Game" in {
    val game = "9- 9- 9- 9- 9- 9- 9- 9- 9- 9-"
    assert(calculateScores(game) === 90)
  }


  "CalculateScores6" should "calculate score for different type frames in bowling Game" in {
    val game = "X 7/ 9- X -9 8/ -6 X X X 9-"
    assert(calculateScores(game) === 170)
  }

  "CalculateScores7" should "calculate score for partial frame" in {
    val game = "X 7/ 9- X -9 8/ -6"
    assert(calculateScores(game) === 92)
  }


}