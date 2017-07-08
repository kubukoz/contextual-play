import PathSpec.{EventId, QuestId}
import org.scalatest.{FlatSpec, Matchers}
import play.api.mvc.PathBindable
import shapeless.test.illTyped
import com.kubukoz.paths._

class PathSpec extends FlatSpec with Matchers {
  implicit val bindable: PathBindable[EventId] =
    PathBindable.bindableLong.transform(EventId, _.value)

  "path" should "interpolate values that have PathBindables" in {
    val eventId = EventId(1)

    path"/events/$eventId" shouldBe Path("/events/1")
  }

  it should "fail to compile if there is no PathBindable" in {
    val questId = QuestId(1)
    illTyped("""path"/quests/$questId"""")
  }

  it should "have the proper return type" in {
    val eventId = EventId(1)

    path"events/$eventId": Path
  }
}

object PathSpec {
  case class EventId(value: Long) extends AnyVal
  case class QuestId(value: Long) extends AnyVal
}
