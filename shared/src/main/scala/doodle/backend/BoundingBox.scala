package doodle.backend

/**
  * @author Gayathri Thiyagarajan
  */
final case class BoundingBox(left:Double, top:Double, right:Double, bottom:Double ) {
  val height:Double = top - bottom;
  val width:Double = left - right;

  //"this" bounding box is above the other bounding box
  def above(theOtherBox:BoundingBox):BoundingBox = {
    BoundingBox(
      this.left,
      this.top,
      this.right,
      theOtherBox.bottom
    )
  }

  //"this" bounding box is on the other bounding box
  def on(theOtherBox:BoundingBox):BoundingBox = {
    BoundingBox(
      this.left max theOtherBox.left,
      this.top max theOtherBox.top,
      this.right max theOtherBox.right,
      this.bottom max theOtherBox.bottom
    )
  }

  //"this" bounding box is beside the other bounding box
  def beside(theOtherBox:BoundingBox):BoundingBox = {
    BoundingBox(
      this.left max theOtherBox.left,
      this.top max theOtherBox.top,
      this.right max theOtherBox.right,
      this.bottom max theOtherBox.bottom
    )
  }
}
