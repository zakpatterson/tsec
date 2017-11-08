package tsec.authentication

import cats.data.OptionT
import cats.effect.IO
import tsec.TestSpec
import tsec.common.ByteEV
import tsec.mac.imports._
import cats.syntax.all._
import org.http4s.{Header, Headers, HttpService, Request, Response, Status}
import org.scalatest.MustMatchers
import tsec.csrf.TSecCSRF
import org.http4s.dsl.io._

class CSRFSpec extends TestSpec with MustMatchers {

  val dummyService: HttpService[IO] = HttpService[IO] {
    case GET -> Root =>
      Ok()
  }

  val dummyRequest: Request[IO] = Request[IO]()
  val orElse: Response[IO]      = Response[IO](Status.Forbidden)

  def testCSRFWithMac[A: ByteEV: MacTag](implicit keygen: MacKeyGenerator[A]) = {
    behavior of s"csrf signing using " + MacTag[A].algorithm

    val newKey   = keygen.generateKeyUnsafe()
    val tsecCSRF = TSecCSRF[IO, A](newKey)

    it should "check for an equal token properly" in {
      (for {
        t  <- OptionT.liftF(tsecCSRF.generateNewToken)
        eq <- tsecCSRF.checkEqual(t, t)
      } yield eq).getOrElse(false).unsafeRunSync() mustBe true
    }

    it should "not validate different tokens" in {
      (for {
        t1 <- OptionT.liftF(tsecCSRF.generateNewToken)
        t2 <- OptionT.liftF(tsecCSRF.generateNewToken)
        eq <- tsecCSRF.checkEqual(t1, t2)
      } yield eq).getOrElse(false).unsafeRunSync() mustBe false
    }

    behavior of s"CSRF middleware using " + MacTag[A].algorithm

    it should "validate for the correct csrf token" in {
      (for {
        token <- OptionT.liftF(tsecCSRF.generateNewToken)
        res <- tsecCSRF.apply(dummyService)(
          dummyRequest.withHeaders(Headers(Header(tsecCSRF.headerName, token))).addCookie(tsecCSRF.cookieName, token)
        )
      } yield res).getOrElse(orElse).unsafeRunSync().status mustBe Status.Ok
    }

    it should "not validate if token is missing in both" in {
      (for {
        res <- tsecCSRF.apply(dummyService)(dummyRequest)
      } yield res).getOrElse(orElse).unsafeRunSync().status mustBe Status.Forbidden
    }

    it should "not validate for token missing in header" in {
      (for {
        token <- OptionT.liftF(tsecCSRF.generateNewToken)
        res <- tsecCSRF.apply(dummyService)(
          dummyRequest.addCookie(tsecCSRF.cookieName, token)
        )
      } yield res).getOrElse(orElse).unsafeRunSync().status mustBe Status.Forbidden
    }

    it should "not validate for token missing in cookie" in {
      (for {
        token <- OptionT.liftF(tsecCSRF.generateNewToken)
        res <- tsecCSRF.apply(dummyService)(
          dummyRequest.withHeaders(Headers(Header(tsecCSRF.headerName, token)))
        )
      } yield res).getOrElse(orElse).unsafeRunSync().status mustBe Status.Forbidden
    }

    it should "not validate for different tokens" in {
      (for {
        token1 <- OptionT.liftF(tsecCSRF.generateNewToken)
        token2 <- OptionT.liftF(tsecCSRF.generateNewToken)
        res <- tsecCSRF.apply(dummyService)(
          dummyRequest.withHeaders(Headers(Header(tsecCSRF.headerName, token1))).addCookie(tsecCSRF.cookieName, token2)
        )
      } yield res).getOrElse(orElse).unsafeRunSync().status mustBe Status.Forbidden
    }
  }

}