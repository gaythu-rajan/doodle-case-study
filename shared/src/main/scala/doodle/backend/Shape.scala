package doodle.backend

import doodle.core.Line.{Cap, Join}
import doodle.core.{Stroke, Color}

/**
  * This class is the base trait for all the shapes
  *
  * @author Gayathri Thiyagarajan
  */
trait Shape {

  def boundingBox:BoundingBox = {
    this match {
      case Circle(r) => BoundingBox(-r, r, r, -r)
      case Rectangle(w, h) => BoundingBox(-w/2, h/2, w/2, -h/2)
      case drawOn(s1, s2) => s1.boundingBox on s2.boundingBox
      case drawBeside(s1, s2) => s1.boundingBox beside s2.boundingBox
      case drawAbove(s1, s2) => s1.boundingBox above s2.boundingBox
    }
  }

  def beside(shape:Shape) = {
      drawBeside(this, shape)
  }
  def on(shape:Shape) = {
    drawOn(this, shape)
  }
  def above(shape:Shape) = {
    drawAbove(this, shape)
  }

  def draw(canvas:Canvas):Unit = {
    this match {
      case Circle(r) => {
        canvas.setFill(Color.red)
        canvas.setSize(1000, 1000)
        canvas.circle(0.0, 0.0, r)
        canvas.fill()
      }
      case Rectangle(w, h) =>  {
        canvas.setFill(Color.blue)
        canvas.setSize(1000, 1000)
        canvas.rectangle(-w/2, -h/2, w, h)
        canvas.fill()
      }
      case drawBeside(s1, s2) => {
        s1.draw(canvas)
        s2.draw(canvas)
      }
      case drawOn(s1, s2) => {
        s1.draw(canvas)
        s2.draw(canvas)
      }
      case drawAbove(s1, s2) => {
        s1.draw(canvas)
        s2.draw(canvas)
      }
    }
  }
}

final case class Circle(radius:Double) extends Shape
final case class Rectangle(width:Double, height:Double) extends Shape
final case class drawOn(shape1:Shape, shape2:Shape) extends Shape
final case class drawBeside(shapeLeft:Shape, shapeRight:Shape) extends Shape
final case class drawAbove(shapeAbove:Shape, shapeBelow:Shape) extends Shape
