import domain.BowLingGameScore.calculateScores
import domain.ExceedTotalFramesException
import org.scalatest.FlatSpec

class ValidationBowlingGameSpec extends  FlatSpec {
  behavior of "Bad frames validation"

  it should "produce an IllegalArgumentException for frame 5" in {

    val thrown = intercept[IllegalArgumentException] {
      val game = "5/ 5/ 5/ 5/ 58 5/ 5/ 5/ 5/ 5/5"
      calculateScores(game)
    }
    assert(thrown.getMessage == "Invalid scores=13 for Frame number=5")
  }

  it should "produce a NumberFormatException for frame 3" in {

    val thrown = intercept[NumberFormatException] {
      val game = "5/ 5/ 2X 5/ 5/ 5/ 5/ 5/ 5/ 5/5"
      calculateScores(game)
    }
    assert(thrown.getMessage == "Invalid Character X found, expect a digit in Frame number=3.")
  }

  it should "produce an ExceedTotalFramesException for 14 frames" in {

    val thrown = intercept[ExceedTotalFramesException] {
      val game = "5/ 5/ 22 5/ 5/ 5/ 5/ 5/ 5/ 5/5 44 44 44 44"
      calculateScores(game)
    }
    assert(thrown.getMessage == "Error number of frames exceed 12 frames.")
  }
}
