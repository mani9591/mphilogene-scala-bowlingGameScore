package domain

sealed trait Frame { val scores: List[Int] }
case class   Normal(sc1: Int, sc2: Int)      extends Frame { val scores = sc1 :: sc2 :: Nil }
case class   ExtraRolls(sc1: Int, sc2: Int)  extends Frame { val scores = sc1 :: sc2 :: Nil }
case class   Spare(sc1: Int)                 extends Frame { val scores = sc1 :: (10 - sc1) :: Nil }
case object  Strike                          extends Frame { val scores = 10 :: Nil }
