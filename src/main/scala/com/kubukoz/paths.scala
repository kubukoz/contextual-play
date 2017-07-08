package com.kubukoz

import contextual.{Case, Context, Interpolator, Prefix}
import play.api.mvc.PathBindable

object paths {
  case class Path(value: String) extends AnyVal

  sealed trait PathContexts extends Context with Product with Serializable
  case object DefaultContext extends PathContexts

  object PathInterpolator extends Interpolator {

    override type ContextType = PathContexts
    override type Input = String
    override type Output = Path

    override def contextualize(interpolation: StaticInterpolation): Seq[ContextType] = {
      interpolation.holes.map(_ => DefaultContext)
    }

    def evaluate(interpolation: RuntimeInterpolation): Path = {
      val strPath = interpolation.parts.map {
        case Literal(_, s) => s
        case Substitution(_, value) => value
      }

      Path(strPath.mkString)
    }
  }

  implicit def embed[T](implicit ev: PathBindable[T]) = PathInterpolator.embed[T](
    Case(DefaultContext, DefaultContext)(ev.unbind("", _))
  )

  implicit class PathStringContext(sc: StringContext) {
    val path = Prefix(PathInterpolator, sc)
  }
}
