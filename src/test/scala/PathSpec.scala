import PathSpec.{EventId, QuestId}
import org.scalatest.{FlatSpec, Matchers}
import play.api.mvc.PathBindable
import shapeless.test.illTyped
import com.kubukoz.paths._

class PathSpec extends FlatSpec with Matchers {

  "path" should "interpolate values that have PathBindables" in {
    val eventId = EventId(1)
    implicit val bindable: PathBindable[EventId] = PathBindable.bindableLong.transform(EventId, _.value)

    path"/events/$eventId" shouldBe Path("/events/1")
  }

  it should "fail to compile if there is no PathBindable" in {
    val questId = QuestId(1)
    illTyped("""path"/quests/$questId"""")
  }
}

object PathSpec {
  case class EventId(value: Long) extends AnyVal
  case class QuestId(value: Long) extends AnyVal
}
